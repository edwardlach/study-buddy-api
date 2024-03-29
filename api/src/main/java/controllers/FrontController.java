package controllers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import dtos.RequestDTO;
import dtos.ResponseDTO;
import models.ErrorMessage;

import java.io.IOException;
import java.sql.SQLException;

import static constants.ApiRequestMappings.*;
import static constants.StatusCodes.*;


/**
 * Reference for the necessary file structure and build instructios for lambada package
 * https://docs.aws.amazon.com/lambda/latest/dg/create-deployment-pkg-zip-java.html
 *
 * Reference for streaming API Gateway event
 * https://www.baeldung.com/aws-lambda-api-gateway
 *
 * Useful input/output options
 * https://blog.symphonia.io/learning-lambda-part-4-4284c2b83b1b
 *
 * Testing Lambda function locally
 * https://www.testingexcellence.com/invoke-test-aws-lambda-function-locally-java/
 *
 * Useful reference for using Jackson
 * https://www.mkyong.com/java/jackson-2-convert-java-object-to-from-json/
 */

public class FrontController implements RequestHandler<RequestDTO, ResponseDTO> {

    private String routeRequestToController(RequestDTO request) throws IOException, SQLException {
        String responseBody = "";
        try {
            if(request.getResource() != null) {
                switch (request.getResource()) {
                    case USERS:
                        UserController userController = new UserController(request);
                        responseBody = userController.getResponseBody();
                        break;
                    case GROUPS:
                        GroupController groupController = new GroupController(request);
                        responseBody = groupController.getResponseBody();
                        break;
                    case SUBJECTS:
                        SubjectController subjectController = new SubjectController(request);
                        responseBody = subjectController.getResponseBody();
                        break;
                    case DELETE_GROUP_MEMBERSHIP:
                    case GROUP_MEMBERSHIP:
                    case USER_GROUP_MEMBERSHIPS:
                        GroupMembershipController groupMembershipController = new GroupMembershipController(request);
                        responseBody = groupMembershipController.getResponseBody();
                        break;
                    case CHAT_MESSAGE:
                    case GROUP_CHAT_MESSAGES:
                        ChatMessageController chatMessageController = new ChatMessageController(request);
                        responseBody = chatMessageController.getResponseBody();
                        break;
                }
            } else {
                WebSocketController webSocketController = new WebSocketController(request);
                responseBody = webSocketController.getResponseBody();
            }
        } catch (IOException | SQLException e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
            try {
                responseBody = ErrorMessage.getMessageAsString(errorMessage);
            } catch (JsonProcessingException ex) {
                ex.printStackTrace();
            }
        }

        return responseBody;
    }

    public ResponseDTO handleRequest(RequestDTO request, Context context) {
        System.out.println("Did this get called at least?");
        String responseBody = "";
        int statusCode;
        ObjectMapper mapper = new ObjectMapper();
        try {
            responseBody = routeRequestToController(request);
            statusCode = SUCCESS;
        } catch (SQLException e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
            try {
                responseBody = ErrorMessage.getMessageAsString(errorMessage);
            } catch (JsonProcessingException ex) {
                ex.printStackTrace();
            }
            statusCode = BAD_REQUEST;
        } catch (IOException e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
            try {
                responseBody = ErrorMessage.getMessageAsString(errorMessage);
            } catch (JsonProcessingException ex) {
                ex.printStackTrace();
            }
            statusCode = INTERNAL_SERVER_ERROR;
        }

        ResponseDTO response = new ResponseDTO(statusCode, responseBody);
        try {
            System.out.println(mapper.writeValueAsString(request));
            System.out.println(mapper.writeValueAsString(response));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response;
    }

}
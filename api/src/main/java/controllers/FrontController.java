package controllers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import dtos.RequestDTO;
import dtos.ResponseDTO;
import dtos.UserDTO;

import java.io.IOException;
import java.sql.SQLException;

import static constants.ApiRequestMappings.GROUPS;
import static constants.ApiRequestMappings.USERS;
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
            switch (request.getResource()) {
                case USERS:
                    UserController controller = new UserController(request);
                    responseBody = controller.getResponseBody();
                    System.out.println(responseBody);
                    break;
                case GROUPS:
                    GroupController groupController = new GroupController(request);
                    responseBody = groupController.getResponseBody();
                    System.out.println(responseBody);
                    break;
            }
        } catch (IOException | SQLException e) {
            responseBody = e.getMessage();

        }
        return responseBody;
    }

    public ResponseDTO handleRequest(RequestDTO request, Context context) {
        String responseBody = "";
        int statusCode;

        try {
            responseBody = routeRequestToController(request);
            statusCode = SUCCESS;
        } catch (SQLException e) {
            responseBody = e.getMessage();
            statusCode = BAD_REQUEST;
        } catch (IOException e) {
            responseBody = e.getMessage();
            statusCode = INTERNAL_SERVER_ERROR;
        }

        return new ResponseDTO(statusCode, responseBody);
    }

}
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
 */

public class FrontController implements RequestHandler<RequestDTO, ResponseDTO> {

    public ResponseDTO handleRequest(RequestDTO request, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log(request.getHttpMethod());
        logger.log(request.getResource());
        logger.log(request.getBody());

        String responseBody = "";
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            responseBody = mapper.writeValueAsString(new UserDTO("Ed", "Lach", "edl5040@psu.edu"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ResponseDTO(200, responseBody);
    }

}
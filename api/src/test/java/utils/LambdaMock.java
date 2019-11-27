package utils;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.FrontController;
import dtos.RequestDTO;
import dtos.ResponseDTO;

import java.io.IOException;

public class LambdaMock {

    public static ResponseDTO invoke(PayloadBuilder payload) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        RequestDTO request = mapper.readValue(payload.toString(), RequestDTO.class);
        Context context = new TestContext();
        FrontController lambda = new FrontController();
        return lambda.handleRequest(request, context);
    }

}

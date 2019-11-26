package controllers;

import com.amazonaws.services.lambda.model.InvokeResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import dtos.ResponseDTO;
import dtos.UserDTO;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.AWS;
import utils.PayloadBuilder;
import utils.Resource;

import java.io.IOException;

public class UserControllerIT {

    @Test
    public void thatANewUserIsSuccessfullyCreated() throws IOException {
        PayloadBuilder payload = new PayloadBuilder(Resource.GET_USER);
        System.out.println(payload.toString());
        InvokeResult result = AWS.invoke("FrontController", payload.toString());
        String rawJson = new String(result.getPayload().array(), "UTF-8");
        System.out.println(rawJson);
        ObjectMapper mapper = new ObjectMapper();
        ResponseDTO response = mapper.readValue(rawJson, ResponseDTO.class);
        UserDTO user = mapper.readValue(response.getBody(), UserDTO.class);
        assertEquals(200, (int)result.getStatusCode());
        assertEquals("edl5040@psu.edu", user.getEmail());
        assertTrue(user.getUserId() > 0);
    }

    @Test
    public void thatAUserCanBeRetrievedWithAnEmailAddress()  {
    }
}

package controllers;

import com.amazonaws.services.lambda.model.InvokeResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import dtos.ResponseDTO;
import dtos.UserDTO;
import org.junit.Test;
import static org.junit.Assert.*;

import utils.*;

import java.util.Map;

import java.io.IOException;

public class UserControllerIT {

    @Test
    public void thatANewUserIsSuccessfullyCreated() throws IOException {
        String email = RandomGen.getRandomEmail();
        PayloadBuilder payload = new PayloadBuilder(Resource.POST_USER, email);
        ObjectMapper mapper = new ObjectMapper();
        ResponseDTO response = LambdaMock.invoke(payload);
        UserDTO user = mapper.readValue(response.getBody(),UserDTO.class);
        assertEquals(200, response.getStatusCode());
        assertEquals(email, user.getEmail());
        assertTrue(user.getUserId()>0);
    }

    @Test
    public void thatAUserCanBeRetrievedWithAnEmailAddress() throws IOException {
        PayloadBuilder payload = new PayloadBuilder(Resource.GET_USER);
        ObjectMapper mapper = new ObjectMapper();
        ResponseDTO response = LambdaMock.invoke(payload);
        UserDTO user = mapper.readValue(response.getBody(), UserDTO.class);
        assertEquals(200, response.getStatusCode());
        assertEquals("edl5040@psu.edu", user.getEmail());
        assertTrue(user.getUserId() > 0);
    }
}

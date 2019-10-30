package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dtos.RequestDTO;
import dtos.UserDTO;
import models.User;
import services.UserService;

import java.io.IOException;
import java.sql.SQLException;

import static constants.ApiRequestMappings.GET;
import static constants.ApiRequestMappings.POST;

public class UserController extends AbstractController {

    private String responseBody;

    public UserController(RequestDTO request) throws IOException, SQLException {
        routeRequest(request);
    }

    @Override
    public void routeRequest(RequestDTO request) throws SQLException, IOException{
        switch(request.getHttpMethod()) {
            case POST:
                UserDTO dto = stringToDTO(request.getBody(), UserDTO.class);
                setResponseBody(createNewUser(dto));
                break;
            case GET:
                setResponseBody(getUserByEmail(request.getQueryStringParameters().getEmail()));
                break;
        }
    }

//    @Override
//    public AbstractDTO stringToDTO(String body) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.readValue(body, UserDTO.class);
//    }

    private UserDTO createNewUser(UserDTO userDTO) throws SQLException, JsonProcessingException {
        UserService userService = new UserService();
        userService.postUser(
            new User(
                userDTO.getCreated(),
                userDTO.getUpdated(),
                userDTO.isDeleted(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getEmail(),
                userDTO.getEducationLevel(),
                userDTO.getUniversityId()
        ));
        User user = userService.getUserByEmail(userDTO.getEmail());
        return new UserDTO(user);
    }

    private UserDTO getUserByEmail(String email) throws SQLException, JsonProcessingException {
        UserService userService = new UserService();
        User user = userService.getUserByEmail(email);
        return new UserDTO(user);
    }

    public String getResponseBody() {
        return this.responseBody;
    }

    public void setResponseBody(UserDTO userDTO) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        this.responseBody = mapper.writeValueAsString(userDTO);
    }


}

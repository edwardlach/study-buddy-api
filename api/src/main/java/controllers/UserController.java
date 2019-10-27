package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dtos.AbstractDTO;
import dtos.RequestDTO;
import dtos.UserDTO;
import models.User;
import services.UserService;

import java.io.IOException;
import java.sql.SQLException;

import static constants.ApiRequestMappings.GET;
import static constants.ApiRequestMappings.POST;

public class UserController implements AbstractController {

    private String responseBody;

    public UserController(RequestDTO request) throws JsonProcessingException, IOException, SQLException {
        routeRequest(request);
    }

    @Override
    public void routeRequest(RequestDTO request) throws JsonProcessingException, SQLException, IOException{
        switch(request.getHttpMethod()) {
            case POST:
                AbstractDTO dto = stringToDTO(request.getBody());
                setResponseBody(createNewUser((UserDTO) dto));
                break;
            case GET:
                setResponseBody(getUserByEmail(request.getQueryStringParameters().getEmail()));
                break;
        }
    }

    @Override
    public AbstractDTO stringToDTO(String body) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(body, UserDTO.class);
    }

    private UserDTO createNewUser(UserDTO userDTO) throws SQLException, JsonProcessingException {
        UserService userService = new UserService();
        userService.postUser(new User(userDTO));
        User user = userService.getUserByEmail(userDTO.getEmail());
        return new UserDTO(user.getCreated(), user.getUpdated(), user.isDeleted(), user.getFirstName(),
                user.getLastName(), user.getEmail(), user.getEducationLevel(), user.getUniversityId(), user.getUserId()
        );
    }

    private UserDTO getUserByEmail(String email) throws SQLException, JsonProcessingException {
        UserService userService = new UserService();
        User user = userService.getUserByEmail(email);
        return new UserDTO(user.getCreated(), user.getUpdated(), user.isDeleted(), user.getFirstName(),
                user.getLastName(), user.getEmail(), user.getEducationLevel(), user.getUniversityId(), user.getUserId()
        );
    }

    public String getResponseBody() {
        return this.responseBody;
    }

    public void setResponseBody(UserDTO userDTO) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        this.responseBody = mapper.writeValueAsString(userDTO);
    }


}

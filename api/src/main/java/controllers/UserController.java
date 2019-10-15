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

import static constants.ApiRequestMappings.POST;

public class UserController implements AbstractController {

    private String responseBody;

    public UserController(RequestDTO request) throws IOException, SQLException {
        routeRequest(request, stringToDTO(request.getBody()));
    }

    @Override
    public void routeRequest(RequestDTO request, AbstractDTO dto) throws SQLException, JsonProcessingException {
        switch(request.getHttpMethod()) {
            case POST:
                createNewUser((UserDTO) dto);
                setResponseBody(getUserByEmail((UserDTO) dto));
                break;
        }
    }

    @Override
    public AbstractDTO stringToDTO(String body) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(body, UserDTO.class);
    }

    private void createNewUser(UserDTO userDTO) throws SQLException {
        User user = new User(userDTO);
        UserService userService = new UserService();
        userService.postUser(user);
    }

    private UserDTO getUserByEmail(UserDTO userDTO) throws SQLException {
        UserService userService = new UserService();
        User user = userService.getUserByEmail(userDTO.getEmail());
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

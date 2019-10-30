package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dtos.RequestDTO;

import java.io.IOException;
import java.sql.SQLException;

abstract class AbstractController {

    abstract void routeRequest(RequestDTO request) throws SQLException, IOException;

    public <T> T stringToDTO(String body, Class<T> classType) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(body, classType);
    }

}

package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dtos.RequestDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

abstract class AbstractController {

    private String responseBody;
    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    abstract void routeRequest(RequestDTO request) throws SQLException, IOException;

    public <T> T stringToDTO(String body, Class<T> classType) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(body, classType);
    }

    public String getResponseBody() {
        return this.responseBody;
    }

    public <T> void setResponseBody(T dto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        this.responseBody = mapper.writeValueAsString(dto);
    }

}

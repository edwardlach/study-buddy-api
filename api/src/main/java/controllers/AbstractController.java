package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import dtos.AbstractDTO;
import dtos.RequestDTO;

import java.io.IOException;
import java.sql.SQLException;

interface AbstractController {
    void routeRequest(RequestDTO request, AbstractDTO dto) throws SQLException, JsonProcessingException;
    AbstractDTO stringToDTO(String body) throws IOException;
}

package dtos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.format.DateTimeFormatter;

public abstract class AbstractDTO {
    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    public static <DTO> String toString(DTO dto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dto);
    }

}

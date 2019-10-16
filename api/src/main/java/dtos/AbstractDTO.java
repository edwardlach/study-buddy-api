package dtos;

import java.time.format.DateTimeFormatter;

public interface AbstractDTO {
    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
}

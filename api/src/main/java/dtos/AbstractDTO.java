package dtos;

import java.time.format.DateTimeFormatter;

public interface AbstractDTO {
    static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
}

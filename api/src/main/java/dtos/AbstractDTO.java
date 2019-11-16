package dtos;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
/*
    Useful information on converting generic lists of models to dtos
    https://stackoverflow.com/questions/17413788/generic-dto-converter-pattern
 */
/**
 *
 * @param <MODEL>
 * @param <DTO>
 */
public interface AbstractDTO<MODEL, DTO> extends Function<MODEL, DTO> {
    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    default DTO convertToDTO(MODEL model) {
        DTO dto = null;
        if (model != null) {
            dto = this.apply(model);
        }
        return dto;
    }

    default List<DTO> convertToDTO(List<MODEL> models) {
        List<DTO> dtos = new ArrayList<DTO>();
        if (models != null) {
            dtos = models.stream().map(this::apply).collect(toList());
        }
        return dtos;
    }

}

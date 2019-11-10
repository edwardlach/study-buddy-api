package controllers;

import dtos.RequestDTO;
import dtos.SubjectDTO;
import models.Subject;
import services.SubjectService;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.sql.SQLException;

import static constants.ApiRequestMappings.GET;

public class SubjectController extends AbstractController {

    private SubjectService subjectService = new SubjectService();

    public SubjectController(RequestDTO request) throws IOException, SQLException {
        routeRequest(request);
    }

    @Override
    void routeRequest(RequestDTO request) throws SQLException, IOException {
        switch(request.getHttpMethod()){
            case GET:
                setResponseBody(getAllClasses());
                break;
        }
    }

    private List<SubjectDTO> getAllClasses() throws SQLException {
        List<Subject> subjects = subjectService.getAllClasses();
        List<SubjectDTO> dto = new ArrayList<SubjectDTO>();
        for(Subject subject: subjects) {
            dto.add(new SubjectDTO(subject));
        }
        return dto;
    }
}

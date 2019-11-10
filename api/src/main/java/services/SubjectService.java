package services;

import daos.SubjectDAO;
import models.Subject;

import java.sql.SQLException;
import java.util.List;

public class SubjectService {

    private SubjectDAO subjectDAO = new SubjectDAO();

    public List<Subject> getAllClasses() throws SQLException {
        return subjectDAO.getAllClasses();
    }

    public List<Subject> getClassesByName(String className) throws SQLException {
        return subjectDAO.getClassesByName(className);
    }

}

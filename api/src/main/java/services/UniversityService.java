package services;

import daos.UniversityDAO;
import models.University;

import java.sql.SQLException;

public class UniversityService {

    private UniversityDAO universityDAO = new UniversityDAO();

    public University getUniversityById(int universityId) throws SQLException {
        return universityDAO.getUniversityById(universityId);
    }
}

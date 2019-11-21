package daos;

import models.Group;
import models.University;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UniversityDAO extends DBConnect {

    public University getUniversityById(int universityId) throws SQLException {
        String sql = "SELECT * from universities where universityId = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, universityId);

        ResultSet result = statement.executeQuery();

        University university = new University();
        int count = 0;
        while (result.next()) {
            university.setUniversityId(result.getInt("universityId"));
            university.setLocation(result.getString("location"));
            university.setName((result.getString("name")));
            ++count;
        }

        if (count == 0) {
            throw new SQLException("No university found with a an id " + universityId);
        }

        result.close();
        disconnect();

        return university;
    }

}

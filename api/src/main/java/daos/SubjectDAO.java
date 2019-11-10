package daos;

import models.Group;
import models.Subject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO extends DBConnect {

    public List<Subject> getAllClasses() throws SQLException {
        String sql = "SELECT * from classes";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);

        ResultSet result = statement.executeQuery();

        List<Subject> subjects = new ArrayList<Subject>();
        int count = 0;
        while (result.next()) {
            Subject subject = new Subject();
            subject.setCreated(new Timestamp(result.getDate("created").getTime()).toLocalDateTime());
            subject.setUpdated(new Timestamp(result.getDate("updated").getTime()).toLocalDateTime());
            subject.setDeleted(result.getBoolean("deleted"));
            subject.setName(result.getString("name"));
            subject.setClassId(result.getInt("classId"));
            subject.setUniversityId(result.getInt("universityId"));
            subject.setClassNumber(result.getInt("classNumber"));
            subject.setSubject(result.getString("subject"));
            subjects.add(subject);
            ++count;
        }

        if (count == 0) {
            throw new SQLException("Classes were not able to be retrieved from the database");
        }

        result.close();
        disconnect();

        return subjects;
    }

    public List<Subject> getClassesByName(String className) throws SQLException {
        String sql = "SELECT * from classes where name like ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, "%" + className + "%");

        ResultSet result = statement.executeQuery();

        List<Subject> subjects = new ArrayList<Subject>();
        int count = 0;
        while (result.next()) {
            Subject subject = new Subject();
            subject.setCreated(new Timestamp(result.getDate("created").getTime()).toLocalDateTime());
            subject.setUpdated(new Timestamp(result.getDate("updated").getTime()).toLocalDateTime());
            subject.setDeleted(result.getBoolean("deleted"));
            subject.setName(result.getString("name"));
            subject.setClassId(result.getInt("classId"));
            subject.setUniversityId(result.getInt("universityId"));
            subject.setClassNumber(result.getInt("classNumber"));
            subject.setSubject(result.getString("subject"));
            subjects.add(subject);
            ++count;
        }

        if (count == 0) {
            throw new SQLException("No classes found with a name like " + className);
        }

        result.close();
        disconnect();

        return subjects;
    }

}

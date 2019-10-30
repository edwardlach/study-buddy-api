package daos;


import com.fasterxml.jackson.core.JsonProcessingException;
import models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Example of the DAO pattern
 * https://www.codejava.net/coding/jsp-servlet-jdbc-mysql-create-read-update-delete-crud-example
 */

public class UserDAO extends DBConnect {

    public boolean insertUser(User user) throws SQLException {
        String sql = "INSERT INTO users (created, updated, deleted, firstName, lastName, email, educationLevel, " +
        "universityId) VALUES (now(), now(), ?, ?, ?, ?, ?, ?)";
        connect();

        System.out.println(sql);

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setBoolean(1, user.isDeleted());
        statement.setString(2, user.getFirstName());
        statement.setString(3, user.getLastName());
        statement.setString(4, user.getEmail());
        statement.setInt(5, user.getEducationLevel());
        statement.setInt(6, user.getUniversityId());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public User getUserByEmail(String email) throws SQLException, JsonProcessingException {
        String sql = "SELECT * from users where email like ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, email);

        ResultSet result = statement.executeQuery();

        User user = new User();
        if (result.next()) {
            user.setCreated(new Timestamp(result.getDate("created").getTime()).toLocalDateTime());
            user.setUpdated(new Timestamp(result.getDate("updated").getTime()).toLocalDateTime());
            user.setDeleted(result.getBoolean("deleted"));
            user.setEmail(result.getString("email"));
            user.setFirstName(result.getString("firstName"));
            user.setLastName(result.getString("lastName"));
            user.setUniversityId(result.getInt("universityId"));
            user.setEducationLevel(result.getInt("educationLevel"));
            user.setUserId(result.getInt("userId"));
        } else {
            throw new SQLException("No user with the email address " + email + " found!");
        }
        result.close();
        disconnect();

        return user;
    }

}

package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import daos.UserDAO;
import models.User;

import java.sql.SQLException;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public Boolean postUser(User user) throws SQLException {
        // TODO - enforce valid email format before posting
        return userDAO.insertUser(user);
    }

    public User getUserByEmail(String email) throws SQLException, JsonProcessingException {
        return userDAO.getUserByEmail(email);
    }

    public User getUserById(int userId) throws SQLException, JsonProcessingException {
        return userDAO.getUserById(userId);
    }

}

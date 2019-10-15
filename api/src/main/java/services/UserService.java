package services;

import daos.UserDAO;
import models.User;

import java.sql.SQLException;

public class UserService {

    public Boolean postUser(User user) throws SQLException {
        // TODO - enforce valid email format before posting
        UserDAO userDAO = new UserDAO();
        return userDAO.insertUser(user);
    }

    public User getUserByEmail(String email) throws SQLException {
        UserDAO userDAO = new UserDAO();
        return userDAO.getUserByEmail(email);
    }

}

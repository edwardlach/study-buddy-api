package services;

import daos.GroupDAO;
import models.Group;

import java.sql.SQLException;

public class GroupService {

    public Boolean postGroup(Group group) throws SQLException {
        // TODO : validation?
        GroupDAO groupDAO = new GroupDAO();
        return groupDAO.insertGroup(group);
    }

    public Group getGroupByGroupName(String groupName) throws SQLException {
        GroupDAO groupDAO = new GroupDAO();
        return groupDAO.getGroupByGroupName(groupName);
    }

}

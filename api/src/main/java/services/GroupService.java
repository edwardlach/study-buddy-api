package services;

import daos.GroupDAO;
import models.Group;

import java.sql.SQLException;
import java.util.List;

public class GroupService {

    public int postGroup(Group group) throws SQLException {
        // TODO : validation?
        GroupDAO groupDAO = new GroupDAO();
        return groupDAO.insertGroup(group);
    }

    public List<Group> getGroupsByName(String groupName) throws SQLException {
        GroupDAO groupDAO = new GroupDAO();
        return groupDAO.getGroupsByName(groupName);
    }

    public Group getGroupById(int id) throws SQLException {
        GroupDAO groupDAO = new GroupDAO();
        return groupDAO.getGroupById(id);
    }

}

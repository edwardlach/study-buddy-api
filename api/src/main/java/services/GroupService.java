package services;

import daos.GroupDAO;
import models.Group;
import models.GroupMembership;

import java.sql.SQLException;
import java.util.List;

public class GroupService {

    private GroupDAO groupDAO = new GroupDAO();

    public int postGroup(Group group) throws SQLException {
        return groupDAO.insertGroup(group);
    }

    public List<Group> getGroupsByName(String groupName) throws SQLException {
        return groupDAO.getGroupsByName(groupName);
    }

    public List<Group> getGroupsByClass(int classId) throws SQLException {
        return groupDAO.getGroupsByClassId(classId);
    }

    public Group getGroupById(int id) throws SQLException {
        return groupDAO.getGroupById(id);
    }

}

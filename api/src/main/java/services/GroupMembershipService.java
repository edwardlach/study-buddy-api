package services;

import daos.GroupMembershipDAO;
import models.GroupMembership;

import java.sql.SQLException;
import java.util.List;

public class GroupMembershipService {

    public int postGroupMembership(GroupMembership groupMembership)throws SQLException{
        GroupMembershipDAO groupMembershipDAO = new GroupMembershipDAO();
        return groupMembershipDAO.insertGroupMembership(groupMembership);
    }

    public GroupMembership getGroupMembershipByGroupId(int groupId) throws SQLException {
        GroupMembershipDAO groupMembershipDAO = new GroupMembershipDAO();
        return groupMembershipDAO.getGroupMembershipByGroupId(groupId);
    }

    public GroupMembership getGroupMembershipByUserId(int userId) throws SQLException {
        GroupMembershipDAO groupMembershipDAO = new GroupMembershipDAO();
        return groupMembershipDAO.getGroupMembershipByUserId(userId);
    }
}

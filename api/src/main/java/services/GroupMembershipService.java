package services;

import daos.GroupMembershipDAO;
import models.GroupMembership;

import java.sql.SQLException;
import java.util.List;

public class GroupMembershipService {

    GroupMembershipDAO groupMembershipDAO = new GroupMembershipDAO();

    public int postGroupMembership(GroupMembership groupMembership)throws SQLException {
        //ToDo check memberhips totals and don't create if the max quota of 7 has already been met
        GroupMembership membership = groupMembershipDAO.getGroupMembershipByUserAndGroup(
                groupMembership.getUserId(),
                groupMembership.getGroupId());
        if (membership.getGroupMembership() > 0) {
             throw new SQLException("The user has an existing membership to the group.");
        }

        return groupMembershipDAO.insertGroupMembership(groupMembership);
    }

    public GroupMembership getGroupMembershipById(int groupMembership) throws SQLException {
        return groupMembershipDAO.getGroupMembershipById(groupMembership);
    }

    public List<GroupMembership> getGroupMembershipsByGroupId(int groupId) throws SQLException {
        return groupMembershipDAO.getGroupMembershipsByGroupId(groupId);
    }

    public List<GroupMembership> getGroupMembershipsByUserId(int userId) throws SQLException {
        return groupMembershipDAO.getGroupMembershipsByUserId(userId);
    }
}

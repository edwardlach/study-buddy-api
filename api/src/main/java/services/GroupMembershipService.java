package services;

import daos.GroupDAO;
import daos.GroupMembershipDAO;
import models.Group;
import models.GroupMembership;
import models.Subject;

import java.sql.SQLException;
import java.util.List;

public class GroupMembershipService {

    GroupMembershipDAO groupMembershipDAO = new GroupMembershipDAO();
    GroupService groupService = new GroupService();
    SubjectService subjectService = new SubjectService();

    public GroupMembership postGroupMembership(GroupMembership groupMembership)throws SQLException {
        GroupMembership membership = groupMembershipDAO.getGroupMembershipByUserAndGroup(
                groupMembership.getUserId(),
                groupMembership.getGroupId());
        List<GroupMembership> existingMembers = getGroupMembershipsByGroupId(groupMembership.getGroupId());

        if (membership.getGroupMembership() > 0) {
             throw new SQLException("The user has an existing membership to the group.");
        }

        if (existingMembers.size() >= 7) {
            throw new SQLException("The requested group is full, not new members are allowed!");
        }

        int newMembershipId = groupMembershipDAO.insertGroupMembership(groupMembership);
        Group group = groupService.getGroupById(groupMembership.getGroupId());
        Subject subject = subjectService.getClassById(group.getClassId());
        group.setSubject(subject);
        GroupMembership newMembership = getGroupMembershipById(newMembershipId);
        newMembership.setGroup(group);
        return newMembership;
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

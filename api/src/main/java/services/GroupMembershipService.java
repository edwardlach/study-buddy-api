package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import daos.GroupDAO;
import daos.GroupMembershipDAO;
import models.Group;
import models.GroupMembership;
import models.Subject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupMembershipService {

    GroupMembershipDAO groupMembershipDAO = new GroupMembershipDAO();
    GroupService groupService = new GroupService();
    SubjectService subjectService = new SubjectService();

    public GroupMembership postGroupMembership(GroupMembership groupMembership)throws SQLException {
        try {
            GroupMembership existingMembership = groupMembershipDAO.getGroupMembershipByUserAndGroup(
                    groupMembership.getUserId(),
                    groupMembership.getGroupId());
            if (existingMembership.getGroupMembership() > 0) {
                throw new SQLException("The user has an existing membership to the group.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            List<GroupMembership> existingMembers = getGroupMembershipsByGroupId(groupMembership.getGroupId());
            if (existingMembers.size() >= 7) {
                throw new SQLException("The requested group is full, not new members are allowed!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        int newMembershipId = groupMembershipDAO.insertGroupMembership(groupMembership);
        Group group = groupService.getGroupById(groupMembership.getGroupId());
        Subject subject = subjectService.getClassById(group.getClassId());
        group.setSubject(subject);
        GroupMembership newMembership = getGroupMembershipById(newMembershipId);
        newMembership.setGroup(group);
        return newMembership;
    }

    public String removeGroupMembership(int groupMembership) throws SQLException {
        GroupMembership membership = getGroupMembershipById(groupMembership);
        if (membership.isDeleted()) {
            throw new SQLException("Membership is already deleted!");
        }
        boolean updated = groupMembershipDAO.removeGroupMembership(membership);
        if (updated) {
            return "GroupMembership " + groupMembership + " has been successfully removed!";
        } else {
            return "GroupMembership " + groupMembership + " was not able to be removed.";
        }
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

    public List<Group> getUserGroupsByUserId(int userId) throws SQLException {
        List<Group> userGroups = new ArrayList<>();
        List<GroupMembership> userMemberships = groupMembershipDAO.getGroupMembershipsByUserId(userId);
        ObjectMapper mapper = new ObjectMapper();
        for(GroupMembership userMembership : userMemberships) {
            Group userGroup = groupService.getDetailedGroupById(userMembership.getGroupId());
            List<GroupMembership> memberships = groupMembershipDAO
                    .getDetailedGroupMembershipsByGroupId(userMembership.getGroupId());
            userGroup.setGroupMemberships(memberships);
            userGroups.add(userGroup);
        }
        return userGroups;
    }
}

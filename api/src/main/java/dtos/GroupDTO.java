package dtos;
import com.fasterxml.jackson.core.JsonProcessingException;
import models.*;
import services.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class GroupDTO extends AbstractDTO {

    private String created;
    private String updated;
    private String startDate;
    private String endDate;
    private boolean deleted;
    private String groupName;
    private int classId;
    private int groupId;
    private SubjectDTO subject;
    private List<GroupMembershipDTO> groupMemberships;

    public GroupDTO(){}

    public GroupDTO(Group group) {
        this.created = group.getCreated().format(formatter);
        this.updated = group.getUpdated().format(formatter);
        this.startDate = group.getStartDate().format(formatter);
        this.endDate = group.getEndDate().format(formatter);
        this.deleted = group.isDeleted();
        this.groupName = group.getGroupName();
        this.classId = group.getClassId();
        this.groupId = group.getGroupId();
        if (group.getSubject() != null) {
            this.subject = new SubjectDTO(group.getSubject());
        }
        if (group.getGroupMemberships() != null) {
            List<GroupMembershipDTO> memberships = new ArrayList<>();
            for (GroupMembership membership : group.getGroupMemberships()) {
                memberships.add(new GroupMembershipDTO(membership));
            }
            this.groupMemberships = memberships;
        }
    }

    public GroupDTO(
        String groupName,
        int classId,
        String startDate,
        String endDate
    ) {
        this.groupName = groupName;
        this.classId = classId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /** Helper Functions **/

    public static GroupDTO buildGroup(GroupDTO group) throws SQLException {
        SubjectService subjectService = new SubjectService();
        UniversityService universityService = new UniversityService();
        Subject subject = subjectService.getClassById(group.getClassId());
        SubjectDTO subjectDTO = new SubjectDTO(subject);
        University university = universityService.getUniversityById(subject.getUniversityId());
        UniversityDTO universityDTO = new UniversityDTO(university);

        List<GroupMembershipDTO> memberships = getMembershipsForGroup(group);
        subjectDTO.setUniversity(universityDTO);
        group.setGroupMemberships(memberships);
        group.setSubject(subjectDTO);
        return group;
    }

    public static GroupDTO buildGroupFromMembership(GroupMembershipDTO membership) throws SQLException {
        GroupService groupService = new GroupService();
        Group group = groupService.getGroupById(membership.getGroupId());
        GroupDTO groupDTO = new GroupDTO(group);
        return buildGroup(groupDTO);
    }

    private static List<GroupMembershipDTO> getMembershipsForGroup(GroupDTO group) {
        GroupMembershipService groupMembershipService = new GroupMembershipService();
        try {
            List<GroupMembership> memberships = groupMembershipService.getGroupMembershipsByGroupId(group.getGroupId());
            List<GroupMembershipDTO> membershipDTOs = memberships
                    .stream()
                    .map(membership -> new GroupMembershipDTO(membership))
                    .collect(toList());

            membershipDTOs
                    .stream()
                    .map(membership -> {
                        try {
                            membership.setUser(getUserForMembership(membership));
                        } catch (SQLException | JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                        return membership;
                    })
                    .collect(toList());

            return membershipDTOs;
        } catch (SQLException e) {
            return null;
        }
    }

    private static UserDTO getUserForMembership(GroupMembershipDTO membership) throws SQLException, JsonProcessingException {
        UserService userService = new UserService();
        User user = userService.getUserById(membership.getUserId());
        return new UserDTO(user);
    }


    /** Setters and Getters **/

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public List<GroupMembershipDTO> getGroupMemberships() {
        return groupMemberships;
    }

    public void setGroupMemberships(List<GroupMembershipDTO> groupMemberships) {
        this.groupMemberships = groupMemberships;
    }

    public SubjectDTO getSubject() {
        return subject;
    }

    public void setSubject(SubjectDTO subject) {
        this.subject = subject;
    }

}
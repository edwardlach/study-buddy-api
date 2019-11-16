package dtos;
import models.GenericEntity;
import models.Group;
import models.GroupMembership;

import java.time.LocalDateTime;
import java.util.List;

public class GroupDTO implements AbstractDTO<Group, GroupDTO> {

    private String created;
    private String updated;
    private String startDate;
    private String endDate;
    private boolean deleted;
    private String groupName;
    private int classId;
    private int groupId;
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
    }

    public GroupDTO(Group group, List<GroupMembership> groupMemberships) {
        this.created = group.getCreated().format(formatter);
        this.updated = group.getUpdated().format(formatter);
        this.startDate = group.getStartDate().format(formatter);
        this.endDate = group.getEndDate().format(formatter);
        this.deleted = group.isDeleted();
        this.groupName = group.getGroupName();
        this.classId = group.getClassId();
        this.groupId = group.getGroupId();
        this.groupMemberships = new GroupMembershipDTO().convertToDTO(groupMemberships);
    }

    @Override
    public GroupDTO apply(Group group) {
        GroupDTO gDTO = new GroupDTO();
        gDTO.created = group.getCreated().format(formatter);
        gDTO.updated = group.getUpdated().format(formatter);
        gDTO.startDate = group.getStartDate().format(formatter);
        gDTO.endDate = group.getEndDate().format(formatter);
        gDTO.deleted = group.isDeleted();
        gDTO.groupName = group.getGroupName();
        gDTO.classId = group.getClassId();
        gDTO.groupId = group.getGroupId();
        return gDTO;
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

}
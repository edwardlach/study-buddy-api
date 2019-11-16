package dtos;

import models.GenericEntity;
import models.Group;
import models.GroupMembership;
import models.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

public class GroupMembershipDTO implements AbstractDTO<GroupMembership, GroupMembershipDTO> {
    private String created;
    private String updated;
    private boolean deleted;
    private boolean active;
    private int userId;
    private int groupId;
    private int groupMembership;
    private UserDTO user;

    public GroupMembershipDTO(){}

    public GroupMembershipDTO(GroupMembership groupMembership){
        this.created = groupMembership.getCreated().format(formatter);
        this.updated = groupMembership.getUpdated().format(formatter);
        this.deleted = groupMembership.isDeleted();
        this.active = groupMembership.isActive();
        this.userId = groupMembership.getUserId();
        this.groupId = groupMembership.getGroupId();
        this.groupMembership = groupMembership.getGroupMembership();
    }

    public GroupMembershipDTO(GroupMembership groupMembership, User user){
        this.created = groupMembership.getCreated().format(formatter);
        this.updated = groupMembership.getUpdated().format(formatter);
        this.deleted = groupMembership.isDeleted();
        this.active = groupMembership.isActive();
        this.userId = groupMembership.getUserId();
        this.groupId = groupMembership.getGroupId();
        this.groupMembership = groupMembership.getGroupMembership();
        this.user = new UserDTO(user);
    }

    @Override
    public GroupMembershipDTO apply(GroupMembership groupMembership) {
        GroupMembershipDTO gmDTO = new GroupMembershipDTO();
        gmDTO.created = groupMembership.getCreated().format(formatter);
        gmDTO.updated = groupMembership.getUpdated().format(formatter);
        gmDTO.deleted = groupMembership.isDeleted();
        gmDTO.active = groupMembership.isActive();
        gmDTO.userId = groupMembership.getUserId();
        gmDTO.groupId = groupMembership.getGroupId();
        gmDTO.groupMembership = groupMembership.getGroupMembership();
        return gmDTO;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getGroupMembership() {
        return groupMembership;
    }

    public void setGroupMembership(int groupMembership) {
        this.groupMembership = groupMembership;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

}

package dtos;

import models.GenericEntity;
import models.Group;
import models.GroupMembership;
import models.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;


public class GroupMembershipDTO extends AbstractDTO {
    private String created;
    private String updated;
    private boolean deleted;
    private boolean active;
    private int userId;
    private int groupId;
    private int groupMembership;
    private UserDTO user;
    private GroupDTO group;

    public GroupMembershipDTO(){}

    public GroupMembershipDTO(GroupMembership groupMembership){
        this.created = groupMembership.getCreated().format(formatter);
        this.updated = groupMembership.getUpdated().format(formatter);
        this.deleted = groupMembership.isDeleted();
        this.active = groupMembership.isActive();
        this.userId = groupMembership.getUserId();
        this.groupId = groupMembership.getGroupId();
        this.groupMembership = groupMembership.getGroupMembership();
        if (groupMembership.getGroup() != null) {
            this.group = new GroupDTO(groupMembership.getGroup());
        }
        if (groupMembership.getUser() != null) {
            this.user = new UserDTO(groupMembership.getUser());
        }
    }

    public GroupMembershipDTO(GroupMembership groupMembership, Group group){
        this.created = groupMembership.getCreated().format(formatter);
        this.updated = groupMembership.getUpdated().format(formatter);
        this.deleted = groupMembership.isDeleted();
        this.active = groupMembership.isActive();
        this.userId = groupMembership.getUserId();
        this.groupId = groupMembership.getGroupId();
        this.groupMembership = groupMembership.getGroupMembership();
        this.group = new GroupDTO(group);
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

    public GroupDTO getGroup() {
        return group;
    }

    public void setGroup(GroupDTO group) {
        this.group = group;
    }

}

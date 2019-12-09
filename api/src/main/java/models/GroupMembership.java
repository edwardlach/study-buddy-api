package models;

import java.time.LocalDateTime;

public class GroupMembership extends GenericEntity {

    private LocalDateTime created;
    private LocalDateTime updated;
    private boolean deleted;
    private boolean active;
    private int userId;
    private int groupId;
    private int groupMembership;
    private Group group;

    public GroupMembership(){}

    public GroupMembership(LocalDateTime created,
                           LocalDateTime updated,
                           boolean deleted,
                           boolean active,
                           int userId,
                           int groupId,
                           int groupMembership){
        this.created = created;
        this.updated = updated;
        this.deleted = deleted;
        this.active = active;
        this.userId = userId;
        this.groupId = groupId;
        this.groupMembership = groupMembership;

    }

    public GroupMembership(int groupId, int userId){
        this.userId = userId;
        this.groupId = groupId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}

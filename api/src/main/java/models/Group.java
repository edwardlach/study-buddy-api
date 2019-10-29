package models;

import dtos.GroupDTO;

import java.time.LocalDateTime;

public class Group{

    private LocalDateTime created, updated, startDatetime;
    private boolean deleted;
    private String groupName;
    private int classId, groupId;

    public Group (GroupDTO group) {
        this.groupId = group.getGroupId();
        this.classId = group.getClassId();
        this.created = group.getCreated();
        this.updated = group.getUpdated();
        this.startDatetime = group.getStartDatetime();
        this.deleted = group.isDeleted();
        this.groupName = group.getGroupName();
    }

    public Group (LocalDateTime created, LocalDateTime updated, Boolean deleted, LocalDateTime startDatetime,
                  String groupName, int classId, int groupId) {
        this.created = created;
        this.updated = updated;
        this.deleted = deleted;
        this.startDatetime = startDatetime;
        this.groupName = groupName;
        this.classId = classId;
        this.groupId = groupId;
    }

    public Group(){}

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

    public LocalDateTime getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(LocalDateTime startDatetime) {
        this.startDatetime = startDatetime;
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
}
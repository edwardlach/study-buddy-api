package models;

import java.time.LocalDateTime;

public class Group extends GenericEntity {

    private LocalDateTime created;
    private LocalDateTime updated;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean deleted;
    private String groupName;
    private int classId;
    private int groupId;
    private Subject subject;

    public Group(){}

    public Group (
            LocalDateTime created,
            LocalDateTime updated,
            Boolean deleted,
            LocalDateTime startDate,
            LocalDateTime endDate,
            String groupName,
            int classId,
            int groupId) {
        this.created = created;
        this.updated = updated;
        this.deleted = deleted;
        this.startDate = startDate;
        this.endDate = endDate;
        this.groupName = groupName;
        this.classId = classId;
        this.groupId = groupId;
    }

    public Group(
            LocalDateTime startDate,
            LocalDateTime endDate,
            boolean deleted,
            String groupName,
            int classId){
        this.startDate = startDate;
        this.endDate = endDate;
        this.deleted = deleted;
        this.groupName = groupName;
        this.classId = classId;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
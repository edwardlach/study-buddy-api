package dtos;
import models.Group;

import java.time.LocalDateTime;

public class GroupDTO implements AbstractDTO{

    private LocalDateTime created;
    private LocalDateTime updated;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean deleted;
    private String groupName;
    private int classId;
    private int groupId;

    public GroupDTO(){}

    public GroupDTO(Group group) {
        this.created = group.getCreated();
        this.updated = group.getUpdated();
        this.startDate = group.getStartDate();
        this.deleted = group.isDeleted();
        this.groupName = group.getGroupName();
        this.classId = group.getClassId();
        this.groupId = group.getGroupId();
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


}
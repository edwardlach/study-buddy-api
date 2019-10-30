package dtos;
import models.Group;

import java.time.LocalDateTime;

public class GroupDTO implements AbstractDTO{

    private String created;
    private String updated;
    private String startDate;
    private String endDate;
    private boolean deleted;
    private String groupName;
    private int classId;
    private int groupId;

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


}
package dtos;

import models.GroupMembership;
import models.User;
import java.time.LocalDateTime;

public class GroupMembershipDTO implements AbstractDTO {
    private String created;
    private String updated;
    private boolean deleted;
    private boolean active;
    private int userId;
    private int groupId;
    private int groupMembershipId;

    public GroupMembershipDTO(){}

    public GroupMembershipDTO(GroupMembership groupMembership){
        this.created = groupMembership.getCreated().format(formatter);
        this.updated = groupMembership.getUpdated().format(formatter);
        this.deleted = groupMembership.isDeleted();
        this.active = groupMembership.isActive();
        this.userId = groupMembership.getUserId();
        this.groupId = groupMembership.getGroupId();
        this.groupMembershipId = groupMembership.getGroupMembershipId();
    }

    public GroupMembershipDTO(GroupMembership groupMembership, User user){

    }
}

package dtos;

import models.GroupMembership;
import java.util.List;

public class GroupMembershipsDTO {

    List<GroupMembership> groupMemberships;

    public GroupMembershipsDTO(){}

    public GroupMembershipsDTO(List<GroupMembership> groupMemberships){
        this.groupMemberships = groupMemberships;
    }

    public List<GroupMembership> getGroupMemberships(){
        return groupMemberships;
    }

    public void setGroupMemberships(List<GroupMembership> groupMemberships){
        this.groupMemberships = groupMemberships;
    }
}

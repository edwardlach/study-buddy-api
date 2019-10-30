package dtos;

import models.Group;

import java.util.List;

public class GroupsDTO {

    List<Group> groups;

    public GroupsDTO(){}

    public GroupsDTO(List<Group> groups) {
        this.groups = groups;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

}

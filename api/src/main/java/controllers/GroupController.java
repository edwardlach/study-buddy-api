package controllers;

import dtos.GroupMembershipDTO;
import dtos.RequestDTO;
import dtos.GroupDTO;
import models.Group;
import models.GroupMembership;
import models.Subject;
import services.GroupMembershipService;
import services.GroupService;
import services.SubjectService;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static constants.ApiRequestMappings.GET;
import static constants.ApiRequestMappings.POST;

public class GroupController extends AbstractController {

    private GroupService groupService = new GroupService();
    private GroupMembershipService groupMembershipService = new GroupMembershipService();
    private SubjectService subjectService = new SubjectService();

    public GroupController(RequestDTO request) throws IOException, SQLException {
        routeRequest(request);
    }

    @Override
    public void routeRequest(RequestDTO request) throws SQLException, IOException {
        switch(request.getHttpMethod()){
            case POST:
                GroupDTO dto = stringToDTO(request.getBody(), GroupDTO.class);
                setResponseBody(createNewGroup(dto, request.getQueryStringParameters().getCreator()));
                break;
            case GET:
                System.out.println(request.getQueryStringParameters().getSearchTerm());
                setResponseBody(getGroupsFromSearch(request.getQueryStringParameters().getSearchTerm()));
                break;
        }
    }

    private GroupMembershipDTO createNewGroup(GroupDTO groupDTO, int userId) throws SQLException{
        Group group = new Group(
                LocalDateTime.parse(groupDTO.getStartDate(), formatter),
                LocalDateTime.parse(groupDTO.getEndDate(), formatter),
                groupDTO.isDeleted(),
                groupDTO.getGroupName(),
                groupDTO.getClassId());
        int groupId = groupService.postGroup(group);
        GroupMembership groupMembership = new GroupMembership(groupId, userId);
        // Creates a group and then a membership for the returning users.
        GroupMembership newMembership = groupMembershipService.postGroupMembership(groupMembership);
        return new GroupMembershipDTO(newMembership);
    }

    private List<GroupDTO> getGroupsFromSearch(String groupName) throws SQLException{
        String errorMessage = "";
        List<GroupDTO> dto = new ArrayList<GroupDTO>();
        try {
            List<Group> groups = groupService.getGroupsByName(groupName);
            for (Group group : groups) {
                dto.add(new GroupDTO(group));
            }
        } catch (SQLException e) {
            errorMessage = e.getMessage();
        }

        try {
            List<Subject> matchingSubjects = subjectService.getClassesByName(groupName);
            for(Subject subject: matchingSubjects) {
                List<Group> groupsWithClass = groupService.getGroupsByClass(subject.getClassId());
                for (Group group : groupsWithClass) {
                    dto.add(new GroupDTO(group));
                }
            }
        } catch (SQLException e) {
            errorMessage = e.getMessage();
        }

        if (dto.size() < 1) {
            throw new SQLException(errorMessage);
        }

        return dto.stream()
                .filter(distinctByKey(GroupDTO::getGroupId))
                .map(group -> {
                    try {
                        return GroupDTO.buildGroup(group);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    public GroupDTO getGroupById(int id) throws SQLException {
        Group group = groupService.getGroupById(id);
        return new GroupDTO(group);
    }
}
package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import dtos.*;

import models.*;
import services.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static constants.ApiRequestMappings.GET;
import static constants.ApiRequestMappings.POST;
import static java.util.stream.Collectors.toList;

public class GroupMembershipController extends AbstractController{

    private GroupMembershipService groupMembershipService = new GroupMembershipService();
    private GroupService groupService = new GroupService();
    private UserService userService = new UserService();
    private SubjectService subjectService = new SubjectService();
    private UniversityService universityService = new UniversityService();

    public GroupMembershipController(RequestDTO request) throws IOException, SQLException{
        routeRequest(request);
    }

    public void routeRequest(RequestDTO request) throws SQLException, IOException {
        switch(request.getHttpMethod()){
            case POST:
                GroupMembershipDTO dto = stringToDTO(request.getBody(), GroupMembershipDTO.class);
                setResponseBody(createNewGroupMembership(dto));
                break;
            case GET:
                setResponseBody(getGroupMembershipsForUser(Integer.parseInt(request.getPathParameters().getUserId())));
                break;
        }
    }

    private GroupMembershipDTO createNewGroupMembership(GroupMembershipDTO groupMembershipDTO) throws SQLException{
        GroupMembership groupMembership = groupMembershipService.postGroupMembership(
                new GroupMembership(
                        groupMembershipDTO.getGroupId(),
                        groupMembershipDTO.getUserId()));
        return new GroupMembershipDTO(groupMembership);
    }

    private List<GroupDTO> getGroupMembershipsForUser(int userId) throws SQLException {
        List<GroupMembership> groupMemberships = groupMembershipService.getGroupMembershipsByUserId(userId);
        List<GroupMembershipDTO> membershipDTOs = groupMemberships
                .stream()
                .map(membership -> new GroupMembershipDTO(membership))
                .collect(toList());

        return membershipDTOs
                .stream()
                .map(membership -> {
                    try {
                        return GroupDTO.buildGroupFromMembership(membership);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(toList());
    }

}


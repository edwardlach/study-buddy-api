package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import dtos.GroupDTO;
import dtos.GroupMembershipDTO;
import dtos.RequestDTO;

import dtos.UserDTO;
import models.Group;
import models.GroupMembership;
import models.User;
import services.GroupMembershipService;
import services.GroupService;
import services.UserService;

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

    private GroupMembershipDTO createNewGroupMembership(GroupMembershipDTO groupMembershipDTO) throws SQLException {
        int groupMembership = groupMembershipService.postGroupMembership(
                new GroupMembership(
                        groupMembershipDTO.getGroupId(),
                        groupMembershipDTO.getUserId()));
        GroupMembership newGroupMembership = groupMembershipService.getGroupMembershipById(groupMembership);
        return new GroupMembershipDTO(newGroupMembership);
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
                        return getGroupFromMembership(membership);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(toList());
    }

    private GroupDTO getGroupFromMembership(GroupMembershipDTO membership) throws SQLException {
        Group group = groupService.getGroupById(membership.getGroupId());
        GroupDTO groupDTO = new GroupDTO(group);
        List<GroupMembershipDTO> memberships = getMembershipsForGroup(groupDTO);
        groupDTO.setGroupMemberships(memberships);
        return groupDTO;
    }

    private List<GroupMembershipDTO> getMembershipsForGroup(GroupDTO group) throws SQLException {
        List<GroupMembership> memberships = groupMembershipService.getGroupMembershipsByGroupId(group.getGroupId());
        List<GroupMembershipDTO> membershipDTOs = memberships
                .stream()
                .map(membership -> new GroupMembershipDTO(membership))
                .collect(toList());

        membershipDTOs
                .stream()
                .map(membership -> {
                    try {
                        membership.setUser(getUserForMembership(membership));
                    } catch (SQLException | JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    return membership;
                })
                .collect(toList());

        return membershipDTOs;
    }

    private UserDTO getUserForMembership(GroupMembershipDTO membership) throws SQLException, JsonProcessingException {
        User user = userService.getUserById(membership.getUserId());
        return new UserDTO(user);
    }
}


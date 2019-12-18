package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import dtos.*;

import models.*;
import services.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static constants.ApiRequestMappings.*;
import static java.util.stream.Collectors.toList;

public class GroupMembershipController extends AbstractController{

    private GroupMembershipService groupMembershipService = new GroupMembershipService();
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
            case PUT:
                setResponseBody(
                        removeGroupMembership(
                                Integer.parseInt(request.getPathParameters().getGroupMembership())
                        )
                );
                break;
            case GET:
                switch(request.getResource()) {
                    case GROUP_MEMBERSHIP:
                        System.out.println("The call was correctly routed!");
                        User user = userService.getUserByEmail(request.getQueryStringParameters().getEmail());
                        System.out.println("User " + user.getUserId() + " was retrieved for email " + request.getQueryStringParameters().getEmail());
                        setResponseBody(getGroupMembershipsForUser(user.getUserId()));
                        break;
                    case USER_GROUP_MEMBERSHIPS:
                        setResponseBody(getGroupMembershipsForUser(Integer.parseInt(request.getPathParameters().getUserId())));
                        break;
                }
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

    private String removeGroupMembership(int groupMembership) throws SQLException {
        return groupMembershipService.removeGroupMembership(groupMembership);
    }

    private List<GroupDTO> getGroupMembershipsForUser(int userId) throws SQLException {
        List<Group> userGroups = groupMembershipService.getUserGroupsByUserId(userId);
        List<GroupDTO> groupDTOs = new ArrayList<>();
        for(Group group : userGroups) {
            groupDTOs.add(new GroupDTO(group));
        }
        return groupDTOs;
    }

}


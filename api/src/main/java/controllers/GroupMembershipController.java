/*package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dtos.AbstractDTO;
import dtos.GroupMembershipDTO;
import dtos.RequestDTO;

import models.GroupMembership;
import services.GroupMembershipService;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static constants.ApiRequestMappings.GET;
import static constants.ApiRequestMappings.POST;

public class GroupMembershipController extends AbstractController{

    private GroupMembershipService groupMembershipService = new GroupMembershipService();

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
                System.out.println(request.getPathStringParameters().getSearchTerm());
                setResponseBody(getGroupMembershipsByGroupId(request.getQueryStringParameters().getSearchTerm()));
                break;
        }
    }

    private GroupMembershipDTO createNewGroupMembership(GroupMembershipDTO groupMembershipDTO) throws SQLException{
        int groupMembership = groupMembershipService.postGroupMembership(new GroupMembership(
                LocalDateTime.parse(groupMembershipDTO.getCreated(), formatter),
                LocalDateTime.parse(groupMembershipDTO.getUpdated(), formatter),
                groupMembershipDTO.isDeleted(),
                groupMembershipDTO.isActive(),
                groupMembershipDTO.getGroupMembership()));
        GroupMembership groupMembership = groupMembershipService.getGroupMembershipsByGroupId(int groupId);
        return new GroupMembershipDTO(groupMembership);
    }

    private List<GroupMembership> getGroupMembershipsByGroupId(int groupId) throws SQLException{
        List<GroupMembership> groupMembership = groupMembershipService.getGroupMembershipsByGroupId(groupId);
        return groupMembership;
    }

    private List<GroupMembership> getGroupMembershipsByUserId(int userId) throws SQLException {
        List<GroupMembership> groupMembership = groupMembershipService.getGroupMembershipsByUserId(UserId);
        return groupMembership;
    }
}*/


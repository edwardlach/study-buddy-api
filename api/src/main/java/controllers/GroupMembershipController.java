package controllers;

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
                System.out.println(request.getQueryStringParameters().getSearchTerm());
                //setResponseBody(getGroupMembershipsByGroupId(request.getQueryStringParameters().getSearchTerm()));
                break;
        }
    }

    private GroupMembershipDTO createNewGroupMembership(GroupMembershipDTO groupMembershipDTO) throws SQLException{
        int membership = groupMembershipService.postGroupMembership(new GroupMembership(
                LocalDateTime.parse(groupMembershipDTO.getCreated(), formatter),
                LocalDateTime.parse(groupMembershipDTO.getUpdated(), formatter),
                groupMembershipDTO.isDeleted(),
                groupMembershipDTO.isActive(),
                groupMembershipDTO.getUserId(),
                groupMembershipDTO.getGroupId(),
                groupMembershipDTO.getGroupMembership()));
        GroupMembership groupMembership = groupMembershipService.getGroupMembershipByGroupId(membership);
        return new GroupMembershipDTO(groupMembership);
    }

    private GroupMembershipDTO getGroupMembershipsByGroupId(int groupId) throws SQLException{
        GroupMembership groupMembership = groupMembershipService.getGroupMembershipByGroupId(groupId);
        return new GroupMembershipDTO(groupMembership);
    }

    private GroupMembershipDTO getGroupMembershipsByUserId(int userId) throws SQLException {
        GroupMembership groupMembership = groupMembershipService.getGroupMembershipByUserId(userId);
        return new GroupMembershipDTO(groupMembership);
    }
}


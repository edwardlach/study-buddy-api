package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dtos.AbstractDTO;
import dtos.GroupsDTO;
import dtos.RequestDTO;
import dtos.GroupDTO;
import models.Group;
import services.GroupService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static constants.ApiRequestMappings.GET;
import static constants.ApiRequestMappings.POST;

public class GroupController extends AbstractController {

    private GroupService groupService = new GroupService();

    public GroupController(RequestDTO request) throws IOException, SQLException {
        routeRequest(request);
    }

    @Override
    public void routeRequest(RequestDTO request) throws SQLException, IOException {
        switch(request.getHttpMethod()){
            case POST:
                GroupDTO dto = stringToDTO(request.getBody(), GroupDTO.class);
                setResponseBody(createNewGroup(dto));
                break;
            case GET:
                setResponseBody(getGroupsByName(request.getQueryStringParameters().getSearchTerm()));
                break;
        }
    }

    private GroupDTO createNewGroup(GroupDTO groupDTO) throws SQLException{
        groupService.postGroup(new Group(
            groupDTO.getStartDate(),
            groupDTO.getEndDate(),
            groupDTO.isDeleted(),
            groupDTO.getGroupName(),
            groupDTO.getClassId()));
        Group group = groupService.getGroupById(groupDTO.getGroupId());
        return new GroupDTO(group);
    }

    private GroupsDTO getGroupsByName(String groupName) throws SQLException{
        List<Group> groups = groupService.getGroupsByName(groupName);
        return new GroupsDTO(groups);
    }

    public GroupDTO getGroupById(int id) throws SQLException {
        Group group = groupService.getGroupById(id);
        return new GroupDTO(group);
    }

}
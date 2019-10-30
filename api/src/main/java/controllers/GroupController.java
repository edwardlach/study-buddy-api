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
import java.time.LocalDateTime;
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
                System.out.println(request.getQueryStringParameters().getSearchTerm());
                setResponseBody(getGroupsByName(request.getQueryStringParameters().getSearchTerm()));
                break;
        }
    }

    private GroupDTO createNewGroup(GroupDTO groupDTO) throws SQLException{
        int groupId = groupService.postGroup(new Group(
            LocalDateTime.parse(groupDTO.getStartDate(), formatter),
            LocalDateTime.parse(groupDTO.getEndDate(), formatter),
            groupDTO.isDeleted(),
            groupDTO.getGroupName(),
            groupDTO.getClassId()));
        Group group = groupService.getGroupById(groupId);
        return new GroupDTO(group);
    }

    private List<Group> getGroupsByName(String groupName) throws SQLException{
        List<Group> groups = groupService.getGroupsByName(groupName);
        return groups;
    }

    public GroupDTO getGroupById(int id) throws SQLException {
        Group group = groupService.getGroupById(id);
        return new GroupDTO(group);
    }

}
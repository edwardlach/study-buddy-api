package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dtos.AbstractDTO;
import dtos.RequestDTO;
import dtos.GroupDTO;
import models.Group;
import services.GroupService;

import java.io.IOException;
import java.sql.SQLException;

import static constants.ApiRequestMappings.GET;
import static constants.ApiRequestMappings.POST;

public class GroupController implements AbstractController {

    private String responseBody;

    public GroupController(RequestDTO request) throws IOException, SQLException{
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(request));
        routeRequest(request);
    }

    @Override
    public void routeRequest(RequestDTO request) throws SQLException, IOException {
        switch(request.getHttpMethod()){
            case POST:
                AbstractDTO dto = stringToDTO(request.getBody());
                setResponseBody(createNewGroup((GroupDTO) dto));
                break;
            case GET:
                // ...
                break;
        }
    }

    @Override
    public AbstractDTO stringToDTO(String body) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(body, GroupDTO.class);
    }

    private GroupDTO createNewGroup(GroupDTO groupDTO) throws SQLException{
        GroupService groupService = new GroupService();
        groupService.postGroup(new Group(groupDTO));
        Group group = groupService.getGroupByGroupName(groupDTO.getGroupName());
        return new GroupDTO(group.getCreated(), group.getUpdated(), group.isDeleted(), group.getStartDatetime(),
                group.getGroupName(), group.getClassId(), group.getGroupId());
    }

    private GroupDTO getGroupByGroupName(String groupName) throws SQLException{
        GroupService groupService = new GroupService();
        Group group = groupService.getGroupByGroupName(groupName);
        return new GroupDTO(group.getCreated(), group.getUpdated(), group.isDeleted(), group.getStartDatetime(),
                group.getGroupName(), group.getClassId(), group.getGroupId());
    }

    public String getResponseBody() {
        return this.responseBody;
    }

    public void setResponseBody(GroupDTO groupDTO) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        this.responseBody = mapper.writeValueAsString(groupDTO);
    }
}
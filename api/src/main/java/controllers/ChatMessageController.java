package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import dtos.*;

import models.*;
import services.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static constants.ApiRequestMappings.GET;
import static constants.ApiRequestMappings.POST;
import static java.util.stream.Collectors.toList;

public class ChatMessageController extends AbstractController{
    private ChatMessageService chatMessageService = new ChatMessageService();
    private GroupService groupService = new GroupService();
    private UserService userService = new UserService();

    public ChatMessageController(RequestDTO request) throws IOException, SQLException{
        routeRequest(request);
    }

    public void routeRequest(RequestDTO request) throws SQLException, IOException {
        switch(request.getHttpMethod()){
            case POST:
                ChatMessageDTO dto = stringToDTO(request.getBody(), ChatMessageDTO.class);
                setResponseBody(createNewChatMessage(dto));
                break;
            case GET:
                setResponseBody(getChatMessagesForUser(Integer.parseInt(request.getPathParameters().getUserId())));
                break;
        }
    }

    private ChatMessageDTO createNewChatMessage(ChatMessageDTO chatMessageDTO) throws SQLException{
        int chatMessage = chatMessageService.postChatMessage(
                new ChatMessage(
                        chatMessageDTO.getUserId(),
                        chatMessageDTO.getGroupId()));
        ChatMessage newChatMessage = chatMessageService.getChatMessageById(chatMessage);
        return new ChatMessageDTO(newChatMessage);
    }

    private List<ChatMessageDTO> getChatMessagesForUser(int groupId) throws SQLException{
        String errorMessage = "";
        List<ChatMessageDTO> dto = new ArrayList<ChatMessageDTO>();
        try {
            List<ChatMessage> messages = chatMessageService.getChatMessagesByGroupId(groupId);
            for (ChatMessage cm : messages) {
                dto.add(new ChatMessageDTO(cm));
            }
        } catch (SQLException e){
            errorMessage = e.getMessage();
        }

        return dto;
    }
}


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

public class ChatMessageController extends AbstractController{
    private ChatMessageService chatMessageService = new ChatMessageService();

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
                setResponseBody(getChatMessagesForGroup(Integer.parseInt(request.getPathParameters().getGroupId())));
                break;
        }
    }

    private ChatMessageDTO createNewChatMessage(ChatMessageDTO chatMessageDTO) throws SQLException, JsonProcessingException {
        ChatMessage chatMessage = chatMessageService.postChatMessage(
                new ChatMessage(
                        chatMessageDTO.getUserId(),
                        chatMessageDTO.getGroupId(),
                        chatMessageDTO.getMessage()));
        return new ChatMessageDTO(chatMessage);
    }

    private List<ChatMessageDTO> getChatMessagesForGroup(int groupId) throws SQLException, JsonProcessingException {
        List<ChatMessageDTO> dto = new ArrayList<>();
        List<ChatMessage> messages = chatMessageService.getChatMessagesByGroupId(groupId);
        for (ChatMessage cm : messages) {
            dto.add(new ChatMessageDTO(cm));
        }
        return dto;
    }
}


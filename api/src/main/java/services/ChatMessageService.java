package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import daos.ChatMessageDAO;
import models.ChatMessage;

import java.sql.SQLException;
import java.util.List;

public class ChatMessageService {

    private ChatMessageDAO chatMessageDAO = new ChatMessageDAO();
    private UserService userService = new UserService();

    public ChatMessage postChatMessage(ChatMessage chatMessage) throws SQLException, JsonProcessingException {
        int chatId = chatMessageDAO.insertChatMessage(chatMessage);
        ChatMessage chat = getChatMessageById(chatId);
        chat.setUser(userService.getUserById(chat.getUserId()));
        return chat;
    }

    public ChatMessage getChatMessageById(int chatMessageId) throws SQLException, JsonProcessingException {
        ChatMessage chat = chatMessageDAO.getChatMessageById(chatMessageId);
        chat.setUser(userService.getUserById(chat.getUserId()));
        return chat;
    }

    public List<ChatMessage> getChatMessagesByGroupId(int groupId) throws SQLException, JsonProcessingException {
        return chatMessageDAO.getChatMessagesByGroupId(groupId);
    }

    public List<ChatMessage> getChatMessagesByUserId(int userId) throws SQLException, JsonProcessingException {
        List<ChatMessage> messages = chatMessageDAO.getChatMessagesByUserId(userId);
        for (ChatMessage message : messages) {
            message.setUser(userService.getUserById(message.getUserId()));
        }
        return messages;
    }
}

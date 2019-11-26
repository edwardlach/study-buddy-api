package services;

import daos.ChatMessageDAO;
import models.ChatMessage;

import java.sql.SQLException;
import java.util.List;

public class ChatMessageService {

    private ChatMessageDAO chatMessageDAO = new ChatMessageDAO();

    public int postChatMessage(ChatMessage chatMessage) throws SQLException{
        return chatMessageDAO.insertChatMessage(chatMessage);
    }

    public ChatMessage getChatMessageById(int chatMessageId) throws SQLException{
        return chatMessageDAO.getChatMessageById(chatMessageId);
    }

    public List<ChatMessage> getChatMessagesByGroupId(int groupId)throws SQLException{
        return chatMessageDAO.getChatMessagesByGroupId(groupId);
    }

    public List<ChatMessage> getChatMessagesByUserId(int userId)throws SQLException{
        return chatMessageDAO.getChatMessagesByUserId(userId);
    }
}

package daos;

import models.ChatMessage;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatMessageDAO extends DBConnect {

    public int insertChatMessage(ChatMessage chatMessage) throws SQLException {

        String sql = "INSERT INTO messages (created, updated, deleted, userId, flagged, groupId, message) VALUES (now(), "
                + "now(), false, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, chatMessage.getGroupId());
        statement.setInt(2, chatMessage.getUserId());

        System.out.println(sql);

        boolean rowInserted = statement.executeUpdate() > 0;
        int chatMessageId = 0;
        ResultSet result = statement.getGeneratedKeys();
        if (result.next()){
            chatMessageId = result.getInt(1);
        }

        statement.close();
        disconnect();
        return chatMessageId;
    }

    public ChatMessage getChatMessageById(int chatMessage) throws SQLException{
        String sql = "SELECT * FROM messages where messageId = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, chatMessage);

        ResultSet result = statement.executeQuery();

        ChatMessage message = new ChatMessage();

        if(result.next()){
            message.setCreated(new Timestamp(result.getDate("created").getTime()).toLocalDateTime());
            message.setUpdated(new Timestamp(result.getDate("updated").getTime()).toLocalDateTime());
            message.setDeleted(result.getBoolean("deleted"));
            message.setUserId(result.getInt("userId"));
            message.setFlagged(result.getBoolean("flagged"));
            message.setGroupId(result.getInt("groupId"));
            message.setMessage(result.getString("message"));
            message.setMessageId(result.getInt("messageId"));
        }
        result.close();
        disconnect();

        return message;
    }

    public ChatMessage getChatMessageByUserAndGroup(int userId, int groupId) throws SQLException{
        String sql = "SELECT * FROM messages where userId = ? and groupId = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, userId);
        statement.setInt(2, groupId);

        ResultSet result = statement.executeQuery();

        ChatMessage message = new ChatMessage();

        if(result.next()){
            message.setCreated(new Timestamp(result.getDate("created").getTime()).toLocalDateTime());
            message.setUpdated(new Timestamp(result.getDate("updated").getTime()).toLocalDateTime());
            message.setDeleted(result.getBoolean("deleted"));
            message.setUserId(result.getInt("userId"));
            message.setFlagged(result.getBoolean("flagged"));
            message.setGroupId(result.getInt("groupId"));
            message.setMessage(result.getString("message"));
            message.setMessageId(result.getInt("messageId"));
        }
        result.close();
        disconnect();

        return message;
    }

    public List<ChatMessage> getChatMessagesByGroupId(int groupId) throws SQLException {
        String sql = "SELECT * from messages where groupId = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, groupId);

        ResultSet result = statement.executeQuery();

        List<ChatMessage> chatMessages = new ArrayList<ChatMessage>();
        int count = 0;
        while (result.next()) {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setCreated(new Timestamp(result.getDate("created").getTime()).toLocalDateTime());
            chatMessage.setUpdated(new Timestamp(result.getDate("updated").getTime()).toLocalDateTime());
            chatMessage.setDeleted(result.getBoolean("deleted"));
            chatMessage.setUserId(result.getInt("userId"));
            chatMessage.setFlagged(result.getBoolean("flagged"));
            chatMessage.setGroupId(result.getInt("groupId"));
            chatMessage.setMessage(result.getString("message"));
            chatMessages.add(chatMessage);
            count++;
        }

        if(count == 0){
            throw new SQLException("No Messages found for Group.");
        }
        result.close();
        disconnect();
        return chatMessages;
    }

    public List<ChatMessage> getChatMessagesByUserId(int userId) throws SQLException {
        String sql = "SELECT * from messages where userId = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, userId);

        ResultSet result = statement.executeQuery();

        List<ChatMessage> chatMessages = new ArrayList<ChatMessage>();
        int count = 0;
        while (result.next()) {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setCreated(new Timestamp(result.getDate("created").getTime()).toLocalDateTime());
            chatMessage.setUpdated(new Timestamp(result.getDate("updated").getTime()).toLocalDateTime());
            chatMessage.setDeleted(result.getBoolean("deleted"));
            chatMessage.setUserId(result.getInt("userId"));
            chatMessage.setFlagged(result.getBoolean("flagged"));
            chatMessage.setGroupId(result.getInt("groupId"));
            chatMessage.setMessage(result.getString("message"));
            chatMessages.add(chatMessage);
            count++;
        }

        if (count == 0) {
            throw new SQLException("No Messages found for User.");
        }

        result.close();
        disconnect();

        return chatMessages;
    }
}

package daos;

import models.ChatMessage;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatMessageDAO extends DBConnect {

    public int insertChatMessage(ChatMessage chatMessage) throws SQLException {

        String sql = "INSERT INTO messages (created, updated, deleted, userId, flagged, groupId, message) VALUES (now(), "
                + "now(), false, ?, false, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, chatMessage.getUserId());
        statement.setInt(2, chatMessage.getGroupId());
        statement.setString(3, chatMessage.getMessage());

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

    public ChatMessage getChatMessageById(int chatMessageId) throws SQLException{
        String sql = "SELECT * FROM messages where messageId = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, chatMessageId);

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
        String sql = "SELECT messages.*," +
                     "users.userId, " +
                     "users.created as userCreated, " +
                     "users.updated as userUpdated, " +
                     "users.deleted as userDeleted, " +
                     "users.firstName, " +
                     "users.lastName, " +
                     "users.email, " +
                     "users.educationLevel, "  +
                     "users.universityId " +
                     "FROM messages " +
                     "JOIN users on users.userId = messages.userId " +
                     "WHERE messages.groupId = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, groupId);

        ResultSet result = statement.executeQuery();

        List<ChatMessage> chatMessages = new ArrayList<ChatMessage>();
        int count = 0;
        while (result.next()) {
            ChatMessage chatMessage = new ChatMessage();
            User user = new User();
            chatMessage.setCreated(new Timestamp(result.getDate("created").getTime()).toLocalDateTime());
            chatMessage.setUpdated(new Timestamp(result.getDate("updated").getTime()).toLocalDateTime());
            chatMessage.setDeleted(result.getBoolean("deleted"));
            chatMessage.setUserId(result.getInt("userId"));
            chatMessage.setFlagged(result.getBoolean("flagged"));
            chatMessage.setGroupId(result.getInt("groupId"));
            chatMessage.setMessage(result.getString("message"));
            chatMessage.setMessageId(result.getInt("messageId"));

            user.setEducationLevel(result.getInt("educationLevel"));
            user.setUniversityId((result.getInt("universityId")));
            user.setCreated(new Timestamp(result.getDate("userCreated").getTime()).toLocalDateTime());
            user.setUpdated(new Timestamp(result.getDate("userUpdated").getTime()).toLocalDateTime());
            user.setDeleted(result.getBoolean("userDeleted"));
            user.setFirstName(result.getString("firstName"));
            user.setLastName(result.getString("lastName"));
            user.setEmail(result.getString("email"));
            user.setUserId(result.getInt("userId"));

            chatMessage.setUser(user);
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
            chatMessage.setMessageId(result.getInt("messageId"));
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

package daos;

import models.WebSocketConnection;

import java.sql.*;

public class WebSocketDAO extends DBConnect {

    public String insertWebsocketConnection(WebSocketConnection websocket) throws SQLException {
        String sql = "INSERT INTO websocketConnections (connectionId, created, updated, deleted, groupId, userId) " +
                    " VALUES (?, now(), now(), false, ?, ?);";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1,websocket.getConnectionId());
        statement.setInt(2, websocket.getGroupId());
        statement.setInt(3, websocket.getUserId());

        System.out.println(sql);

        boolean rowInserted = statement.executeUpdate() > 0;

        statement.close();
        disconnect();
        return websocket.getConnectionId();
    }

    public String deleteWebsocketConnection(WebSocketConnection websocket) throws SQLException {
        String sql = "UPDATE websocketConnections set deleted = true, updated = now() where " +
                "connectionId = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, websocket.getConnectionId());

        System.out.println(sql);

        boolean rowInserted = statement.executeUpdate() > 0;

        statement.close();
        disconnect();
        return websocket.getConnectionId();
    }
}

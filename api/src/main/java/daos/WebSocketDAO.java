package daos;

import models.WebSocketConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WebSocketDAO extends DBConnect {

    public String insertWebsocketConnection(WebSocketConnection websocket) throws SQLException {
        String sql = "INSERT INTO websocketConnections (connectionId, created, updated, deleted, groupId, userId) " +
                    " VALUES (?, now(), now(), false, ?, ?);";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1,websocket.getConnectionId());
        statement.setInt(2, websocket.getGroupId());
        statement.setInt(3, websocket.getUserId());

        boolean rowInserted = statement.executeUpdate() > 0;

        statement.close();
        disconnect();
        return websocket.getConnectionId();
    }

    public WebSocketConnection updateWebSocketConnection(WebSocketConnection webSocket) throws SQLException {
        String sql = "UPDATE websocketConnections set updated = now(), deleted = ?, groupId = ?, userId = ? " +
                     "WHERE connectionId = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setBoolean(1, webSocket.isDeleted());
        statement.setInt(2, webSocket.getGroupId());
        statement.setInt(3, webSocket.getUserId());
        statement.setString(4, webSocket.getConnectionId());

        boolean rowInserted = statement.executeUpdate() > 0;

        statement.close();
        disconnect();

        return webSocket;
    }

    public String deleteWebsocketConnection(WebSocketConnection websocket) throws SQLException {
        String sql = "UPDATE websocketConnections set deleted = true, updated = now() where " +
                "connectionId = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, websocket.getConnectionId());

        boolean rowInserted = statement.executeUpdate() > 0;

        statement.close();
        disconnect();
        return websocket.getConnectionId();
    }

    public WebSocketConnection getWebSocketConnectionById(String connectionId) throws SQLException {
        String sql = "SELECT * from websocketConnections where connectionId = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, connectionId);

        ResultSet result = statement.executeQuery();

        WebSocketConnection connection = new WebSocketConnection();
        if (result.next()) {
            connection.setConnectionId(result.getString("connectionId"));
            connection.setCreated(new Timestamp(result.getDate("created").getTime()).toLocalDateTime());
            connection.setUpdated(new Timestamp(result.getDate("updated").getTime()).toLocalDateTime());
            connection.setDeleted(result.getBoolean("deleted"));
            connection.setGroupId(result.getInt("groupId"));
            connection.setUserId(result.getInt("userId"));
        } else {
            throw new SQLException("No connection found with the id  " + connectionId);
        }
        result.close();
        disconnect();

        return connection;
    }

    public List<WebSocketConnection> getOpenWebSocketConnectionsByGroupId(int groupId) throws SQLException {
        String sql = "SELECT * from websocketConnections where groupId = ? and deleted = false";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, groupId);

        ResultSet result = statement.executeQuery();

        List<WebSocketConnection> connections = new ArrayList<>();
        int count = 0;
        while (result.next()) {
            WebSocketConnection connection = new WebSocketConnection();
            connection.setConnectionId(result.getString("connectionId"));
            connection.setCreated(new Timestamp(result.getDate("created").getTime()).toLocalDateTime());
            connection.setUpdated(new Timestamp(result.getDate("updated").getTime()).toLocalDateTime());
            connection.setDeleted(result.getBoolean("deleted"));
            connection.setGroupId(result.getInt("groupId"));
            connection.setUserId(result.getInt("userId"));
            connections.add(connection);
            count++;
        }

        if (count == 0) {
            throw new SQLException("No available connections with the groupId  " + groupId);
        }

        result.close();
        disconnect();

        return connections;
    }
}

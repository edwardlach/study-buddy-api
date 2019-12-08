package services;

import daos.WebSocketDAO;
import dtos.WebSocketHeadersDTO;
import models.WebSocketConnection;

import java.sql.SQLException;
import java.util.List;

public class WebSocketService {

    private WebSocketDAO websocketDAO = new WebSocketDAO();

    public WebSocketConnection saveWebsocketConnection(WebSocketConnection websocket) throws SQLException {
        String connectionId = websocketDAO.insertWebsocketConnection(websocket);
        return websocketDAO.getWebSocketConnectionById(connectionId);
    }

    public WebSocketConnection deleteWebsocketConnection(WebSocketConnection websocket) throws SQLException {
        String connectionId = websocketDAO.deleteWebsocketConnection(websocket);
        return websocketDAO.getWebSocketConnectionById(connectionId);
    }

    public WebSocketConnection getWebSocketByConnectionId(String connectionId) throws SQLException {
        return websocketDAO.getWebSocketConnectionById(connectionId);
    }

    public WebSocketConnection updateWebSocketConnection(int userId, int groupId, String connectionId) throws SQLException {
        WebSocketConnection ws = getWebSocketByConnectionId(connectionId);
        ws.setGroupId(groupId);
        ws.setUserId(userId);
        return websocketDAO.updateWebSocketConnection(ws);
    }

    public List<WebSocketConnection> getOpenGroupWebSocketConnections(int groupId) throws SQLException {
        return websocketDAO.getOpenWebSocketConnectionsByGroupId(groupId);
    }

}

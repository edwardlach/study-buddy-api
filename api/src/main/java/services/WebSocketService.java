package services;

import daos.WebSocketDAO;
import models.WebSocketConnection;

import java.sql.SQLException;

public class WebSocketService {

    private WebSocketDAO websocketDAO = new WebSocketDAO();

    public String saveWebsocketConnection(WebSocketConnection websocket) throws SQLException {
        return websocketDAO.insertWebsocketConnection(websocket);
    }

    public String deleteWebsocketConnection(WebSocketConnection websocket) throws SQLException {
        return websocketDAO.deleteWebsocketConnection(websocket);
    }

}

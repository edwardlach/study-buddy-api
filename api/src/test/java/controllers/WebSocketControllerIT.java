package controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import dtos.ChatMessageDTO;
import dtos.ResponseDTO;
import dtos.WebSocketDTO;
import org.glassfish.tyrus.client.ClientManager;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.LambdaMock;
import utils.PayloadBuilder;
import utils.RandomGen;

import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static constants.ApiRequestMappings.*;


public class WebSocketControllerIT {

    private static ObjectMapper mapper;
    private static String message;
    private static String server;
    private static int userId = 40;
    private static int groupId = 24;

    @BeforeClass
    public static void setUp() {
        mapper = new ObjectMapper();
        server = "wss://j1g49nfi28.execute-api.us-east-1.amazonaws.com/dev";
        message = RandomGen.getRandomMessage();
    }

    @Test
    public void thatAWebSocketConnectionIsSaved() throws IOException {
        String connectionId = createConnection();
        deleteConnection(connectionId);
    }

    @Test
    public void thatAWeSocketConnectionIsUpdatedWithMessageIdentity() throws IOException {
        String connectionId = createConnection();
        Map<String, String> body = new HashMap<>();
        body.put("action", IDENTIFY);
        body.put("userId", Integer.toString(userId));
        body.put("groupId", Integer.toString(groupId));
        PayloadBuilder payload = new PayloadBuilder(
                connectionId,
                IDENTIFY,
                Optional.of(body));
        ResponseDTO response = LambdaMock.invoke(payload);
        WebSocketDTO webSocket = mapper.readValue(response.getBody(), WebSocketDTO.class);
        assertEquals(200, response.getStatusCode());
        assertEquals(connectionId, webSocket.getConnectionId());
        assertEquals(userId, webSocket.getUserId());
        assertEquals(groupId, webSocket.getGroupId());
        deleteConnection(connectionId);
    }

    @Test
    public void thatAWebSocketCreatesANewMessage() throws IOException {
        String connectionId = createConnection();
        Map<String, String> body = new HashMap<>();
        body.put("action", SEND_MESSAGE);
        body.put("message", message);
        body.put("userId", Integer.toString(userId));
        body.put("groupId", Integer.toString(groupId));
        PayloadBuilder payload = new PayloadBuilder(
            connectionId,
            SEND_MESSAGE,
            Optional.of(body));
        ResponseDTO response = LambdaMock.invoke(payload);
        ChatMessageDTO chatMessage = mapper.readValue(response.getBody(), ChatMessageDTO.class);
        assertEquals(200, response.getStatusCode());
        assertEquals(message, chatMessage.getMessage());
        deleteConnection(connectionId);
    }

    @Test
    public void thatAWebSocketConnectionIsDeleted() throws IOException {
        String connectionId = createConnection();
        deleteConnection(connectionId);
    }


    /*************************** Helpers ***************************/

    private String createConnection() throws IOException {
        String connectionId = RandomGen.getRandomConnectionId();
        PayloadBuilder payload = new PayloadBuilder(
                connectionId,
                CONNECT,
                Optional.empty());
        ResponseDTO response = LambdaMock.invoke(payload);
        WebSocketDTO webSocket = mapper.readValue(response.getBody(), WebSocketDTO.class);
        assertEquals(200, response.getStatusCode());
        assertEquals(connectionId, webSocket.getConnectionId());
        assertTrue(!webSocket.isDeleted());
        return connectionId;
    }

    private void deleteConnection(String connectionId) throws IOException {
        PayloadBuilder payload = new PayloadBuilder(
                connectionId,
                DISCONNECT,
                Optional.empty());
        ResponseDTO response = LambdaMock.invoke(payload);
        WebSocketDTO webSocket = mapper.readValue(response.getBody(), WebSocketDTO.class);
        assertEquals(200, response.getStatusCode());
        assertEquals(connectionId, webSocket.getConnectionId());
        assertTrue(webSocket.isDeleted());
    }
}

package controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import dtos.ChatMessageDTO;
import dtos.ResponseDTO;
import dtos.WebSocketDTO;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.LambdaMock;
import utils.PayloadBuilder;
import utils.RandomGen;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WebSocketControllerIT {

    private static ObjectMapper mapper;
    private static String connectionId;
    private static String message;
    private static Map<String, String> body = new HashMap<>();
    private static Map<String, String> headers = new HashMap<>();

    @BeforeClass
    public static void setUp() {
        mapper = new ObjectMapper();
        connectionId = RandomGen.getRandomConnectionId();
        message = RandomGen.getRandomMessage();
        body.put("action", "sendmessage");
        body.put("message", message);
        body.put("userId", "40");
        body.put("groupId", "24");
        headers.put("userId", "40");
        headers.put("groupId", "24");
    }

    @Test
    public void thatAWebSocketConnectionIsSaved() throws IOException {
        PayloadBuilder payload = new PayloadBuilder(
            connectionId,
            "$connect",
            Optional.empty(),
            Optional.of(headers));
        ResponseDTO response = LambdaMock.invoke(payload);
        WebSocketDTO webSocket = mapper.readValue(response.getBody(), WebSocketDTO.class);
        assertEquals(200, response.getStatusCode());
        assertEquals(connectionId, webSocket.getConnectionId());
        assertTrue(!webSocket.isDeleted());
    }

    @Test
    public void thatAWebSocketCreatesANewMessage() throws IOException {
        PayloadBuilder payload = new PayloadBuilder(
            connectionId,
            "sendmessage",
            Optional.of(body),
            Optional.empty());
        ResponseDTO response = LambdaMock.invoke(payload);
        ChatMessageDTO chatMessage = mapper.readValue(response.getBody(), ChatMessageDTO.class);
        assertEquals(200, response.getStatusCode());
        assertEquals(message, chatMessage.getMessage());
    }

    @Test
    public void thatAWebSocketConnectionIsDeleted() throws IOException {
        PayloadBuilder payload = new PayloadBuilder(
                connectionId,
                "$disconnect",
                Optional.empty(),
                Optional.empty());
        ResponseDTO response = LambdaMock.invoke(payload);
        WebSocketDTO webSocket = mapper.readValue(response.getBody(), WebSocketDTO.class);
        assertEquals(200, response.getStatusCode());
        assertEquals(connectionId, webSocket.getConnectionId());
        assertTrue(webSocket.isDeleted());
    }

}

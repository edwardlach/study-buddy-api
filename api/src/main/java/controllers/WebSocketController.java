package controllers;

import com.amazonaws.http.HttpMethodName;
import dtos.ChatMessageDTO;
import dtos.RequestContextDTO;
import dtos.RequestDTO;
import dtos.WebSocketDTO;
import models.ChatMessage;
import models.WebSocketConnection;
import org.apache.http.client.methods.CloseableHttpResponse;
import services.ChatMessageService;
import services.WebSocketService;
import utils.AwsRequest;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;


import static constants.ApiRequestMappings.*;

public class WebSocketController extends AbstractController {

    private WebSocketService webSocketService = new WebSocketService();
    private ChatMessageService chatMessageService = new ChatMessageService();

    public WebSocketController(RequestDTO request) throws IOException, SQLException {
         routeRequest(request);
    }

    @Override
    void routeRequest(RequestDTO request) throws SQLException, IOException {
        RequestContextDTO requestContext = request.getRequestContext();
        WebSocketDTO webSocket = new WebSocketDTO(requestContext);
        System.out.println("Route Key: " + request.getRequestContext().getRouteKey());
        switch(request.getRequestContext().getRouteKey()) {
            case CONNECT:
                setResponseBody(saveWebSocketConnection(webSocket));
                break;
            case DISCONNECT:
                setResponseBody(deleteWebSocketConnection(webSocket));
                break;
            case SEND_MESSAGE:
                ChatMessageDTO chatMessageDTO = stringToDTO(request.getBody(), ChatMessageDTO.class);
                System.out.println(ChatMessageDTO.toString(chatMessageDTO));
                setResponseBody(createNewChatMessage(chatMessageDTO));
                notifyConnectedClientsOfNewMessage(requestContext, chatMessageDTO);
                break;
        }
    }

    private WebSocketDTO saveWebSocketConnection(WebSocketDTO webSocket) throws SQLException {
        WebSocketConnection websocketConnection = new WebSocketConnection();
        websocketConnection.setConnectionId(webSocket.getConnectionId());
        webSocketService.saveWebsocketConnection(websocketConnection);
        return webSocket;
    }

    private WebSocketDTO deleteWebSocketConnection(WebSocketDTO webSocket) throws SQLException {
        WebSocketConnection websocketConnection = new WebSocketConnection();
        websocketConnection.setConnectionId(webSocket.getConnectionId());
        webSocketService.deleteWebsocketConnection(websocketConnection);
        return webSocket;
    }

    private ChatMessageDTO createNewChatMessage(ChatMessageDTO chatMessageDTO) throws SQLException{
        int chatMessage = chatMessageService.postChatMessage(
                new ChatMessage(
                        chatMessageDTO.getUserId(),
                        chatMessageDTO.getGroupId(),
                        chatMessageDTO.getMessage()));
        ChatMessage newChatMessage = chatMessageService.getChatMessageById(chatMessage);
        return new ChatMessageDTO(newChatMessage);
    }

    private void notifyConnectedClientsOfNewMessage(
            RequestContextDTO requestContext,
            ChatMessageDTO chatMessageDTO) throws IOException {
        URI uri = URI.create("https://" +
                requestContext.getDomainName() + "/" +
                requestContext.getStage() + "/@connections/" +
                requestContext.getConnectionId());

        AwsRequest awsRequest = new AwsRequest(
                HttpMethodName.POST,
                uri,
                ChatMessageDTO.toString(chatMessageDTO)
        );

        CloseableHttpResponse awsResponse = awsRequest.sendRequest();
    }
}

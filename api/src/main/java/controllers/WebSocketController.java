package controllers;

import com.amazonaws.http.HttpMethodName;
import com.fasterxml.jackson.core.JsonProcessingException;
import dtos.*;
import models.ChatMessage;
import models.WebSocketConnection;
import org.apache.http.client.methods.CloseableHttpResponse;
import services.ChatMessageService;
import services.WebSocketService;
import utils.AwsRequest;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;


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
        WebSocketDTO webSocket;
        switch(request.getRequestContext().getRouteKey()) {
            case CONNECT:
                webSocket = new WebSocketDTO(requestContext);
                setResponseBody(saveWebSocketConnection(webSocket));
                break;
            case DISCONNECT:
                webSocket = new WebSocketDTO(requestContext);
                setResponseBody(deleteWebSocketConnection(webSocket));
                break;
            case IDENTIFY:
                MessageIdentityDTO messageIdentity = stringToDTO(request.getBody(), MessageIdentityDTO.class);
                webSocket = new WebSocketDTO(requestContext, messageIdentity);
                setResponseBody(
                        updateWebSocketConnection(
                            webSocket.getUserId(),
                            webSocket.getGroupId(),
                            webSocket.getConnectionId()
                ));
                break;
            case SEND_MESSAGE:
                ChatMessageDTO chatMessageDTO = stringToDTO(request.getBody(), ChatMessageDTO.class);
                ChatMessageDTO newChatMessage = createNewChatMessage(chatMessageDTO);
                setResponseBody(newChatMessage);
                notifyConnectedClientsOfNewMessage(
                        requestContext.getDomainName(),
                        requestContext.getStage(),
                        newChatMessage);
                break;
        }
    }

    private WebSocketDTO saveWebSocketConnection(WebSocketDTO webSocket) throws SQLException {
        WebSocketConnection websocketConnection = new WebSocketConnection();
        websocketConnection.setConnectionId(webSocket.getConnectionId());
        return new WebSocketDTO(webSocketService.saveWebsocketConnection(websocketConnection));
    }

    private WebSocketDTO deleteWebSocketConnection(WebSocketDTO webSocket) throws SQLException {
        WebSocketConnection websocketConnection = new WebSocketConnection();
        websocketConnection.setConnectionId(webSocket.getConnectionId());
        return new WebSocketDTO(webSocketService.deleteWebsocketConnection(websocketConnection));
    }

    private WebSocketDTO updateWebSocketConnection(int userId, int groupId, String connectionId) throws SQLException {
        return new WebSocketDTO((webSocketService.updateWebSocketConnection(userId, groupId, connectionId)));
    }

    private ChatMessageDTO createNewChatMessage(ChatMessageDTO chatMessageDTO) throws SQLException, JsonProcessingException {
        ChatMessage chatMessage = chatMessageService.postChatMessage(
                new ChatMessage(
                        chatMessageDTO.getUserId(),
                        chatMessageDTO.getGroupId(),
                        chatMessageDTO.getMessage()
                )
        );
        return new ChatMessageDTO(chatMessage);
    }

    private void notifyConnectedClientsOfNewMessage(
            String domainName,
            String stage,
            ChatMessageDTO chatMessageDTO) throws IOException, SQLException
    {
        List<WebSocketConnection> connections =
                webSocketService.getOpenGroupWebSocketConnections(chatMessageDTO.getGroupId());

        for (WebSocketConnection connection : connections ) {
            URI uri = URI.create("https://" +
                    domainName + "/" +
                    stage + "/@connections/" +
                    connection.getConnectionId());

            AwsRequest awsRequest = new AwsRequest(
                    HttpMethodName.POST,
                    uri,
                    ChatMessageDTO.toString(chatMessageDTO)
            );

            CloseableHttpResponse response = awsRequest.sendRequest();
            System.out.println(response.toString());
        }
    }

}

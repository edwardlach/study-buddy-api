package dtos;

import models.WebSocketConnection;

import java.time.LocalDateTime;

public class WebSocketDTO extends AbstractDTO {

    private RequestContextDTO requestContext;
    private String connectionId;
    private String created;
    private String updated;
    private boolean deleted;
    private int userId;
    private int groupId;

    public WebSocketDTO(
            String connectionId,
            LocalDateTime created,
            LocalDateTime updated,
            boolean deleted,
            int userId,
            int groupId) {
        this.connectionId = connectionId;
        this.created = created.format(formatter);
        this.updated = updated.format(formatter);
        this.deleted = deleted;
        this.userId = userId;
        this.groupId = groupId;
    }

    public WebSocketDTO(
            RequestContextDTO requestContext,
            MessageIdentityDTO messageIdentity)
    {
        this.requestContext = requestContext;
        this.connectionId = requestContext.getConnectionId();
        this.userId = messageIdentity.getUserId();
        this.groupId = messageIdentity.getGroupId();
    }

    public WebSocketDTO(
            RequestContextDTO requestContext)
    {
        this.requestContext = requestContext;
        this.connectionId = requestContext.getConnectionId();
    }

    public WebSocketDTO(WebSocketConnection connection) {
        this.connectionId = connection.getConnectionId();
        this.created = connection.getCreated().format(formatter);
        this.updated = connection.getUpdated().format(formatter);
        this.deleted = connection.isDeleted();
        this.userId = connection.getUserId();
        this.groupId = connection.getGroupId();
    }

    public WebSocketDTO() {}

    public String getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

}

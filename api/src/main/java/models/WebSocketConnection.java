package models;

import java.time.LocalDateTime;

public class WebSocketConnection {
    private String connectionId;
    private LocalDateTime created;
    private LocalDateTime updated;
    private boolean deleted;
    private int userId;
    private int groupId;

    public WebSocketConnection(
            String connectionId,
            LocalDateTime created,
            LocalDateTime updated,
            boolean deleted,
            int userId,
            int groupId) {
        this.connectionId = connectionId;
        this.created = created;
        this.updated = updated;
        this.deleted = deleted;
        this.userId = userId;
        this.groupId = groupId;
    }

    public WebSocketConnection() {}

    public String getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
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

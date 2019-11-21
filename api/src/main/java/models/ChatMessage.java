package models;

import java.time.LocalDateTime;

public class ChatMessage extends GenericEntity{

    private LocalDateTime created, updated;
    private boolean deleted;
    private int userId;
    private boolean flagged;
    private int groupId;
    private String message;
    private int messageId;

    public ChatMessage(LocalDateTime created, LocalDateTime updated, Boolean deleted, int userId,
                       Boolean flagged, int groupId){
        this.created = created;
        this.updated = updated;
        this.deleted = deleted;
        this.userId = userId;
        this.flagged = flagged;
        this.groupId = groupId;
    }

    public ChatMessage(int messageId, String message, LocalDateTime created, LocalDateTime updated, Boolean deleted, int userId,
                       Boolean flagged, int groupId){
        this.messageId = messageId;
        this.message = message;
        this.created = created;
        this.updated = updated;
        this.deleted = deleted;
        this.userId = userId;
        this.flagged = flagged;
        this.groupId = groupId;
    }

    public ChatMessage(int userId, int groupId){
        this.userId = userId;
        this.groupId = groupId;

    }

    public ChatMessage(){}

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

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }
}

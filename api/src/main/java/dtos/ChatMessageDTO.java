package dtos;

import models.ChatMessage;
import models.User;


public class ChatMessageDTO extends AbstractDTO {
    private String created, updated, message, action;
    private boolean deleted, flagged;
    private int userId, groupId, messageId;
    private UserDTO user;

    public ChatMessageDTO(){}

    public ChatMessageDTO(ChatMessage chatMessage){
        this.created = chatMessage.getCreated().format(formatter);
        this.updated = chatMessage.getUpdated().format(formatter);
        this.deleted = chatMessage.isDeleted();
        this.flagged = chatMessage.isFlagged();
        this.userId = chatMessage.getUserId();
        this.groupId = chatMessage.getGroupId();
        this.messageId = chatMessage.getMessageId();
        this.message = chatMessage.getMessage();
    }

    public ChatMessageDTO(ChatMessage chatMessage, User user){
        this.created = chatMessage.getCreated().format(formatter);
        this.updated = chatMessage.getUpdated().format(formatter);
        this.deleted = chatMessage.isDeleted();
        this.flagged = chatMessage.isFlagged();
        this.userId = chatMessage.getUserId();
        this.groupId = chatMessage.getGroupId();
        this.messageId = chatMessage.getMessageId();
        this.message = chatMessage.getMessage();
        this.user = new UserDTO(user);
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
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

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

}

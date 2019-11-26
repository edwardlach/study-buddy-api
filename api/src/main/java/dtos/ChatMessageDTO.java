package dtos;

import models.GenericEntity;
import models.ChatMessage;
import models.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

public class ChatMessageDTO implements AbstractDTO<ChatMessage, ChatMessageDTO> {
    private String created, updated, message;
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

    @Override
    public ChatMessageDTO apply(ChatMessage chatMessage){
        ChatMessageDTO cmDTO = new ChatMessageDTO();
        cmDTO.created = chatMessage.getCreated().format(formatter);
        cmDTO.updated = chatMessage.getUpdated().format(formatter);
        cmDTO.deleted = chatMessage.isDeleted();
        cmDTO.flagged = chatMessage.isFlagged();
        cmDTO.userId = chatMessage.getUserId();
        cmDTO.groupId = chatMessage.getGroupId();
        cmDTO.messageId = chatMessage.getMessageId();
        cmDTO.message = chatMessage.getMessage();
        return cmDTO;
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
}

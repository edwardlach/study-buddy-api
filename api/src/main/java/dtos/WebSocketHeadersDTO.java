package dtos;

public class WebSocketHeadersDTO {
    private int userId;
    private int groupId;

    public WebSocketHeadersDTO(int userId, int groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    public WebSocketHeadersDTO() {}

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

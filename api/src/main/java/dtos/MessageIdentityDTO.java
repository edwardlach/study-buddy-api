package dtos;

public class MessageIdentityDTO {
    private int userId, groupId;
    private String action;

    public MessageIdentityDTO() {}

    public MessageIdentityDTO(int userId, int groupId, String action) {
        this.userId = userId;
        this.groupId = groupId;
        this.action = action;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}

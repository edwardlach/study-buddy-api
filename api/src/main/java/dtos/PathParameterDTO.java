package dtos;

public class PathParameterDTO {
    String userId;
    String groupId;

    public PathParameterDTO(String userId, String groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    public PathParameterDTO(){}


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

}

package dtos;

public class PathParameterDTO {
    String userId;
    String groupId;
    String groupMembership;

    public PathParameterDTO(
            String userId,
            String groupId,
            String groupMembership)
    {
        this.userId = userId;
        this.groupId = groupId;
        this.groupMembership = groupMembership;
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

    public String getGroupMembership() {
        return groupMembership;
    }

    public void setGroupMembership(String groupMembership) {
        this.groupMembership = groupMembership;
    }
}

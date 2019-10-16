package dtos;

public class PathParameterDTO {
    String userId;

    public PathParameterDTO(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public PathParameterDTO(){}

}

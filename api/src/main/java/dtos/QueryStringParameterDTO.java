package dtos;

public class QueryStringParameterDTO {
    String email;
    public QueryStringParameterDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public QueryStringParameterDTO(){}

}


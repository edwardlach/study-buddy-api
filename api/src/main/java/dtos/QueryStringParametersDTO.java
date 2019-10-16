package dtos;

public class QueryStringParametersDTO {
    String email;

    public QueryStringParametersDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public QueryStringParametersDTO(){}

}


package dtos;

public class QueryStringParametersDTO {
    String email;
    String searchTerm;

    public QueryStringParametersDTO(){}

    public QueryStringParametersDTO(
            String email,
            String searchTerm) {
        this.email = email;
        this.searchTerm = searchTerm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

}


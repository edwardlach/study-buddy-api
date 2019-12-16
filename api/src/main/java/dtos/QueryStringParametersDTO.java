package dtos;

public class QueryStringParametersDTO {
    String email;
    String searchTerm;
    int creator;

    public QueryStringParametersDTO(){}

    public QueryStringParametersDTO(
            String email,
            String searchTerm,
            int creator) {
        this.email = email;
        this.searchTerm = searchTerm;
        this.creator = creator;
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

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }
}


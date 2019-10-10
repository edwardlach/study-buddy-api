package dtos;

public class ResponseDTO {
    int statusCode;
    String body;

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ResponseDTO(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public ResponseDTO() {}
}

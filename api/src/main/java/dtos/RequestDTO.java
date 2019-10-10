package dtos;

public class RequestDTO {
    String resource, httpMethod, body;

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public RequestDTO(String resource, String httpMethod, String body) {
        this.resource = resource;
        this.httpMethod = httpMethod;
        this.body = body;
    }

    public RequestDTO() {}

}

package dtos;

public class RequestDTO {
    String resource;
    String httpMethod;
    String body;
    PathParameterDTO pathParameters;
    QueryStringParametersDTO queryStringParameters;

    public PathParameterDTO getPathParameters() {
        return pathParameters;
    }

    public void setPathParameters(PathParameterDTO pathParameters) {
        this.pathParameters = pathParameters;
    }

    public QueryStringParametersDTO getQueryStringParameters() {
        return queryStringParameters;
    }

    public void setQueryStringParameters(QueryStringParametersDTO queryStringParameters) {
        this.queryStringParameters = queryStringParameters;
    }

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

    public RequestDTO(String resource, String httpMethod, String body, PathParameterDTO pathParameters,
                      QueryStringParametersDTO queryStringParameters) {
        this.resource = resource;
        this.httpMethod = httpMethod;
        this.body = body;
        this.pathParameters = pathParameters;
        this.queryStringParameters = queryStringParameters;
    }

    public RequestDTO() {}

}

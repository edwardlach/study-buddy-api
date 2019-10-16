package dtos;

public class RequestDTO {
    String resource;
    String httpMethod;
    String body;
    PathParameterDTO pathParameters;
    QueryStringParameterDTO queryStringParameter;

    public PathParameterDTO getPathParameters() {
        return pathParameters;
    }

    public void setPathParameters(PathParameterDTO pathParameters) {
        this.pathParameters = pathParameters;
    }

    public QueryStringParameterDTO getQueryStringParameter() {
        return queryStringParameter;
    }

    public void setQueryStringParameter(QueryStringParameterDTO queryStringParameter) {
        this.queryStringParameter = queryStringParameter;
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
                      QueryStringParameterDTO queryStringParameter) {
        this.resource = resource;
        this.httpMethod = httpMethod;
        this.body = body;
        this.pathParameters = pathParameters;
        this.queryStringParameter = queryStringParameter;
    }

    public RequestDTO() {}

}

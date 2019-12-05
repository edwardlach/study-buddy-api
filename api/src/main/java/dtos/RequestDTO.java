package dtos;

public class RequestDTO {
    String resource;
    String httpMethod;
    String body;
    String path;
    WebSocketHeadersDTO headers;
    PathParameterDTO pathParameters;
    QueryStringParametersDTO queryStringParameters;
    RequestContextDTO requestContext;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public RequestContextDTO getRequestContext() {
        return requestContext;
    }

    public void setRequestContext(RequestContextDTO requestContext) {
        this.requestContext = requestContext;
    }

    public WebSocketHeadersDTO getHeaders() {
        return headers;
    }

    public void setHeaders(WebSocketHeadersDTO headers) {
        this.headers = headers;
    }

    public RequestDTO(
            String resource,
            String httpMethod,
            String body,
            String path,
            PathParameterDTO pathParameters,
            RequestContextDTO requestContext,
            QueryStringParametersDTO queryStringParameters,
            WebSocketHeadersDTO headers)
    {
        this.resource = resource;
        this.httpMethod = httpMethod;
        this.body = body;
        this.path = path;
        this.pathParameters = pathParameters;
        this.requestContext = requestContext;
        this.queryStringParameters = queryStringParameters;
        this.headers = headers;
    }

    public RequestDTO() {}

}

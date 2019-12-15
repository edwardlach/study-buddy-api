package utils;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PayloadBuilder {
    private String payload;
    private String resource;
    private String path;
    private String method;
    private Map<String, String> requestContext = new HashMap<>();
    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> body = new HashMap<>();
    private Map<String, String> queryStringParameters = new HashMap<String, String>();
    private Map<String, String> pathParameters = new HashMap<String, String>();


    public PayloadBuilder(Resource resource) {
        buildGenericPayload(resource);
    }

    public PayloadBuilder(Resource resource, String email){
        buildGenericPayload(resource, email);
    }

    public PayloadBuilder(
            String connectionId,
            String routeKey,
            Optional<Map<String, String>> body)
    {
        setRequestContext("connectionId", connectionId);
        setRequestContext("routeKey", routeKey);
        setRequestContext("domainName", "0xj242xk57.execute-api.us-east-2.amazonaws.com");
        setRequestContext("stage", "dev");
        if (body.isPresent()) {
            setBody(body.get());
        }
    }

    private void buildGenericPayload(Resource resource) {
        switch (resource) {
            case GET_USER:
                setResource("/users");
                setPath("/users");
                setMethod("GET");
                setQueryStringParameters("email", "edl5040@psu.edu");
                break;
        }
    }

    private void buildGenericPayload(Resource resource, String email){
        switch (resource) {
            case POST_USER:
                setResource("/users");
                setPath("/users");
                setMethod("POST");
                setBody("firstName","Ickis");
                setBody("lastName","Rossi");
                setBody("email",email);
        }
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequestContext() {
        if (this.body == null) {
            return "";
        } else {
            return mappedValuesToString(this.requestContext, false);
        }
    }

    public void setRequestContext(String key, String value) {
        this.requestContext.put(key, value);
    }

    public String getHeaders() {
        if (this.body == null) {
            return "";
        } else {
            return mappedValuesToString(this.headers, false);
        }
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        if (this.body == null) {
            return "";
        } else {
            return "\"" + mappedValuesToString(body, true) + "\"";
        }
    }

    public void setBody(String key, String value) {
        this.body.put(key, value);
    }

    public void setBody(Map<String, String> body) {
        this.body = body;
    }

    public void setQueryStringParameters(String key, String value) {
        this.queryStringParameters.put(key, value);
    }

    public String getQueryStringParameters() {
        return mappedValuesToString(this.queryStringParameters, false);
    }

    public void setPathParameters(String key, String value) {
        this.pathParameters.put(key, value);
    }

    public String getPathParameters() {
        return mappedValuesToString(this.pathParameters, false);
    }

    public String mappedValuesToString(Map<String, String> values, Boolean escape) {
        String parameterString = "{";
        if(!values.isEmpty()) {
            int parameterSize = values.size();
            int parametersParsed = 0;
            for (Map.Entry<String, String> entry : values.entrySet()) {
                String key = entry.getKey();
                Object val = entry.getValue();
                if (escape) {
                    parameterString += "\\\"" + key + "\\\":\\\"" + val + "\\\"";
                } else {
                    parameterString += "\"" + key + "\":\"" + val + "\"";
                }
                parametersParsed += 1;
                if (parametersParsed < parameterSize) {
                    parameterString += ",";
                }
            }
        }
        parameterString += "}";
        return parameterString;
    }

    public String webSocketRequestToString() {
        return "{"+
                    "\"requestContext\": " + getRequestContext() + "," +
                    "\"body\": " + getBody() +
                "}";
    }

    public String toString() {
        return "{"+
                    "\"resource\": \"" + getResource() + "\"," +
                    "\"path\": \"" + getPath() + "\"," +
                    "\"httpMethod\": \"" + getMethod() + "\"," +
                    "\"queryStringParameters\": " + getQueryStringParameters() + "," +
                    "\"pathParameters\": " + getPathParameters() + "," +
                    "\"requestContext\": " + getRequestContext() + "," +
                    "\"body\": " + getBody() +
                "}";
    }

}

package utils;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class PayloadBuilder {
    private String payload;
    private String resource;
    private String path;
    private String method;
    private String body;
    private Map<String, String> queryStringParameters = new HashMap<String, String>();
    private Map<String, String> pathParameters = new HashMap<String, String>();


    public PayloadBuilder(String resource, String method, String body) {

    }

    public PayloadBuilder(Resource resource) {
        buildGenericPayload(resource);
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

    public String getBody() {
        if (this.body == null) {
            return body;
        } else {
            return "\"" + body + "\"";
        }
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setQueryStringParameters(String key, String value) {
        this.queryStringParameters.put(key, value);
    }

    public String getQueryStringParameters() {
        return mappedValuesToString(this.queryStringParameters);
    }

    public void setPathParameters(String key, String value) {
        this.pathParameters.put(key, value);
    }

    public String getPathParameters() {
        return mappedValuesToString(this.pathParameters);
    }

    public String mappedValuesToString(Map<String, String> values) {
        String parameterString = "{";
        if(!values.isEmpty()) {
            int parameterSize = values.size();
            int parametersParsed = 0;
            for (Map.Entry<String, String> entry : values.entrySet()) {
                String key = entry.getKey();
                Object val = entry.getValue();
                parameterString += "\"" + key + "\":\"" + val + "\"";
                parametersParsed += 1;
                if (parametersParsed < parameterSize) {
                    parameterString += ",";
                }
            }
        }
        parameterString += "}";
        return parameterString;
    }

    public String toString() {
        return "{"+
                    "\"resource\": \"" + getResource() + "\"," +
                    "\"path\": \"" + getPath() + "\"," +
                    "\"httpMethod\": \"" + getMethod() + "\"," +
                    "\"queryStringParameters\": " + getQueryStringParameters() + "," +
                    "\"pathParameters\": " + getPathParameters() + "," +
                    "\"body\": " + getBody() +
                "}";
    }
}

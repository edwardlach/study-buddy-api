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
                "\"headers\": {" +
                    "\"content-type\": \"application/json\"," +
                    "\"Host\": \"4b7jtysje3.execute-api.us-east-1.amazonaws.com\"," +
                    "\"X-Amzn-Trace-Id\": \"Root=1-5ddaa6a6-95e88235218551815d5c84bf\"," +
                    "\"X-Forwarded-For\": \"173.75.23.67\"," +
                    "\"X-Forwarded-Port\": \"443\"," +
                    "\"X-Forwarded-Proto\": \"https\"" +
                "}," +
                "\"multiValueHeaders\": {" +
                    "\"content-type\": [" +
                        "\"application/json\"" +
                    "]," +
                    "\"Host\": [" +
                        "\"4b7jtysje3.execute-api.us-east-1.amazonaws.com\"" +
                    "]," +
                    "\"X-Amzn-Trace-Id\": [" +
                        "\"Root=1-5ddaa6a6-95e88235218551815d5c84bf\"" +
                    "]," +
                    "\"X-Forwarded-For\": [" +
                        "\"173.75.23.67\"" +
                    "]," +
                    "\"X-Forwarded-Port\": [" +
                       "\"443\"" +
                     "]," +
                    "\"X-Forwarded-Proto\": [" +
                        "\"https\"" +
                    "]" +
                "}," +
                "\"queryStringParameters\": " + getQueryStringParameters() + "," +
                "\"multiValueQueryStringParameters\": " + getPathParameters() + "," +
                "\"pathParameters\": " + getPathParameters() + "," +
                "\"stageVariables\": null, " +
                "\"requestContext\": {" +
                    "\"resourceId\": \"iab5et\"," +
                    "\"resourcePath\": \"" + getResource() + "\"," +
                    "\"httpMethod\": \"" + getMethod() + "\"," +
                    "\"extendedRequestId\": \"Dq76BGNgIAMFyjQ=\"," +
                    "\"requestTime\": \"24/Nov/2019:15:49:58 +0000\"," +
                    "\"path\": \"" + getPath() + "\"," +
                    "\"accountId\": \"001952273251\"," +
                    "\"protocol\": \"HTTP/1.1\"," +
                    "\"stage\": \"develop\"," +
                    "\"domainPrefix\": \"4b7jtysje3\"," +
                    "\"requestTimeEpoch\": 1574610598423," +
                    "\"requestId\": \"03718b9b-2b9f-4736-80c2-2cad66fd30b8\"," +
                    "\"identity\": {" +
                        "\"cognitoIdentityPoolId\": null," +
                        "\"accountId\": null," +
                        "\"cognitoIdentityId\": null," +
                        "\"caller\": null," +
                        "\"sourceIp\": \"173.75.23.67\"," +
                        "\"principalOrgId\": null," +
                        "\"accessKey\": null," +
                        "\"cognitoAuthenticationType\": null," +
                        "\"cognitoAuthenticationProvider\": null," +
                        "\"userArn\": null," +
                        "\"userAgent\": null," +
                        "\"user\": null" +
                    "}," +
                    "\"domainName\": \"4b7jtysje3.execute-api.us-east-1.amazonaws.com\"," +
                    "\"apiId\": \"4b7jtysje3\"" +
                    "}," +
                "\"body\": " + getBody() + "," +
                    "\"isBase64Encoded\": false" +
                "}";
    }
}

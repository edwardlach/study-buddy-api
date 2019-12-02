package utils;

import com.amazonaws.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;


public class AwsRequest {
    private String key = System.getenv("AWS_KEY");
    private String secret = System.getenv("AWS_SECRET");
    private String regionName = "us-east-1";
    private String serviceName = "execute-api";
    private String contentType = "application/json";
    private String payload;
    private HttpMethodName requestMethod;
    private URI uri;

    public AwsRequest(
            HttpMethodName requestMethod,
            URI uri,
            String payload) {
        this.requestMethod = requestMethod;
        this.uri = uri;
        this.payload = payload;
    }

    public CloseableHttpResponse sendRequest() throws IOException {
        AWS4SignerForAuthorizationHeader awsSigner = new AWS4SignerForAuthorizationHeader(
                this.uri.toURL(),
                this.requestMethod.name(),
                this.serviceName,
                this.regionName
        );
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("content-type", this.contentType);
        String hashedBody = BinaryUtils.toHex(AWS4SignerBase.hash(this.payload));
        String awsSignedAuth = awsSigner.computeSignature(
                headers,
                null,
                hashedBody,
                this.key,
                this.secret
        );
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(this.uri);
        post.setEntity(new StringEntity(this.payload));
        post.setHeader("content-type", this.contentType);
        post.setHeader("host", this.uri.getHost());
        post.setHeader("x-amz-date", awsSigner.getDateTimeStamp());
        post.setHeader("Authorization", awsSignedAuth);
        return httpClient.execute(post);
    }

}

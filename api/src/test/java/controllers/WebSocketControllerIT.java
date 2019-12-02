package controllers;

import org.apache.http.entity.StringEntity;
import org.junit.Test;
import static org.junit.Assert.*;

import utils.AwsRequest;
import utils.Hmac;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class WebSocketControllerIT {

    @Test
    public void thatThePayloadIsHashedCorrectly() throws UnsupportedEncodingException, NoSuchAlgorithmException {
//        String payload = "{\"action\":\"sendmessage\", \"message\":\"Hello World!\"}";
//        String escapedPayload = "\"{\\\"action\\\":\\\"sendmessage\\\", \\\"message\\\":\\\"Hello World!\\\"}\"";
//        String escaped = AwsRequest.escapePayload(payload);
//        String hashedPayload = Hmac.getSha256Hash(payload);
//        assertEquals(escapedPayload, escaped);
//        assertEquals("188d10c66bff6dfd0f6c5132c22d2cf7e054bd7bc23e1dbecb47e138c470a366", hashedPayload);
    }

    @Test
    public void thatTheCanonicalRequestIsHashedCorrectly() throws UnsupportedEncodingException, NoSuchAlgorithmException {
//        String canonicalRequest =
//                "POST\n" + "\n" +
//                "/dev/%40connections/EAnTydnAoAMCL1A%3D\n"+
//                "content-type:application/json\n" +
//                "host:j1g49nfi28.execute-api.us-east-1.amazonaws.com\n" +
//                "x-amz-date:20191201T054125Z\n" +
//                "content-type;host;x-amz-date\n" +
//                "188d10c66bff6dfd0f6c5132c22d2cf7e054bd7bc23e1dbecb47e138c470a366";
//        System.out.println(canonicalRequest);
//        String hashedRequest = Hmac.getSha256Hash(canonicalRequest);
//        assertEquals("ad696766e08e8ab0a26c8242853a29fb10034def8fff581e26a0cb7799120bdc", hashedRequest);
    }



}

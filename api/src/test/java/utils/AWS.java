package utils;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;

public class AWS {

    private static String AWS_KEY=System.getenv("AWS_KEY");
    private static String AWS_SECRET=System.getenv("AWS_SECRET");
    private static BasicAWSCredentials awsCredentials = new BasicAWSCredentials(AWS_KEY, AWS_SECRET);
    private static AWSLambda awsLambda = AWSLambdaClientBuilder.standard()
                                    .withRegion(Regions.US_EAST_1)
                                    .withCredentials((new AWSStaticCredentialsProvider(awsCredentials)))
                                    .build();

    public AWS() {}


    public static InvokeResult invoke(String functionName, String payload) {
        InvokeRequest request = new InvokeRequest()
                                    .withFunctionName(functionName)
                                    .withPayload(payload);
        InvokeResult result = awsLambda.invoke(request);
        return result;
    }



}

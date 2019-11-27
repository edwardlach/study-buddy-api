package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomGen {


    public static String getRandomEmail(){
        String generatedString = RandomStringUtils.random(10, true, false);
        String email = generatedString + "@psu.edu";
        return email;
    }
}

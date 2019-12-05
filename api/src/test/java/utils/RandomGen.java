package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomGen {

    public static String getRandomEmail() {
        String generatedString = RandomStringUtils.random(10, true, false);
        String email = generatedString + "@psu.edu";
        return email;
    }

    public static String getRandomConnectionId() {
        String generatedString = RandomStringUtils.random(15, true, false);
        return generatedString + "=";
    }

    public static String getRandomMessage() {
        String message = "";
        int max = 10;
        int min = 3;
        for (int i = 0; i < 20; i++) {
            int wordLength = (int)((Math.random() * ((max - min) + 1)) + min);
            message += RandomStringUtils.random(wordLength, true, false);
            if (i < 19) {
                message += " ";
            }
        }
        return message;
    }

}

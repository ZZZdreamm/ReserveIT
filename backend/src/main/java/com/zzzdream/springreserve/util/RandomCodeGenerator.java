package com.zzzdream.springreserve.util;

import java.util.Random;

public class RandomCodeGenerator {
    public static String generateRandomCode(int length) {
        // Define the character set from which to generate the code
        String charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();

        // Generate 'length' random characters
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(charset.length());
            code.append(charset.charAt(randomIndex));
        }

        return code.toString();
    }
}

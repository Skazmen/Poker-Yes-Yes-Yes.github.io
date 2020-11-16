package Client;

import java.util.*;

public class Helper {

    public static String getRandomString(int n) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        int length = n;

        for(int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        String randomString = sb.toString();
        return randomString;
    }

    public static int getRandom(int n) {
        Random random = new Random();

        return random.nextInt(n);
    }
}

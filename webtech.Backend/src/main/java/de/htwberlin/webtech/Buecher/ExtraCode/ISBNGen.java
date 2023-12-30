package de.htwberlin.webtech.Buecher.ExtraCode;

import java.util.Random;

public class ISBNGen {
    public static String generateISBN() {
        // Format: 978-x-xx-xxxxxx-x
        StringBuilder isbnBuilder = new StringBuilder("978-");

        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            int digit = random.nextInt(10);
            isbnBuilder.append(digit);
        }

        isbnBuilder.append("-");

        for (int i = 0; i < 2; i++) {
            int digit = random.nextInt(10);
            isbnBuilder.append(digit);
        }

        isbnBuilder.append("-");

        for (int i = 0; i < 6; i++) {
            int digit = random.nextInt(10);
            isbnBuilder.append(digit);
        }

        int checkDigit = calculateCheckDigit(isbnBuilder.toString());
        isbnBuilder.append(checkDigit);

        return isbnBuilder.toString();
    }

    private static int calculateCheckDigit(String isbn) {
        int sum = 0;
        for (int i = 0; i < isbn.length(); i++) {
            int digit = Character.getNumericValue(isbn.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }
        int checkDigit = (10 - (sum % 10)) % 10;
        return checkDigit;
    }
}

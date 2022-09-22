package com.ua.javarush.cryptoanalizer;


import java.util.ArrayList;

import java.util.List;


public class BruteForce {

    public static List<String> bruteForceDecode(String encryptedText) {
        String decodedText;
        List<String> encodedTextsList = new ArrayList<>();

        for (int key = 0; key < CaesarEncryptor.cryptoSymbols.length; key++) {
            decodedText = CaesarEncryptor.decrypt(encryptedText, key);
            char mostUsed = mostCommonCharacter(decodedText);
            if (mostUsed == ' ') {
                encodedTextsList.add(decodedText);
            }
        }

        return encodedTextsList;
    }

    private static char mostCommonCharacter(String decodedText) {
        char mostCommonChar;
        String mostUsedLetter="";
        int count = 0;


        String[] array = decodedText.split(" ");

        for (int i = 0; i < array.length; i++) {
            int tempCount = 0;

            for (int j = 0; j < array.length; j++) {
                if (array[i].equals(array[j])) {
                    tempCount++;
                }
                if (tempCount > count) {
                    count = tempCount;
                    mostUsedLetter = array[i];
                }
            }
        }
        mostCommonChar = mostUsedLetter.charAt(0);
        return mostCommonChar;
    }

}

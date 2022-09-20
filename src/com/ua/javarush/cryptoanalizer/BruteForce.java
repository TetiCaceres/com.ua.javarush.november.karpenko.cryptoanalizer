package com.ua.javarush.cryptoanalizer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BruteForce {


    public static List<String> bruteForceDecode(String encryptedText) {

        List<String> encodedTextsList = new ArrayList<>();
        String decodedText;
        for (int key = 0; key < CaesarEncryptor.cryptoSymbols.length; key++) {
            decodedText = CaesarEncryptor.decrypt(encryptedText, key);
            char mostUsed = mostCommonCharacter(decodedText);
            if (mostUsed == ' ') {
                encodedTextsList.add(decodedText);
            }
        }
        System.out.println(encodedTextsList);

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

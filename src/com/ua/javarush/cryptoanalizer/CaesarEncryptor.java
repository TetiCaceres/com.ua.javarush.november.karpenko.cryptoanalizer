package com.ua.javarush.cryptoanalizer;

import java.util.HashMap;

public class CaesarEncryptor {

    public static String symbols = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZzАаБбВвГгҐґДдЕеЄєЖжЗзИиІіЇїЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщЬьЮюЯяЪъэЭыЫёЁ., \"1-!?";

    public static char[] cryptoSymbols = symbols.toCharArray();


    public static String encrypt(String text, int key) {
        char[] userText = text.toCharArray();
        char[] encodedText = new char[text.length()];
        key = key % cryptoSymbols.length;

        for (int i = 0; i < userText.length; i++) {
            for (int j = 0; j < cryptoSymbols.length; j++) {
                if (userText[i] == cryptoSymbols[j] & (j + key) < symbols.length()) {
                    encodedText[i] = cryptoSymbols[j + key];
                } else if (userText[i] == cryptoSymbols[j] & (j + key) > cryptoSymbols.length) {
                    encodedText[i] = cryptoSymbols[j + key - cryptoSymbols.length];
                }
            }
        }
        for (int i = 0; i < encodedText.length; i++) {
            if (encodedText[i] == 0) {
                encodedText[i] = userText[i];
            }
        }


        return new String(encodedText);

    }

    public static String decrypt(String text, int key) {

        char[] encodedText = text.toCharArray();
        char[] decodedText = new char[text.length()];
        key = key % cryptoSymbols.length;
        for (int i = 0; i < encodedText.length; i++) {
            for (int j = 0; j < cryptoSymbols.length; j++) {
                if (encodedText[i] == cryptoSymbols[j] & (j - key) >= 0) {
                    decodedText[i] = cryptoSymbols[j - key];
                } else if (encodedText[i] == cryptoSymbols[j] & (j - key) < 0) {
                    decodedText[i] = cryptoSymbols[j - key + cryptoSymbols.length];
                }
            }
        }

        for (int i = 0; i < encodedText.length; i++) {
            if (decodedText[i] == 0) {
                decodedText[i] = encodedText[i];
            }
        }
        return new String(decodedText);
    }
}

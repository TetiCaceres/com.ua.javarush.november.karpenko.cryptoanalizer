package com.ua.javarush.cryptoanalizer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Runner {
    public static void welcomeUser() {
        System.out.println("Input your choose...");
        System.out.println("ENCRYPTION - press \"1\"");
        System.out.println("DECRYPTION - press \"2\"");
        System.out.println("BRUTE FORCE - press \"3\"");
        System.out.println("EXIT - press \"4\"");
        System.out.println("");
        System.out.println("select an action and press \"ENTER\"");
        userSelection();
    }

    public static void userSelection() {
//        Scanner scanner = new Scanner(System.in);

//        welcomeUser();
        boolean isWorking = true;

        while (isWorking) {

            try {
                Scanner console = new Scanner(System.in);
                int choise = console.nextInt();

                if (choise == 1) {
                    caesarEncrypt();
                } else if (choise == 2) {
                    caesarDecrypt();
                } else if (choise == 3) {
                    bruteForce();
                } else if (choise == 4) {
                    isWorking = false;
                } else {
                    System.out.println(" try again...make sure you enter a number from 1 to 4");
                }
            } catch (InputMismatchException e) {
                System.out.println(" try again...make sure you enter a number");
            }
        }

    }


    private static void caesarEncrypt() {
        String userText = getTextFromUser();
        int key = getKeyFromUser();
        String encryptedText = CaesarEncryptor.encrypt(userText, key);

        String pathResultingFile = String.valueOf(createEncryptedFile(encryptedText));
        System.out.println("Path to the file with the encrypted text: \n" + pathResultingFile);
    }

    private static void caesarDecrypt() {
        String userText = getTextFromUser();
        int key = getKeyFromUser();
        String decryptedText = CaesarEncryptor.decrypt(userText, key);
        String pathResultingFile = String.valueOf(createDecryptedFile(decryptedText));
        System.out.println("Path to the file with the decrypted text: \n" + pathResultingFile);
    }


    private static void bruteForce() {
        String userText = getTextFromUser();
        System.out.println("The decryption process is in progress, it may take a couple of minutes.");
        System.out.println();

        String result = String.join(", ", BruteForce.bruteForceDecode(userText));
        String pathResultingFile = String.valueOf(createDecryptedFile(result));
        System.out.println("Path to the file with the decrypted text: \n" + pathResultingFile);

    }


    private static String getTextFromUser() {
        Scanner scanner = new Scanner(System.in);
        String userText = "";
        while (true) {
            System.out.println("Please enter the path to the file: ");
            Path filePath = Path.of(scanner.nextLine());
            if (Files.isRegularFile(filePath)) {
                try {
                   // Files.lines(filePath, StandardCharsets.UTF_8);
                    userText = Files.readString(filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            } else {
                System.out.println("The path does not lead to a file");
            }
        }
        return userText;
    }

    private static int getKeyFromUser() {
        int key = 0;
        System.out.println("Enter key (any integer):");
        try {
            Scanner console = new Scanner(System.in);
            key = console.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("be sure you entered integer, try again");
            key = getKeyFromUser();
        }
        return key;
    }

    private static Path createEncryptedFile(String encryptedText) {
        String usersPath = System.getProperty("user.home") + "/Desktop/";
        Path outputFile;
        File file = new File(usersPath + "EncryptedText" + ".txt");
        int increase = 1;
        while (file.exists()) {
            increase++;
            file = new File(usersPath + "EncryptedText" + increase + ".txt");
        }

        try {

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(encryptedText);
            bw.flush();
            bw.close();

            System.out.println("Ready");

        } catch (IOException ignored) {
        }
        // }
        outputFile = Path.of(String.valueOf(file));
        return outputFile;
    }

    private static Path createDecryptedFile(String decryptedText) {
        String usersPath = System.getProperty("user.home") + "/Desktop/";
        Path outputFile;
        File file = new File(usersPath + "Decrypted" + ".txt");
        int increase = 1;
        while (file.exists()) {
            increase++;
            file = new File(usersPath + "Decrypted" + increase + ".txt");
        }

        try {

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(decryptedText);
            bw.flush();
            bw.close();

            System.out.println("Готово");

        } catch (IOException ignored) {
        }
        outputFile = Path.of(String.valueOf(file));
        return outputFile;
    }
}





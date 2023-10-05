package com.codecool.encryptedletters.app;

import java.util.Scanner;

import com.codecool.encryptedletters.cipher.VigenereCipher;
import com.codecool.encryptedletters.cipher.CaesarCipher;

public class CipherApp {
  private CaesarCipher caesarCipher = new CaesarCipher();
  private VigenereCipher vigenereCipher = new VigenereCipher(new CaesarCipher());
  private Scanner scanner = new Scanner(System.in);
  boolean shouldExit = false;

  String lastAction = "N/A";
  String lastCipherModifier = "N/A";
  String lastOriginalMessage = "N/A";
  String lastResultMessage = "N/A";
  String lastUsedCipher = "N/A";

  private void handleCipherChoice(String cipherChoice) {

    while (!shouldExit) {
      System.out.println("\n*****");
      System.out.println("Last Used Cipher: " + lastUsedCipher);
      System.out.println("Last Action: " + lastAction);
      System.out.println("Last Cipher Modifier: " + lastCipherModifier);
      System.out.println(" ");
      System.out.println("Last Original Message: " + lastOriginalMessage);
      System.out.println("Last Result Message: " + lastResultMessage);
      System.out.println("*****");
      System.out.println("Current Cipher: " + (cipherChoice.equals("CC") ? "Caesar Cipher" : "Vigenere Cipher"));
      System.out.println("*****");
      System.out.println("It's dangerous to go alone, choose wisely:");
      System.out.println("1. Encrypt message");
      System.out.println("2. Decrypt message");
      System.out.println("3. Change Cipher");
      System.out.println("4. I don't even want to be here!");
      System.out.print("\nWhat will you do? THINK!: ");

      int actionChoice = scanner.nextInt();
      scanner.nextLine();  // consume the newline

      if (actionChoice == 1 || actionChoice == 2) {
        System.out.print("\nMessage: ");
        String message = scanner.nextLine();

        if (cipherChoice.equals("CC")) {
          handleCaesarCipher(message, actionChoice);
        } else if (cipherChoice.equals("VC")) {
          handleVigenereCipher(message, actionChoice);
        }
      } else if (actionChoice == 3) {
        System.out.println("\nChange Cipher!");
        break; // exit inner loop & choose new cipher
      } else if (actionChoice == 4) {
        System.out.println("\nOver & out!");
        shouldExit = true;
        return; // exit program
      } else {
        System.out.println("Nope, try again.");
      }
    }
  }

  public void start() {
    System.out.println("Welcome to Shcoobz Cipher App!");

    while (!shouldExit) {
      System.out.println("\nChoose cipher:");
      System.out.println("CC: Caesar Cipher!");
      System.out.println("VC: Vigenere Cipher!");
      System.out.println("X: I came here by mistake!");
      System.out.print("\nYou want to: ");

      String cipherChoice = scanner.nextLine().toUpperCase(); // users choice

      // loop back to the cipher menu
      if (!cipherChoice.equals("CC") && !cipherChoice.equals("VC") && !cipherChoice.equals("X")) {
        System.out.println("WRONG! Select CC, VC, or X.");
        continue;
      }

      // exit method/end program
      if (cipherChoice.equals("X")) {
        System.out.println("\nOver & out!");
        shouldExit = true;
        return;
      }

      handleCipherChoice(cipherChoice);

    }
  }

  private void updateLastValues(String cipher, String action, String modifier, String originalMessage, String resultMessage) {
    lastUsedCipher = cipher;
    lastAction = action;
    lastCipherModifier = modifier;
    lastOriginalMessage = originalMessage;
    lastResultMessage = resultMessage;
  }

  private void handleCaesarCipher(String message, int actionChoice) {
    System.out.print("Shift value?: ");
    int shift = scanner.nextInt();
    scanner.nextLine();  // consume the newline

    String resultMessage;
    String action;

    if (actionChoice == 1) {
      resultMessage = caesarCipher.encrypt(message, shift);
      action = "Encrypted";
    } else {
      resultMessage = caesarCipher.decrypt(message, shift);
      action = "Decrypted";
    }

    updateLastValues("Caesar Cipher", action, "Shift by " + shift, message, resultMessage);
    displayResult(message, resultMessage, action);
  }

  private void handleVigenereCipher(String message, int actionChoice) {
    System.out.print("Keyword?: ");
    String keyword = scanner.nextLine();

    String resultMessage;
    String action;

    if (actionChoice == 1) {
      resultMessage = vigenereCipher.encrypt(message, keyword);
      action = "Encrypted";
    } else {
      resultMessage = vigenereCipher.decrypt(message, keyword);
      action = "Decrypted";
    }

    updateLastValues("Vigenere Cipher", action, "Keyword was " + keyword, message, resultMessage);
    displayResult(message, resultMessage, action);
  }

  private void displayResult(String originalMessage, String resultMessage, String operation) {
    System.out.println("\nOriginal Message: " + originalMessage);
    System.out.println(operation + " Message: " + resultMessage);
  }

  public static void main(String[] args) {
    CipherApp app = new CipherApp();
    app.start();
  }
}

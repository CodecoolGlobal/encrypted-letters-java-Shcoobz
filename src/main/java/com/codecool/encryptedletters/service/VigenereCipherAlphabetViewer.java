package com.codecool.encryptedletters.service;

import com.codecool.encryptedletters.cipher.CaesarCipher;
import com.codecool.encryptedletters.cipher.VigenereCipher;

import java.util.Scanner;

public class VigenereCipherAlphabetViewer {
  private VigenereCipher vigenereCipher;
  private static final String UPPERCASE_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String LOWERCASE_ALPHABET = "abcdefghijklmnopqrstuvwxyz";

  public VigenereCipherAlphabetViewer(VigenereCipher vigenereCipher) {
    this.vigenereCipher = vigenereCipher;
  }

  private String getShiftedAlphabet(String keyword, boolean isUppercase) {
    String original = isUppercase ? UPPERCASE_ALPHABET : LOWERCASE_ALPHABET;
    StringBuilder shifted = new StringBuilder();

    for (int i = 0; i < original.length(); i++) {
      char originalChar = original.charAt(i);
      char shiftedChar = vigenereCipher.encrypt(Character.toString(originalChar), keyword).charAt(0);
      shifted.append(shiftedChar);
    }

    return shifted.toString();
  }

  public void displayShiftedAlphabet(String keyword) {
    char[] originalUppercase = UPPERCASE_ALPHABET.toCharArray();
    char[] shiftedUppercase = getShiftedAlphabet(keyword, true).toCharArray();

    char[] originalLowercase = LOWERCASE_ALPHABET.toCharArray();
    char[] shiftedLowercase = getShiftedAlphabet(keyword, false).toCharArray();

    System.out.println("\nKeyword: " + keyword + "\n");
    System.out.println(String.format("%-20s %-20s", "Uppercase", "Lowercase"));
    System.out.println(String.format("%-20s %-20s", "---------", "---------"));

    for (int i = 0; i < originalUppercase.length; i++) {
      String upperCaseMapping = originalUppercase[i] + " -> " + shiftedUppercase[i];
      String lowerCaseMapping = originalLowercase[i] + " -> " + shiftedLowercase[i];

      System.out.println(String.format(" %-20s %-20s", upperCaseMapping, lowerCaseMapping));
    }
  }

/*  public static void main(String[] args) {
    VigenereCipher cipher = new VigenereCipher(new CaesarCipher());
    VigenereCipherAlphabetViewer viewer = new VigenereCipherAlphabetViewer(cipher);

    viewer.displayShiftedAlphabet("KEY");
  }*/

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter keyword: ");
    String keyword = scanner.nextLine();  // keyword can be upper&lower case

    VigenereCipher cipher = new VigenereCipher(new CaesarCipher());
    VigenereCipherAlphabetViewer viewer = new VigenereCipherAlphabetViewer(cipher);

    viewer.displayShiftedAlphabet(keyword);
  }
}

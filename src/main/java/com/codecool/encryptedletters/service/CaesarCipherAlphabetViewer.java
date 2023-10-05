package com.codecool.encryptedletters.service;

import java.util.Scanner;

import com.codecool.encryptedletters.cipher.CaesarCipher;

public class CaesarCipherAlphabetViewer {
  private CaesarCipher caesarCipher = new CaesarCipher();
  private static final String UPPERCASE_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String LOWERCASE_ALPHABET = "abcdefghijklmnopqrstuvwxyz";

  private String getOriginalAlphabet(boolean isUppercase) {
    return isUppercase ? UPPERCASE_ALPHABET : LOWERCASE_ALPHABET;
  }

  private String getShiftedAlphabet(int shift, boolean isUppercase) {
    String original = getOriginalAlphabet(isUppercase);
    StringBuilder shifted = new StringBuilder();

    for (char character : original.toCharArray()) {
      shifted.append(caesarCipher.shiftCharacter(character, shift, isUppercase ? 'A' : 'a', isUppercase ? 'Z' : 'z'));
    }

    return shifted.toString();
  }

  public void displayShiftedAlphabet(int shift) {
    char[] originalUppercase = getOriginalAlphabet(true).toCharArray();
    char[] shiftedUppercase = getShiftedAlphabet(shift, true).toCharArray();

    char[] originalLowercase = getOriginalAlphabet(false).toCharArray();
    char[] shiftedLowercase = getShiftedAlphabet(shift, false).toCharArray();

    System.out.println("\nShift by " + shift + ":\n");

    // String.format("%-20s %-20s", "Uppercase", "Lowercase"):
    // %: beginning of a format specifier
    // -: specifies left justification of value; default: right-justified
    // 20: width of 20 characters
    // s: type specifier -> argument is a string (d for integers, f for floating-point numbers, etc.)
    // %-20s: string argument fomatted to be left-justified in a field that's 20 characters wide

    System.out.println(String.format("%-20s %-20s", "Uppercase", "Lowercase"));
    System.out.println(String.format("%-20s %-20s", "---------", "---------"));

    for (int i = 0; i < originalUppercase.length; i++) {
      String upperCaseMapping = originalUppercase[i] + " -> " + shiftedUppercase[i];
      String lowerCaseMapping = originalLowercase[i] + " -> " + shiftedLowercase[i];

      System.out.println(String.format(" %-20s %-20s", upperCaseMapping, lowerCaseMapping));
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter shift value: ");
    int shift = scanner.nextInt();

    CaesarCipherAlphabetViewer viewer = new CaesarCipherAlphabetViewer();
    viewer.displayShiftedAlphabet(shift);
  }

  // testing
  /*  public static void main(String[] args) {
    CaesarCipherAlphabetViewer viewer = new CaesarCipherAlphabetViewer();
    viewer.displayShiftedAlphabet(3);  // shift alphabets by 3
  }*/
}

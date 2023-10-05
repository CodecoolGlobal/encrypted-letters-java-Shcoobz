package com.codecool.encryptedletters.cipher;

public class VigenereCipher {

  private CaesarCipher caesarCipher;

  public VigenereCipher(CaesarCipher caesarCipher) { // constructor to initialize VigenereCipher with a CaesarCipher
    this.caesarCipher = caesarCipher;  // assign passed CaesarCipher instance to private member variable
  }

  private String processText(String text, String keyword, boolean isEncryption) {
    StringBuilder processedText = new StringBuilder(); // efficiently build encrypted text
    int keywordIndex = 0; // position in keyword

    for (int i = 0; i < text.length(); i++) {
      char charToBeShifted = text.charAt(i); // curr char from text

      if (Character.isLetter(charToBeShifted)) { // check if char is letter
        char keywordChar = keyword.charAt(keywordIndex % keyword.length()); // get corresponding char from keyword; modulo for wrap around if plainText is longer than keyword
        int shift = Character.isUpperCase(keywordChar) ? keywordChar - 'A' : keywordChar - 'a'; // calc shift amount based on keyword char -> 'A' or 'a' means a shift of 0, 'B' or 'b' means a shift of 1

        // based on the flag, decide to encrypt/decrypt
        if (isEncryption) {
          processedText.append(caesarCipher.encrypt(Character.toString(charToBeShifted), shift)); // encrypt char with Caesar cipher with calc shift
        } else {
          processedText.append(caesarCipher.decrypt(Character.toString(charToBeShifted), shift)); // decrypt char with Caesar cipher with calc shift
        }

        keywordIndex++; // only increment keywordIndex for letters
      } else {
        processedText.append(charToBeShifted); // not a letter = add unchanged
      }
    }

    return processedText.toString();
  }

  public String encrypt(String text, String keyword) {
    return processText(text, keyword, true); // true = encryption
  }

  public String decrypt(String text, String keyword) {
    return processText(text, keyword, false); // false = decryption
  }

  // testing
  public static void runTest(String description, String inputText, String keyword, String expectedOutcome, boolean isEncryptionTest) {
    System.out.println(description);

    VigenereCipher cipher = new VigenereCipher(new CaesarCipher()); // Assuming CaesarCipher has a default constructor

    String actualOutcome;

    if (isEncryptionTest) {
      actualOutcome = cipher.encrypt(inputText, keyword);
    } else {
      actualOutcome = cipher.decrypt(inputText, keyword);
    }

    if (actualOutcome.equals(expectedOutcome)) {
      System.out.println("SUCCESS: Expected " + (isEncryptionTest ? "encryption" : "decryption") + " to be " + expectedOutcome + ", got " + actualOutcome);
    } else {
      System.out.println("FAIL: Expected " + (isEncryptionTest ? "encryption" : "decryption") + " to be " + expectedOutcome + ", but got " + actualOutcome);
    }
  }

  public static void main(String[] args) {
    runTest("\n>>> Test Encryption 1 - Success <<<", "Encryption Test", "Keyword", "Oranmgwssl Psjw", true);
    runTest("\n>>> Test Encryption 1 - Fail <<<", "Encryption Test", "Keyword", "No", true);

    runTest("\n>>> Test Decryption 1 - Success<<<", "Decryption Test", "Keyword", "Taevkyqykp Xqbq", false);
    runTest("\n>>> Test Decryption 1 <<<", "Decryption Test", "Keyword", "No", false);

  }
}

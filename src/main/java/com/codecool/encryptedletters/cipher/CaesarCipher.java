package com.codecool.encryptedletters.cipher;

public class CaesarCipher {

  public char shiftCharacter(char charToBeShifted, int numberOfPositionToShift, char lowerBoundary, char upperBoundary) {
    int shifted = charToBeShifted + numberOfPositionToShift; // calc initial shifted value by adding shift to charToBeShifted's ASCII value

    if (shifted < lowerBoundary) { // checks if shifted value is less than lower boundary of acceptable characters ("A" = 65, "a" = 97)
      shifted = upperBoundary - (lowerBoundary - shifted) + 1;
    } else if (shifted > upperBoundary) { // checks if shifted value is more than upper boundary of acceptable characters ("Z" = 90, "z" = 122)
      shifted = lowerBoundary + (shifted - upperBoundary) - 1;
    }

    return (char) shifted;
  }

  public String encrypt(String plainText, int shift) {
    StringBuilder encryptedText = new StringBuilder();
    for (char charToBeShifted : plainText.toCharArray()) {
      if (Character.isUpperCase(charToBeShifted)) {
        encryptedText.append(shiftCharacter(charToBeShifted, shift, 'A', 'Z'));
      } else if (Character.isLowerCase(charToBeShifted)) {
        encryptedText.append(shiftCharacter(charToBeShifted, shift, 'a', 'z'));
      } else {
        encryptedText.append(charToBeShifted);
      }
    }
    return encryptedText.toString();
  }

  public String decrypt(String cipherText, int shift) {
    // decrypt is the negative shift of encryption
    return encrypt(cipherText, -shift);
  }

  // testing
  public static void runTest(String description, String inputText, int shift, String expectedOutcome, boolean isEncryptTest) {
    System.out.println(description);

    CaesarCipher cipher = new CaesarCipher();

    try {
      String actualOutcome;
      if (isEncryptTest) {
        actualOutcome = cipher.encrypt(inputText, shift);
      } else {
        actualOutcome = cipher.decrypt(inputText, shift);
      }

      if (actualOutcome.equals(expectedOutcome)) {
        System.out.println("SUCCESS: Expected text to be " + expectedOutcome + ", got " + actualOutcome);
      } else {
        System.out.println("FAIL: Expected text to be " + expectedOutcome + ", but got " + actualOutcome);
      }
    } catch (Exception e) {
      System.out.println("ERROR: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    // Encryption tests
    runTest("\n>>> Test Encrypt 'hello' with shift 3 - Success <<<", "hello", 3, "khoor", true);
    runTest("\n>>> Test Encrypt 'hello' with shift 3 - Failure <<<", "hello", 3, "wrong", true);

    // Decryption tests
    runTest("\n>>> Test Decrypt 'khoor' with shift 3 - Success <<<", "khoor", 3, "hello", false);
    runTest("\n>>> Test Decrypt 'khoor' with shift 3 - Failure <<<", "khoor", 3, "wrong", false);
  }
}

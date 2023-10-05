package com.codecool.encryptedletters;

import com.codecool.encryptedletters.cipher.CaesarCipher;
import com.codecool.encryptedletters.cipher.VigenereCipher;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VigenereCipherTest {
    private final CaesarCipher caesarCipher = new CaesarCipher();
    private final VigenereCipher vigenereCipher = new VigenereCipher(caesarCipher);
    private final String testKeyword = "caesarcipher";
    private final String zeroKeyword = "aaa";

    @Test
    void encryptsEmptyText() {
        String plainText = "";
        String cipherText = vigenereCipher.encrypt(plainText, testKeyword);
        assertEquals("", cipherText);
    }

    @Test
    void encryptsShortTextUsingZeroKeyword() {
        String plainText = "ZzAa";
        String cipherText = vigenereCipher.encrypt(plainText, zeroKeyword);
        assertEquals(plainText, cipherText);
    }

    @Test
    void encryptsShortText() {
        String plainText = "ZzAa";
        String cipherText = vigenereCipher.encrypt(plainText, testKeyword);
        assertEquals("BzEs", cipherText);
    }

    @Test
    void encryptsTextWithSpecialCharacters() {
        String plainText = "You can implement Vigenere cipher in C# or Java.";
        String cipherText = vigenereCipher.encrypt(plainText, testKeyword);
        assertEquals("Aoy uae kuesidgnx Nixgvtyi tkplwr zp K# dy Nrxa.", cipherText);
    }

    @Test
    void decryptsEmptyText() {
        String cipherText = "";
        String plainText = vigenereCipher.decrypt(cipherText, testKeyword);
        assertEquals("", plainText);
    }

    @Test
    void decryptsShortTextUsingZeroKeyword() {
        String cipherText = "ZzAa";
        String plainText = vigenereCipher.decrypt(cipherText, zeroKeyword);
        assertEquals(cipherText, plainText);
    }

    @Test
    void decryptsShortText() {
        String cipherText = "BzEs";
        String plainText = vigenereCipher.decrypt(cipherText, testKeyword);
        assertEquals("ZzAa", plainText);
    }

    @Test
    void decryptsTextWithSpecialCharacters() {
        String cipherText = "Aoy uae kuesidgnx Nixgvtyi tkplwr zp K# dy Nrxa.";
        String plainText = vigenereCipher.decrypt(cipherText, testKeyword);
        assertEquals("You can implement Vigenere cipher in C# or Java.", plainText);
    }

    @Test
    void decryptsCaesarCipherTextCharacters() {
        int caesarShift = 3;
        /*String keywordForCaesarShift3 = "whatisthekeyword";*/
        String keywordForCaesarShift3 = "D"; // D or d
        String plaintextOriginal = "Caesar cipher is a special case of Vigenere cipher.";
        String cipherText = caesarCipher.encrypt(plaintextOriginal, caesarShift);
        String plainText = vigenereCipher.decrypt(cipherText, keywordForCaesarShift3);
        assertEquals(plaintextOriginal, plainText);
    }
}

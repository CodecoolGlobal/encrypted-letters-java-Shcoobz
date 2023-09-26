package com.codecool.encryptedletters;

import com.codecool.encryptedletters.cipher.CaesarCipher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CaesarCipherTest {
    private final CaesarCipher caesarCipher = new CaesarCipher();
    private final int testShift = 3;
    private final int zeroShift = 0;

    @Test
    void encryptsEmptyText() {
        String plainText = "";
        String cipherText = caesarCipher.encrypt(plainText, testShift);
        assertEquals("", cipherText);
    }

    @Test
    void encryptsShortTextUsingZeroShift() {
        String plainText = "ZzAa";
        String cipherText = caesarCipher.encrypt(plainText, zeroShift);
        assertEquals(plainText, cipherText);
    }

    @Test
    void encryptsShortText() {
        String plainText = "ZzAa";
        String cipherText = caesarCipher.encrypt(plainText, testShift);
        assertEquals("CcDd", cipherText);
    }

    @Test
    void encryptsTextWithSpecialCharacters() {
        String plainText = "You can implement Caesar cipher in C# or Java.";
        String cipherText = caesarCipher.encrypt(plainText, testShift);
        assertEquals("Brx fdq lpsohphqw Fdhvdu flskhu lq F# ru Mdyd.", cipherText);
    }

    @Test
    void decryptsEmptyText() {
        String cipherText = "";
        String plainText = caesarCipher.decrypt(cipherText, testShift);
        assertEquals("", plainText);
    }

    @Test
    void decryptsShortTextUsingZeroShift() {
        String cipherText = "ZzAa";
        String plainText = caesarCipher.decrypt(cipherText, zeroShift);
        assertEquals(cipherText, plainText);
    }

    @Test
    void decryptsShortText() {
        String cipherText = "CcDd";
        String plainText = caesarCipher.decrypt(cipherText, testShift);
        assertEquals("ZzAa", plainText);
    }
    @Test
    void decryptsTextWithSpecialCharacters() {
        String cipherText = "Brx fdq lpsohphqw Fdhvdu flskhu lq F# ru Mdyd.";
        String plainText = caesarCipher.decrypt(cipherText, testShift);
        assertEquals("You can implement Caesar cipher in C# or Java.", plainText);
    }

}

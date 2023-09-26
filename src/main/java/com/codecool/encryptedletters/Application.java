package com.codecool.encryptedletters;

import com.codecool.encryptedletters.cipher.CaesarCipher;
import com.codecool.encryptedletters.cipher.VigenereCipher;

public class Application {

    public static void main(String[] args) {
        CaesarCipher caesarCipher = new CaesarCipher();
        VigenereCipher vigenereCipher = new VigenereCipher(caesarCipher);

        int caesarShift = 13;
        String loveLetter1 = "Url unaqfbzr!";
        System.out.println(caesarCipher.decrypt(loveLetter1, caesarShift));

        String vigenereKeyword = "codecool";
        String loveLetter2 = "Acx tcggpf ab jkfge vsvx :)";
        System.out.println(vigenereCipher.decrypt(loveLetter2, vigenereKeyword));

    }

}

/* SELF ASSESSMENT
 1. Did I use easy-to-understand meaningful variable names formatted properly (in lowerCamelCase)?
       Mark out of 5: 5
        Variable names are easy to understand and meaningful, all formatted in lowerCamelCase

 2. Did I indent the code appropriately?
       Mark out of 5: 5
        Code indented correctly using IDE

 3. Did I write the createCipher function correctly (parameters, return type and function body) and invoke it correctly?
       Mark out of 20: 20
        Function has the correct parameters: char[] characterArray, returns char[], invoked correctly in the main method

 4. Did I write the encrypt function correctly (parameters, return type and function body) and invoke it correctly?
       Mark out of 20: 20
        encrypt - parameters: characterArray, cipher and alphabet array. Returns char[] encryptedText, invoked in the
        main method correctly

 5. Did I write the decrypt function correctly (parameters, return type and function body) and invoke it correctly?
       Mark out of 20: 20
        decrypt - parameters: encryptedText, cipher and alphabet array. Returns a string - the decrypted phrase.
        Invoked correctly in main method

 6. Did I write the main function body correctly (repeatedly obtaining a string and encrypting it and then decrypting the encrypted version)?
       Mark out of 25: 25
        Main function body repeatedly obtains a string, encrypts it, then decrypts the encrypted text, printing the results to the console
        both times. Basic error checking with regexp

 7. How well did I complete this self-assessment?
        Mark out of 5: 5
         All questions marked appropriately and detailed response given for each.
 Total Mark out of 100 (Add all the previous marks): 100
*/
package Week12;

import java.util.Random;
import java.util.Scanner;

public class Cipher {
    public static void main(String[] args) {
        System.out.println("This program converts user-entered plain text to cipher text. Type 'exit' to terminate " +
                "the program. ");
        boolean finished = false;

        while (!finished) {
            char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t',
                    'u','v','w','x','y','z',' '};
            char[] alphaCopy = new char[27];
            System.arraycopy(alphabet, 0, alphaCopy, 0, alphabet.length);
            System.out.println("Enter plain text:");
            Scanner inputScanner = new Scanner(System.in);

            if (inputScanner.hasNext()) {
                String userInput = inputScanner.nextLine();

                if (userInput.matches(".*[[^\\w ]\\d]")) {
                    System.out.println("Error: Enter only the characters a-z, A-Z and the space character.");
                } else {
                    String lowerCaseString = userInput.toLowerCase();

                    char[] characterArray = lowerCaseString.toCharArray();
                    char[] cipher = createCipher(alphaCopy);
                    //System.out.println(cipher);
                    //System.out.println(alphabet);
                    char[] encrypted = encrypt(characterArray, cipher, alphabet);
                    String encryptedString = new String(encrypted);
                    System.out.println("Encrypted text:\n" + encryptedString);
                    String decryptedString = decrypt(encrypted, cipher, alphabet);
                    System.out.println("Decrypted text:\n" + decryptedString);

                }
            }
        }

    }

    private static char[] createCipher(char[] characterArray) {
        char[] cipher = randomiseOrder(characterArray);
        return cipher;
    }

    private static char[] encrypt(char[] text, char[] cipher, char[] alphabet) {
        char[] encryptedText = new char[text.length];
        char currentChar;
        char currentAlpha;
        int encryptedIndex = 0;

        for (int index=0; index<text.length; index++) {
            currentChar = text[index];

            for (int index1=0; index1<alphabet.length; index1++) {
                currentAlpha = alphabet[index1];
                if (currentAlpha == currentChar) {
                    encryptedText[encryptedIndex] = cipher[index1];
                    encryptedIndex++;
                }
            }
        }
        return encryptedText;
    }

    private static String decrypt(char[] encryptedText, char[] cipher, char[] alphabet) {
        String decryptedString = "";
        char currentChar;
        char currentCipherChar;

        for (int index=0; index<encryptedText.length; index++) {
            currentChar = encryptedText[index];

            for (int index1=0; index1<cipher.length; index1++) {
                currentCipherChar = cipher[index1];

                if (currentCipherChar == currentChar) {
                    char decryptedChar = alphabet[index1];
                    decryptedString += decryptedChar;
                }
            }
        }
        return decryptedString;
    }

    //CS1010; Imperative programming > Week 12 - 1D and 2D Arrays
    public static char[] randomiseOrder(char[] array)
    {
        if (array!=null) {
            Random generator = new Random();
            for (int index=0; index<array.length; index++ ) {
                int otherIndex = generator.nextInt(array.length);
                char temp = array[index];
                array[index] = array[otherIndex];
                array[otherIndex] = temp;
            }
        }
        return array;
    }
}

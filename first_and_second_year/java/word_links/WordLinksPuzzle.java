/* SELF ASSESSMENT

1. readDictionary
- I have the correct method definition [Mark out of 5:5]
- Comment: static, returns ArrayList<String>, throws IOException
- My method reads the words from the "words.txt" file. [Mark out of 5:5]
- Comment: file reader and buffered file reader used to read words from the text file
- It returns the contents from "words.txt" in a String array or an ArrayList. [Mark out of 5:5]
- Comment: An ArrayList of the words is returned.

2. readWordList
- I have the correct method definition [Mark out of 5:5]
- Comment: static, returns ArrayList<String>, parameter: String user input word list.
- My method reads the words provided (which are separated by commas, saves them to an array or ArrayList of String references and returns it. [Mark out of 5:5]
- Comment: the method correctly reads words provided (separated by commas, or commas and spaces) and saves them to an array list.

3. isUniqueList
- I have the correct method definition [Mark out of 5:5]
- Comment: static, returns boolean, parameter: user input word list as an array list
- My method compares each word in the array with the rest of the words in the list. [Mark out of 5:5]
- Comment: A copy of the array is made, then a word is deleted, if after the delete the same word is still present, a duplicate must exist.
- Exits the loop when a non-unique word is found. [Mark out of 5:5]
- Comment: the loop is exited when a duplicate is found and boolean isUnique is set to false
- Returns true is all the words are unique and false otherwise. [Mark out of 5:5]
- Comment: the isUnique boolean is by default true, if a duplicate word is found it is set to false.

4. isEnglishWord
- I have the correct method definition [Mark out of 5:5]
- Comment: static, returns boolean, parameters: word and the ArrayList of the words.txt file.
- My method uses the binarySearch method in Arrays library class. [Mark out of 3:3]
- Comment: the binarySearch method is used to search the array list for the word.
- Returns true if the binarySearch method return a value >= 0, otherwise false is returned. [Mark out of 2:2]
- Comment: if binarySearch finds a match the index is returned, therefore if returned index is >= 0 the word is in the dictionary and true is returned

5. isDifferentByOne
- I have the correct method definition [Mark out of 5:5]
- Comment: static, returns boolean, parameters: two words of type String
- My method loops through the length of a words comparing characters at the same position in both words searching for one difference. [Mark out of 10:10]
- Comment: loops through words and compares each corresponding character, counts number of differences, if more than 1 difference returns false

6. isWordChain
- I have the correct method definition [Mark out of 5:5]
- Comment: static, void, parameters: array list of the words that were entered by the user AND array of words.txt
- My method calls isUniqueList, isEnglishWord and isDifferentByOne methods and prints the appropriate message [Mark out of 10:10]
- Comment: these methods are called, and isWordChain prints to console if the word chain is valid or not.

7. main
- Reads all the words from file words.txt into an array or an ArrayList using the any of teh Java.IO classes covered in lectures [Mark out of 10:10]
- Comment: readDictionary method used to do this. So that words.txt is only read into an array once the isWordChain method also takes the full word list as a parameter.
- Asks the user for input and calls isWordChain [Mark out of 5:5]
- Comment: asks for input in a loop and calls isWordChain method to determine if input is a valid word chain or not.

 Total Mark out of 100 (Add all the previous marks):100
*/
package Week3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class WordLinksPuzzle {
    public static void main(String[] args) throws IOException {
        boolean finished = false;
        ArrayList<String> validWordList = readDictionary();
        while (!finished) {
            System.out.println("\nEnter a comma separated list of words (or an empty list to quit):");
            Scanner inputScanner = new Scanner(System.in);
            String userInput = inputScanner.nextLine();
            if (userInput.matches(".+")) {
                ArrayList<String> userInputArray = readWordList(userInput);
                isWordChain(userInputArray, validWordList);
            } else {
                System.out.println("Goodbye.");
                finished = true;
            }
        }
    }

    public static ArrayList<String> readDictionary() throws IOException {
        ArrayList<String> wordList = new ArrayList<>();
        FileReader fileReader = new FileReader("./src/Week3/words.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        boolean fileEnd = false;
        try {
            while (!fileEnd) {
                String word = bufferedReader.readLine();
                if (word != null) {
                    wordList.add(word);
                } else {
                    fileEnd = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordList;
    }

    public static ArrayList<String> readWordList(String wordListInput) {
        return new ArrayList<>(Arrays.asList(wordListInput.split("[,|, ]+")));
    }

    public static boolean isUniqueList(ArrayList<String> wordList) {
        boolean isUnique = true;
        ArrayList<String> arrayListCopy = new ArrayList<>(wordList);
        for (int index = 0; index < wordList.size() && isUnique; index++) {
            String currentWord = wordList.get(index);
            arrayListCopy.remove(currentWord);
            if (arrayListCopy.contains(currentWord)) {
                isUnique = false;
            }
        }
        return isUnique;
    }

    //searches the array list of valid words, if the word is found, true is returned.
    public static boolean isEnglishWord(String word, ArrayList<String> wordListFile) {
        return (Collections.binarySearch(wordListFile, word) >= 0);
    }

    //int counts how many characters differ in the two strings. If more than 1 char differs or the strings are
    //of different lengths, false is returned.
    public static boolean isDifferentByOne(String word1, String word2) {
        if (word1.length() == word2.length()) {
            int diffCharCount = 0;
            char[] word1Array = word1.toCharArray();
            char[] word2Array = word2.toCharArray();
            for (int index = 0; index < word1Array.length && diffCharCount < 2; index++) {
                if (word1Array[index] != word2Array[index]) {
                    diffCharCount++;
                }
            }
            return diffCharCount < 2;
        } else {
            return false;
        }
    }

    public static void isWordChain(ArrayList<String> wordListInput, ArrayList<String> validWordList) throws IOException {
        boolean isWordChain = false;
        if (isUniqueList(wordListInput)) {
            isWordChain = true;
            //Ensure all words in the list are in the dictionary (i.e.: words.txt)
            for (int index = 0; index < wordListInput.size() && isWordChain; index++) {
                if (!isEnglishWord(wordListInput.get(index), validWordList)) {
                    isWordChain = false;
                }
            }
            //Ensure words differ by only one letter
            if (wordListInput.size() > 2) {
                for (int index = 0; index < (wordListInput.size() - 2) && isWordChain; index++) {
                    String word1, word2;
                    word1 = wordListInput.get(index);
                    word2 = wordListInput.get(index + 1);

                    if (!isDifferentByOne(word1, word2)) {
                        isWordChain = false;
                    }
                }
            } else if (isWordChain) {
                String word1, word2;
                word1 = wordListInput.get(0);
                word2 = wordListInput.get(1);
                isWordChain = isDifferentByOne(word1, word2);
            }
        }
        if (isWordChain) {
            System.out.println("Valid chain of words from Lewis Carroll's word-links game.");
        } else {
            System.out.println("Not a valid chain of words from Lewis Carroll's word-links game.");
        }
    }
}
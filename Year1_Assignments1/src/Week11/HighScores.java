/* SELF ASSESSMENT
 1. Did I use easy-to-understand meaningful variable names formatted properly (in lowerCamelCase)?
        Mark out of 5: 5
        Variable names are easy to understand and meaningful
 2. Did I indent the code appropriately?
        Mark out of 5: 5
        Code automatically indented by the IDE
 3. Did I write the initialiseHighScores function correctly (parameters, return type and function body) and invoke it correctly?
       Mark out of 15: 15
       Parameters: int[] highScores / return: void / function body: for loop to go through each element in the array
       Function is invoked when (re)setting all scores to zero
 4. Did I write the printHighScores function correctly (parameters, return type and function body) and invoke it correctly?
       Mark out of 15: 15
       Parameters: int[] highScores / return: void / function body: prints current scores to the console
       Invoked when the user types the keyword 'print'
 5. Did I write the higherThan function correctly (parameters, return type and function body) and invoke it correctly?
       Mark out of 15: 15
       Parameters: int[] highScores & int newScore / return: boolean / function body: explained at function
       Invoked by the insertScore function to determine whether or not the new score is higher than any of the
            elements in the array, returns a boolean.
 6. Did I write the insertScore function correctly (parameters, return type and function body) and invoke it correctly?
       Mark out of 20: 20
       Parameters: int[] highScores & int newScore / return: int[] / function body: explained at function
       Invoked when a score is typed by the user, if the score is greater than any of the current scores in the array,
            the score is placed into the array at the correct location
 7. Did I write the main function body correctly (first asking for the number of scores to be maintained and then repeatedly asking for scores)?
       Mark out of 20: 20
       The main asks for the number of scores, with error checking, then repeatedly asks for scores / other commands.
 8. How well did I complete this self-assessment?
        Mark out of 5: 5
        Marks appropriately awarded for every question and detailed comments given.
 Total Mark out of 100 (Add all the previous marks): 100
*/
package Week11;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HighScores {
    public static void main(String[] args) {

        boolean finishedNumbScores = false;
        int numberOfHighScores;
        int[] highScores = null;

        while (!finishedNumbScores) {
            System.out.println("Enter the number of high scores you would like to maintain in the list: ");
            Scanner highScoresScanner = new Scanner(System.in);
            try {
                numberOfHighScores = highScoresScanner.nextInt();
                if (numberOfHighScores <= 0) {
                    throw new InputMismatchException();
                }
                highScores = new int[numberOfHighScores];
                initialiseScores(highScores);
                finishedNumbScores = true;
            } catch (InputMismatchException exception) {
                System.out.println("Error: Please enter a positive whole number.");
            }
        }

        boolean finished = false;
        while (!finished) {
            System.out.println("For a list of commands, type 'help' \nEnter a new score:");
            Scanner inputScanner = new Scanner(System.in);
            String userInput = inputScanner.nextLine();
            Scanner inputStringScanner = new Scanner(userInput);

            if (inputStringScanner.hasNext("exit")) {
                System.out.println("FINAL HIGH SCORES:");
                printHighScores(highScores);
                finished = true;
            } else if (inputStringScanner.hasNext("help")) {
                System.out.println("To see current high scores, type 'print'\nTo clear the score board, type 'reset'" +
                        "\nTo terminate the program, type 'exit'");
            } else if (inputStringScanner.hasNextInt()) {
                int newScore = inputStringScanner.nextInt();
                if (inputStringScanner.hasNextInt() || newScore <= 0) {
                    System.out.println("Error: Invalid input. Please enter one positive whole number at a time.");
                } else {
                    highScores = insertScore(highScores, newScore);
                }
            } else if (inputStringScanner.hasNext("reset")) {
                initialiseScores(highScores);
            } else if (inputStringScanner.hasNext("print")) {
                System.out.println("The high scores are: ");
                printHighScores(highScores);
            } else {
                System.out.println("Error: Invalid input. Please enter a whole number.");
            }
        }
    }

    //sets all elements in the array to 0
    private static void initialiseScores(int[] highScores) {
        if (highScores != null) {
            for (int index = 0; index < highScores.length; index++) {
                highScores[index] = 0;
            }
        }
    }

    //prints the array to the console (if scores have been put into the array)
    private static void printHighScores(int[] highScores) {
        if (highScores[0] == 0) {
            System.out.println("No scores to show.\n");
        } else {
            for (int index = 0; index < highScores.length; index++) {
                if (highScores[index] != 0) {
                    String punctuation = (index == highScores.length - 1 || highScores[index+1] == 0) ? ".\n" : ", ";
                    System.out.print(highScores[index] + punctuation);
                }
            }
        }
    }

    //returns the index if the new score is greater than or equal to the element at that index.
    //returns -1 if no elements are greater than the new score.
    private static int determinePosition(int[] highScores, int newScore) {
        for (int index = 0; index < highScores.length; index++) {
            if (newScore >= highScores[index]) {
                return index;
            }
        }
        return -1;
    }

    //returns whether or not the passed new score is higher than any of the elements in the
    //high scores array
    private static boolean higherThan(int[] highScores, int newScore) {
        for (int index = 0; index < highScores.length; index++) {
            if (newScore >= highScores[index]) {
                return true;
            }
        }
        return false;
    }

    //inserts the score at index returned by higherThan()
    //creates and returns a new array with the new score inserted into it
    private static int[] insertScore(int[] highScores, int newScore) {
        if (higherThan(highScores, newScore)) {
            int position = determinePosition(highScores, newScore);
            int[] newHighScores;
            newHighScores = new int[highScores.length];
            for (int index = 0; index < position; index++) {
                newHighScores[index] = highScores[index];
            }
            newHighScores[position] = newScore;
            for (int index = position + 1; index < newHighScores.length; index++) {
                newHighScores[index] = highScores[index - 1];
            }
            return newHighScores;
        }
        return highScores;
    }
}
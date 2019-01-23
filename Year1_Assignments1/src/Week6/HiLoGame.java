/* SELF ASSESSMENT
   1. Did I use appropriate CONSTANTS instead of numbers within the code?
       Mark out of 5: 5
       Comment: Constants used for cards without number values - J, K, Q, A
   2. Did I use easy-to-understand, meaningful CONSTANT names formatted correctly in UPPERCASE?
       Mark out of 5: 5
       Comment: All constants formatted correctly.
   3. Did I use easy-to-understand meaningful variable names?
       Mark out of 10: 10
       Comment: Variable names are easily understood and self-explanatory
   4. Did I format the variable names properly (in lowerCamelCase)?
       Mark out of 5: 5
       Comment: Variable names are formatted correctly in lowerCamelCase
   5. Did I indent the code appropriately?
       Mark out of 10: 10
       Comment: Code indented automatically with IDE
   6. Did I use an appropriate loop to allow the user to enter their guesses until they win or lose?
       Mark out of 20: 20
       Comment: The game counts the user's streak, if it reaches 4 they win, if they lose the streak is reset to 0. The
            game can be quit at any time.
   7. Did I check the input to ensure that invalid input was handled appropriately?
       Mark out of 10: 10
       Comment: if the user enters anything other than higher, lower, equal or quit an error message is sent to the
            console.
   8. Did I generate the cards properly using random number generation (assuming all cards are equally likely each time)?
       Mark out of 10: 10
       Comment: Random generator used in the given range of 2-14 (2-10, 11=J, 12=Q, K=13, A=14)
   9. Did I output the cards correctly as 2, 3, 4, ... 9, 10, Jack, Queen, King?
       Mark out of 10: 10
       Comment: All number cards are output correctly, as are J, Q, K and A
   10. Did I report whether the user won or lost the game before the program finished?
       Mark out of 10: 10
       Comment: If the user gets a streak of four it is reported as a win. If they lose the streak is reset - the equivalent
            of a loss, but the user can try again without re-running the program. Type 'quit' to end.
   11. How well did I complete this self-assessment?
       Mark out of 5: 5
       Comment: Appropriate marks and detailed comments given for each question.
   Total Mark out of 100 (Add all the previous marks): 100
*/
package Week6;

import java.util.Random;
import java.util.Scanner;

public class HiLoGame {

    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    public static final int ACE = 14;

    public static void main(String[] args) {

        boolean finished = false;

        Random cardGenerator = new Random();
        System.out.println("Hi-Lo Game: Guess if the next card will be 'higher', 'lower' or 'equal' to the current card. " +
                "\nIf you guess correctly 4 times in a row you win." + "\nType 'quit' at any time to end the game.");

        int currentCard = cardGenerator.nextInt(13) + 2;
        int streak = 0;

        while (!finished) {
            int nextCard = cardGenerator.nextInt(13) + 2;
            int cardIsHiLoOrEqual = 2; // 2=default setting, 1=higher, -1=lower, 0=equal

            if (currentCard == JACK) {
                System.out.println("Current card: Jack " + "| Streak: " + streak);
            } else if (currentCard == QUEEN) {
                System.out.println("Current card: Queen " + "| Streak: " + streak);
            } else if (currentCard == KING) {
                System.out.println("Current card: King " + "| Streak: " + streak);
            } else if (currentCard == ACE) {
                System.out.println("Current card: Ace " + "| Streak: " + streak);
            } else {
                System.out.println("Current card: " + currentCard + " | Streak: " + streak);
            }

            if (nextCard > currentCard) {
                cardIsHiLoOrEqual = 1;
            } else if (nextCard < currentCard) {
                cardIsHiLoOrEqual = -1;
            } else if (nextCard == currentCard) {
                cardIsHiLoOrEqual = 0;
            }

            int userAnswer = 2; //default setting

            while (userAnswer == 2) {
                Scanner userInput = new Scanner(System.in);
                if (userInput.hasNext("higher") || userInput.hasNext("h")) {
                    userAnswer = 1; //1 = higher
                } else if (userInput.hasNext("lower") || userInput.hasNext("l")) {
                    userAnswer = -1; //-1 = lower
                } else if (userInput.hasNext("equal") || userInput.hasNext("e")) {
                    userAnswer = 0; //0 = equal
                } else if (userInput.hasNext("quit")) {
                    finished = true;
                    userAnswer = cardIsHiLoOrEqual;
                } else {
                    System.out.println("Invalid input. Please enter 'higher' ('h'), 'lower' ('l') or 'equal' ('e')." +
                            "\nIf you want to quit type 'quit'.");
                }
            }

            if (userAnswer == cardIsHiLoOrEqual) {
                streak++;
                if (streak == 4) {
                    System.out.println("Congratulations. You correctly guessed 4 in a row.");
                    finished = true;
                }
            } else {
                System.out.println("Incorrect! Streak reset.");
                streak = 0;
            }

            currentCard = nextCard;
        }

    }
}

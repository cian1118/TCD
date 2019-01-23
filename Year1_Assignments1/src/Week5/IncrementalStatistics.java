package Week5;
/* SELF ASSESSMENT
   1. Did I use easy-to-understand meaningful variable names?
       Mark out of 10: 10
        All variable names self-explanatory.
   2. Did I format the variable names properly (in lowerCamelCase)?
       Mark out of 5: 5
        All variables are formatted in lowerCamelCase.
   3. Did I indent the code appropriately?
       Mark out of 10: 10
        Code has been indented properly - used IDE's auto-indent function.
   4. Did I input the numbers one at a time from the command line?
       Mark out of 10: 10
        The given example can be replicated correctly (1, 2, 3, 10, 5, exit)
   5. Did I check the input to ensure that invalid input was handled appropriately?
       Mark out of 10: 10
        Error is returned if user inputs a non-integer. An error will also be returned if the user attempts to input
        more than one integer at a time.
   6. Did I use an appropriate while or do-while loop to allow the user to enter numbers until they entered exit/quit?
       Mark out of 20: 20
        The while loop will run indefinitely until 'exit'/'quit' is typed by the user.
   7. Did I implement the loop body correctly so that the average and variance were updated and output appropriately?
       Mark out of 30: 30
        The average and variance are calculated on each iteration and outputted to the user appropriately.
   8. How well did I complete this self-assessment?
       Mark out of 5: 5
        Appropriate marks and comments given for each question.
   Total Mark out of 100 (Add all the previous marks): 100
*/

import java.util.Scanner;
import java.lang.String;

public class IncrementalStatistics {
    public static void main(String[] args) {

        boolean finished = false;
        int count = 1;
        double average;
        double variance = 0;
        double previousAverage = 0;

        System.out.println("This program computes the average and variance of all numbers entered.");

        while (!finished)
        {
            System.out.println("Enter a number (or type 'exit'): ");
            Scanner userInput = new Scanner(System.in);

            if (userInput.hasNextInt())
            {
                String inputString = userInput.nextLine();
                if (inputString.contains(" ")) {
                    System.out.println("Error: Enter only 1 integer at a time.");
                }
                else {
                    int number = Integer.parseInt(inputString);
                    average = previousAverage + ((number - previousAverage) / count);

                    variance = ((variance * (count - 1)) + (number - previousAverage) * (number - average)) / count;

                    count++;
                    System.out.println("So far the average is: " + average + " and the variance is: " + variance);
                    previousAverage = average;
                }
            }
            else if (userInput.hasNext("exit") || userInput.hasNext("quit"))
            {
                System.out.println("Goodbye.");
                finished = true;
            }
            else
                System.out.println("Error: Input must be an integer.");
        }
    }
}
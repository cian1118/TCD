package Week2;
    /*  SELF ASSESSMENT
   1. Did I use appropriate CONSTANTS instead of numbers within the code?
       Mark out of 10: 10
   2. Did I use easy-to-understand, meaningful CONSTANT names?
       Mark out of 5: 4
    3. Did I format the CONSTANT names properly (in UPPERCASE)?
       Mark out of 5: 5
   4. Did I use easy-to-understand meaningful variable names?
       Mark out of 10: 7
   5. Did I format the variable names properly (in lowerCamelCase)?
       Mark out of 10: 10
   6. Did I indent the code appropriately?
       Mark out of 10: 10
   7. Did I read the input correctly from the user using (an) appropriate question(s)?
       Mark out of 10: 10
   8. Did I compute the answer correctly for all cases?
       Mark out of 20: 20
   9. Did I output the correct answer in the correct format (as shown in the examples)?
       Mark out of 10: 10
   10. How well did I complete this self-assessment?
       Mark out of 10: 8
   Total Mark out of 100 (Add all the previous marks): 94
*/
import java.text.DecimalFormat;
import java.util.Scanner;

public class OldSterlingToDecimalSterling {

        public static final int NEW_PENNIES_IN_ONE_OLD_PENNY = 67;
        public static final int OLD_PENNIES_IN_ONE_SHILLING = 12;
        public static final int OLD_SHILLINGS_IN_ONE_OLD_POUND = 20;
        public static final int NEW_PENNIES_IN_ONE_NEW_POUND = 100;

        public static void main(String[] args) {

            System.out.println("Enter the number of OLD pounds, OLD shillings and OLD pence"
                    + " - each separated by a space.");
            Scanner inputScanner = new Scanner(System.in);
            int oldPounds = inputScanner.nextInt();
            int oldShillings = inputScanner.nextInt();
            int oldPennies = inputScanner.nextInt();
            inputScanner.close();

            int numberOfOldShillingsInOldPounds = oldPounds * OLD_SHILLINGS_IN_ONE_OLD_POUND;
            int totalOldShillings = numberOfOldShillingsInOldPounds + oldShillings;
            int totalOldPennies = (totalOldShillings * OLD_PENNIES_IN_ONE_SHILLING) + oldPennies;
            int totalInNewPennies = totalOldPennies * NEW_PENNIES_IN_ONE_OLD_PENNY;
            double totalInNewPounds = ((double) totalInNewPennies / NEW_PENNIES_IN_ONE_NEW_POUND);

            DecimalFormat df = new DecimalFormat("£0.00");

            System.out.print(oldPounds + " old pound, " + oldShillings + " old shilling and " +
                    oldPennies + " old pence = " + (df.format(totalInNewPounds)));

        }

    }

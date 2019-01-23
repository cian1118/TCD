/* SELF ASSESSMENT
   1. Did I use appropriate CONSTANTS instead of numbers within the code?
       Mark out of 5: 5
        Constants were used for each line of the song as they are repeated throughout the song.
   2. Did I use easy-to-understand, meaningful CONSTANT names formatted correctly in UPPERCASE?
       Mark out of 5: 5
        Constant names are easy to understand, they are named after each day and they are also formatted correctly.
   3. Did I use easy-to-understand meaningful variable names formatted properly (in lowerCamelCase)?
       Mark out of 10: 10
        Variable names are easily understood and are formatted correctly in lowerCamelCase.
   4. Did I indent the code appropriately?
       Mark out of 10: 10
        Code is correctly indented as the IDE auto-indent was used.
   5. Did I use an appropriate loop (or loops) to produce the different verses?
       Mark out of 20: 20
        A while loop is used to
   6. Did I use a switch to build up the verses?
       Mark out of 25: 25
        Two switch statements are used. One to get the ordinal number for the first line of each verse and another which
            prints the current verse to the console.
   7. Did I avoid duplication of code and of the lines which make up the verses (each line should be referred to in the code only once (or twice))?
       Mark out of 10: 10
        Yes, there is little to none repetition of the code and each line of the song is only used once.
   8. Does the program produce the correct output?
       Mark out of 10: 10
        The program produces the correct output of all twelve verses.
   9. How well did I complete this self-assessment?
       Mark out of 5: 5
        Marks were awarded appropriately and comments were written for each question.
   Total Mark out of 100 (Add all the previous marks): 100
*/

package Week8;

public class TwelveDaysOfChristmas {

    public static final String SECOND_LINE = "My true love sent to me:";
    public static final String DAY_TWO = "Two turtle doves";
    public static final String DAY_THREE = "Three French hens";
    public static final String DAY_FOUR = "Four calling birds";
    public static final String DAY_FIVE = "Five golden rings";
    public static final String DAY_SIX = "Six geese a laying";
    public static final String DAY_SEVEN = "Seven swans a swimming";
    public static final String DAY_EIGHT = "Eight maids a milking";
    public static final String DAY_NINE = "Nine ladies dancing";
    public static final String DAY_TEN = "Ten lords a leaping";
    public static final String DAY_ELEVEN = "Eleven pipers piping";
    public static final String DAY_TWELVE = "Twelve drummers drumming";

    public static void main(String[] args) {

        int dayOfChristmas = 1;
        String ordinalNumber = "";
        String dayOne;

        while (dayOfChristmas <= 12)
        {
            if (dayOfChristmas == 1) {
                dayOne = "A partridge in a pear tree.";
            } else {
                dayOne = "And a partridge in a pear tree.";
            }

            switch (dayOfChristmas)
            {
                case 1:
                    ordinalNumber = "first";
                    break;
                case 2:
                    ordinalNumber = "second";
                    break;
                case 3:
                    ordinalNumber = "third";
                    break;
                case 4:
                    ordinalNumber = "fourth";
                    break;
                case 5:
                    ordinalNumber = "fifth";
                    break;
                case 6:
                    ordinalNumber = "sixth";
                    break;
                case 7:
                    ordinalNumber = "seventh";
                    break;
                case 8:
                    ordinalNumber = "eighth";
                    break;
                case 9:
                    ordinalNumber = "ninth";
                    break;
                case 10:
                    ordinalNumber = "tenth";
                    break;
                case 11:
                    ordinalNumber = "eleventh";
                    break;
                case 12:
                    ordinalNumber = "twelfth";
                    break;
                default:
                    break;
            }
            System.out.println("\nOn the " + ordinalNumber + " day of Christmas" +
            "\n" + SECOND_LINE);

            switch (dayOfChristmas)
            {
                case 12:
                    System.out.println(DAY_TWELVE);
                case 11:
                    System.out.println(DAY_ELEVEN);
                case 10:
                    System.out.println(DAY_TEN);
                case 9:
                    System.out.println(DAY_NINE);
                case 8:
                    System.out.println(DAY_EIGHT);
                case 7:
                    System.out.println(DAY_SEVEN);
                case 6:
                    System.out.println(DAY_SIX);
                case 5:
                    System.out.println(DAY_FIVE);
                case 4:
                    System.out.println(DAY_FOUR);
                case 3:
                    System.out.println(DAY_THREE);
                case 2:
                    System.out.println(DAY_TWO);
                default:
                    System.out.println(dayOne);
                    break;
            }
            dayOfChristmas++;
        }
    }
}

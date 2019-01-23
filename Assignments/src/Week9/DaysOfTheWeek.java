/* SELF ASSESSMENT
 1. Did I use appropriate, easy-to-understand, meaningful CONSTANT names formatted correctly in UPPERCASE?
        Mark out of 5: 5
            Constant names are easy to understand and formatted correctly in UPPERCASE / words separated by underscores.
 2. Did I use easy-to-understand meaningful variable names formatted properly (in lowerCamelCase)?
        Mark out of 5: 5
            Variable names are meaningful and also formatted correctly in lowerCamelCase.
 3. Did I indent the code appropriately?
        Mark out of 5: 5
            Yes, indentation was checked using the IDE's auto-indent function.
 4. Did I define the required function correctly (names, parameters & return type) and invoke them correctly?
       Mark out of 20: 20
            The required functions were defined with easy-to-understand names, the correct parameters and return type.
                The functions were also invoked correctly in the main method.
 5. Did I implement the dayOfTheWeek function correctly and in a manner that can be understood?
       Mark out of 20: 20
                The dayOfTheWeek function was implemented correctly and in an understandable way. (Variable names help
                    to make the equation easier to understand).
 6. Did I implement the other functions correctly, giving credit for any code that you take from elsewhere?
       Mark out of 20: 20
            Yes, other functions were implemented correctly and credit was given for the functions validDate and
                daysInMonth which were taken from Blackboard.
 7. Did I obtain (and process) the input from the user in the correct format (dd/mm/yyyy), and deal with any invalid input properly?
       Mark out of 10: 10
            User input is obtained and processed correctly. The following delimiters can be used: / - . |
                Input is checked with the validDate function and invalid inputs are rejected.
 8. Does the program produce the output in the correct format (e.g. Monday, 25th December 2017)?
       Mark out of 10: 10
            The program produces outputs in the correct format, as required by the assignment.
 9. How well did I complete this self-assessment?
        Mark out of 5: 5
            Marks awarded for each question along with appropriate comments on every question.
 Total Mark out of 100 (Add all the previous marks): 100
*/
package Week9;

import java.util.Scanner;

public class DaysOfTheWeek {

    public static final int DAYS_IN_APR_JUN_SEP_NOV = 30;
    public static final int DAYS_IN_FEBRUARY_COMMON_YEAR = 28;
    public static final int DAYS_IN_FEBRUARY_LEAP_YEAR = 29;
    public static final int DAYS_IN_MOST_MONTHS = 31;
    public static final int NUMBER_OF_MONTHS = 12;

    public static void main(String[] args) {

        int day = 0;
        int month = 0;
        int year = 0;

        System.out.println("Enter a date in the format 'dd/mm/yyyy':");
        Scanner inputScanner = new Scanner(System.in);
        String userInput = inputScanner.nextLine();
        inputScanner.close();

        try {
            Scanner stringScanner = new Scanner(userInput);
            stringScanner.useDelimiter("/|-|\\.|\\|");

            day = stringScanner.nextInt();
            month = stringScanner.nextInt();
            year = stringScanner.nextInt();

            stringScanner.close();
        } catch (Exception exception) {
            System.out.println("Invalid input.");
            System.exit(0);
        }
        if (validDate(day, month, year)) {
            System.out.println(dayOfTheWeek(day, month, year) + ", " + day + numberEnding(day) + " " +
                    monthName(month) + " " + year);
        } else {
            System.out.println("The input is not a valid date.");
            System.exit(0);
        }
    }

    //Credit: CS1010 > Imperative Programming > Week 9. > Functions ValidDate.java
    public static boolean validDate(int day, int month, int year)
    {
        return ((year >= 0) && (month >= 1) && (month <= NUMBER_OF_MONTHS) &&
                (day >= 1) && (day <= daysInMonth(month,year)));
    }

    //Credit: CS1010 > Imperative Programming > Week 9. > Functions ValidDate.java
    public static int daysInMonth( int month, int year )
    {
        boolean isLeapYear = ((year%4 == 0) && (year%100 != 0)) || (year%400 == 0);
        int numberOfDaysInMonth;
        switch (month)
        {
            case 2:
                numberOfDaysInMonth = isLeapYear ? DAYS_IN_FEBRUARY_LEAP_YEAR : DAYS_IN_FEBRUARY_COMMON_YEAR;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                numberOfDaysInMonth = DAYS_IN_APR_JUN_SEP_NOV;
                break;
            default:
                numberOfDaysInMonth = DAYS_IN_MOST_MONTHS;
                break;
        }
        return numberOfDaysInMonth;
    }

    public static String numberEnding (int day) {
        int lastDigit = day % 10;
        String ordinalIndicator;
        switch (lastDigit)
        {
            case 1:
                ordinalIndicator = (day == 11) ? "th" : "st";
                break;
            case 2:
                ordinalIndicator = (day == 12) ? "th" : "nd";
                break;
            case 3:
                ordinalIndicator = (day == 13) ? "th" : "rd";
                break;
            default:
                ordinalIndicator = "th";
                break;
        }
        return ordinalIndicator;
    }

    public static String monthName (int month) {
        String nameOfMonth = "";
        switch (month)
        {
            case 1:
                nameOfMonth = "January";
                break;
            case 2:
                nameOfMonth = "February";
                break;
            case 3:
                nameOfMonth = "March";
                break;
            case 4:
                nameOfMonth = "April";
                break;
            case 5:
                nameOfMonth = "May";
                break;
            case 6:
                nameOfMonth = "June";
                break;
            case 7:
                nameOfMonth = "July";
                break;
            case 8:
                nameOfMonth = "August";
                break;
            case 9:
                nameOfMonth = "September";
                break;
            case 10:
                nameOfMonth = "October";
                break;
            case 11:
                nameOfMonth = "November";
                break;
            case 12:
                nameOfMonth = "December";
                break;
            default:
                break;
        }
        return nameOfMonth;
    }

    public static String dayOfTheWeek (int day, int month, int year) {
        int yearForCalc = year;

        if (month == 1 || month == 2) {
            yearForCalc -= 1;
        }

        int lastTwoDigitsOfYear = yearForCalc % 100;
        int firstTwoDigitsOfYear = yearForCalc / 100;
        int monthForCalc = ((month + 9) % 12) + 1;

        int dayOfWeekNumber = (int) (day + Math.floor((2.6 * monthForCalc) - 0.2) + lastTwoDigitsOfYear +
                Math.floor(lastTwoDigitsOfYear/4) + Math.floor(firstTwoDigitsOfYear/4) - (2 * firstTwoDigitsOfYear));

        if (dayOfWeekNumber < 0) {
            dayOfWeekNumber += 7;
        }

        dayOfWeekNumber %= 7;

        String stringDayOfWeek = "";
        switch (dayOfWeekNumber)
        {
            case 0:
                stringDayOfWeek = "Sunday";
                break;
            case 1:
                stringDayOfWeek = "Monday";
                break;
            case 2:
                stringDayOfWeek = "Tuesday";
                break;
            case 3:
                stringDayOfWeek = "Wednesday";
                break;
            case 4:
                stringDayOfWeek = "Thursday";
                break;
            case 5:
                stringDayOfWeek = "Friday";
                break;
            case 6:
                stringDayOfWeek = "Saturday";
                break;
            default:
                break;
        }
        return stringDayOfWeek;
    }
}
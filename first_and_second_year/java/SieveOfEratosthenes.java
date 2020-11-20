/* SELF ASSESSMENT
   1. createSequence:
Did I use the correct method definition?
Mark out of 5: 5
Comment: public static int[] createSequence(int number)
        returns int[] (integer array), parameter is the number, n.
Did I create an array of size n (passed as the parameter) and initialise it?
Mark out of 5: 5
Comment: Array is created from 2 - n and returned.
Did I return the correct item?
Mark out of 5: 5
Comment: the method returns an integer array as specified in the assignment.

   2. crossOutMultiples
Did I use the correct method definition?
Mark out of 5: 5
Comment: public static void crossOutHigherMultiples(int[] sequence, int number)
        void, edits the int array passed by crossing out multiples of the given number.
Did I ensure the parameters are not null and one of them is a valid index into the array
Mark out of 2: 2
Comment: if statement ensures number sequence != null and number > 1. Parameters are a valid index to the number array
        and the current multiple being used
Did I loop through the array using the correct multiple?
Mark out of 5: 5
Comment: loops through the array, if current multiple is found it is crossed out (if not already)
Did I cross out correct items in the array that were not already crossed out?
Mark out of 3: 3
Comment: correct items are crossed out by making them negative, provided they aren't already.

   3. sieve
Did I have the correct function definition?
Mark out of 5: 5
Comment: public static int[] sieve(int number)
        returns int array with non-primes crossed out up to the given number.
Did I make calls to other methods?
Mark out of 5: 5
Comment: calls made to createSequence() and crossOutHigherMultiples()
Did I return an array with all non-prime numbers are crossed out?
Mark out of 2: 2
Comment: yes, int array returned with all non-prime numbers made negative ("crossed out")

   4. sequenceToString
Did I have the correct function definition?
Mark out of 5: 5
Comment: public static String sequenceToString(int[] sequence)
        returns String of the int array parameter - crossed out numbers in square brackets.
Did I ensure the parameter to be used is not null?
Mark out of 3: 3
Comment: if statement ensures number sequence != null
Did I Loop through the array updating the String variable with the non-crossed out numbers and the crossed numbers in brackets?
Mark out of 10: 10
Comment: string is concatenated with each number in the array, if the number is crossed out, brackets are used.

   5. nonCrossedOutSubseqToString
Did I have the correct function definition
Mark out of 5: 5
Comment: public static String nonCrossedOutSubseqToString(int[] sequence)
        returns String of all the prime no.s (numbers not crossed out) in the int array parameter.
Did I ensure the parameter to be used is not null?
Mark out of 3: 3
Comment: if statement ensures the array != null before making the string.
Did I loop through the array updating the String variable with just the non-crossed out numbers?
Mark out of 5: 5
Comment: yes, if a number in the array is crossed out (i.e.: negative) it is not added to the string.

   6. main
Did I ask  the user for input n and handles input errors?
Mark out of 5: 5
Comments: input errors are dealt with properly. User is asked for number n >= 2
Did I make calls to other methods (at least one)?
Mark out of 5: 5
Comment: calls made to sieve() and nonCrossedOutSubseqToString()
Did I print the output as shown in the question?
Mark out of 5: 5
Comment: output is printed as shown in the given question

   7. Overall
Is my code indented correctly?
Mark out of 4: 4
Comments: IDE's auto-indentation function used
Do my variable names make sense?
Mark out of 4: 4
Comments: Variable names make sense and are easily understood.
Do my variable names, method names and class name follow the Java coding standard
Mark out of 4: 4
Comments: variable and method names lowerCamelCase, class name starts with capital
      Total Mark out of 100 (Add all the previous marks): 100
*/
package Week1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SieveOfEratosthenes {

    public static void main(String[] args) {
        boolean finished = false;
        int number = 0;
        System.out.println("Enter number >= 2 to find prime numbers up to that number:");
        while (!finished) {
            Scanner inputScanner = new Scanner(System.in);
            try {
                number = inputScanner.nextInt();
                if (number < 2) {
                    throw new InputMismatchException();
                }
                finished = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: enter a positive number greater than or equal to 2.");
            }
        }
        int[] sequence = sieve(number);
        System.out.print("Prime numbers: ");
        System.out.println(nonCrossedOutSubseqToString(sequence));

    }

    public static int[] createSequence(int number) {
        int[] sequence = new int[number-1];
        if (number >= 2) {
            int count = 2;
            for (int index = 0; index<sequence.length; index++, count++) {
                sequence[index] = count;
                System.out.print(sequence[index] + ((index!=sequence.length-1) ? ", ":".\n"));
            }
        }
        return sequence;
    }

    //Multiples are made negative when being crossed out.
    public static void crossOutHigherMultiples(int[] sequence, int number) {
        if (sequence != null && number > 1) {
            int maxNumb = Math.abs(sequence[sequence.length - 1]);
            if (number * number < maxNumb) {
                int currNumb = 0;
                for (int count = 2; currNumb <= maxNumb; count++) {
                    currNumb = number * count;
                    for (int index = 0; index < sequence.length; index++) {
                        if (currNumb == sequence[index]) {
                            sequence[index] *= -1;
                        }
                    }
                }
                System.out.println(sequenceToString(sequence));
            }
        }
    }

    public static String sequenceToString(int[] sequence) {
        if (sequence != null) {
            String sequenceString = "";
            for (int index = 0; index < sequence.length; index++) {
                if (sequence[index] < 0) {
                    String currNumb = Integer.toString(sequence[index] * -1);
                    sequenceString += "[";
                    sequenceString += currNumb;
                    sequenceString += "]";
                } else {
                    sequenceString += sequence[index];
                }
                sequenceString += (index == sequence.length - 1) ? "." : ", ";
            }
            return sequenceString;
        }
        else {
            return "Error: array == null.";
        }
    }

    public static int[] sieve(int number) {
        int[] sequence = createSequence(number);
        int multiple = 2;
        for (int index = 0; index<sequence.length; index++) {
            if (sequence[index] > 0) {
                multiple = sequence[index];
                crossOutHigherMultiples(sequence, multiple);
            }
        }
        return sequence;
    }

    public static String nonCrossedOutSubseqToString(int[] sequence) {
        if (sequence != null) {
            String primesString = "";
            for (int index = 0; index < sequence.length; index++) {
                if (index == 0) {
                    primesString += sequence[index];
                } else if (sequence[index] > 0) {
                    primesString += ", ";
                    primesString += sequence[index];
                }
            }
            primesString += ".";
            return primesString;
        } else {
            return "Error: array == null";
        }
    }
}
package Week5;

import java.util.Scanner;

public class TestClient {
    public static void main(String[] args) throws NoSuchFieldException {

        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter 1st rational's NUMERATOR.");
        int firstNumerator = inputScanner.nextInt();
        System.out.println("Enter 1st rational's DENOMINATOR.");
        int firstDenominator = inputScanner.nextInt();
        Rational firstNumber = new Rational(firstNumerator, firstDenominator);
        System.out.println(firstNumber.toString());

        System.out.println("Enter 2nd rational's NUMERATOR");
        int secondNumerator = inputScanner.nextInt();
        System.out.println("Enter 2nd rational's DENOMINATOR.");
        int secondDenominator = inputScanner.nextInt();
        Rational secondNumber = new Rational(secondNumerator, secondDenominator);
        System.out.println(secondNumber.toString());

        System.out.println("\nAdded: " + firstNumber.add(secondNumber).toString() +
                "\nSubtracted: " + firstNumber.subtract(secondNumber).toString() +
                "\nMultiplied: " + firstNumber.multiply(secondNumber).toString() +
                "\nDivided: " + firstNumber.divide(secondNumber).toString() +
                "\n" + firstNumber.toString() + " less than " + secondNumber.toString() + "? " +
                firstNumber.isLessThan(secondNumber) +
                "\n" + firstNumber.toString() + " equals " + secondNumber.toString() + "? " +
                firstNumber.equals(secondNumber) +
                "\nFirst number simplified: " + firstNumber.simplify().toString() +
                "\nSecond number simplified: " + secondNumber.simplify().toString());

    }
}
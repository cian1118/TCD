//package BonusAssignment; // TODO remove package
// -------------------------------------------------------------------------

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

import static java.util.Collections.reverse;

/**
 *  Utility class containing validation/evaluation/conversion operations
 *  for prefix and postfix arithmetic expressions.
 *
 *  @author Cian Higgins
 *  @version 6/12/18
 */

public class Arith
{
    //~ Validation methods ..........................................................


    /**
     * Validation method for prefix notation.
     *
     * @param prefixLiterals : an array containing the string literals hopefully in prefix order.
     * The method assumes that each of these literals can be one of:
     * - "+", "-", "*", or "/"
     * - or a valid string representation of an integer.
     *
     * @return true if the parameter is indeed in prefix notation, and false otherwise.
     **/
    public static boolean validatePrefixOrder(String prefixLiterals[])
    {
        int count = 1;
        int index = 0;

        while (count > 0 && index < prefixLiterals.length) {
            String current = prefixLiterals[index];
            if (current.matches("[+]|-|[*]|/")) {
                count++;
            } else if (current.matches("\\d+")) {
                count--;
            } else {
                throw new NumberFormatException();
            }

            index++;
        }
        return (count == 0);
    }


    /**
     * Validation method for postfix notation.
     *
     * @param postfixLiterals : an array containing the string literals hopefully in postfix order.
     * The method assumes that each of these literals can be one of:
     * - "+", "-", "*", or "/"
     * - or a valid string representation of an integer.
     *
     * @return true if the parameter is indeed in postfix notation, and false otherwise.
     **/
    public static boolean validatePostfixOrder(String postfixLiterals[]) {
        int count = 0;
        int index = 0;

        while (count >= 0 && index < postfixLiterals.length) {
            String current = postfixLiterals[index];
            if (current.matches("\\d+")) {
                count++;
            } else if (current.matches("[+]|-|[*]|/")) {
                count--;
            } else {
                throw new NumberFormatException();
            }
            index++;
        }
        return (count == 1);
    }


    //~ Evaluation  methods ..........................................................


    /**
     * Evaluation method for prefix notation.
     *
     * @param prefixLiterals : an array containing the string literals in prefix order.
     * The method assumes that each of these literals can be one of:
     * - "+", "-", "*", or "/"
     * - or a valid string representation of an integer.
     *
     * @return the integer result of evaluating the expression
     **/
    public static int evaluatePrefixOrder(String prefixLiterals[])
    {
        int index = prefixLiterals.length - 1;
        Stack<String> stack = new Stack<>();
        while (index >= 0) {
            String current = prefixLiterals[index];
            if (current.matches("\\d+")) {
                stack.push(current);
            } else if (current.matches("[+]|-|[*]|/")) {
                int n1 = Integer.valueOf(stack.pop());
                int n2 = Integer.valueOf(stack.pop());

                int result = calculateResult(current, n2, n1);
                //prefix evaluation: n1 p n2
                //postfix evaluation: n2 p n1
                String resultStr = Integer.toString(result);
                stack.push(resultStr);

            } else {
                throw new NumberFormatException();
            }
            index--;
        }
        return Integer.valueOf(stack.pop());
    }


    /**
     * Evaluation method for postfix notation.
     *
     * @param postfixLiterals : an array containing the string literals in postfix order.
     * The method assumes that each of these literals can be one of:
     * - "+", "-", "*", or "/"
     * - or a valid string representation of an integer.
     *
     * @return the integer result of evaluating the expression
     **/
    public static int evaluatePostfixOrder(String postfixLiterals[])
    {
        int index = 0;
        Stack<String> stack = new Stack<>();
        while (index < postfixLiterals.length) {
            String current = postfixLiterals[index];
            if (current.matches("\\d+")) {
                stack.push(current);
            } else if (current.matches("[+]|-|[*]|/")) {
                int n1 = Integer.valueOf(stack.pop());
                int n2 = Integer.valueOf(stack.pop());

                int result = calculateResult(current, n1, n2);
                String resultStr = Integer.toString(result);
                stack.push(resultStr);
            } else {
                throw new NumberFormatException();
            }
            index++;
        }
        return Integer.valueOf(stack.pop());
    }


    //~ Conversion  methods ..........................................................


    /**
     * Converts prefix to postfix.
     *
     * @param prefixLiterals : an array containing the string literals in prefix order.
     * The method assumes that each of these literals can be one of:
     * - "+", "-", "*", or "/"
     * - or a valid string representation of an integer.
     *
     * @return the expression in postfix order.
     **/
    public static String[] convertPrefixToPostfix(String prefixLiterals[])
    {
        Stack<String> stack = new Stack<>();
        int index = prefixLiterals.length - 1;

        while(index >= 0) {
            String current = prefixLiterals[index];
            stackOperation(current, stack);
            index--;
        }
        String[] postfix = new String[stack.size()];
        stack.toArray(postfix);

        return postfix;
    }


    /**
     * Converts postfix to prefix.
     *
     * @param postfixLiterals : an array containing the string literals in postfix order.
     * The method assumes that each of these literals can be one of:
     * - "+", "-", "*", or "/"
     * - or a valid string representation of an integer.
     *
     * @return the expression in prefix order.
     **/
    public static String[] convertPostfixToPrefix(String postfixLiterals[])
    {
        Stack<String> stack = new Stack<>();
        int index = 0;

        while(index < postfixLiterals.length) {
            String current = postfixLiterals[index];
            stackOperation(current, stack);
            index++;
        }
        String[] prefix = new String[stack.size()];
        reverse(stack);
        stack.toArray(prefix);

        for (int i = 0; i < prefix.length; i++) {

        }
        return prefix;
    }

    private static void stackOperation(String current, Stack<String> stack) {
        if (current.matches("\\d+")) {
            stack.push(current);
        } else if (current.matches("[+]|-|[*]|/")) {
            if (!stack.peek().matches("[+]|-|[*]|/")) {
                String s1 = stack.pop();
                String s2 = stack.pop();

                stack.push(s1);
                stack.push(s2);
            }
            stack.push(current);
        }
    }
    /**
     * Converts prefix to infix.
     *
     * @param infixLiterals : an array containing the string literals in prefix order.
     * The method assumes that each of these literals can be one of:
     * - "+", "-", "*", or "/"
     * - or a valid string representation of an integer.
     *
     * @return the expression in infix order.
     **/
    public static String[] convertPrefixToInfix(String prefixLiterals[])
    {
        Stack<String> stack = new Stack<>();
        int index = prefixLiterals.length-1;
        while (index >=0) {
            if (prefixLiterals[index].matches("\\d+")) {
                stack.push(prefixLiterals[index]);
            } else if (prefixLiterals[index].matches("[+]|-|[*]|/")) {
                String s1 = stack.pop();
                String s2 = stack.pop();
                String newStr =  "(" + " "+ s1 + " " +prefixLiterals[index]+" "+ s2 + " "+ ")";
                stack.push(newStr);
            } else {
                throw new NumberFormatException();
            }
            index--;
        }

        return stack.pop().split("\\s+");
    }

    /**
     * Converts postfix to infix.
     *
     * @param infixLiterals : an array containing the string literals in postfix order.
     * The method assumes that each of these literals can be one of:
     * - "+", "-", "*", or "/"
     * - or a valid string representation of an integer.
     *
     * @return the expression in infix order.
     **/
    public static String[] convertPostfixToInfix(String postfixLiterals[])
    {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < postfixLiterals.length; i++) {
            if((postfixLiterals[i]).matches("\\d+"))
                stack.push(postfixLiterals[i]);
            else if (postfixLiterals[i].matches("[+]|-|[*]|/"))
            {
                String s1 = stack.pop();
                String s2 = stack.pop();
                String newStr =  "(" + " "+ s2 + " " +postfixLiterals[i]+" "+ s1 + " "+ ")";
                stack.push(newStr);
            }
        }
        return stack.pop().split("\\s+");
    }

    private static int calculateResult(String operator, int n1, int n2) {
        int result = 0;
        switch (operator) {
            case "*":
                result = n2 * n1;
                break;
            case "/":
                result = n2 / n1;
                break;
            case "+":
                result = n2 + n1;
                break;
            case "-":
                result = n2 - n1;
                break;
            default:
                break;
        }
        return result;
    }

}
//package BonusAssignment; // TODO remove package

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static Arith.*;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ArithTest {

    @Test
    public void testValidatePrefixOrder() {
        String[] prefix = {"*","-","1","2","3"};
        assertTrue(validatePrefixOrder(prefix));

        prefix = new String[] {"+", "*", "-", "1", "2", "3", "-", "10", "+", "3", "/", "6", "3"};
        assertTrue(validatePrefixOrder(prefix));
    }

    @Test
    public void testValidatePostfixOrder() {
        String[] postfix = {"3","1","2","-","*"};
        assertTrue(validatePostfixOrder(postfix));

        postfix = new String[] {"20","3","*","2","5","-","+","2","6","3","/","1","-","*","*"};
        assertTrue(validatePostfixOrder(postfix));

        postfix = new String[] {"1","2","-","3","*","10","3","6","3","/","+","-","+"};
        assertTrue(validatePostfixOrder(postfix));
    }

    @Test
    public void testEvaluatePrefixOrder() {
        String[] prefix = {"*","-","1","2","3"};
        assertEquals(-3, evaluatePrefixOrder(prefix));

        prefix = new String[] {"+", "*", "-", "1", "2", "3", "-", "10", "+", "3", "/", "6", "3"};
        assertEquals(2, evaluatePrefixOrder(prefix));
    }

    @Test
    public void testEvaluatePostfixOrder() {
        String[] postfix = {"3","1","2","-","*"};
        assertEquals(-3, evaluatePostfixOrder(postfix));

        postfix = new String[] {"10","2","8","*","+", "3","-"};
        assertEquals(23, evaluatePostfixOrder(postfix));

        postfix = new String[] {"20","3","*","2","5","-","+","2","6","3","/","1","-","*","*"};
        assertEquals(114, evaluatePostfixOrder(postfix));
    }

    @Test
    public void testConvertPrefixToPostfix() {
        String[] prefix = {"*","-","1","2","3"};
        String[] postfix = {"3","1","2","-","*"};
        assertEquals(postfix, convertPrefixToPostfix(prefix));

        prefix  = new String[] {"*","3","2"};
        postfix = new String[] {"3","2","*"};
        assertEquals(postfix,convertPrefixToPostfix(prefix));
    }

    @Test
    public void testConvertPostfixToPrefix() {
        String[] postfix = {"3","1","2","-","*"};
        String[] prefix = {"*","-","1","2","3"};
        assertEquals(prefix, convertPostfixToPrefix(postfix));

        postfix = new String[] {"3","2","*"};
        prefix  = new String[] {"*","3","2"};
        assertEquals(prefix,convertPostfixToPrefix(postfix));
    }

    @Test
    public void testConvertPrefixToInfix() {
        String prefix [] = {"*","+","4","3","+","22","100"};
        String infixLiterals[] = {"(","(","4","+","3",")", "*", "(","22", "+","100",")",")"};

        assertArrayEquals(infixLiterals, convertPrefixToInfix(prefix));
    }

    @Test
    public void testConvertPostfixToInfix() {
        String infixLiterals[] = {"(","(","16","-","67",")", "*", "(","3", "+","99",")",")"};
        String postfixLiterals[] = {"16", "67", "-", "3", "99","+", "*"};

        assertArrayEquals(infixLiterals, convertPostfixToInfix(postfixLiterals));
    }

}

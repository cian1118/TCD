//package BonusAssignment; // TODO remove package

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ArithTest {

    @Test
    public void testValidatePrefixOrder() {
        String[] prefix = {"*","-","1","2","3"};
        assertTrue(Arith.validatePrefixOrder(prefix));

        prefix = new String[] {"+", "*", "-", "1", "2", "3", "-", "10", "+", "3", "/", "6", "3"};
        assertTrue(Arith.validatePrefixOrder(prefix));
    }

    @Test
    public void testValidatePostfixOrder() {
        String[] postfix = {"3","1","2","-","*"};
        assertTrue(Arith.validatePostfixOrder(postfix));

        postfix = new String[] {"20","3","*","2","5","-","+","2","6","3","/","1","-","*","*"};
        assertTrue(Arith.validatePostfixOrder(postfix));

        postfix = new String[] {"1","2","-","3","*","10","3","6","3","/","+","-","+"};
        assertTrue(Arith.validatePostfixOrder(postfix));
    }

    @Test
    public void testEvaluatePrefixOrder() {
        String[] prefix = {"*","-","1","2","3"};
        assertEquals(-3, Arith.evaluatePrefixOrder(prefix));

        prefix = new String[] {"+", "*", "-", "1", "2", "3", "-", "10", "+", "3", "/", "6", "3"};
        assertEquals(2, Arith.evaluatePrefixOrder(prefix));
    }

    @Test
    public void testEvaluatePostfixOrder() {
        String[] postfix = {"3","1","2","-","*"};
        assertEquals(-3, Arith.evaluatePostfixOrder(postfix));

        postfix = new String[] {"10","2","8","*","+", "3","-"};
        assertEquals(23, Arith.evaluatePostfixOrder(postfix));

        postfix = new String[] {"20","3","*","2","5","-","+","2","6","3","/","1","-","*","*"};
        assertEquals(114, Arith.evaluatePostfixOrder(postfix));
    }

    @Test
    public void testConvertPrefixToPostfix() {
        String[] prefix = {"*","-","1","2","3"};
        String[] postfix = {"3","1","2","-","*"};
        assertEquals(postfix, Arith.convertPrefixToPostfix(prefix));

        prefix  = new String[] {"*","3","2"};
        postfix = new String[] {"3","2","*"};
        assertEquals(postfix,Arith.convertPrefixToPostfix(prefix));
    }

    @Test
    public void testConvertPostfixToPrefix() {
        String[] postfix = {"3","1","2","-","*"};
        String[] prefix = {"*","-","1","2","3"};
        assertEquals(prefix, Arith.convertPostfixToPrefix(postfix));

        postfix = new String[] {"3","2","*"};
        prefix  = new String[] {"*","3","2"};
        assertEquals(prefix,Arith.convertPostfixToPrefix(postfix));
    }

    @Test
    public void testConvertPrefixToInfix() {
        String prefix [] = {"*","+","4","3","+","22","100"};
        String infixLiterals[] = {"(","(","4","+","3",")", "*", "(","22", "+","100",")",")"};

        assertArrayEquals(infixLiterals, Arith.convertPrefixToInfix(prefix));
    }

    @Test
    public void testConvertPostfixToInfix() {
        String infixLiterals[] = {"(","(","16","-","67",")", "*", "(","3", "+","99",")",")"};
        String postfixLiterals[] = {"16", "67", "-", "3", "99","+", "*"};

        assertArrayEquals(infixLiterals, Arith.convertPostfixToInfix(postfixLiterals));
    }

}

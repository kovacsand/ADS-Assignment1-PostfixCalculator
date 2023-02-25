import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PostfixCalculatorImplTest
{
    Calculator calculator;

    @org.junit.jupiter.api.BeforeEach
    void setUp()
    {
        calculator = new PostfixCalculatorImpl();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown()
    {
        calculator = null;
    }

    @org.junit.jupiter.api.Test
    void correctExampleFromBook() throws Exception
    {
        ArrayList<Token> tokens = new ArrayList<>(
                Arrays.asList(
                        new Operand(6),
                        new Operand(5),
                        new Operand(2),
                        new Operand(3),
                        new Operator('+'),
                        new Operand(8),
                        new Operator('*'),
                        new Operator('+'),
                        new Operand(3),
                        new Operator('+'),
                        new Operator('*')
                ));

        assertEquals(288, calculator.evaluateExpression(tokens));
    }

    @org.junit.jupiter.api.Test
    void correctExampleFromSlides() throws Exception
    {
        ArrayList<Token> tokens = new ArrayList<>(
                Arrays.asList(
                        new Operand(3),
                        new Operand(4),
                        new Operand(7),
                        new Operator('*'),
                        new Operand(2),
                        new Operator('/'),
                        new Operator('+')
                ));

        assertEquals(17, calculator.evaluateExpression(tokens));
    }

    @org.junit.jupiter.api.Test
    void correctExampleWithDecimal() throws Exception
    {
        ArrayList<Token> tokens = new ArrayList<>(
                Arrays.asList(
                        new Operand(6),
                        new Operand(4),
                        new Operator('/'),
                        new Operand(2),
                        new Operator('+'),
                        new Operand(3),
                        new Operator('*'),
                        new Operand(7),
                        new Operator('-')
                ));

        assertEquals(4, calculator.evaluateExpression(tokens));
    }

    @org.junit.jupiter.api.Test
    void oneAddition() throws Exception
    {
        ArrayList<Token> tokens = new ArrayList<>(
                Arrays.asList(
                        new Operand(4),
                        new Operand(7),
                        new Operator('+')
                ));

        assertEquals(11, calculator.evaluateExpression(tokens));
    }

    @org.junit.jupiter.api.Test
    void oneSubtraction() throws Exception
    {
        ArrayList<Token> tokens = new ArrayList<>(
                Arrays.asList(
                        new Operand(4),
                        new Operand(7),
                        new Operator('-')
                ));

        assertEquals(-3, calculator.evaluateExpression(tokens));
    }

    @org.junit.jupiter.api.Test
    void oneMultiplication() throws Exception
    {
        ArrayList<Token> tokens = new ArrayList<>(
                Arrays.asList(
                        new Operand(3),
                        new Operand(6),
                        new Operator('*')
                ));

        assertEquals(18, calculator.evaluateExpression(tokens));
    }

    @org.junit.jupiter.api.Test
    void oneDivision() throws Exception
    {
        ArrayList<Token> tokens = new ArrayList<>(
                Arrays.asList(
                        new Operand(8),
                        new Operand(2),
                        new Operator('/')
                ));

        assertEquals(4, calculator.evaluateExpression(tokens));
    }


    @org.junit.jupiter.api.Test
    void oneDivisionRounded() throws Exception
    {
        ArrayList<Token> tokens = new ArrayList<>(
                Arrays.asList(
                        new Operand(9),
                        new Operand(5),
                        new Operator('/')
                ));

        assertEquals(2, calculator.evaluateExpression(tokens));
    }

    @org.junit.jupiter.api.Test
    void oneDivisionByZero()
    {
        ArrayList<Token> tokens = new ArrayList<>(
                Arrays.asList(
                        new Operand(8),
                        new Operand(0),
                        new Operator('/')
                ));

        Exception thrown = assertThrows(
                Exception.class,
                () -> calculator.evaluateExpression(tokens),
                "Math error, cannot divide by 0"
        );

        assertTrue(thrown.getMessage().contentEquals("Math error, cannot divide by 0"));
    }



    @org.junit.jupiter.api.Test
    void errorNoTokens()
    {
        ArrayList<Token> tokens = new ArrayList<>(
                Arrays.asList(

                ));

        Exception thrown = assertThrows(
                Exception.class,
                () -> calculator.evaluateExpression(tokens),
                "Invalid argument, expression doesn't contain tokens"
        );

        assertTrue(thrown.getMessage().contentEquals("Invalid argument, expression doesn't contain tokens"));
    }

    @org.junit.jupiter.api.Test
    void errorOperatorBeforeTwoOperands()
    {
        ArrayList<Token> tokens = new ArrayList<>(
                Arrays.asList(
                        new Operand(8),
                        new Operator('/')
                ));

        Exception thrown = assertThrows(
                Exception.class,
                () -> calculator.evaluateExpression(tokens),
                "Operator before having two operands"
        );

        assertTrue(thrown.getMessage().contentEquals("Operator before having two operands"));
    }

    @org.junit.jupiter.api.Test
    void errorMoreThanPlusTwoOperandsThanOperators()
    {
        ArrayList<Token> tokens = new ArrayList<>(
                Arrays.asList(
                        new Operand(8),
                        new Operand(4),
                        new Operand(9),
                        new Operand(10),
                        new Operator('/')
                ));

        Exception thrown = assertThrows(
                Exception.class,
                () -> calculator.evaluateExpression(tokens),
                "For n operators more than n+1 operands were provided"
        );

        assertTrue(thrown.getMessage().contentEquals("For n operators more than n+1 operands were provided"));
    }
}
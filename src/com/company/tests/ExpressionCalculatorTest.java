package com.company.tests;

import com.company.ExpressionCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionCalculatorTest {

    @org.junit.jupiter.api.Test
    void whenExpressionContainsLettersExceptionIsThrown() {
        String expression = "This expresion should not work";

        Exception exception = assertThrows(RuntimeException.class, () -> {
            ExpressionCalculator.calculateExpression(expression);
        });

        String expectedMessage = "Unexpected char:";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void whenExpressionContainsEmptySpacesItShouldWorkAnyway() throws Exception {
        String expression = "1 + (25 * 2)";
        double result = 51;
        assertTrue(result == ExpressionCalculator.calculateExpression(expression));
    }

    @Test
    void whenPassingMoreThenOneOperatorsTheLastOneIsEvaluated() throws Exception {
        String expression = "5+-2";
        double result = 3.0;
        assertTrue(result == ExpressionCalculator.calculateExpression(expression));
    }

    @Test
    void whenNothingIsPassedToCalculatorExceptionIsThrown() throws Exception {
        String expression = "    ";

        double result = 0;
        assertTrue(result == ExpressionCalculator.calculateExpression(expression));
    }


}
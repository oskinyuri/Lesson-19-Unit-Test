package com.example.lesson_19_unit_test;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    private static Calculator sCalculator;

    @BeforeClass
    public static void initCalculator(){
        sCalculator = new Calculator();
    }

    @Test
    public void calculatePlusOperation() {
        float expected = 15+5;
        float result = sCalculator.calculate(15, 5, Calculator.OPERATION_PLUS);
        assertEquals(expected, result, 0);
    }

    @Test
    public void calculateMinusOperation() {
        float expected = 15-5;
        float result = sCalculator.calculate(15, 5, Calculator.OPERATION_MINUS);
        assertEquals(expected, result, 0);
    }

    @Test
    public void calculateDivideOperation() {
        float expected = 15 / 5;
        float result = sCalculator.calculate(15, 5, Calculator.OPERATION_DIVIDE);
        assertEquals(expected, result, 0);
    }

    @Test
    public void calculateMultOperation() {
        float expected = 15*5;
        float result = sCalculator.calculate(15, 5, Calculator.OPERATION_MULTIPLICATION);
        assertEquals(expected, result, 0);
    }

    @Test
    public void getLastOperation() {
        String string = sCalculator.getLastOperation();
        assertEquals("Error", "15.0 + 5.0", sCalculator.getLastOperation());
    }
}
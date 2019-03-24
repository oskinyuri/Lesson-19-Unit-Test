package com.example.lesson_19_unit_test;

import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class CalculatorTest {

    private static Calculator sCalculator;

    @BeforeClass
    public static void initCalculator() {
        sCalculator = new Calculator();
    }

    @Test
    public void calculatePlusOperation() {
        float expected = 15 + 5;
        float result = sCalculator.calculate(15, 5, Calculator.OPERATION_PLUS);
        assertEquals(expected, result, 0);
    }

    @Test
    public void calculateMinusOperation() {
        float expected = 15 - 5;
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
        float expected = 15 * 5;
        float result = sCalculator.calculate(15, 5, Calculator.OPERATION_MULTIPLICATION);
        assertEquals(expected, result, 0);
    }

    @Test
    public void getLastOperation() {
        String string = sCalculator.getLastOperation();
        assertEquals("Error", "15.0 + 5.0", sCalculator.getLastOperation());
    }

    //Тестирование приватого метода
    @Test
    public void testPrivateMethod() {
        float expected = 10 / 2;
        Calculator calculator = new Calculator();
        try {
            Method privateMethod = Calculator.class.getDeclaredMethod("divide", float.class, float.class);
            privateMethod.setAccessible(true);
            Float result = (Float) privateMethod.invoke(calculator, 10, 2);
            privateMethod.setAccessible(false);
            assertEquals(expected, result, 0);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
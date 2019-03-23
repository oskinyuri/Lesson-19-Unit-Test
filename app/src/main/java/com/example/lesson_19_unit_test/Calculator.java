package com.example.lesson_19_unit_test;

public class Calculator {

    public static final int OPERATION_PLUS = 10;
    public static final int OPERATION_MINUS = 11;
    public static final int OPERATION_DIVIDE = 12;
    public static final int OPERATION_MULTIPLICATION = 13;

    private String lastOperation;

    public float calculate(float firstOperand, float secondOperand, int operation) {
        switch (operation) {
            case OPERATION_PLUS:
                return plus(firstOperand, secondOperand);
            case OPERATION_MINUS:
                return minus(firstOperand, secondOperand);
            case OPERATION_MULTIPLICATION:
                return multiplication(firstOperand, secondOperand);
            case OPERATION_DIVIDE:
                return divide(firstOperand, secondOperand);
            default:
                return 0;
        }
    }

    private float plus(float firstOperand, float secondOperand) {
        float result = firstOperand + secondOperand;
        lastOperation = firstOperand + " + " + secondOperand;
        return result;
    }

    private float minus(float firstOperand, float secondOperand) {
        float result = firstOperand - secondOperand;
        lastOperation = firstOperand + " - " + secondOperand;
        return result;
    }

    private float multiplication(float firstOperand, float secondOperand) {
        float result = firstOperand * secondOperand;
        lastOperation = firstOperand + " * " + secondOperand;
        return result;
    }

    private float divide(float firstOperand, float secondOperand) {
        float result = firstOperand / secondOperand;
        lastOperation = firstOperand + " / " + secondOperand;
        return result;
    }

    public String getLastOperation() {
        return lastOperation;
    }

}

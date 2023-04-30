package com.houarizegai.calculator;

import com.houarizegai.calculator.ui.CalculatorUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class OperationsTest {

    private CalculatorUI calculatorUI;


    @BeforeEach
    void setUp() {
        calculatorUI = new CalculatorUI();
    }

    // Testing New PI button
    @Test
    void PITest (){
        double firstNum = Math.PI;
        double secondNum = 2;
        try {
            assertEquals(1.1415926535897931, calculatorUI.calculate(firstNum, secondNum, '-'));
            assertEquals(5.1415926535897931, calculatorUI.calculate(firstNum, secondNum, '+'));
            assertEquals(6.283185307179586, calculatorUI.calculate(firstNum, secondNum, '*'));
            assertEquals(1.5707963267948966, calculatorUI.calculate(firstNum, secondNum, '/'));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Testing New Euler's number (e) button
    @Test
    void EulerNumTest (){
        double firstNum = Math.E;
        double secondNum = 2;
        try {
            assertEquals(0.7182818284590451, calculatorUI.calculate(firstNum, secondNum, '-'));
            assertEquals(4.718281828459045, calculatorUI.calculate(firstNum, secondNum, '+'));
            assertEquals(5.43656365691809, calculatorUI.calculate(firstNum, secondNum, '*'));
            assertEquals(1.3591409142295225, calculatorUI.calculate(firstNum, secondNum, '/'));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void dividebyZeroTest(){
        double firstNum = 10;
        double secondNum = 0;
        try {
            assertEquals("Infinity", String.valueOf(calculatorUI.calculate(firstNum, secondNum, '/')));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void powerFunctionalityTest() {

        //positive exponent
        double firstNum = 10;
        double secondNum = 5;

        //negative exponent
        double thirdNum = 10;
        double fourthNum = -5;

        //zeroth power
        double fifthNum = 5;
        double sixthNum = 0;

        try {
            assertEquals(100000, calculatorUI.calculate(firstNum, secondNum, '^'));
            assertEquals(0.00001, calculatorUI.calculate(thirdNum, fourthNum, '^'));
            assertEquals(1, calculatorUI.calculate(fifthNum, sixthNum, '^'));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
 
    @Test
    void addFunctionalityTest() {
        double firstNum = 10;
        double secondNum = 5;
        try {
            assertEquals(15, calculatorUI.calculate(firstNum, secondNum, '+'));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void subtractFunctionalityTest() {
        double firstNum = 10;
        double secondNum = 5;
        try {
            assertEquals(5, calculatorUI.calculate(firstNum, secondNum, '-'));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void multiplyFunctionalityTest() {
        double firstNum = 10;
        double secondNum = 5;
        try {
            assertEquals(50, calculatorUI.calculate(firstNum, secondNum, '*'));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void divideFunctionalityTest() {
        double firstNum = 10;
        double secondNum = 5;
        try {
            assertEquals(2, calculatorUI.calculate(firstNum, secondNum, '/'));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    void modFunctionalityTest() {
        double firstNum = 10;
        double secondNum = 7;
        try {
            assertEquals(3, calculatorUI.calculate(firstNum, secondNum, '%'));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

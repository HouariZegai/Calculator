package com.houarizegai.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() { // Create object before compilation
        calculator = new Calculator();
    }

    /*
     * testCalc() test method
     */
    @Test
    void testCalc() {
        double first = 3;
        String non = "";
        String second = "5";

        try {
            Assertions.assertEquals(8, calculator.calc(first, second, '+'));
            Assertions.assertEquals(-2, calculator.calc(first, second, '-'));
            Assertions.assertEquals(15, calculator.calc(first, second, '*'));
            Assertions.assertEquals(0.6, calculator.calc(first, second, '/'));
            Assertions.assertEquals(3d, calculator.calc(first, second, '%'));
            Assertions.assertEquals(243, calculator.calc(first, second, '^'));
            Assertions.assertEquals(0.05233595624294383272211862960908, calculator.calc(first,non,'S'));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

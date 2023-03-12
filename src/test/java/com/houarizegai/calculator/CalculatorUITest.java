package com.houarizegai.calculator;

import com.houarizegai.calculator.ui.CalculatorUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorUITest {

    private CalculatorUI calculatorUI;

    @BeforeEach
    void setUp() {
        calculatorUI = new CalculatorUI();
    }

    @ParameterizedTest
    @CsvSource({"3,5,+,8", "2,8,-,-6", "44.5,10,*,445", "320,5,/,64", "3,5,%,3", "5,3,^,125"})
    void testCalculation(double firstNumber, double secondNumber, char operator, double expectedResult) {
        assertEquals(expectedResult, calculatorUI.calculate(firstNumber, secondNumber, operator));
    }
}

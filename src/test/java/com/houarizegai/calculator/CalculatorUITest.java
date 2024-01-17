package com.houarizegai.calculator;

import com.houarizegai.calculator.ui.CalculatorUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @Test
    public void testCalculate(){
        double result = new CalculatorUI().calculate(1,2,'+');
        double result1 = new CalculatorUI().calculate(1,2,'*');
        double result2 = new CalculatorUI().calculate(1,2,'/');
        double result3 = new CalculatorUI().calculate(1,2,'%');
        double result4 = new CalculatorUI().calculate(1,2,'^');
        double result5 = new CalculatorUI().calculate(1,2,'-');
        double result6 = new CalculatorUI().calculate(1,2,' ');
        assertNotNull(result);assertNotNull(result1);assertNotNull(result2);assertNotNull(result3);assertNotNull(result4);assertNotNull(result5);
    }
}

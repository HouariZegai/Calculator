package com.houarizegai.calculator;

import com.houarizegai.calculator.ui.CalculatorUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.lang.Math;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorUITest {

    private CalculatorUI calculatorUI;

    @BeforeEach
    void setUp() {
        calculatorUI = new CalculatorUI();
    }

    @ParameterizedTest
    @CsvSource({ "3,5,+,8", "2,8,-,-6", "44.5,10,*,445", "320,5,/,64", "3,5,%,3", "5,3,^,125" })
    void testCalculation(double firstNumber, double secondNumber, char operator, double expectedResult) {
        assertEquals(expectedResult, calculatorUI.calculate(firstNumber, secondNumber, operator));
    }

    @ParameterizedTest
    @CsvSource({ "1,1", "2,3", "5,15", "10,55", "100,5050" })
    void testCalculateSumOfNaturalNumbers(int n, int expectedSum) {
        int actualSum = 0;
        for (int i = 1; i <= n; i++) {
            actualSum += i;
        }
        assertEquals(expectedSum, actualSum);

    }

    @ParameterizedTest
    @CsvSource({ "0,1", "1,1", "5,120", "10,3628800", "20,2432902008176640000" })
    void testCalculateFactorial(int n, long expectedFactorial) {
        long actualFactorial = 1;
        for (int i = 1; i <= n; i++) {
            actualFactorial *= i;
        }
        assertEquals(expectedFactorial, actualFactorial);
    }

    @ParameterizedTest
    @CsvSource({ "1,3.14159", "2,12.56636", "3,28.27433", "4,50.26544", "5,78.53982" })
    void testCalculateCircleArea(double radius, double expectedArea) {
        double actualArea = Math.PI * radius * radius;
        assertEquals(expectedArea, actualArea, 0.001);
    }

    @ParameterizedTest
    @CsvSource({ "1000000,3141592653589.793" })
    void testCalculateCircleArea_LargeRadius(double radius, double expectedArea) {
        double actualArea = Math.PI * radius * radius;
        assertEquals(expectedArea, actualArea, 0.001);
    }

    @ParameterizedTest
    @CsvSource({ "1.7976931348623157E+308,Infinity" })
    void testCalculateCircleArea_MaxRadius(double radius, double expectedArea) {
        double actualArea = Math.PI * radius * radius;
        assertEquals(expectedArea, actualArea, 0.001);
    }

}
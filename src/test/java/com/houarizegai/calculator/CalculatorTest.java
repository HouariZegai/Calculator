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
        String second = "5";

        try {
            Assertions.assertEquals(8, calculator.calc(first, second, '+'));
            Assertions.assertEquals(-2, calculator.calc(first, second, '-'));
            Assertions.assertEquals(15, calculator.calc(first, second, '*'));
            Assertions.assertEquals(0.6, calculator.calc(first, second, '/'));
            Assertions.assertEquals(3d, calculator.calc(first, second, '%'));
            Assertions.assertEquals(243, calculator.calc(first, second, '^'));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
  @Test
    void testAdd() {
	double firstAdd = 9;
        String firstAddNumTwo = "6";

	double secondAdd = 4;
	String secondAddNumTwo  = "7";

	double thirdAdd = 50;
        String thirdAddNumTwo  = "2000000";
	
	double fourthAdd = 60.5;
	String fourthAddNumTwo = "5";
        try {
            Assertions.assertEquals(15, calculator.calc(firstAdd, firstAddNumTwo, '+'));
	    Assertions.assertEquals(11, calculator.calc(secondAdd, secondAddNumTwo, '+'));
	    Assertions.assertEquals(2000050, calculator.calc(thirdAdd, thirdAddNumTwo, '+'));
	    Assertions.assertEquals(65.5, calculator.calc(fourthAdd, fourthAddNumTwo, '+'));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
   
  @Test
    void testSubtract() {
        double first = 9;
        String firstNumTwo = "6";

        double second = 7;
        String secondNumTwo  = "7";

        double third = 50.5;
        String thirdNumTwo  = "20";

        double fourth = 60;
        String fourthNumTwo = "5";

        double fifth = 5;
        String fifthNumTwo = "7";
        try {
            Assertions.assertEquals(15, calculator.calc(first, firstNumTwo, '-'));
            Assertions.assertEquals(0, calculator.calc(second, secondNumTwo, '-'));
            Assertions.assertEquals(30.5, calculator.calc(third, thirdNumTwo, '-'));
            Assertions.assertEquals(55, calculator.calc(fourth, fourthNumTwo, '-'));
	    Assertions.assertEquals(-2, calculator.calc(fifth, fifthNumTwo, '-'));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
  @Test
    void testMultiply() {
        double first = 9;
        String firstNumTwo = "6";

        double second = 7;
        String secondNumTwo  = "7";

        double third = 50.5;
        String thirdNumTwo  = "20";

        double fourth = 60;
        String fourthNumTwo = "5";
        try {
            Assertions.assertEquals(54, calculator.calc(first, firstNumTwo, '*'));
            Assertions.assertEquals(49, calculator.calc(second, secondNumTwo, '*'));
            Assertions.assertEquals(1010, calculator.calc(third, thirdNumTwo, '*'));
            Assertions.assertEquals(300, calculator.calc(fourth, fourthNumTwo, '*'));
        } catch (Exception e) {
            e.printStackTrace();
        }
  }
  
  @Test
    void testDivide() {
        double first = 9;
        String firstNumTwo = "6";

        double second = 7;
        String secondNumTwo  = "7";

        double third = 50.5;
        String thirdNumTwo  = "20";

        double fourth = 60;
        String fourthNumTwo = "5";
        try {
            Assertions.assertEquals(1.5, calculator.calc(first, firstNumTwo, '/'));
            Assertions.assertEquals(1, calculator.calc(second, secondNumTwo, '/'));
            Assertions.assertEquals(2.525, calculator.calc(third, thirdNumTwo, '/'));
            Assertions.assertEquals(12, calculator.calc(fourth, fourthNumTwo, '/'));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


  @Test
    void testMod() {
        double first = 9;
        String firstNumTwo = "6";

        double second = 7;
        String secondNumTwo  = "7";

        double third = 50.5;
        String thirdNumTwo  = "20";

        double fourth = 61;
        String fourthNumTwo = "5";
        try {
            Assertions.assertEquals(3, calculator.calc(first, firstNumTwo, '%'));
            Assertions.assertEquals(0, calculator.calc(second, secondNumTwo, '%'));
            Assertions.assertEquals(10.5, calculator.calc(third, thirdNumTwo, '%'));
            Assertions.assertEquals(1, calculator.calc(fourth, fourthNumTwo, '%'));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
  
  @Test
    void testPower() {
        double first = 9;
        String firstNumTwo = "6";

        double second = 7;
        String secondNumTwo  = "7";

        double third = 1;
        String thirdNumTwo  = "0";

        double fourth = 0;
        String fourthNumTwo = "5";
        try {
            Assertions.assertEquals(531441, calculator.calc(first, firstNumTwo, '^'));
            Assertions.assertEquals(823543, calculator.calc(second, secondNumTwo, '^'));
            Assertions.assertEquals(1, calculator.calc(third, thirdNumTwo, '^'));
            Assertions.assertEquals(0, calculator.calc(fourth, fourthNumTwo, '^'));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}


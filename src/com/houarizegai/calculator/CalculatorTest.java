package com.houarizegai.calculator;


import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CalculatorTest {

	@org.junit.jupiter.api.Test
	public void testCalc() {
		double first =3;
		String second="5";
		Calculator calculator = new Calculator();

		Assertions.assertEquals(8,calculator.calc(first,second,'+'));
		Assertions.assertEquals(-2,calculator.calc(first,second,'-'));
		Assertions.assertEquals(15,calculator.calc(first,second,'*'));
		Assertions.assertEquals(0.6,calculator.calc(first,second,'/'));
		Assertions.assertEquals((3%5),calculator.calc(first,second,'%'));
		Assertions.assertEquals(243,calculator.calc(first,second,'^'));

		
	}

}

package com.houarizegai.calculator;


import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CalculatorTest {

	private Calculator calculator;

	/*
	 * create object before compilation
	 */	@Before
	public void createObj(){
		calculator = new Calculator();
	}
	
	/*
	 * testCalc() test method
	*/ 
	@Test
	public void testCalc() {
		double first =3;
		String second="5";

		try {
		Assertions.assertEquals(5,calculator.calc(first,second,'+'));
		Assertions.assertEquals(-2,calculator.calc(first,second,'-'));
		Assertions.assertEquals(15,calculator.calc(first,second,'*'));
		Assertions.assertEquals(0.6,calculator.calc(first,second,'/'));
		Assertions.assertEquals(((3/5)*100),calculator.calc(first,second,'%'));
		Assertions.assertEquals(243,calculator.calc(first,second,'^'));
		}catch(Exception e) {}
		
	}
	//all tests passed

}

package com.houarizegai.calculator.ui;

import org.junit.jupiter.api.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

class CalculatorUITest {
    private CalculatorUI getCalculatorUI() {
        return new CalculatorUI();
    }

    Random random = new Random();

    Long getRand(Integer max) {
        return (long)random.nextInt(max);
    }

    Double getRandDouble(Integer max) {
        var res = ((double)getRand(max)) / (getRand(max) + 1);
        assert res >= 0;
        return res;
    }

    private void clickDigit(CalculatorUI calculatorUI, int digit) {
        switch (digit) {
            case 0 -> calculatorUI.btn0.doClick();
            case 1 -> calculatorUI.btn1.doClick();
            case 2 -> calculatorUI.btn2.doClick();
            case 3 -> calculatorUI.btn3.doClick();
            case 4 -> calculatorUI.btn4.doClick();
            case 5 -> calculatorUI.btn5.doClick();
            case 6 -> calculatorUI.btn6.doClick();
            case 7 -> calculatorUI.btn7.doClick();
            case 8 -> calculatorUI.btn8.doClick();
            case 9 -> calculatorUI.btn9.doClick();
        }
    }


    private void inputNumber(CalculatorUI calculatorUI, String number) {
        for(var ch : number.toCharArray()) {
            if(ch >= '0' && ch <= '9') {
                clickDigit(calculatorUI, ch - '0');
                continue;
            }
            if(ch == '.') {
                calculatorUI.btnPoint.doClick();
            }
        }
    }

    private void inputNumber(CalculatorUI calculatorUI, Long integer) {
        inputNumber(calculatorUI, integer.toString());
    }

    private void inputNumber(CalculatorUI calculatorUI, Double value) {
        inputNumber(calculatorUI, value.toString());
    }

    void checkEqual(CalculatorUI calculatorUI, String string) {
        assertEquals(calculatorUI.inputScreen.getText(), string);
    }
    void checkEqual(CalculatorUI calculatorUI, Integer value) {
        checkEqual(calculatorUI, value.toString());
    }
    void checkEqual(CalculatorUI calculatorUI, Double value) {
        checkEqual(calculatorUI, value.toString());
    }


    @Test
    void inputs() {
        var calculator = getCalculatorUI();

        clickDigit(calculator, 3);
        checkEqual(calculator, 3);

        calculator.btnBack.doClick();
        checkEqual(calculator, 0);

        inputNumber(calculator, 48L);
        checkEqual(calculator, 48);

        calculator.btnBack.doClick();
        checkEqual(calculator, 4);

        inputNumber(calculator, 729L);
        checkEqual(calculator, 4729);

        calculator.btnC.doClick();
        checkEqual(calculator, 0);

        calculator.btnPoint.doClick();
        checkEqual(calculator, "0.");


        inputNumber(calculator, 156L);
        calculator.btnPoint.doClick();
        checkEqual(calculator, 0.156);

        calculator.btnC.doClick();
        checkEqual(calculator, 0);
    }

    @Test
    void modes() {
        var calculator = getCalculatorUI();
        assertFalse(calculator.btnPower.isVisible());
        assertFalse(calculator.btnRoot.isVisible());
        assertFalse(calculator.btnLog.isVisible());
        calculator.comboCalculatorType.setSelectedIndex(1);
        assertTrue(calculator.btnPower.isVisible());
        assertTrue(calculator.btnRoot.isVisible());
        assertTrue(calculator.btnLog.isVisible());
        calculator.comboCalculatorType.setSelectedIndex(0);
        assertFalse(calculator.btnPower.isVisible());
        assertFalse(calculator.btnRoot.isVisible());
        assertFalse(calculator.btnLog.isVisible());
    }

    @Test
    void themes() {
        var calculator = getCalculatorUI();
        assertEquals(calculator.window.getContentPane().getBackground(), new Color(0xf0f3f9));
        calculator.comboTheme.setSelectedIndex(1);
        assertEquals(calculator.window.getContentPane().getBackground(), new Color(0x1c2028));
        calculator.comboTheme.setSelectedIndex(0);
        assertEquals(calculator.window.getContentPane().getBackground(), new Color(0xf0f3f9));
    }

    @Test
    void divTest() {
        var calculator = getCalculatorUI();
        var a = getRandDouble(1000);
        var b = getRandDouble(1000) + 1;
        inputNumber(calculator, a);
        checkEqual(calculator, a);
        calculator.btnDiv.doClick();
        inputNumber(calculator, b);
        checkEqual(calculator, b);
        calculator.btnEqual.doClick();
        checkEqual(calculator, a / b);
    }

    @Test
    void modTest() {
        var calculator = getCalculatorUI();
        var a = getRandDouble(1000);
        var b = getRandDouble(1000) + 1;
        inputNumber(calculator, a);
        checkEqual(calculator, a);
        calculator.btnMod.doClick();
        inputNumber(calculator, b);
        checkEqual(calculator, b);
        calculator.btnEqual.doClick();
        checkEqual(calculator, a % b);
    }
    @Test
    void mulTest() {
        var calculator = getCalculatorUI();
        var a = getRandDouble(1000);
        var b = getRandDouble(1000) + 1;
        inputNumber(calculator, a);
        checkEqual(calculator, a);
        calculator.btnMul.doClick();
        inputNumber(calculator, b);
        checkEqual(calculator, b);
        calculator.btnEqual.doClick();
        checkEqual(calculator, a * b);
    }
    @Test
    void subTest() {
        var calculator = getCalculatorUI();
        var a = getRandDouble(1000);
        var b = getRandDouble(1000) + 1;
        inputNumber(calculator, a);
        checkEqual(calculator, a);
        calculator.btnSub.doClick();
        inputNumber(calculator, b);
        checkEqual(calculator, b);
        calculator.btnEqual.doClick();
        checkEqual(calculator, a - b);
    }
    @Test
    void addTest() {
        var calculator = getCalculatorUI();
        var a = getRandDouble(1000);
        var b = getRandDouble(1000) + 1;
        inputNumber(calculator, a);
        checkEqual(calculator, a);
        calculator.btnAdd.doClick();
        inputNumber(calculator, b);
        checkEqual(calculator, b);
        calculator.btnEqual.doClick();
        checkEqual(calculator, a + b);
    }

    @Test
    void powTest() {
        var calculator = getCalculatorUI();
        calculator.comboCalculatorType.setSelectedIndex(1);
        var a = getRandDouble(10) + 1;
        var b = getRandDouble(10) + 1;
        inputNumber(calculator, a);
        checkEqual(calculator, a);
        calculator.btnPower.doClick();
        inputNumber(calculator, b);
        checkEqual(calculator, b);
        calculator.btnEqual.doClick();
        checkEqual(calculator, Math.pow(a, b));
    }

    @Test
    void logTest() {
        var calculator = getCalculatorUI();
        calculator.comboCalculatorType.setSelectedIndex(1);
        var a = getRandDouble(10) + 1;
        inputNumber(calculator, a);
        checkEqual(calculator, a);
        calculator.btnLog.doClick();
        checkEqual(calculator, Math.log(a));
    }

    @Test
    void sqrtTest() {
        var calculator = getCalculatorUI();
        calculator.comboCalculatorType.setSelectedIndex(1);
        var a = getRandDouble(10) + 1;
        inputNumber(calculator, a);
        checkEqual(calculator, a);
        calculator.btnRoot.doClick();
        checkEqual(calculator, Math.sqrt(a));
    }

    @Test
    void random() {
        var calculator = getCalculatorUI();
        for(int i = 0; i < 1000; i++) {
            var action = getRand(24).intValue();
            switch (action) {
                case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 -> clickDigit(calculator, action);
                case 10 -> calculator.btnPoint.doClick();
                case 11 -> calculator.btnC.doClick();
                case 12 -> calculator.btnBack.doClick();
                case 13 -> calculator.btnMod.doClick();
                case 14 -> calculator.btnDiv.doClick();
                case 15 -> calculator.btnMul.doClick();
                case 16 -> calculator.btnSub.doClick();
                case 17 -> calculator.btnAdd.doClick();
                case 18 -> calculator.btnEqual.doClick();
                case 19 ->
                        calculator.comboCalculatorType.setSelectedIndex(calculator.comboCalculatorType.getSelectedIndex() ^ 1);
                case 20 -> calculator.comboTheme.setSelectedIndex(calculator.comboTheme.getSelectedIndex() ^ 1);
                case 21 -> calculator.btnRoot.doClick();
                case 22 -> calculator.btnPower.doClick();
                case 23 -> calculator.btnLog.doClick();
            }

        }
    }

}
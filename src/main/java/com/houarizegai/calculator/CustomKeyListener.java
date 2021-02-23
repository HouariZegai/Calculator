package com.houarizegai.calculator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class CustomKeyListener implements KeyListener {

    Calculator calculator;

    public CustomKeyListener(Calculator calculator){
        this.calculator=calculator;
    }

    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()){

            case KeyEvent.VK_NUMPAD0:
            case KeyEvent.VK_0 :
                calculator.set_action("0");
                break;

            case KeyEvent.VK_NUMPAD1:
            case KeyEvent.VK_1:
                calculator.set_action("1");
                break;

            case KeyEvent.VK_NUMPAD2:
            case KeyEvent.VK_2:
                calculator.set_action("2");
                break;

            case KeyEvent.VK_NUMPAD3:
            case KeyEvent.VK_3:
                calculator.set_action("3");
                break;

            case KeyEvent.VK_NUMPAD4:
            case KeyEvent.VK_4:
                calculator.set_action("4");
                break;

            case KeyEvent.VK_NUMPAD5:
            case KeyEvent.VK_5:
                calculator.set_action("5");
                break;

            case KeyEvent.VK_NUMPAD6:
            case KeyEvent.VK_6:
                calculator.set_action("6");
                break;

            case KeyEvent.VK_NUMPAD7:
            case KeyEvent.VK_7:
                calculator.set_action("7");
                break;

            case KeyEvent.VK_NUMPAD8:
            case KeyEvent.VK_8:
                calculator.set_action("8");
                break;

            case KeyEvent.VK_NUMPAD9:
            case KeyEvent.VK_9:
                calculator.set_action("9");
                break;

            case KeyEvent.VK_ADD:
                calculator.set_action("+");
                break;

            case KeyEvent.VK_DIVIDE:
                calculator.set_action("/");
                break;

            case KeyEvent.VK_MULTIPLY:
                calculator.set_action("*");
                break;

            case KeyEvent.VK_SUBTRACT:
                calculator.set_action("-");
                break;

            case KeyEvent.VK_ENTER:
                calculator.set_action("=");
                break;

            case KeyEvent.VK_PERIOD:
                calculator.set_action(".");
                break;

            case KeyEvent.VK_BACK_SPACE:
                calculator.set_action("<-");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

}
package com.houarizegai.calculator;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.awt.Color;
import javax.swing.*;
import java.lang.Math;

public class Calculator {

    private static final int WINDOW_WIDTH = 410;
    private static final int WINDOW_HEIGHT = 600;
    private static final int BUTTON_WIDTH = 80;
    private static final int BUTTON_HEIGHT = 70;
    private static final int MARGIN_X = 20;
    private static final int MARGIN_Y = 60;
    private static final String FONT_NAME = "Comic Sans MS";

    private JFrame window;
    private final JComboBox<String> comboCalculatorType;
    private final JComboBox<String> comboTheme;
    private final JTextField inputScreen;
    private JButton btnC;
    private JButton btnBack;
    private JButton btnMod;
    private JButton btnDiv;
    private JButton btnMul;
    private JButton btnSub;
    private JButton btnAdd;
    private JButton btn0;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private JButton btn6;
    private JButton btn7;
    private JButton btn8;
    private JButton btn9;
    private JButton btnPoint;
    private JButton btnEqual;
    private JButton btnRoot;
    private JButton btnPower;
    private JButton btnLog;

    private char operator = ' ';
    private boolean go = true; // For calculate with Opt != (=)
    private boolean addWrite = true; // Connect numbers in display
    private double val = 0; // Save the value typed for calculation

    private final Theme lightTheme = Theme.builder()
            .applicationBackgroundColor(new Color(240, 243, 249, 255))
            .textColor(new Color(0, 0, 0, 255))
            .operatorBackgroundColor(new Color(247, 249, 252, 255))
            .numbersBackgroundColor(new Color(255, 255, 255, 255))
            .btnEqualTextColor(new Color(255, 255, 255, 255))
            .btnEqualBackgroundColor(new Color(0, 103, 192, 255))
            .build();

    private final Theme darkTheme = Theme.builder()
            .applicationBackgroundColor(new Color(28, 32, 40, 255))
            .textColor(new Color(255, 255, 255, 255))
            .operatorBackgroundColor(new Color(45, 51, 61, 255))
            .numbersBackgroundColor(new Color(54, 60, 71, 255))
            .btnEqualTextColor(new Color(255, 255, 255, 255))
            .btnEqualBackgroundColor(new Color(76, 194, 255, 255))
            .build();

    /*
        Mx Calculator: 
        X = Row
        Y = Column
    
        +-------------------+
        |   +-----------+   |   y[0]
        |   |           |   |
        |   +-----------+   |
        |                   |
        |   C  <-   %   /   |   y[1]
        |   7   8   9   *   |   y[2]
        |   4   5   6   -   |   y[3]
        |   1   2   3   +   |   y[4]
        |   .   0     =     |   y[5]
        +-------------------+
         x[0] x[1] x[2] x[3]
    
    */
    
    /*    
        +-------------------+
        |   +-----------+   |   y[0]
        |   |           |   |
        |   +-----------+   |
        |                   |
        |   0   1   1   3   |   y[1]
        |   4   5   6   7   |   y[2]
        |   8   9   10  11  |   y[3]
        |   12  13  14  15  |   y[4]
        |   16  17    18    |   y[5]
        +-------------------+
         x[0] x[1] x[2] x[3]
    
    */

    public Calculator() {
        window = new JFrame("Calculator");
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setLocationRelativeTo(null); // Move window to center

        comboTheme = createComboBox(new String[]{"Light", "Dark"}, 230, 30, "Theme", themeSwitchEventConsumer);
        comboCalculatorType = createComboBox(new String[]{"Standard", "Scientific"}, 20, 30, "Calculator type", calcTypeSwitchEventConsumer);

        int[] x = {MARGIN_X, MARGIN_X + 90, 200, 290, 380};
        int[] y = {MARGIN_Y, MARGIN_Y + 100, MARGIN_Y + 180, MARGIN_Y + 260, MARGIN_Y + 340, MARGIN_Y + 420};

        inputScreen = new JTextField("0");
        inputScreen.setBounds(x[0], y[0], 350, 70);
        inputScreen.setEditable(false);
        inputScreen.setBackground(Color.WHITE);
        inputScreen.setFont(new Font(FONT_NAME, Font.PLAIN, 33));
        window.add(inputScreen);

        btnC = initButton("C", x[0], y[1], event -> {
            repaintFont();
            inputScreen.setText("0");
            operator = ' ';
            val = 0;
        });

        btnBack = initButton("<-", x[1], y[1], event -> {
            repaintFont();
            String str = inputScreen.getText();
            StringBuilder str2 = new StringBuilder();
            for (int i = 0; i < (str.length() - 1); i++) {
                str2.append(str.charAt(i));
            }
            if (str2.toString().equals("")) {
                inputScreen.setText("0");
            } else {
                inputScreen.setText(str2.toString());
            }
        });

        btnMod = initButton("%", x[2], y[1], event -> {
            repaintFont();
            if (!Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inputScreen.getText()) || !go)
                return;

            val = calculate(val, inputScreen.getText(), operator);
            if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                inputScreen.setText(String.valueOf((int) val));
            } else {
                inputScreen.setText(String.valueOf(val));
            }
            operator = '%';
            go = false;
            addWrite = false;
        });

        btnDiv = initButton("/", x[3], y[1], event -> {
            repaintFont();
            if (!Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inputScreen.getText()))
                return;

            if (go) {
                val = calculate(val, inputScreen.getText(), operator);
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                    inputScreen.setText(String.valueOf((int) val));
                } else {
                    inputScreen.setText(String.valueOf(val));
                }
                operator = '/';
                go = false;
                addWrite = false;
            } else {
                operator = '/';
            }
        });

        btn7 = initButton("7", x[0], y[2], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inputScreen.getText())) {
                    inputScreen.setText("7");
                } else {
                    inputScreen.setText(inputScreen.getText() + "7");
                }
            } else {
                inputScreen.setText("7");
                addWrite = true;
            }
            go = true;
        });

        btn8 = initButton("8", x[1], y[2], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inputScreen.getText())) {
                    inputScreen.setText("8");
                } else {
                    inputScreen.setText(inputScreen.getText() + "8");
                }
            } else {
                inputScreen.setText("8");
                addWrite = true;
            }
            go = true;
        });

        btn9 = initButton("9", x[2], y[2], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inputScreen.getText())) {
                    inputScreen.setText("9");
                } else {
                    inputScreen.setText(inputScreen.getText() + "9");
                }
            } else {
                inputScreen.setText("9");
                addWrite = true;
            }
            go = true;
        });

        btnMul = initButton("*", x[3], y[2], event -> {
            repaintFont();
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inputScreen.getText()))
                if (go) {
                    val = calculate(val, inputScreen.getText(), operator);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inputScreen.setText(String.valueOf((int) val));
                    } else {
                        inputScreen.setText(String.valueOf(val));
                    }
                    operator = '*';
                    go = false;
                    addWrite = false;
                } else {
                    operator = '*';
                }
        });

        btn4 = initButton("4", x[0], y[3], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inputScreen.getText())) {
                    inputScreen.setText("4");
                } else {
                    inputScreen.setText(inputScreen.getText() + "4");
                }
            } else {
                inputScreen.setText("4");
                addWrite = true;
            }
            go = true;
        });

        btn5 = initButton("5", x[1], y[3], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inputScreen.getText())) {
                    inputScreen.setText("5");
                } else {
                    inputScreen.setText(inputScreen.getText() + "5");
                }
            } else {
                inputScreen.setText("5");
                addWrite = true;
            }
            go = true;
        });

        btn6 = initButton("6", x[2], y[3], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inputScreen.getText())) {
                    inputScreen.setText("6");
                } else {
                    inputScreen.setText(inputScreen.getText() + "6");
                }
            } else {
                inputScreen.setText("6");
                addWrite = true;
            }
            go = true;
        });

        btnSub = initButton("-", x[3], y[3], event -> {
            repaintFont();
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inputScreen.getText()))
                if (go) {
                    val = calculate(val, inputScreen.getText(), operator);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inputScreen.setText(String.valueOf((int) val));
                    } else {
                        inputScreen.setText(String.valueOf(val));
                    }

                    operator = '-';
                    go = false;
                    addWrite = false;
                } else {
                    operator = '-';
                }
        });

        btn1 = initButton("1", x[0], y[4], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inputScreen.getText())) {
                    inputScreen.setText("1");
                } else {
                    inputScreen.setText(inputScreen.getText() + "1");
                }
            } else {
                inputScreen.setText("1");
                addWrite = true;
            }
            go = true;
        });

        btn2 = initButton("2", x[1], y[4], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inputScreen.getText())) {
                    inputScreen.setText("2");
                } else {
                    inputScreen.setText(inputScreen.getText() + "2");
                }
            } else {
                inputScreen.setText("2");
                addWrite = true;
            }
            go = true;
        });

        btn3 = initButton("3", x[2], y[4], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inputScreen.getText())) {
                    inputScreen.setText("3");
                } else {
                    inputScreen.setText(inputScreen.getText() + "3");
                }
            } else {
                inputScreen.setText("3");
                addWrite = true;
            }
            go = true;
        });

        btnAdd = initButton("+", x[3], y[4], event -> {
            repaintFont();
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inputScreen.getText()))
                if (go) {
                    val = calculate(val, inputScreen.getText(), operator);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inputScreen.setText(String.valueOf((int) val));
                    } else {
                        inputScreen.setText(String.valueOf(val));
                    }
                    operator = '+';
                    go = false;
                    addWrite = false;
                } else {
                    operator = '+';
                }
        });

        btnPoint = initButton(".", x[0], y[5], event -> {
            repaintFont();
            if (addWrite) {
                if (!inputScreen.getText().contains(".")) {
                    inputScreen.setText(inputScreen.getText() + ".");
                }
            } else {
                inputScreen.setText("0.");
                addWrite = true;
            }
            go = true;
        });

        btn0 = initButton("0", x[1], y[5], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inputScreen.getText())) {
                    inputScreen.setText("0");
                } else {
                    inputScreen.setText(inputScreen.getText() + "0");
                }
            } else {
                inputScreen.setText("0");
                addWrite = true;
            }
            go = true;
        });

        btnEqual = initButton("=", x[2], y[5], event -> {
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inputScreen.getText()))
                if (go) {
                    val = calculate(val, inputScreen.getText(), operator);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inputScreen.setText(String.valueOf((int) val));
                    } else {
                        inputScreen.setText(String.valueOf(val));
                    }
                    operator = '=';
                    addWrite = false;
                }
        });
        btnEqual.setSize(2 * BUTTON_WIDTH + 10, BUTTON_HEIGHT);

        btnRoot = initButton("√", x[4], y[1], event -> {
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inputScreen.getText()))
                if (go) {
                    val = Math.sqrt(Double.parseDouble(inputScreen.getText()));
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inputScreen.setText(String.valueOf((int) val));
                    } else {
                        inputScreen.setText(String.valueOf(val));
                    }
                    operator = '√';
                    addWrite = false;
                }
        });
        btnRoot.setVisible(false);

        btnPower = initButton("pow", x[4], y[2], event -> {
            repaintFont();
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inputScreen.getText()))
                if (go) {
                    val = calculate(val, inputScreen.getText(), operator);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inputScreen.setText(String.valueOf((int) val));
                    } else {
                        inputScreen.setText(String.valueOf(val));
                    }
                    operator = '^';
                    go = false;
                    addWrite = false;
                } else {
                    operator = '^';
                }
        });
        btnPower.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        btnPower.setVisible(false);

        btnLog = initButton("ln", x[4], y[3], event -> {
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inputScreen.getText()))
                if (go) {
                    val = Math.log(Double.parseDouble(inputScreen.getText()));
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inputScreen.setText(String.valueOf((int) val));
                    } else {
                        inputScreen.setText(String.valueOf(val));
                    }
                    operator = 'l';
                    addWrite = false;
                }
        });
        btnLog.setVisible(false);

        changeApplicationColor(lightTheme);

        window.setLayout(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close button clicked? = End The process
        window.setVisible(true);
    }

    private JComboBox<String> createComboBox(String[] items, int x, int y, String toolTip, Consumer consumerEvent) {
        JComboBox<String> combo = new JComboBox<>(items);
        combo.setBounds(x, y, 140, 25);
        combo.setToolTipText(toolTip);
        combo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        combo.addItemListener(consumerEvent::accept);
        window.add(combo);

        return combo;
    }

    private JButton initButton(String label, int x, int y, ActionListener event) {
        JButton btn = new JButton(label);
        btn.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(event);
        btn.setFocusable(false);
        window.add(btn);

        return btn;
    }

    public double calculate(double x, String input, char opt) {
        inputScreen.setFont(inputScreen.getFont().deriveFont(Font.PLAIN));
        double y = Double.parseDouble(input);
        switch (opt) {
            case '+':
                return x + y;
            case '-':
                return x - y;
            case '*':
                return x * y;
            case '/':
                return x / y;
            case '%':
                return x % y;
            case '^':
                return Math.pow(x, y);
            default:
                inputScreen.setFont(inputScreen.getFont().deriveFont(Font.PLAIN));
                return y;
        }
    }

    private void repaintFont() {
        inputScreen.setFont(inputScreen.getFont().deriveFont(Font.PLAIN));
    }

    private final Consumer<ItemEvent> calcTypeSwitchEventConsumer = event -> {
        if (event.getStateChange() != ItemEvent.SELECTED) return;

        String selectedItem = (String) event.getItem();
        switch (selectedItem) {
            case "Standard":
                window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
                btnRoot.setVisible(false);
                btnPower.setVisible(false);
                btnLog.setVisible(false);
                break;
            case "Scientific":
                window.setSize(WINDOW_WIDTH + 80, WINDOW_HEIGHT);
                btnRoot.setVisible(true);
                btnPower.setVisible(true);
                btnLog.setVisible(true);
                break;
        }
    };

    private final Consumer<ItemEvent> themeSwitchEventConsumer = event -> {
        if (event.getStateChange() != ItemEvent.SELECTED)
            return;

        String selectedTheme = (String) event.getItem();
        switch (selectedTheme) {
            case "Light":
                changeApplicationColor(lightTheme);
                break;
            case "Dark":
                changeApplicationColor(darkTheme);
        }
    };

    private void changeApplicationColor(Theme theme) {
        window.getContentPane().setBackground(theme.getApplicationBackgroundColor());

        comboCalculatorType.setForeground(theme.getTextColor());
        comboTheme.setForeground(theme.getTextColor());
        inputScreen.setForeground(theme.getTextColor());
        btn0.setForeground(theme.getTextColor());
        btn1.setForeground(theme.getTextColor());
        btn2.setForeground(theme.getTextColor());
        btn3.setForeground(theme.getTextColor());
        btn4.setForeground(theme.getTextColor());
        btn5.setForeground(theme.getTextColor());
        btn6.setForeground(theme.getTextColor());
        btn7.setForeground(theme.getTextColor());
        btn8.setForeground(theme.getTextColor());
        btn9.setForeground(theme.getTextColor());
        btnPoint.setForeground(theme.getTextColor());
        btnC.setForeground(theme.getTextColor());
        btnBack.setForeground(theme.getTextColor());
        btnMod.setForeground(theme.getTextColor());
        btnDiv.setForeground(theme.getTextColor());
        btnMul.setForeground(theme.getTextColor());
        btnSub.setForeground(theme.getTextColor());
        btnAdd.setForeground(theme.getTextColor());
        btnRoot.setForeground(theme.getTextColor());
        btnLog.setForeground(theme.getTextColor());
        btnPower.setForeground(theme.getTextColor());
        btnEqual.setForeground(theme.getBtnEqualTextColor());

        comboCalculatorType.setBackground(theme.getApplicationBackgroundColor());
        comboTheme.setBackground(theme.getApplicationBackgroundColor());
        inputScreen.setBackground(theme.getApplicationBackgroundColor());
        btn0.setBackground(theme.getNumbersBackgroundColor());
        btn1.setBackground(theme.getNumbersBackgroundColor());
        btn2.setBackground(theme.getNumbersBackgroundColor());
        btn3.setBackground(theme.getNumbersBackgroundColor());
        btn4.setBackground(theme.getNumbersBackgroundColor());
        btn5.setBackground(theme.getNumbersBackgroundColor());
        btn6.setBackground(theme.getNumbersBackgroundColor());
        btn7.setBackground(theme.getNumbersBackgroundColor());
        btn8.setBackground(theme.getNumbersBackgroundColor());
        btn9.setBackground(theme.getNumbersBackgroundColor());
        btnPoint.setBackground(theme.getNumbersBackgroundColor());
        btnC.setBackground(theme.getOperatorBackgroundColor());
        btnBack.setBackground(theme.getOperatorBackgroundColor());
        btnMod.setBackground(theme.getOperatorBackgroundColor());
        btnDiv.setBackground(theme.getOperatorBackgroundColor());
        btnMul.setBackground(theme.getOperatorBackgroundColor());
        btnSub.setBackground(theme.getOperatorBackgroundColor());
        btnAdd.setBackground(theme.getOperatorBackgroundColor());
        btnRoot.setBackground(theme.getOperatorBackgroundColor());
        btnLog.setBackground(theme.getOperatorBackgroundColor());
        btnPower.setBackground(theme.getOperatorBackgroundColor());
        btnEqual.setBackground(theme.getBtnEqualBackgroundColor());
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
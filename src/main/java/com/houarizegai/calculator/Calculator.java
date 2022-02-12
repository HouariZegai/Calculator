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
import java.util.logging.*;

public class Calculator {
    
    private static final int WINDOW_WIDTH = 410;
    private static final int WINDOW_HEIGHT = 600;
    private static final int BUTTON_WIDTH = 80;
    private static final int BUTTON_HEIGHT = 70;
    private static final int MARGIN_X = 20;
    private static final int MARGIN_Y = 60;

    private JFrame window; // Main window
    private JComboBox<String> comboCalcType, comboTheme; 
    private JTextField inText; // Input
    private JButton btnC, btnBack, btnMod, btnDiv, btnMul, btnSub, btnAdd,
            btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
            btnPoint, btnEqual, btnRoot, btnPower, btnLog, btnFact;

    private char opt = ' '; // Save the operator
    private boolean go = true; // For calculate with Opt != (=)
    private boolean addWrite = true; // Connect numbers in display
    private double val = 0; // Save the value typed for calculation

    private final static Logger LOGGER = 
                Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

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

    public long factorialUsingForLoop(double d) {
        long fact = 1;
        for (double i = d; i >= 1; i--) {
            fact = (long) (fact * i);
        }
        return fact;
    }

    public boolean isNumber(String numString){
        if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", numString)) {
            return true;
        } else {
            return false;
        }
    }

    public Calculator() {
        window = new JFrame("Calculator");
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setLocationRelativeTo(null); // Move window to center

        comboTheme = initCombo(new String[]{"Simple", "Colored", "DarkTheme"}, 230, 30, "Theme", themeSwitchEventConsumer);

        comboCalcType = initCombo(new String[]{"Standard", "Scientific"}, 20, 30, "Calculator type", calcTypeSwitchEventConsumer);

        int[] x = {MARGIN_X, MARGIN_X + 90, 200, 290, 380};
        int[] y = {MARGIN_Y, MARGIN_Y + 100, MARGIN_Y + 180, MARGIN_Y + 260, MARGIN_Y + 340, MARGIN_Y + 420};

        inText = new JTextField("0");
        inText.setBounds(x[0], y[0], 350, 70);
        inText.setEditable(false);
        inText.setBackground(Color.WHITE);
        inText.setFont(new Font("Comic Sans MS", Font.PLAIN, 33));
        window.add(inText);

        btnC = initBtn("C", x[0], y[1], event -> {
            repaintFont();
            inText.setText("0");
            opt = ' ';
            val = 0;
        });

        //This back button takes you one step back on the numbers you provided 
        btnBack = initBtn("<-", x[1], y[1], event -> {
            repaintFont();
            String str = inText.getText();
            StringBuilder str2 = new StringBuilder();
            for (int i = 0; i < (str.length() - 1); i++) {
                str2.append(str.charAt(i));
            }
            if (str2.toString().equals("")) {
                inText.setText("0");
            } else {
                inText.setText(str2.toString());
            }
        });

        //This button performs modulus operation (e.g: 5%3 = 2) 
        btnMod = initBtn("%", x[2], y[1], event -> {
            repaintFont();
            if (isNumber(inText.getText())) {
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    LOGGER.log(Level.INFO, "Modulus performed");
                    opt = '%';
                    go = false;
                    addWrite = false;
                }
            }
        });

        //This button performs Divison operation (e.g: 4/2 = 2)
        btnDiv = initBtn("/", x[3], y[1], event -> {
            repaintFont();
            if (isNumber(inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    LOGGER.log(Level.INFO, "Divison performed");
                    opt = '/';
                    go = false;
                    addWrite = false;
                } else {
                    opt = '/';
                }
        });

        btn7 = initBtn("7", x[0], y[2], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("7");
                } else {
                    inText.setText(inText.getText() + "7");
                }
            } else {
                inText.setText("7");
                addWrite = true;
            }
            go = true;
        });

        btn8 = initBtn("8", x[1], y[2], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("8");
                } else {
                    inText.setText(inText.getText() + "8");
                }
            } else {
                inText.setText("8");
                addWrite = true;
            }
            go = true;
        });

        btn9 = initBtn("9", x[2], y[2], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("9");
                } else {
                    inText.setText(inText.getText() + "9");
                }
            } else {
                inText.setText("9");
                addWrite = true;
            }
            go = true;
        });

        //This button performs Multiplication operation (e.g: 4*2 = 8)
        btnMul = initBtn("*", x[3], y[2], event -> {
            repaintFont();
            if (isNumber(inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    LOGGER.log(Level.INFO, "Multiplication performed");
                    opt = '*';
                    go = false;
                    addWrite = false;
                } else {
                    opt = '*';
                }
        });

        btn4 = initBtn("4", x[0], y[3], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("4");
                } else {
                    inText.setText(inText.getText() + "4");
                }
            } else {
                inText.setText("4");
                addWrite = true;
            }
            go = true;
        });

        btn5 = initBtn("5", x[1], y[3], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("5");
                } else {
                    inText.setText(inText.getText() + "5");
                }
            } else {
                inText.setText("5");
                addWrite = true;
            }
            go = true;
        });

        btn6 = initBtn("6", x[2], y[3], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("6");
                } else {
                    inText.setText(inText.getText() + "6");
                }
            } else {
                inText.setText("6");
                addWrite = true;
            }
            go = true;
        });

        //This button performs Substraction operation (e.g: 4*2 = 8)
        btnSub = initBtn("-", x[3], y[3], event -> {
            repaintFont();
            if (isNumber(inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    LOGGER.log(Level.INFO, "Substraction performed");
                    opt = '-';
                    go = false;
                    addWrite = false;
                } else {
                    opt = '-';
                }
        });

        btn1 = initBtn("1", x[0], y[4], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("1");
                } else {
                    inText.setText(inText.getText() + "1");
                }
            } else {
                inText.setText("1");
                addWrite = true;
            }
            go = true;
        });

        btn2 = initBtn("2", x[1], y[4], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("2");
                } else {
                    inText.setText(inText.getText() + "2");
                }
            } else {
                inText.setText("2");
                addWrite = true;
            }
            go = true;
        });

        btn3 = initBtn("3", x[2], y[4], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("3");
                } else {
                    inText.setText(inText.getText() + "3");
                }
            } else {
                inText.setText("3");
                addWrite = true;
            }
            go = true;
        });

        //This button performs Addition operation (e.g: 4+2 = 6)
        btnAdd = initBtn("+", x[3], y[4], event -> {
            repaintFont();
            if (isNumber(inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    LOGGER.log(Level.INFO, "Addition performed");
                    opt = '+';
                    go = false;
                    addWrite = false;
                } else {
                    opt = '+';
                }
        });

        btnPoint = initBtn(".", x[0], y[5], event -> {
            repaintFont();
            if (addWrite) {
                if (!inText.getText().contains(".")) {
                    inText.setText(inText.getText() + ".");
                }
            } else {
                inText.setText("0.");
                addWrite = true;
            }
            go = true;
        });

        btn0 = initBtn("0", x[1], y[5], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("0");
                } else {
                    inText.setText(inText.getText() + "0");
                }
            } else {
                inText.setText("0");
                addWrite = true;
            }
            go = true;
        });

        btnEqual = initBtn("=", x[2], y[5], event -> {
            if (isNumber(inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    opt = '=';
                    addWrite = false;
                }
        });
        btnEqual.setSize(2 * BUTTON_WIDTH + 10, BUTTON_HEIGHT);

        //This button performs Squart Root operation (e.g: √4 = 2)
        btnRoot = initBtn("√", x[4], y[1], event -> {
            if (isNumber(inText.getText()))
                if (go) {
                    val = Math.sqrt(Double.parseDouble(inText.getText()));
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    LOGGER.log(Level.INFO, "Square Root performed");
                    opt = '√';
                    addWrite = false;
                }
        });
        btnRoot.setVisible(false);

        //This button performs Power of operation (e.g: 4^2 = 16)
        btnPower = initBtn("pow", x[4], y[2], event -> {
            repaintFont();
            if (isNumber(inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    LOGGER.log(Level.INFO, "Power of performed");
                    opt = '^';
                    go = false;
                    addWrite = false;
                } else {
                    opt = '^';
                }
        });
        btnPower.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        btnPower.setVisible(false);

        //This button performs log operation (e.g: log(4) = 1.3862943611198906)
        btnLog = initBtn("ln", x[4], y[3], event -> {
            if (isNumber(inText.getText()))
                if (go) {
                    val = Math.log(Double.parseDouble(inText.getText()));
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    LOGGER.log(Level.INFO, "Log performed");
                    opt = 'l';
                    addWrite = false;
                }
        });
        btnLog.setVisible(false);

        //This button performs factorial operation (e.g: 4! = 24)
        btnFact = initBtn("x!", x[4], y[4], event -> {
            if (isNumber(inText.getText()))
                if (go) {
                    val = factorialUsingForLoop(Double.parseDouble(inText.getText()));
                    // val = Math.log(Double.parseDouble(inText.getText()));
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    LOGGER.log(Level.INFO, "Factorial performed");
                    opt = '!';
                    addWrite = false;
                }
        });
        btnFact.setVisible(false);

        window.setLayout(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close button clicked? = End The process
        window.setVisible(true);
    }

    private JComboBox<String> initCombo(String[] items, int x, int y, String toolTip, Consumer consumerEvent) {
        JComboBox<String> combo = new JComboBox<>(items);
        combo.setBounds(x, y, 140, 25);
        combo.setToolTipText(toolTip);
        combo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        combo.addItemListener(consumerEvent::accept);
        window.add(combo);

        return combo;
    }

    private JButton initBtn(String label, int x, int y, ActionListener event) {
        JButton btn = new JButton(label);
        btn.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(event);
        btn.setFocusable(false);
        window.add(btn);

        return btn;
    }

    public double calc(double x, String input, char opt) {
        inText.setFont(inText.getFont().deriveFont(Font.PLAIN));
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
            case '√':
                return Math.sqrt(x);
            case 'l':
                return Math.log(x);
            case '!':
                return factorialUsingForLoop(x);
            default:
                inText.setFont(inText.getFont().deriveFont(Font.PLAIN));
                return y;
        }
    }

    private void repaintFont() {
        inText.setFont(inText.getFont().deriveFont(Font.PLAIN));
    }

    private Consumer<ItemEvent> calcTypeSwitchEventConsumer = event -> {
        if (event.getStateChange() != ItemEvent.SELECTED) return;

        String selectedItem = (String) event.getItem();
        switch (selectedItem) {
            case "Standard":
                window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
                btnRoot.setVisible(false);
                btnPower.setVisible(false);
                btnLog.setVisible(false);
                btnFact.setVisible(false);
                break;
            case "Scientific":
                window.setSize(WINDOW_WIDTH + 80, WINDOW_HEIGHT);
                btnRoot.setVisible(true);
                btnPower.setVisible(true);
                btnLog.setVisible(true);
                btnFact.setVisible(true);
                break;
        }
    };

    private Consumer<ItemEvent> themeSwitchEventConsumer = event -> {
        if (event.getStateChange() != ItemEvent.SELECTED) return;

        String selectedTheme = (String) event.getItem();
        switch (selectedTheme) {
            case "Simple":
                window.getContentPane().setBackground(null);
                btnC.setBackground(null);
                btnBack.setBackground(null);
                btnMod.setBackground(null);
                btnDiv.setBackground(null);
                btnMul.setBackground(null);
                btnSub.setBackground(null);
                btnAdd.setBackground(null);
                btnRoot.setBackground(null);
                btnLog.setBackground(null);
                btnFact.setBackground(null);
                btnPower.setBackground(null);
                btnEqual.setBackground(null);
                btn0.setBackground(null);
                btn1.setBackground(null);
                btn2.setBackground(null);
                btn3.setBackground(null);
                btn4.setBackground(null);
                btn5.setBackground(null);
                btn6.setBackground(null);
                btn7.setBackground(null);
                btn8.setBackground(null);
                btn9.setBackground(null);
                btnPoint.setBackground(null);

                btnC.setForeground(Color.BLACK);
                btnBack.setForeground(Color.BLACK);
                btnMod.setForeground(Color.BLACK);
                btnDiv.setForeground(Color.BLACK);
                btnMul.setForeground(Color.BLACK);
                btnSub.setForeground(Color.BLACK);
                btnAdd.setForeground(Color.BLACK);
                btnEqual.setForeground(Color.BLACK);
                btnLog.setForeground(Color.BLACK);
                btnFact.setForeground(Color.BLACK);
                btnPower.setForeground(Color.BLACK);
                btnRoot.setForeground(Color.BLACK);
                break;
            case "Colored":
                window.getContentPane().setBackground(null);
                btnC.setBackground(Color.RED);
                btnBack.setBackground(Color.ORANGE);
                btnMod.setBackground(Color.GREEN);
                btnDiv.setBackground(Color.PINK);
                btnMul.setBackground(Color.PINK);
                btnSub.setBackground(Color.PINK);
                btnAdd.setBackground(Color.PINK);
                btnRoot.setBackground(Color.PINK);
                btnLog.setBackground(Color.PINK);
                btnFact.setBackground(Color.PINK);
                btnPower.setBackground(Color.PINK);
                btnEqual.setBackground(Color.BLUE);
                btn0.setBackground(Color.WHITE);
                btn1.setBackground(Color.WHITE);
                btn2.setBackground(Color.WHITE);
                btn3.setBackground(Color.WHITE);
                btn4.setBackground(Color.WHITE);
                btn5.setBackground(Color.WHITE);
                btn6.setBackground(Color.WHITE);
                btn7.setBackground(Color.WHITE);
                btn8.setBackground(Color.WHITE);
                btn9.setBackground(Color.WHITE);
                btnPoint.setBackground(Color.WHITE);

                btnC.setForeground(Color.WHITE);
                btnBack.setForeground(Color.WHITE);
                btnMod.setForeground(Color.WHITE);
                btnDiv.setForeground(Color.WHITE);
                btnMul.setForeground(Color.WHITE);
                btnSub.setForeground(Color.WHITE);
                btnAdd.setForeground(Color.WHITE);
                btnEqual.setForeground(Color.WHITE);
                btnLog.setForeground(Color.WHITE);
                btnFact.setForeground(Color.WHITE);
                btnPower.setForeground(Color.WHITE);
                btnRoot.setForeground(Color.WHITE);
                break;
            case "DarkTheme":
                final Color primaryDarkColor = new Color(141, 38, 99);
                final Color secondaryDarkColor = new Color(171, 171, 171);

                window.getContentPane().setBackground(new Color(68, 68, 68));
                btn0.setBackground(secondaryDarkColor);
                btn1.setBackground(secondaryDarkColor);
                btn2.setBackground(secondaryDarkColor);
                btn3.setBackground(secondaryDarkColor);
                btn4.setBackground(secondaryDarkColor);
                btn5.setBackground(secondaryDarkColor);
                btn6.setBackground(secondaryDarkColor);
                btn7.setBackground(secondaryDarkColor);
                btn8.setBackground(secondaryDarkColor);
                btn9.setBackground(secondaryDarkColor);
                btnPoint.setBackground(secondaryDarkColor);

                btnC.setForeground(secondaryDarkColor);
                btnBack.setForeground(secondaryDarkColor);
                btnMod.setForeground(secondaryDarkColor);
                btnDiv.setForeground(secondaryDarkColor);
                btnMul.setForeground(secondaryDarkColor);
                btnSub.setForeground(secondaryDarkColor);
                btnAdd.setForeground(secondaryDarkColor);
                btnEqual.setForeground(secondaryDarkColor);
                btnLog.setForeground(secondaryDarkColor);
                btnFact.setForeground(secondaryDarkColor);
                btnPower.setForeground(secondaryDarkColor);
                btnRoot.setForeground(secondaryDarkColor);
                btnC.setBackground(primaryDarkColor);
                btnBack.setBackground(primaryDarkColor);
                btnMod.setBackground(primaryDarkColor);
                btnDiv.setBackground(primaryDarkColor);
                btnMul.setBackground(primaryDarkColor);
                btnSub.setBackground(primaryDarkColor);
                btnAdd.setBackground(primaryDarkColor);
                btnRoot.setBackground(primaryDarkColor);
                btnLog.setBackground(primaryDarkColor);
                btnFact.setBackground(primaryDarkColor);
                btnPower.setBackground(primaryDarkColor);
                btnEqual.setBackground(primaryDarkColor);
        }
    };

    public static void main(String[] args) {
        new Calculator();
    }
}
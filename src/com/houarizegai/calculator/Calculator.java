
package Calculator.src.com.houarizegai.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.awt.Cursor;
import java.awt.Font;
import java.util.regex.Pattern;
import java.awt.Color;
import javax.swing.*;
import java.lang.Math;

public class Calculator {

    JFrame window; // This is Main Window
    JTextField inText,affichageCalc; // Input Text
    
    JButton btnC, btnBack,btnDiv,btn7,btn8,btn9,
            btnMul,btn4,btn5,btn6,btnSub,btn1,btn2,btn3,
            btnAdd,btnPoint,btn0,btnEqual,choixColor ,btnPow;
  
    char opt = ' ';             // Storage Oparator
   
    boolean go = true,          // Faire Calcule Avec Opt != (=)
            addWrite = true;    // Racordé des Nombres dans l'Affichage
    double val = 0; // Storage Values For Calcule
    boolean bool = false;
    /*

public class Calculator {

    private static final int WINDOW_WIDTH = 410;
    private static final int WINDOW_HEIGHT = 600;
    private static final int BUTTON_WIDTH = 80; // Button width
    private static final int BUTTON_HEIGHT = 70; // Button height
    private static final int MARGIN_X = 20;
    private static final int MARGIN_Y = 60;

    private JFrame window; // Main window
    private JTextField inText; // Input text
    private JButton btnC, btnBack, btnDiv, btn7, btn8, btn9,
            btnMul, btn4, btn5, btn6, btnSub, btn1, btn2, btn3, btnAdd, btnPoint, btn0, btnEqual, btnRoot, btnPower, btnLog, btnSwitchThemes, btnSwitchToScientificMode;
    private char opt = ' '; // Save the operator
    private boolean go = true; // For calculate with Opt != (=)
    private boolean addWrite = true; // Racordé des Nombres dans l'Affichage
    private double val = 0; // Save value typed for calculation
    private boolean isToggleColorSelected = false;
    private boolean isScientificMode = false;

    /*

        Mx Calculator:
        X = Row
        Y = Column
        
        +-------------------+
        |   +-----------+   |   y[0]
        |   |           |   |
        |   +-----------+   |
        |                   |
        |   0   1   2   3   |   y[1]
        |   4   5   6   7   |   y[2]
        |   8   9   10  11  |   y[3]
        |   12  13  14  15  |   y[4]
        |   16  17  18  19  |   y[5]
        +-------------------+
         x[0] x[1] x[2] x[3]

    */
    Calculator () {
        window = new JFrame("Calculator");
        window.setSize(530,700); // Height And Width Of Window
        window.setLocationRelativeTo(null); // Move Window To Center

        Font btnFont = new Font("Helvetica", Font.PLAIN, 28);

        choixColor = new JButton();
        choixColor.setBounds(330, 30, 38, 18);
        choixColor.setCursor(new Cursor(Cursor.HAND_CURSOR));
        choixColor.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                themeColor();
            }
        });
        window.add(choixColor);

        int wBtn = 80,      // Width Button
                hBtn = 70,      // Height Button
                marginX = 20,
                marginY = 60,
                j = -1,
                k = -1,
                x[] = {marginX, marginX+90, 200, 290, 380},
                y[] = {marginY, marginY+100, marginY+180,
                        marginY+260, marginY+340, marginY+420};

        inText = new JTextField("0");
        inText.setBounds(x[0],y[0],350,70);
        inText.setFont(new Font("Helvetica", Font.PLAIN, 33));
        inText.setEnabled(true);
        window.add(inText);


    private Calculator() {
        window = new JFrame("Calculator");
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT); // Set the width and the Height of the window
        window.setLocationRelativeTo(null); // Move Window To Center

        // Button fonts
        Font btnFont = new Font("Comic Sans MS", Font.PLAIN, 28);
        Font smallTxtBtnFont = new Font("Comic Sans MS", Font.PLAIN, 24);

        btnSwitchThemes = new JButton();
        btnSwitchThemes.setBounds(230, 30, 140, 18);
        btnSwitchThemes.setText("Toggle colors");
        btnSwitchThemes.setBackground(Color.GREEN.darker());
        btnSwitchThemes.setForeground(Color.WHITE);
        btnSwitchThemes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSwitchThemes.addActionListener(event -> onSwitchTheme());
        window.add(btnSwitchThemes);

        btnSwitchToScientificMode = new JButton();
        btnSwitchToScientificMode.setBounds(30, 30, 140, 18);
        btnSwitchToScientificMode.setText("Scientific Mode");
        btnSwitchToScientificMode.setBackground(Color.BLUE.darker());
        btnSwitchToScientificMode.setForeground(Color.WHITE);
        btnSwitchToScientificMode.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSwitchToScientificMode.addActionListener(event -> onShowScientificMode());
        window.add(btnSwitchToScientificMode);

        int j = -1;
        int k = -1;
        int[] x = {MARGIN_X, MARGIN_X + 90, 200, 290, 380};
        int[] y = {MARGIN_Y, MARGIN_Y + 100, MARGIN_Y + 180, MARGIN_Y + 260, MARGIN_Y + 340, MARGIN_Y + 420};

        inText = new JTextField("0");
        inText.setBounds(x[0], y[0], 350, 70);
        inText.setEditable(false);
        inText.setBackground(Color.WHITE);
        inText.setFont(new Font("Comic Sans MS", Font.PLAIN, 33));
        window.add(inText);


        btnC = new JButton("C");
        btnC.setBounds(x[0], y[1], BUTTON_WIDTH, BUTTON_HEIGHT);
        btnC.setFont(btnFont);
        btnC.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnC.addActionListener(event -> {
            repaintFont();
            inText.setText("0");
            opt = ' ';
            val = 0;
        });
        window.add(btnC);

        /*
         * This is my modified code that squares a number that is
         * inputted into the calculator, using the Math Java package
         * and powering that number to the value of number inputted's power.
         */
        btnPow = new JButton("^");
        btnPow.setBounds(x[1], y[1], wBtn, hBtn);
        btnPow.setFont(btnFont);
        btnPow.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText())) {
                    if (go) {
                        val = calc(val, inText.getText(), opt);
                        if ( Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val)) ) {
                            inText.setText(String.valueOf((int)val));
                        } else {
                            inText.setText(String.valueOf(val));
                        }
                        opt = '^';
                        go = false;
                        addWrite = false;
                    }
                }
            }
        });
        window.add(btnPow); // add the square button

        /*
         * This is the code for backspacing on characters entered into calculator
         */
        btnBack = new JButton("<-");
        btnBack.setBounds(x[1], y[1], BUTTON_WIDTH, BUTTON_HEIGHT);
        btnBack.setFont(btnFont);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(event -> {
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
        window.add(btnBack);

        btnDiv = new JButton("/");
        btnDiv.setBounds(x[3], y[1], BUTTON_WIDTH, BUTTON_HEIGHT);
        btnDiv.setFont(btnFont);
        btnDiv.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDiv.addActionListener(event -> {
            repaintFont();
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    opt = '/';
                    go = false;
                    addWrite = false;
                } else {
                    opt = '/';
                }
        });
        window.add(btnDiv);

        btn7 = new JButton("7");
        btn7.setBounds(x[0], y[2], BUTTON_WIDTH, BUTTON_HEIGHT);
        btn7.setFont(btnFont);
        btn7.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn7.addActionListener(event -> {
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
        window.add(btn7);

        btn8 = new JButton("8");
        btn8.setBounds(x[1], y[2], BUTTON_WIDTH, BUTTON_HEIGHT);
        btn8.setFont(btnFont);
        btn8.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn8.addActionListener(event -> {
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
        window.add(btn8);

        btn9 = new JButton("9");
        btn9.setBounds(x[2], y[2], BUTTON_WIDTH, BUTTON_HEIGHT);
        btn9.setFont(btnFont);
        btn9.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn9.addActionListener(event -> {
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
        window.add(btn9);

        btnMul = new JButton("*");
        btnMul.setBounds(x[3], y[2], BUTTON_WIDTH, BUTTON_HEIGHT);
        btnMul.setFont(btnFont);
        btnMul.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnMul.addActionListener(event -> {
            repaintFont();
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    opt = '*';
                    go = false;
                    addWrite = false;
                } else {
                    opt = '*';
                }
        });
        window.add(btnMul);

        btn4 = new JButton("4");
        btn4.setBounds(x[0], y[3], BUTTON_WIDTH, BUTTON_HEIGHT);
        btn4.setFont(btnFont);
        btn4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn4.addActionListener(event -> {
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
        window.add(btn4);

        btn5 = new JButton("5");
        btn5.setBounds(x[1], y[3], BUTTON_WIDTH, BUTTON_HEIGHT);
        btn5.setFont(btnFont);
        btn5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn5.addActionListener(event -> {
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
        window.add(btn5);

        btn6 = new JButton("6");
        btn6.setBounds(x[2], y[3], BUTTON_WIDTH, BUTTON_HEIGHT);
        btn6.setFont(btnFont);
        btn6.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn6.addActionListener(event -> {
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
        window.add(btn6);

        btnSub = new JButton("-");
        btnSub.setBounds(x[3], y[3], BUTTON_WIDTH, BUTTON_HEIGHT);
        btnSub.setFont(btnFont);
        btnSub.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSub.addActionListener(event -> {
            repaintFont();
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }

                    opt = '-';
                    go = false;
                    addWrite = false;
                } else {
                    opt = '-';
                }
        });
        window.add(btnSub);

        btn1 = new JButton("1");
        btn1.setBounds(x[0], y[4], BUTTON_WIDTH, BUTTON_HEIGHT);
        btn1.setFont(btnFont);
        btn1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn1.addActionListener(event -> {
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
        window.add(btn1);

        btn2 = new JButton("2");
        btn2.setBounds(x[1], y[4], BUTTON_WIDTH, BUTTON_HEIGHT);
        btn2.setFont(btnFont);
        btn2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn2.addActionListener(event -> {
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
        window.add(btn2);

        btn3 = new JButton("3");
        btn3.setBounds(x[2], y[4], BUTTON_WIDTH, BUTTON_HEIGHT);
        btn3.setFont(btnFont);
        btn3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn3.addActionListener(event -> {
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
        window.add(btn3);

        btnAdd = new JButton("+");
        btnAdd.setBounds(x[3], y[4], BUTTON_WIDTH, BUTTON_HEIGHT);
        btnAdd.setFont(btnFont);
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAdd.addActionListener(event -> {
            repaintFont();
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    opt = '+';
                    go = false;
                    addWrite = false;
                } else {
                    opt = '+';
                }
        });
        window.add(btnAdd);

        btnPoint = new JButton(".");
        btnPoint.setBounds(x[0],y[5],wBtn,hBtn);
        btnPoint.setFont(new Font("Helvetica", Font.BOLD, 32));
        btnPoint.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addWrite) {
                    inText.setText(inText.getText() + ".");
                } else {
                    inText.setText("0.");
                    addWrite = true;
                }
                go = true;

        btnPoint = new JButton(".");
        btnPoint.setBounds(x[0], y[5], BUTTON_WIDTH, BUTTON_HEIGHT);
        btnPoint.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        btnPoint.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPoint.addActionListener(event -> {
            repaintFont();
            if (addWrite) {
                inText.setText(inText.getText() + ".");
            } else {
                inText.setText("0.");
                addWrite = true;
            }
            go = true;
        });
        window.add(btnPoint);

        btn0 = new JButton("0");
        btn0.setBounds(x[1], y[5], BUTTON_WIDTH, BUTTON_HEIGHT);
        btn0.setFont(btnFont);
        btn0.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn0.addActionListener(event -> {
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
        window.add(btn0);

        btnEqual = new JButton("=");
        btnEqual.setBounds(x[2], y[5], 2 * BUTTON_WIDTH + 10, BUTTON_HEIGHT);
        btnEqual.setFont(btnFont);
        btnEqual.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEqual.addActionListener(event -> {
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
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
        window.add(btnEqual);

        btnPower = new JButton("pow");
        btnPower.setBounds(x[4], y[2], BUTTON_WIDTH, BUTTON_HEIGHT);
        btnPower.setFont(smallTxtBtnFont);
        btnPower.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPower.addActionListener(event -> {
            repaintFont();
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    opt = '^';
                    go = false;
                    addWrite = false;
                } else {
                    opt = '^';
                }
        });
        btnPower.setVisible(false);
        window.add(btnPower);

    // Calculation switch cases
    static double calc(double x, String input, char opt) {
        double y = Double.parseDouble(input);
        switch(opt) {
            case '+':
                return x + y; // Addition operation
            case '-':
                return x - y; // Subtraction operation
            case '*':
                return x * y; // Multiplication operation
            case '/':
                return x / y; // Division operation
            case '^':
                return Math.pow(x, y); // Power operation
            default:
                return y;
        }
    }

    /**
     * root
     *
     * Takes the nth root of a number.
     *
     * Example:
     * cubed root of 64 is 4, user would have to enter
     * the base number first (64) of double type followed
     * by the double root value (3) in order to get 4
     *
     * @param num the number base
     * @param root the nth root of the number base num
     * @return r - the nth root of the number
     */
    public static double root(double num, double root) {

        /*
            Take square root of number and check if the
            root number is 2 and that root matches the value
            of what it is in square root function

            * Only possible if user checking for square root
            of number *
         */
        double sq = (int) Math.sqrt(num);

        /*
            Use the power function and take value of e to the
            power of quotient of the log of the base number and
            the degree of the root

            This is value of r as listed below
         */
        double r = Math.pow(Math.E, Math.log(num)/root);

        /*
            If the base number is less than 0 then multiply by -1
            the solution for the value of r
         */
        if (num < 0) {
            r *= -1;
            return r;
        }
        /*
            If value of square root function sqrt matches the value of
            r as listed above, return the square root value to prevent most
            actual square roots from being presented as decimals close to
            the actual value of square root of a number

            Only possible if root value is equal to 2
         */
        if (sq != r && root == 2){
            return sq;
        }
        return (int) r+1;
    }


    public void themeColor() {

        if (bool) {

            btnC.setBackground(Color.getColor(""));
            btnBack.setBackground(Color.getColor(""));
            btnPow.setBackground(Color.BLACK);
            btnDiv.setBackground(Color.getColor(""));
            btnMul.setBackground(Color.getColor(""));
            btnSub.setBackground(Color.getColor(""));
            btnAdd.setBackground(Color.getColor(""));
            btnEqual.setBackground(Color.getColor(""));
            btn0.setBackground(Color.getColor(""));
            btn1.setBackground(Color.getColor(""));
            btn2.setBackground(Color.getColor(""));
            btn3.setBackground(Color.getColor(""));
            btn4.setBackground(Color.getColor(""));
            btn5.setBackground(Color.getColor(""));
            btn6.setBackground(Color.getColor(""));
            btn7.setBackground(Color.getColor(""));
            btn8.setBackground(Color.getColor(""));
            btn9.setBackground(Color.getColor(""));
            btnPoint.setBackground(Color.getColor(""));

    private double calc(double x, String input, char opt) {
        inText.setFont(inText.getFont().deriveFont(Font.BOLD));
        double y = Double.parseDouble(input);
        if (opt == '+') {
            return x + y;
        } else if (opt == '-') {
            return x - y;
        } else if (opt == '*') {
            return x * y;
        } else if (opt == '/') {
            return x / y;
        } else if (opt == '^') {
            return Math.pow(x, y);
        }
        inText.setFont(inText.getFont().deriveFont(Font.PLAIN));
        return y;
    }

    private void repaintFont() {
        inText.setFont(inText.getFont().deriveFont(Font.PLAIN));
    }

    private void onSwitchTheme() {
        if (isToggleColorSelected) {
            btnSwitchThemes.setText("Toggle colors");
            btnSwitchThemes.setBackground(Color.GREEN.darker());
            btnSwitchThemes.setForeground(Color.WHITE);
            btnC.setBackground(null);
            btnBack.setBackground(null);
            btnDiv.setBackground(null);
            btnMul.setBackground(null);
            btnSub.setBackground(null);
            btnAdd.setBackground(null);
            btnRoot.setBackground(null);
            btnLog.setBackground(null);
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
            btnPow.setForeground(Color.BLACK);

            btnDiv.setForeground(Color.BLACK);
            btnMul.setForeground(Color.BLACK);
            btnSub.setForeground(Color.BLACK);
            btnAdd.setForeground(Color.BLACK);
            btnEqual.setForeground(Color.BLACK);
            btnLog.setForeground(Color.BLACK);
            btnPower.setForeground(Color.BLACK);
            btnRoot.setForeground(Color.BLACK);

            isToggleColorSelected = false;
        } 
      else {
            btnSwitchThemes.setText("Untoggle colors");
            btnSwitchThemes.setBackground(null);
            btnSwitchThemes.setForeground(Color.BLACK);
            btnC.setBackground(Color.RED);
            btnBack.setBackground(Color.ORANGE);

            btnPow.setForeground(Color.PINK);

            btnDiv.setBackground(Color.PINK);
            btnMul.setBackground(Color.PINK);
            btnSub.setBackground(Color.PINK);
            btnAdd.setBackground(Color.PINK);
            btnRoot.setBackground(Color.PINK);
            btnLog.setBackground(Color.PINK);
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
            btnPow.setForeground(Color.WHITE);

            btnDiv.setForeground(Color.WHITE);
            btnMul.setForeground(Color.WHITE);
            btnSub.setForeground(Color.WHITE);
            btnAdd.setForeground(Color.WHITE);
            btnEqual.setForeground(Color.WHITE);
            btnLog.setForeground(Color.WHITE);
            btnPower.setForeground(Color.WHITE);
            isToggleColorSelected = true;
        }
    }

    private void onShowScientificMode() {
        if (isScientificMode) {
            window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
            btnRoot.setVisible(false);
            btnPower.setVisible(false);
            btnLog.setVisible(false);
            isScientificMode = false;
        } else {
            window.setSize(WINDOW_WIDTH + 80, WINDOW_HEIGHT);
            btnRoot.setVisible(true);
            btnPower.setVisible(true);
            btnLog.setVisible(true);
            isScientificMode = true;
        }
    }


    // Creates a calculator object for the user once program is run

    public static void main(String[] args) {

        new Calculator();
    }
}


package Calculator.src.com.houarizegai.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class Calculator {

    JFrame window; // This is Main Window
    JTextField inText,affichageCalc; // Input Text
    JButton btnC, //btnBack,

            btnMod,btnDiv,btn7,btn8,btn9,
            btnMul,btn4,btn5,btn6,btnSub,btn1,btn2,btn3,
            btnAdd,btnPoint,btn0,btnEqual,choixColor

        ,btnPow, btnRoot

        ;
    char opt = ' ';             // Storage Oparator
    // maybe change that to string for instances like log, ln, etc.

    boolean go = true,          // Faire Calcule Avec Opt != (=)
            addWrite = true;    // Racordé des Nombres dans l'Affichage
    double val = 0; // Storage Values For Calcule
    boolean bool = false;
    /*
        Mx Calculator:
        X = Row
        Y = Column

        +-------------------+
        |   +-----------+   |   y[0]
        |   |           |   |
        |   +-----------+   |
        |                   |
        |   C   ^   %   /   |   y[1]
        |   7   8   9   *   |   y[2]
        |   4   5   6   -   |   y[3]
        |   1   2   3   +   |   y[4]
        |   .   0     =     |   y[5]
        +-------------------+
         x[0] x[1] x[2] x[3]

    */

    /*

        * Note the modifications made regarding the backspace.
        * This has been removed in place for the power button,
        * which will take one number and power it to the input
        * from the user that is the second number.

        * -Added by Matthew Chin (github.com/matthewjchin)
        * 3 August 2019 15:26


        Mx Calculator:
        X = Row
        Y = Column

        +------------------------+
        |   +---------------+    |   y[0]
        |   |               |    |
        |   +---------------+    |
        |                        |
        |   C   ^   %   /   √    |   y[1]
        |   7   8   9   *        |   y[2]
        |   4   5   6   -        |   y[3]
        |   1   2   3   +        |   y[4]
        |   .   0     =          |   y[5]
        +------------------------+
         x[0] x[1] x[2] x[3] x[4]

    */

    /*
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


        // Column 0

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

        // Column 1

        btnC = new JButton("C");
        btnC.setBounds(x[0],y[1],wBtn,hBtn);
        btnC.setFont(btnFont);
        btnC.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inText.setText("0");
                opt = ' ';
                val = 0;
            }
        });
        window.add(btnC);

        /*
         * This is the code for backspacing on characters entered into calculator
         */
//        btnBack = new JButton("<-");
//        btnBack.setBounds(x[1],y[1],wBtn,hBtn);
//        btnBack.setFont(btnFont);
//        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        btnBack.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String str = inText.getText();
//                String str2 = "";
//                for(int i = 0; i < (str.length() - 1); i++) {
//                    str2 += str.charAt(i);
//                }
//                if(str2 == "") {
//                    inText.setText("0");
//                } else {
//                    inText.setText(str2);
//                }
//            }
//        });
//        window.add(btnBack);

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


        btnMod = new JButton("%");
        btnMod.setBounds(x[2],y[1],wBtn,hBtn);
        btnMod.setFont(btnFont);
        btnMod.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnMod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                    if (go) {
                        val = calc(val, inText.getText(),opt);
                        if ( Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val)) ) {
                            inText.setText(String.valueOf((int)val));
                        } else {
                            inText.setText(String.valueOf(val));
                        }
                        opt = '%';
                        go = false;
                        addWrite = false;
                    }
            }
        });
        window.add(btnMod);

        btnDiv = new JButton("/");
        btnDiv.setBounds(x[3],y[1],wBtn,hBtn);
        btnDiv.setFont(btnFont);
        btnDiv.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDiv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                    if (go) {
                        val = calc(val, inText.getText(),opt);
                        if ( Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val)) ) {
                            inText.setText(String.valueOf((int)val));
                        } else {
                            inText.setText(String.valueOf(val));
                        }
                        opt = '/';
                        go = false;
                        addWrite = false;
                    }
            }
        });
        window.add(btnDiv);


        /*
            This is the root button.
            The Java.lang.Math package does not have a function that takes a number to the
            nth root; to say the least there is no known function for a number to be taken
            to the nth root at all. Only known function is sqrt() which takes one number
            to the second/square root of the number that is entered

         */
        btnRoot = new JButton("√");
        btnRoot.setBounds(x[4], y[1], wBtn, hBtn);
        btnRoot.setFont(btnFont);
        btnRoot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRoot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                    if (go) {
                        val = calc(val, inText.getText(), opt);
                        if ( Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val)) ) {
                            inText.setText(String.valueOf((int)val));
                        } else {
                            inText.setText(String.valueOf(val));
                        }
                        opt = '√';
                        go = false;
                        addWrite = false;
                    }
            }
        });
        window.add(btnRoot);


        // Column 2

        btn7 = new JButton("7");
        btn7.setBounds(x[0],y[2],wBtn,hBtn);
        btn7.setFont(btnFont);
        btn7.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addWrite) {
                    if(Pattern.matches("[0]*", inText.getText())){
                        inText.setText("7");
                    } else {
                        inText.setText(inText.getText() + "7");
                    }
                } else {
                    inText.setText("7");
                    addWrite = true;
                }
                go = true;
            }
        });
        window.add(btn7);

        btn8 = new JButton("8");
        btn8.setBounds(x[1],y[2],wBtn,hBtn);
        btn8.setFont(btnFont);
        btn8.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addWrite) {
                    if(Pattern.matches("[0]*", inText.getText())){
                        inText.setText("8");
                    } else {
                        inText.setText(inText.getText() + "8");
                    }
                } else {
                    inText.setText("8");
                    addWrite = true;
                }
                go = true;
            }
        });
        window.add(btn8);

        btn9 = new JButton("9");
        btn9.setBounds(x[2],y[2],wBtn,hBtn);
        btn9.setFont(btnFont);
        btn9.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addWrite) {
                    if(Pattern.matches("[0]*", inText.getText())){
                        inText.setText("9");
                    } else {
                        inText.setText(inText.getText() + "9");
                    }
                } else {
                    inText.setText("9");
                    addWrite = true;
                }
                go = true;
            }
        });
        window.add(btn9);

        btnMul = new JButton("*");
        btnMul.setBounds(x[3],y[2],wBtn,hBtn);
        btnMul.setFont(btnFont);
        btnMul.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnMul.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                    if (go) {
                        val = calc(val, inText.getText(), opt);
                        if ( Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val)) ) {
                            inText.setText(String.valueOf((int)val));
                        } else {
                            inText.setText(String.valueOf(val));
                        }
                        opt = '*';
                        go = false;
                        addWrite = false;
                    } else {
                        opt = '*';
                    }
            }
        });
        window.add(btnMul);


        // Column 3

        btn4 = new JButton("4");
        btn4.setBounds(x[0],y[3],wBtn,hBtn);
        btn4.setFont(btnFont);
        btn4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addWrite) {
                    if(Pattern.matches("[0]*", inText.getText())){
                        inText.setText("4");
                    } else {
                        inText.setText(inText.getText() + "4");
                    }
                } else {
                    inText.setText("4");
                    addWrite = true;
                }
                go = true;
            }
        });
        window.add(btn4);

        btn5 = new JButton("5");
        btn5.setBounds(x[1],y[3],wBtn,hBtn);
        btn5.setFont(btnFont);
        btn5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addWrite) {
                    if(Pattern.matches("[0]*", inText.getText())){
                        inText.setText("5");
                    } else {
                        inText.setText(inText.getText() + "5");
                    }
                } else {
                    inText.setText("5");
                    addWrite = true;
                }
                go = true;
            }
        });
        window.add(btn5);

        btn6 = new JButton("6");
        btn6.setBounds(x[2],y[3],wBtn,hBtn);
        btn6.setFont(btnFont);
        btn6.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addWrite) {
                    if(Pattern.matches("[0]*", inText.getText())){
                        inText.setText("6");
                    } else {
                        inText.setText(inText.getText() + "6");
                    }
                } else {
                    inText.setText("6");
                    addWrite = true;
                }
                go = true;
            }
        });
        window.add(btn6);

        btnSub = new JButton("-");
        btnSub.setBounds(x[3],y[3],wBtn,hBtn);
        btnSub.setFont(btnFont);
        btnSub.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                    if (go) {
                        val = calc(val, inText.getText(), opt);
                        if ( Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val)) ) {
                            inText.setText(String.valueOf((int)val));
                        } else {
                            inText.setText(String.valueOf(val));
                        }

                        opt = '-';
                        go = false;
                        addWrite = false;
                    } else {
                        opt = '-';
                    }
            }
        });
        window.add(btnSub);


        // Column 4

        btn1 = new JButton("1");
        btn1.setBounds(x[0],y[4],wBtn,hBtn);
        btn1.setFont(btnFont);
        btn1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addWrite) {
                    if(Pattern.matches("[0]*", inText.getText())){
                        inText.setText("1");
                    } else {
                        inText.setText(inText.getText() + "1");
                    }
                } else {
                    inText.setText("1");
                    addWrite = true;
                }
                go = true;
            }
        });
        window.add(btn1);

        btn2 = new JButton("2");
        btn2.setBounds(x[1],y[4],wBtn,hBtn);
        btn2.setFont(btnFont);
        btn2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addWrite) {
                    if(Pattern.matches("[0]*", inText.getText())){
                        inText.setText("2");
                    } else {
                        inText.setText(inText.getText() + "2");
                    }
                } else {
                    inText.setText("2");
                    addWrite = true;
                }
                go = true;
            }
        });
        window.add(btn2);

        btn3 = new JButton("3");
        btn3.setBounds(x[2], y[4], wBtn, hBtn);
        btn3.setFont(btnFont);
        btn3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addWrite) {
                    if(Pattern.matches("[0]*", inText.getText())){
                        inText.setText("3");
                    } else {
                        inText.setText(inText.getText() + "3");
                    }
                } else {
                    inText.setText("3");
                    addWrite = true;
                }
                go = true;
            }
        });
        window.add(btn3);

        btnAdd = new JButton("+");
        btnAdd.setBounds(x[3], y[4], wBtn, hBtn);
        btnAdd.setFont(btnFont);
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                    if (go) {
                        val = calc(val, inText.getText(),opt);
                        if ( Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val)) ) {
                            inText.setText(String.valueOf((int)val));
                        } else {
                            inText.setText(String.valueOf(val));
                        }
                        opt = '+';
                        go = false;
                        addWrite = false;
                    } else {
                        opt = '+';
                    }
            }
        });
        window.add(btnAdd);

        // Column 5

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
            }
        });
        window.add(btnPoint);

        btn0 = new JButton("0");
        btn0.setBounds(x[1],y[5],wBtn,hBtn);
        btn0.setFont(btnFont);
        btn0.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addWrite) {
                    if(Pattern.matches("[0]*", inText.getText())){
                        inText.setText("0");
                    } else {
                        inText.setText(inText.getText() + "0");
                    }
                } else {
                    inText.setText("0");
                    addWrite = true;
                }
                go = true;
            }
        });
        window.add(btn0);

        btnEqual = new JButton("=");
        btnEqual.setBounds(x[2],y[5],2*wBtn+10,hBtn);
        btnEqual.setFont(btnFont);
        btnEqual.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEqual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                    if (go) {
                        val = calc(val, inText.getText(),opt);
                        if ( Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val)) ) {
                            inText.setText(String.valueOf((int)val));
                        } else {
                            inText.setText(String.valueOf(val));
                        }
                        opt = '=';
                        addWrite = false;
                    }
            }
        });
        window.add(btnEqual);

        window.setLayout(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // If Click into The Red Button => End The Processus
        window.setVisible(true);
    }


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
            case '√':
                return root(x, y); // Root operation, returns
            case '%':
                return x % y; // Modulus operation
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
//            btnBack.setBackground(Color.getColor(""));
            btnPow.setBackground(Color.BLACK);
            btnRoot.setBackground(Color.BLACK);

            btnMod.setBackground(Color.getColor(""));
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

            btnC.setForeground(Color.BLACK);
//            btnBack.setForeground(Color.BLACK);

            btnPow.setForeground(Color.BLACK);
            btnRoot.setForeground(Color.BLACK);

            btnMod.setForeground(Color.BLACK);
            btnDiv.setForeground(Color.BLACK);
            btnMul.setForeground(Color.BLACK);
            btnSub.setForeground(Color.BLACK);
            btnAdd.setForeground(Color.BLACK);
            btnEqual.setForeground(Color.BLACK);

            bool = false;
        } else {
            btnC.setBackground(Color.RED);
//            btnBack.setBackground(Color.ORANGE);

            btnPow.setForeground(Color.PINK);
            btnRoot.setForeground(Color.PINK);

            btnMod.setBackground(Color.GREEN);
            btnDiv.setBackground(Color.PINK);
            btnMul.setBackground(Color.PINK);
            btnSub.setBackground(Color.PINK);
            btnAdd.setBackground(Color.PINK);
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
//            btnBack.setForeground(Color.WHITE);
            btnPow.setForeground(Color.WHITE);
            btnRoot.setForeground(Color.WHITE);

            btnMod.setForeground(Color.WHITE);
            btnDiv.setForeground(Color.WHITE);
            btnMul.setForeground(Color.WHITE);
            btnSub.setForeground(Color.WHITE);
            btnAdd.setForeground(Color.WHITE);
            btnEqual.setForeground(Color.WHITE);

            bool = true;
        }
    }

    // Creates a calculator object for the user once program is run
    public static void main(String[] args) {

        new Calculator();
    }
}

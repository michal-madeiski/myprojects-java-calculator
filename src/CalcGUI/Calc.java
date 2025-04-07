package CalcGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

//png files from: https://www.vecteezy.com/search?qterm=calculator-icon-free&content_type=png
//png files authors: Auraiwan Dechsom
//<a href="https://www.vecteezy.com/free-png/market">Market PNGs by Vecteezy</a>
//all png files are free

public class Calc extends JFrame implements ActionListener {
     JTextField textField;
     JButton[] funcs = new JButton[9];
     JButton add_, sub_, mul_, div_, equ_, del_, clr_, dot_, neg_;
     JButton[] nums = new JButton[10];
     JPanel panel;
     BigDecimal num1, num2, result;
     String oper = "!";
     int temp;
     int prev = 0;
     int dot_count = 0;
     boolean second = false;

    public Calc() {
        super("Kalkulator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(420, 550);
        setLocation(350, 100);
        getContentPane().setBackground(new Color(30, 30, 30));
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/calcIcon.png"))).getImage());
        setLayout(null);

        //TEXT_FIELD
        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(new Font("Verdana", Font.PLAIN, 23));
        textField.setBackground(new Color (50, 50, 50));
        textField.setForeground(new Color(240, 240, 240));
        textField.setBorder(BorderFactory.createLineBorder(new Color (50, 50, 50), 2));
        textField.setEditable(false);
        //TEXT_FIELD

        //BUTTONS
        add_ = new JButton("+");
        sub_ = new JButton("-");
        mul_ = new JButton("*");
        div_ = new JButton("/");
        equ_ = new JButton("=");;
        del_ = new JButton("Delete");
        clr_ = new JButton("Clear");
        dot_ = new JButton(".");
        neg_ = new JButton("(-)");

        funcs[0] = add_;
        funcs[1] = sub_;
        funcs[2] = mul_;
        funcs[3] = div_;
        funcs[4] = equ_;
        funcs[5] = dot_;
        funcs[6] = clr_;
        funcs[7] = del_;
        funcs[8] = neg_;

        for (int i = 0; i < funcs.length; i++) {
            funcs[i].addActionListener(this);
            funcs[i].setForeground(new Color(240, 240, 240));
            if (i < 6) {
                funcs[i].setFont(new Font("Verdana", Font.BOLD, 15));
                funcs[i].setBackground(new Color (255, 129, 28));
                funcs[i].setBorder(BorderFactory.createLineBorder(new Color (255, 129, 28), 2));
            } else {
                funcs[i].setBackground(new Color (100, 100, 100));
                funcs[i].setBorder(BorderFactory.createLineBorder(new Color (100, 100, 100), 2));
            }
            funcs[i].setFocusable(false);
        }
        funcs[6].setFont(new Font("Verdana", Font.ITALIC, 17));
        funcs[7].setFont(new Font("Verdana", Font.ITALIC, 17));
        funcs[8].setFont(new Font("Verdana", Font.PLAIN, 17));

        for (int i = 0; i < nums.length; i++) {
            nums[i] = new JButton(String.valueOf(i));
            nums[i].addActionListener(this);
            nums[i].setFont(new Font("Verdana", Font.PLAIN, 15));
            nums[i].setForeground(new Color(240, 240, 240));
            nums[i].setBackground(new Color (50, 50, 50));
            nums[i].setBorder(BorderFactory.createLineBorder(new Color (50, 50, 50), 2));
            nums[i].setFocusable(false);
        }

        neg_.setBounds(50, 430, 95, 50);
        del_.setBounds(150, 430, 95, 50);
        clr_.setBounds(250, 430, 95, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(new Color(30, 30, 30));

        for (int i = 1; i < 4; i++) {
            panel.add(nums[i]);
        }
        panel.add(div_);
        for (int i = 4; i < 7; i++) {
            panel.add(nums[i]);
        }
        panel.add(mul_);
        for (int i = 7; i < 10; i++) {
            panel.add(nums[i]);
        }
        panel.add(sub_);
        panel.add(dot_);
        panel.add(nums[0]);
        panel.add(equ_);
        panel.add(add_);
        //BUTTONS

        add(panel);
        add(textField);
        add(neg_);
        add(del_);
        add(clr_);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == nums[i] && !second) {
                if (textField.getText().equals("Error")) {
                    textField.setText(String.valueOf(i));
                } else {
                    if (textField.getText().equals("0")) {
                        textField.setText(String.valueOf(i));
                    } else {
                        textField.setText(textField.getText().concat(String.valueOf(i)));
                    }
                }
            } else if (e.getSource() == nums[i] && second){
                dot_count = 0;
                textField.setText(String.valueOf(i));
                second = false;
                funcs[temp].setForeground(new Color(240, 240, 240));
                funcs[temp].setBackground(new Color (255, 129, 28));
                funcs[temp].setBorder(BorderFactory.createLineBorder(new Color (255, 129, 28), 2));
            }
        }

        if (e.getSource() == dot_) {
            if (!textField.getText().equals("Error") && !textField.getText().equals("") && (oper.equals("!") || !second) && dot_count == 0) {
                textField.setText(textField.getText().concat("."));
                dot_count++;
            }
            else if (result == null && !second && num1 == null) {
                textField.setText("0.");
            }
        }

        for (int i = 0; i < 4; i++) {
            if (e.getSource() == funcs[i]) {
                if (!textField.getText().equals("Error") && !textField.getText().equals("")) {
                    funcs[prev].setForeground(new Color(240, 240, 240));
                    funcs[prev].setBackground(new Color(255, 129, 28));
                    funcs[prev].setBorder(BorderFactory.createLineBorder(new Color(255, 129, 28), 2));
                    temp = i;
                    funcs[i].setForeground(new Color(255, 129, 28));
                    funcs[i].setBackground(new Color(240, 240, 240));
                    funcs[i].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 2));
                    oper = funcs[i].getText();
                    num1 = new BigDecimal(String.valueOf(textField.getText()));
                    second = true;
                    prev = i;
                }
            }
        }

        if (e.getSource() == equ_) {
            if (!textField.getText().equals("Error") && !textField.getText().equals("")) {
                num2 = new BigDecimal(String.valueOf(textField.getText()));
                if (oper.equals("/")) {
                    if (num2.equals(new BigDecimal(0))) {
                        textField.setText("Error");
                        oper = "!";
                        num1 = null;
                        num2 = null;
                        dot_count = 0;
                    } else {
                        try {
                            result = num1.divide(num2);
                            oper = "!";
                            dot_count = 1;
                            textField.setText(String.valueOf(result));
                        } catch (ArithmeticException arithmeticException) {
                            result = num1.divide(num2, 9, RoundingMode.HALF_UP);
                            oper = "!";
                            dot_count = 1;
                            textField.setText(String.valueOf(result));
                            num1 = null;
                            second = false;
                            result = null;
                        }
                    }
                } else {
                    funcs[prev].setForeground(new Color(240, 240, 240));
                    funcs[prev].setBackground(new Color(255, 129, 28));
                    funcs[prev].setBorder(BorderFactory.createLineBorder(new Color(255, 129, 28), 2));
                    result = switch (oper) {
                        case "+" -> num1.add(num2);
                        case "-" -> num1.subtract(num2);
                        case "*" -> num1.multiply(num2);
                        default -> num2;
                    };
                    oper = "!";
                    dot_count = 1;
                    textField.setText(String.valueOf(result));
                    result = null;
                    num1 = null;
                    second = false;
                }
            } else if (!textField.getText().equals("Error")){
                textField.setText("");
            }
        }

        if (e.getSource() == clr_) {
            dot_count = 0;
            textField.setText("");
        }

        if (e.getSource() == del_ && (oper.equals("!") || !second)) {
            if (!textField.getText().equals("Error")) {
                String s = textField.getText();
                textField.setText("");
                for (int i = 0; i < s.length() - 1; i++) {
                    textField.setText(textField.getText() + s.charAt(i));
                }
            }
        }

        if (e.getSource() == neg_ && (oper.equals("!") || !second)) {
            if (!textField.getText().equals("Error")) {
                String s = textField.getText();
                if (!s.equals("")) {
                    if (s.charAt(0) == '-') {
                        textField.setText("");
                        for (int i = 1; i < s.length(); i++) {
                            textField.setText(textField.getText() + s.charAt(i));
                        }
                    } else {
                        textField.setText("-");
                        for (int i = 0; i < s.length(); i++) {
                            textField.setText(textField.getText() + s.charAt(i));
                        }
                    }
                }
            }
        }
    }
}

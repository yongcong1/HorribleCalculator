/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horriblecalculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.EmptyStackException;
import java.util.HashSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Yongcong
 */
public class CalculatorDisplay implements KeyListener {

    private int screenWidth = 305;
    private int screenHeight = 400;
    private int fontSize = 16;
    private JTextField input = new JTextField("");
    private JTextField solution = new JTextField("");
    private JFrame calcFrame;
    private HashSet<Character> operations = new HashSet<>();
    private int buttonWidth = 60;
    private int buttonHeight = 35;
    private int buttonGap = 15;
    private String defaultInputText = "0";
    private String defaultSolutionText = "answer";

    public CalculatorDisplay() {
        operations = new HashSet<>();
        setUpOperation(operations);
        calcFrame = new JFrame();
        createFrame(calcFrame);
        calculatorInterface(calcFrame);
    }

    private int setUpTextField(int column1, int solutionRow) {
        int textFieldHeight = 40;
        /*------------------------------
            solution and input textfield
        ------------------------------*/
        input.setHorizontalAlignment(JTextField.RIGHT);
        input.setSize(screenWidth - buttonGap * 2, textFieldHeight);
        input.setLocation(column1, solutionRow + textFieldHeight + 10);
        input.setBackground(Color.WHITE);
        input.setEditable(false);
        input.setFocusable(false);
        input.setText(defaultInputText);
        //input
        solution.setSize(screenWidth - buttonGap * 2, textFieldHeight);
        solution.setHorizontalAlignment(JTextField.RIGHT);
        solution.setLocation(column1, solutionRow);
        solution.setEditable(false);
        solution.setFocusable(false);
        solution.setText(defaultSolutionText);

        return solutionRow + (textFieldHeight + buttonGap) * 2;
    }

    private void setUpOperation(HashSet<Character> operations) {
        operations.add('+');
        operations.add('-');
        operations.add('/');
        operations.add('*');
    }

    private void createFrame(JFrame frame) {
        String title = "Horrible Calculator";
        frame.setTitle(title);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(screenWidth, screenHeight);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setLocationRelativeTo(null);
        frame.addKeyListener(this);
        frame.setVisible(true);
    }

    private void calculatorInterface(JFrame calcFrame) {
        int solutionRow = 10;
        int column1 = 15;
        int column2 = buttonGap + column1 + buttonWidth;
        int column3 = buttonGap + column2 + buttonWidth;
        int column4 = buttonGap + column3 + buttonWidth;
        int row5 = setUpTextField(column1, solutionRow);
        int row4 = row5 + 50;
        int row3 = row4 + 50;
        int row2 = row3 + 50;
        int row1 = row2 + 50;
        /*Button Layout
   
          1 2 3 4 
          _______
        1|  / * -
        2|7 8 9
        3|4 5 6 +
        4|1 2 3
        5|0   . =
        
        **********/

 /*------------------------------
            create buttons here
        ------------------------------*/
        JButton clear = new JButton("c");
        clear.setFocusable(false);
        JButton back = new JButton("delete");
        back.setFocusable(false);
        JButton[] buttonNums = new JButton[10];
        for (int i = 0; i < buttonNums.length; i++) {
            buttonNums[i] = new JButton(i + "");
            buttonNums[i].setFocusable(false);
        }

        JButton plus = new JButton("+");
        plus.setFocusable(false);
        JButton equal = new JButton("=");
        equal.setFocusable(false);
        JButton times = new JButton("*");
        times.setFocusable(false);
        JButton point = new JButton(".");
        point.setFocusable(false);
        JButton division = new JButton("/");
        division.setFocusable(false);
        JButton minus = new JButton("-");
        minus.setFocusable(false);

        /*------------------------------
            Button text font
        ------------------------------*/
        clear.setFont(new Font("Serif", Font.BOLD, fontSize));
        back.setFont(new Font("Serif", Font.BOLD, fontSize));
        plus.setFont(new Font("Serif", Font.BOLD, fontSize));
        equal.setFont(new Font("Serif", Font.BOLD, fontSize));
        times.setFont(new Font("Serif", Font.BOLD, fontSize));
        point.setFont(new Font("Serif", Font.BOLD, fontSize));
        division.setFont(new Font("Serif", Font.BOLD, fontSize));
        minus.setFont(new Font("Serif", Font.BOLD, fontSize));

        /*------------------------------
            button placement
        ------------------------------*/
        point.setSize(60, 35);
        point.setLocation(column3, row1);

        for (int i = 1; i < buttonNums.length; i++) {
            buttonNums[i].setSize(buttonWidth, buttonHeight);
        }
        //0
        buttonNums[0].setSize(2 * buttonWidth + buttonGap, buttonHeight);
        buttonNums[0].setLocation(column1, row1);
        //9
        buttonNums[9].setLocation(column3, row4);
        //8
        buttonNums[8].setLocation(column2, row4);
        //7
        buttonNums[7].setLocation(column1, row4);
        //6
        buttonNums[6].setLocation(column3, row3);
        //5
        buttonNums[5].setLocation(column2, row3);
        //4
        buttonNums[4].setLocation(column1, row3);
        //3
        buttonNums[3].setLocation(column3, row2);
        //2
        buttonNums[2].setLocation(column2, row2);
        //1
        buttonNums[1].setLocation(column1, row2);

        //delete
        //back.setSize(90, 35);
        //back.setLocation(15, row2);
        //multiplication
        times.setSize(buttonWidth, buttonHeight);
        times.setLocation(column3, row5);
        //subtraction
        minus.setSize(buttonWidth - 10, buttonHeight);
        minus.setLocation(column4, row5);
        //clear
        clear.setSize(buttonWidth, buttonHeight);
        clear.setLocation(column1, row5);
        //addition
        plus.setSize(buttonWidth - 10, buttonHeight * 2 + buttonGap);
        plus.setLocation(column4, row4);
        //division
        division.setSize(buttonWidth, buttonHeight);
        division.setLocation(column2, row5);
        //equal
        equal.setSize(buttonWidth - 10, buttonHeight * 2 + buttonGap);
        equal.setLocation(column4, row2);

        /*------------------------------
            add components here
        ------------------------------*/
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(equal);
        panel.add(minus);
        panel.add(division);
        panel.add(plus);
        panel.add(point);
        panel.add(clear);
        for (int i = 0; i < buttonNums.length; i++) {
            panel.add(buttonNums[i]);
        }
        panel.add(solution);
        panel.add(back);
        panel.add(input);
        panel.add(times);

        /*------------------------------
            button actions
        ------------------------------*/
        for (int i = 0; i < buttonNums.length; i++) {
            buttonNums[i].addActionListener(buttonNumListener(i));
        }
        clear.addActionListener(clearInputTextAction());
        times.addActionListener(operatorListener('*'));
        division.addActionListener(operatorListener('/'));
        minus.addActionListener(operatorListener('-'));
        plus.addActionListener(operatorListener('+'));
        back.addActionListener(deleteInputTextAction());
        equal.addActionListener(resultListener());
        point.addActionListener(operatorListener('.'));

        calcFrame.add(panel, BorderLayout.CENTER);
        calcFrame.setVisible(true);
    }

    private ActionListener deleteInputTextAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = deleteOneInputText(input.getText());
                input.setText(result);
            }
        };
    }

    private ActionListener clearInputTextAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.setText(defaultInputText);
                solution.setText(defaultSolutionText);
            }
        };
    }

    private ActionListener buttonNumListener(int value) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setInputText(value);
            }
        };
    }

    private ActionListener operatorListener(Character operator) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String op = operator + "";
                setInputText(op);
            }
        };
    }

    private ActionListener resultListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSolutionText(input.getText());
                input.setText(defaultInputText);
            }
        };
    }

    private void setInputText(String sample) {
        String s = sample;
        String inputText = input.getText();

        //there is already previous input
        if (!inputText.equals("0")) {
            char lastChar = inputText.charAt(inputText.length() - 1);

            //override the previous operation if different
            if (operations.contains(lastChar) && operations.contains(sample.charAt(0))) {
                if (lastChar == sample.charAt(0)) {
                    s = inputText;
                } else {
                    s = inputText.substring(0, inputText.length() - 1) + sample;
                }
            } else if (operations.contains(lastChar) && !operations.contains(sample.charAt(0))) {
                s = inputText + " " + sample;
            } else if (operations.contains(sample.charAt(0))) {
                s = inputText + " " + sample;
            } else {
                s = inputText + sample;
            }
        } else //no input or the display text is cleared
        if (operations.contains(sample.charAt(0))) {
            s = "0 " + sample;
        }
        input.setText(s);
    }

    private String deleteOneInputText(String input) {
        String removedLastChar = input.substring(0, input.length() - 1);
        if (removedLastChar.length() == 0) {
            return "0";
        } else if (removedLastChar.length() > 1 && input.charAt(input.length() - 2) == ' ') {
            removedLastChar = input.substring(0, input.length() - 2);
        }
        return removedLastChar;
    }

    public void setInputText(int sample) {
        setInputText(sample + "");
    }

    public void setSolutionText(String inputText) {
        Calculate expression = new Calculate(inputText);
        try {
            Double result = expression.calculateExpression();
            DecimalFormat formatter = new DecimalFormat("#,###.####");
            solution.setText(formatter.format(result) + "");
            input.setText(defaultInputText);
        } catch (EmptyStackException e) {
            solution.setText("enter valid expression");
        }
    }

    /*------------------------------
            keyboard Event
        ------------------------------*/
    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        calcFrame.requestFocus();
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            //numbers
            case KeyEvent.VK_0:
                setInputText(0);
                break;
            case KeyEvent.VK_NUMPAD0:
                setInputText(0);
                break;
            case KeyEvent.VK_1:
                setInputText(1);
                break;
            case KeyEvent.VK_NUMPAD1:
                setInputText(1);
                break;
            case KeyEvent.VK_2:
                setInputText(2);
                break;
            case KeyEvent.VK_NUMPAD2:
                setInputText(2);
                break;
            case KeyEvent.VK_3:
                setInputText(3);
                break;
            case KeyEvent.VK_NUMPAD3:
                setInputText(3);
                break;
            case KeyEvent.VK_4:
                setInputText(4);
                break;
            case KeyEvent.VK_NUMPAD4:
                setInputText(4);
                break;
            case KeyEvent.VK_5:
                setInputText(5);
                break;
            case KeyEvent.VK_NUMPAD5:
                setInputText(5);
                break;
            case KeyEvent.VK_6:
                setInputText(6);
                break;
            case KeyEvent.VK_NUMPAD6:
                setInputText(6);
                break;
            case KeyEvent.VK_7:
                setInputText(7);
                break;
            case KeyEvent.VK_NUMPAD7:
                setInputText(7);
                break;
            case KeyEvent.VK_8:
                setInputText(8);
                break;
            case KeyEvent.VK_NUMPAD8:
                setInputText(8);
                break;
            case KeyEvent.VK_9:
                setInputText(9);
                break;
            case KeyEvent.VK_NUMPAD9:
                setInputText(9);
                break;

            //operations
            case KeyEvent.VK_MULTIPLY:
                setInputText("*");
                break;
            case KeyEvent.VK_DIVIDE:
                setInputText("/");
                break;
            case KeyEvent.VK_SUBTRACT:
                setInputText("-");
                break;
            case KeyEvent.VK_ADD:
                setInputText("+");
                break;
            case KeyEvent.VK_DECIMAL:
                setInputText(".");
                break;
            case KeyEvent.VK_ENTER:
                setSolutionText(input.getText());
                break;
            case KeyEvent.VK_BACK_SPACE:
                String result = deleteOneInputText(input.getText());
                input.setText(result);
                break;
            default:
                return;
        }
    }

    public void keyReleased(KeyEvent e) {
    }
}

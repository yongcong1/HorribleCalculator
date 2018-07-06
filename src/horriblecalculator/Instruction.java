/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horriblecalculator;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Yongcong
 */
public class Instruction {
    public Instruction(){
        JFrame j1 = new JFrame("Instructions");
        j1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        j1.setSize(360, 200);
        j1.setResizable(false);
        j1.setLocationRelativeTo(null);
        j1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JTextArea instruct = new JTextArea("simple and horrible calculator with rounding \nup to thousands, it can only do the basic \nfour operations, "
                + "but it will follow order of \noperation");
        Color c = new Color(238, 238, 238);
        instruct.setBackground(c);
        instruct.setFont(new Font("Times", Font.BOLD, 14));
        instruct.setSize(360, 100);
        instruct.setLocation(5, 40);
        instruct.setEditable(false);
        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.add(instruct);
        j1.add(panel1);
        j1.setVisible(true);
    }
}

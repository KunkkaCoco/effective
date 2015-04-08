package com.effective.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by chenweichao on 15-4-7.
 */
public class TextFieldDemo {
    public static void main(String[] args) {
        MyFrame2 myFrame = new MyFrame2("Integer Adder");
        myFrame.setVisible(true);
        myFrame.setSize(500, 100);
    }
}

class MyFrame2 extends JFrame implements ActionListener, WindowListener {

    private JTextField number1 = new JTextField();
    private JTextField number2 = new JTextField();
    private JButton adder = new JButton("+");
    private JLabel result = new JLabel("0.0", JLabel.RIGHT);

    public MyFrame2(String str) {
        super(str);
        this.setLayout(new GridLayout(2, 4));
        add(new JLabel("Number1", JLabel.CENTER));
        add(new JLabel("Number2", JLabel.CENTER));
        add(new JLabel("Operator", JLabel.CENTER));
        add(new JLabel("Result", JLabel.CENTER));
        add(number1);
        add(number2);
        add(adder);
        add(result);
        adder.addActionListener(this);
        this.addWindowListener(this);

    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(adder)) {
            try {
                int num1 = Integer.parseInt(number1.getText());
                int num2 = Integer.parseInt(number2.getText());
                int answer = num1 + num2;
                result.setText(String.valueOf(answer));
            } catch (NumberFormatException ne) {
                System.out.println("Number parsing error " + ne.getMessage());
            }

        }
    }

    public void windowOpened(WindowEvent e) {

    }

    public void windowClosing(WindowEvent e) {

    }

    public void windowClosed(WindowEvent e) {
        System.exit(0);
    }

    public void windowIconified(WindowEvent e) {

    }

    public void windowDeiconified(WindowEvent e) {

    }

    public void windowActivated(WindowEvent e) {

    }

    public void windowDeactivated(WindowEvent e) {

    }
}

package com.effective.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by chenweichao on 15-4-7.
 */
public class ListDemoApp {
    public static void main(String[] args) {
        MyFrame3 myFrame = new MyFrame3("Integer Adder");
        myFrame.setVisible(true);
        myFrame.setBounds(20,50,400, 300);
    }
}

class MyFrame3 extends JFrame implements ActionListener {

    private DefaultListModel sourceModel;
    private DefaultListModel destModel;
    private JList source;
    private JList dest = new JList();
    private JButton addButton = new JButton(">>");
    private JButton removeButton = new JButton("<<");

    public MyFrame3(String title) {
        super(title);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        sourceModel = new DefaultListModel();
        sourceModel.addElement("Banana");
        sourceModel.addElement("Apple");
        sourceModel.addElement("Orange");
        sourceModel.addElement("Pineapple");
        sourceModel.addElement("Kiwi");
        sourceModel.addElement("Strawberry");
        sourceModel.addElement("Peach");
        source = new JList(sourceModel);

        source.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        source.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5), "" + "Shop",
                0, 0, null, Color.RED));
        source.setSelectedIndex(0);
        source.setSelectionBackground(Color.BLACK);
        source.setSelectionForeground(Color.WHITE);

        destModel = new DefaultListModel();
        dest.setModel(destModel);
        dest.setSelectionBackground(Color.BLACK);
        dest.setSelectionForeground(Color.WHITE);
        dest.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5), "" + "Fruit Basker",
                0, 0, null, Color.RED));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 20, 20));
        panel.add(new JLabel());
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(new JLabel());
        this.setLayout(new GridLayout(1, 3, 20, 20));
        add(source);
        add(panel);
        add(dest);
        addButton.addActionListener(this);
        removeButton.addActionListener(this);

    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(addButton)) {
            if (source.getSelectedValue() != null) {
                String  str = (String)source.getSelectedValue();
                if (str != null) {
                    destModel.addElement(str);
                    dest.setSelectedIndex(0);
                    sourceModel.removeElement(str);
                    source.setSelectedIndex(0);
                }
            }
        }
        if (evt.getSource().equals(removeButton)) {
            if (dest.getSelectedValue() != null) {
                String str = (String) dest.getSelectedValue();
                if (str != null) {
                    sourceModel.addElement(str);
                    source.setSelectedIndex(0);
                    destModel.removeElement(str);
                    dest.setSelectedIndex(0);
                }
            }
        }
    }
}
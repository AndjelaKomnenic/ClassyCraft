package raf.dsw.popUps;

import raf.dsw.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUpChooseCon extends JDialog {
    String rbResult = "";
    ButtonGroup buttonGroup = new ButtonGroup();
    JRadioButton radioButton1 = new JRadioButton("Agregacija");
    JRadioButton radioButton2 = new JRadioButton("Kompozicija");
    JRadioButton radioButton3 = new JRadioButton("Zavisnost");
    JRadioButton radioButton4 = new JRadioButton("Generalizacija");
    public PopUpChooseCon(){
        super(MainFrame.getInstance(), "Dodavanje nove veze", true);
        setUp();
    }
    public void setUp(){
        setLayout(new GridLayout(3, 1));
        JLabel label = new JLabel("Odaberite tip veze koju biste dodali");
        JPanel radioButtons = new JPanel(new GridLayout(1, 4));
        JPanel buttonHolder = new JPanel(new GridLayout(1, 3));
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);
        buttonGroup.add(radioButton4);
        radioButtons.add(radioButton1, 0);
        radioButtons.add(radioButton2, 1);
        radioButtons.add(radioButton3, 2);
        radioButtons.add(radioButton4, 3);
        JButton addElement = new JButton("Dodaj");
        addElement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonClick();
            }
        });
        buttonHolder.add(new Label(), 0);
        buttonHolder.add(addElement, 1);
        buttonHolder.add(new Label(), 2);
        add(label, 0);
        add(radioButtons, 1);
        add(buttonHolder, 2);
        setSize(500, 150);
        setLocationRelativeTo(MainFrame.getInstance());
        setVisible(true);
    }
    public void handleButtonClick(){
        dispose();
    }
}

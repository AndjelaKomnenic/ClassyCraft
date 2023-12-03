package raf.dsw.popUps;

import raf.dsw.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUpSetUpParameters extends JDialog {
    String s;
    JTextField naziv = new JTextField();
    JTextField vidljivost = new JTextField();
    JButton addAtribut = new JButton("Dodaj atribut");
    JButton addMetodu = new JButton("Dodaj metodu");
    JButton napravi = new JButton("Napravi");
    public PopUpSetUpParameters(String s, PopUpChooseIC parent){
        super(MainFrame.getInstance(), "Podesavanje izgleda elementa", true);
        this.s = s;
        setUp();
    }
    public void setUp(){
        System.out.println(s);
        setLayout(new GridLayout(3, 1));
        JPanel firstRow = new JPanel(new GridLayout(1, 4));
        JPanel secondRow = new JPanel(new GridLayout(1, 2));
        firstRow.add(new Label("Naziv: "), 0);
        firstRow.add(naziv, 1);
        if(s.equalsIgnoreCase("klasa")) {
            firstRow.add(new Label("Vidljivost: "), 2);
            firstRow.add(vidljivost, 3);
        }
        secondRow.add(addAtribut, 0);
        secondRow.add(addMetodu, 1);
        add(firstRow, 0);
        add(secondRow, 1);
        add(napravi, 2);
        addMetodu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call your function or perform an action when the button is clicked
                handleButtonClick1();
            }
        });
        addAtribut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call your function or perform an action when the button is clicked
                handleButtonClick2();
            }
        });
        napravi.addActionListener(e ->{dispose();});
        setSize(300, 150);
        setLocationRelativeTo(getParent());
        setVisible(true);
    }
    public void handleButtonClick1(){
        //dispose();
        PopUpMetoda popMet = new PopUpMetoda(this);
    }
    public void handleButtonClick2(){
        //dispose();
        PopUpAtribut popMet = new PopUpAtribut(this);
    }
}

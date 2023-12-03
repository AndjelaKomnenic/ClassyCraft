package raf.dsw.popUps;

import javax.swing.*;
import java.awt.*;

public class PopUpMetoda extends JDialog {
    //PopUpSetUpParameters parent;
    private JTextField vidljivost = new JTextField();
    private JTextField povratnaVrednost = new JTextField();
    private JTextField naziv = new JTextField();
    private JButton dodaj = new JButton("Dodaj");
    public PopUpMetoda(PopUpSetUpParameters parent){
        super(parent, "Dodavanje metode", true);
        setUp();
    }
    public void setUp(){
        setLayout(new GridLayout(4, 1));
        JPanel firstRow = new JPanel(new GridLayout(1, 2));
        JPanel secondRow = new JPanel(new GridLayout(1, 2));
        JPanel thirdRow = new JPanel(new GridLayout(1, 2));
        JPanel fourthRow = new JPanel(new FlowLayout());
        firstRow.add(new Label("Vidljivost: "), 0);
        firstRow.add(vidljivost, 1);
        secondRow.add(new Label("Povratna vrednost: "), 0);
        secondRow.add(povratnaVrednost, 1);
        thirdRow.add(new Label("Naziv: "), 0);
        thirdRow.add(naziv, 1);
        fourthRow.add(dodaj);
        add(firstRow, 0);
        add(secondRow, 1);
        add(thirdRow, 2);
        add(fourthRow, 3);
        dodaj.addActionListener(e ->{dispose();});
        setSize(300, 150);
        setLocationRelativeTo(getParent());
        setVisible(true);
    }
}

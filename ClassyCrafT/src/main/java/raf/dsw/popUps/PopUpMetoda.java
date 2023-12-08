package raf.dsw.popUps;

import lombok.Getter;
import raf.dsw.components.Metod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
public class PopUpMetoda extends JDialog {
    //PopUpSetUpParameters parent;
    private JTextField vidljivost = new JTextField();
    private JTextField povratnaVrednost = new JTextField();
    private JTextField naziv = new JTextField();
    private JButton dodaj = new JButton("Dodaj");
    private PopUpSetUpParameters parent;
    public PopUpMetoda(PopUpSetUpParameters parent){
        super(parent, "Dodavanje metode", true);
        this.parent = parent;
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
        dodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonClick();
            }
        });
        add(firstRow, 0);
        add(secondRow, 1);
        add(thirdRow, 2);
        add(fourthRow, 3);
        dodaj.addActionListener(e ->{dispose();});
        setSize(300, 150);
        setLocationRelativeTo(getParent());
        setVisible(true);
    }
    public void handleButtonClick(){
        String v = vidljivost.getText();
        String p = povratnaVrednost.getText();
        String n = naziv.getText();
        Metod m = new Metod(v, p, n);
        parent.addToList(m);
        dispose();
    }
}

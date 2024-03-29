package raf.dsw.popUps;

import lombok.Getter;
import raf.dsw.components.Atribut;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
public class PopUpAtribut extends JDialog {
    private JComboBox<String> vidljivost = new JComboBox<>();
    private JTextField tip = new JTextField();
    private JTextField naziv = new JTextField();
    private JButton dodaj = new JButton("Dodaj");
    private PopUpSetUpParameters parent;
    public PopUpAtribut(PopUpSetUpParameters parent){
        super(parent, "Dodavanje atributa", true);
        this.parent = parent;
        setUp();
    }
    public void setUp(){
        vidljivost.addItem("private");
        vidljivost.addItem("public");
        vidljivost.addItem("protected");
        vidljivost.addItem("default");
        setLayout(new GridLayout(4, 1));
        JPanel firstRow = new JPanel(new GridLayout(1, 2));
        JPanel secondRow = new JPanel(new GridLayout(1, 2));
        JPanel thirdRow = new JPanel(new GridLayout(1, 2));
        JPanel fourthRow = new JPanel(new FlowLayout());
        firstRow.add(new Label("Vidljivost: "), 0);
        firstRow.add(vidljivost, 1);
        secondRow.add(new Label("Tip: "), 0);
        secondRow.add(tip, 1);
        thirdRow.add(new Label("Naziv: "), 0);
        thirdRow.add(naziv, 1);
        fourthRow.add(dodaj);
        add(firstRow, 0);
        add(secondRow, 1);
        add(thirdRow, 2);
        add(fourthRow, 3);
        dodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonClick();
            }
        });
        setSize(300, 150);
        setLocationRelativeTo(getParent());
        setVisible(true);
    }
    public void handleButtonClick(){
        String v = (String) vidljivost.getSelectedItem();
        String t = tip.getText();
        String n = naziv.getText();
        Atribut a = new Atribut(v, t, n);
        parent.addToList(a);
        dispose();
    }

}

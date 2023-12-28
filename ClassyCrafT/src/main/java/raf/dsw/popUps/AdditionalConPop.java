package raf.dsw.popUps;

import raf.dsw.components.Atribut;
import raf.dsw.components.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdditionalConPop extends JDialog {
    private JComboBox<String> vidljivost = new JComboBox<>();
    private JTextField kardinalnost = new JTextField();
    private JButton dodaj = new JButton("Podesi");
    private Connection c;
    public AdditionalConPop(JDialog parent, Connection c){
        super(parent, "Dodatno podesavanje veze", true);
        this.c = c;
        setUp();
    }
    public void setUp(){
        vidljivost.addItem("private");
        vidljivost.addItem("public");
        vidljivost.addItem("protected");
        vidljivost.addItem("package");
        setLayout(new GridLayout(3, 1));
        JPanel firstRow = new JPanel(new GridLayout(1, 2));
        JPanel secondRow = new JPanel(new GridLayout(1, 2));
        JPanel thirdRow = new JPanel(new FlowLayout());
        firstRow.add(new Label("Vidljivost: "), 0);
        firstRow.add(vidljivost, 1);
        secondRow.add(new Label("Kardinalnost: "), 0);
        secondRow.add(kardinalnost, 1);
        thirdRow.add(dodaj);
        add(firstRow, 0);
        add(secondRow, 1);
        add(thirdRow, 2);
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
        String v = (String)vidljivost.getSelectedItem();
        String k = kardinalnost.getText();
        c.setVidljivost(v);
        c.setKardinalnost(k);
        dispose();
    }

}
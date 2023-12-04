package raf.dsw.popUps;

import raf.dsw.novo.ClanEnuma;
import raf.dsw.novo.ClassContent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PopUpEnumDetails extends JDialog {
    private List<ClassContent> cl = new ArrayList<>();
    JTextField naziv = new JTextField();
    JButton addClan = new JButton("Dodaj clan enuma");
    JButton napravi = new JButton("Napravi");
    public PopUpEnumDetails(PopUpChooseIC parent){
        super(parent, "Dodavanje novog enuma", true);
        setUp();
    }

    public void setUp(){
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gridbag);
        naziv.setSize(200, 50);
        c.fill = GridBagConstraints.HORIZONTAL;
        Label lb1 = new Label("Naziv:");
        c.gridx = 0;
        c.gridy = 0;
        gridbag.setConstraints(lb1, c);
        add(lb1);
        c.gridx = 1;
        c.gridy = 0;
        gridbag.setConstraints(naziv, c);
        add(naziv);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        gridbag.setConstraints(addClan, c);
        add(addClan);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        gridbag.setConstraints(napravi, c);
        add(napravi);
        /*Dimension d = new Dimension(120, 20);
        addAtribut.setPreferredSize(d);
        addMetodu.setPreferredSize(d);
        gridbag.setConstraints(addAtribut, c);
        add(addAtribut);
        c.gridx = 2;
        c.gridy = 1;
        gridbag.setConstraints(addMetodu, c);
        add(addMetodu);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        gridbag.setConstraints(napravi, c);
        add(napravi);*/
        addClan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonClick1();
            }
        });
        napravi.addActionListener(e ->{dispose();});
        setSize(300, 200);
        setLocationRelativeTo(getParent());
        setVisible(true);
    }
    public void handleButtonClick1(){
        //dispose();
        var name = JOptionPane.showInputDialog("Novi clan:");
        ClanEnuma ce = new ClanEnuma(name);
        addToList(ce);
    }
    public void addToList(ClassContent cc){
        cl.add(cc);
    }
}

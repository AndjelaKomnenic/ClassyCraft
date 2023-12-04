package raf.dsw.popUps;

import raf.dsw.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUpChooseIC extends JDialog {
    String rbResult = "";
    ButtonGroup buttonGroup = new ButtonGroup();
    JRadioButton radioButton1 = new JRadioButton("Interfejs");
    JRadioButton radioButton2 = new JRadioButton("Klasa");
    JRadioButton radioButton3 = new JRadioButton("Enum");
    public PopUpChooseIC(){
        super(MainFrame.getInstance(), "Dodavanje novog elementa", true);
        setUp();
    }
    public void setUp(){
        setLayout(new GridLayout(3, 1));
        JLabel label = new JLabel("Odaberite koji element biste dodali");
        JPanel radioButtons = new JPanel(new GridLayout(1, 3));
        JPanel buttonHolder = new JPanel(new GridLayout(1, 3));
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);
        radioButtons.add(radioButton1, 0);
        radioButtons.add(radioButton2, 1);
        radioButtons.add(radioButton3, 2);
        JButton addElement = new JButton("Dodaj");
        addElement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call your function or perform an action when the button is clicked
                handleButtonClick();
            }
        });
        buttonHolder.add(new Label(), 0);
        buttonHolder.add(addElement, 1);
        buttonHolder.add(new Label(), 2);
        /*Dimension buttonDimention = new Dimension(10, 5);
        addElement.setMaximumSize(buttonDimention);*/

        add(label, 0);
        add(radioButtons, 1);
        add(buttonHolder, 2);
        setSize(300, 150);
        setLocationRelativeTo(MainFrame.getInstance());
        setVisible(true);
       /*

                .addActionListener(ev -> {
            dispose();
            PopUpSetUpParameters popSet = new PopUpSetUpParameters("test", this);
        });*/
    }
    public void handleButtonClick(){
        if(radioButton1.isSelected())
            rbResult = "Interfejs";
        else if(radioButton2.isSelected())
            rbResult = "Klasa";
        else if(radioButton3.isSelected())
            rbResult = "Enum";
        //dodati gresku ako nije nista
        dispose();
        if(rbResult.equalsIgnoreCase("Enum")){
            PopUpEnumDetails popEnum = new PopUpEnumDetails(this);
        }
        else {
            PopUpSetUpParameters popSet = new PopUpSetUpParameters(rbResult, this);
        }
        //PopUpSetUpParameters popSet = new PopUpSetUpParameters(rbResult, this);
    }
}

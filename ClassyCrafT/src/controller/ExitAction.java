package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;

public class ExitAction extends AbstractAction{

    public ExitAction(){
        Icon icon = null;
        URL ImageURL = getClass().getResource("/images/exit.png"); // dodaj URL
        if (ImageURL != null){

            Image img = new ImageIcon(ImageURL).getImage();
            Image newImg = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
            icon = new ImageIcon(newImg);
        } else{
            System.err.println("File " + "...ta putanja..." + "not found");
        }

        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, icon);
        putValue(NAME, "Exit");
        putValue(SHORT_DESCRIPTION, "Exit");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}

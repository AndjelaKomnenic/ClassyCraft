package raf.dsw.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ExitAction extends AbstractClassyAction{

    public ExitAction(){

        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/exit.png"));
        putValue(NAME, "Exit");
        putValue(SHORT_DESCRIPTION, "Exit");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
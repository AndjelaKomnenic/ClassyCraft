package raf.dsw.controller;

import raf.dsw.message.Message;
import raf.dsw.message.MessageGenerator;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public abstract class AbstractClassyAction extends AbstractAction {
    public Icon loadIcon(String fileName){

        Icon icon = null;
        URL ImageURL = getClass().getResource(fileName);
        if (ImageURL != null){

            Image img = new ImageIcon(ImageURL).getImage();
            Image newImg = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
            icon = new ImageIcon(newImg);
        } else{
            System.err.println("File " + "...ta putanja..." + "not found");
        }

        return icon;
    }
    public void generateMessage(Message message){};

}
package raf.dsw.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AboutUs extends AbstractClassyAction {

    public AboutUs() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/information.png"));
        putValue(NAME, "AboutUs");
        putValue(SHORT_DESCRIPTION, "AboutUs");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(new JLabel("Andjela Komnenic RN-13/2022"));
        panel.add(new JLabel("Irina Radojevic RN-27/2022"));
        ImageIcon originalIcon1 = new ImageIcon(getClass().getResource("/images/irina.jpeg"));
        Image scaledImage1 = originalIcon1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);
        JLabel imageLabel1 = new JLabel(scaledIcon1);
        ImageIcon originalIcon2 = new ImageIcon(getClass().getResource("/images/andjela.png"));
        Image scaledImage2 = originalIcon2.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
        JLabel imageLabel2 = new JLabel(scaledIcon2);
        panel.add(imageLabel2);
        panel.add(imageLabel1);

        JDialog dialog = new JDialog();
        dialog.add(panel);
        dialog.setSize(300, 500);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
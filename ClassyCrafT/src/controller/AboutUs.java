package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AboutUs extends AbstractClassyAction {

    public AboutUs() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/exit.png")); // za sada ima exit slicicu posle treba dodati odgovarajucu!
        putValue(NAME, "AboutUs");
        putValue(SHORT_DESCRIPTION, "AboutUs");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(new JLabel("Andjela Komnenic RN-13/2022"));
        panel.add(new JLabel("Irina Radojevic RN-27/2022"));

        JDialog dialog = new JDialog();
        dialog.add(panel);
        dialog.setSize(200, 300);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        // treba dodati jos nase slike...
    }
}

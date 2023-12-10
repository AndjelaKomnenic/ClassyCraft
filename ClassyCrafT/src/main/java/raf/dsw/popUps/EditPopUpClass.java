package raf.dsw.popUps;

import raf.dsw.components.ClassContent;
import raf.dsw.components.DiagramElement;
import raf.dsw.components.InterClass;
import raf.dsw.components.Klasa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class EditPopUpClass extends Frame {
    private int cc = 0;
    private Label nazivLabela, vidljivostLabela;
    private TextField nazivField, vidljivostField;
    public EditPopUpClass(DiagramElement diagramElement) {
        add(new Label("Naziv:"));
        nazivLabela = new Label(diagramElement.getName());
        nazivLabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 3) {
                    showTextField(nazivLabela, nazivField, 1);
                }
            }
        });
        nazivField = new TextField();
        nazivField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    updateLabel(nazivLabela, nazivField, 1);
                }
            }
        });
        add(nazivLabela);
        if(diagramElement instanceof Klasa) {
            add(new Label("Vidljivost:"));
            vidljivostLabela = new Label(((Klasa) diagramElement).getVidljivost());
            add(vidljivostLabela);
            vidljivostLabela.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 3) {
                        showTextField(vidljivostLabela, vidljivostField, 3);
                    }
                }
            });
            vidljivostField = new TextField();
            vidljivostField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        updateLabel(vidljivostLabela, vidljivostField, 3);
                    }
                }
            });
        }
        add(new Label("Odaberite metode/atribute koje biste zeleli da obrisete"));
        List<ClassContent> listaZaBrisanje = new ArrayList<>();
        List<JCheckBox> listaCheckova = new ArrayList<>();
        int c = 0;
        for(ClassContent cc: ((InterClass)diagramElement).getCl()){
            listaCheckova.add(new JCheckBox(cc.getNaziv()));
            add(listaCheckova.get(c));
            c++;
        }
        Button b = new Button("Zatvori");
        add(b);
        b.addActionListener(e->{dispose();});
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(350, 400);
        setLocationRelativeTo(getParent());
        setVisible(true);
    }
    private void showTextField(Label lb, TextField tf, int index) {
        remove(lb);
        add(tf, index);
        tf.setText(lb.getText());
        tf.requestFocus();
        revalidate();
        repaint();
    }

    private void updateLabel(Label lb, TextField tf, int index) {
        remove(tf);
        add(lb, index);
        lb.setText(tf.getText());
        revalidate();
        repaint();
        cc = 0;
    }
}

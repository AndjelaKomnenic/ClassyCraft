package raf.dsw.popUps;

import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;
import raf.dsw.components.ClassContent;
import raf.dsw.components.DiagramElement;
import raf.dsw.components.InterClass;
import raf.dsw.components.Klasa;
import raf.dsw.core.ApplicationFramework;
import raf.dsw.message.PossibleErrors;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.WorkSpaceImplementation;
import raf.dsw.workspace.view.PackageView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.List;

public class EditPopUpClass extends Frame {
    private int cc = 0, c = 0;
    private Label nazivLabela, vidljivostLabela;
    private TextField nazivField, vidljivostField;
    private DiagramElement diagramElement;
    private List<ClassContent> listaZaBrisanje = new ArrayList<>();
    private List<JCheckBox> listaCheckova = new ArrayList<>();
    private PackageView packageView;
    private Button btnDodajAtribut = new Button("Dodaj atribut");
    private Button btnDodajMetodu = new Button("Dodaj metodu");
    public EditPopUpClass(DiagramElement diagramElement) {
        packageView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
        this.diagramElement = diagramElement;
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
        listaZaBrisanje = new ArrayList<>();
        listaCheckova = new ArrayList<>();
        c = 0;
        for(ClassContent cc: ((InterClass)diagramElement).getCl()){
            listaCheckova.add(new JCheckBox(cc.getNaziv()));
            add(listaCheckova.get(c));
            c++;
        }
        JPanel dodavanja = new JPanel();
        dodavanja.setLayout(new FlowLayout());
        dodavanja.add(btnDodajAtribut);
        dodavanja.add(btnDodajMetodu);
        btnDodajAtribut.addActionListener(e -> dodajAtribut());
        btnDodajMetodu.addActionListener(e -> dodajMetodu());
        add(dodavanja);
        Button b = new Button("Zatvori");
        add(b);
        b.addActionListener(e -> handleButtonClick());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(350, 300);
        setLocationRelativeTo(getParent());
        setVisible(true);
    }

    private void dodajMetodu() {
        PopUpMetodaAfter popUpMetoda = new PopUpMetodaAfter(this);
    }

    private void dodajAtribut() {
        PopUpAtributAfter popUpAtribut = new PopUpAtributAfter(this);
    }

    private void handleButtonClick() {
        for(int i = 0; i < c; i++){
            if(listaCheckova.get(i).isSelected()){
                ((InterClass)diagramElement).getList().remove(i);
            }
        }
        packageView.repaint();
        dispose();
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
        if(index == 1){
            boolean flag = true;
            for(ClassyNode cn: ((ClassyNodeComposite)diagramElement.getParent()).getChildren()){
                if(cn.getName().equalsIgnoreCase(tf.getText())){
                    flag = false;
                    break;
                }
            }
            if(flag) {
                lb.setText(tf.getText());
                diagramElement.setName(tf.getText());
                packageView.repaint();
                MainFrame.getInstance().getClassyTree().update();
            }
            else{
                ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.NAME_ALREADY_EXISTS);
            }
        }
        else{
            ((InterClass)diagramElement).setVidljivost(tf.getText());
            lb.setText(tf.getText());
            packageView.repaint();
        }
        add(lb, index);
        revalidate();
        repaint();
        cc = 0;
    }
    public void addToList(ClassContent cc){
        ((InterClass)diagramElement).addToList(cc);
    }
}

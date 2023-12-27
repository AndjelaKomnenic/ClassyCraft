package raf.dsw.popUps;

import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;
import raf.dsw.commands.EditICCommand;
import raf.dsw.components.*;
import raf.dsw.core.ApplicationFramework;
import raf.dsw.message.PossibleErrors;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.WorkSpaceImplementation;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class EditPopUpEnum extends Frame{
    private int c = 0;
    private Label nazivLabela;
    private TextField nazivField;
    private DiagramElement diagramElement;
    private java.util.List<ClassContent> originalnaListaCC = new ArrayList<>();
    private java.util.List<ClanEnuma> originalnaListaE = new ArrayList<>();
    private List<JCheckBox> listaCheckova = new ArrayList<>();
    private PackageView packageView;
    private Button btnDodajClanEnuma = new Button("Dodaj clan enuma");
    private String oldName;
    public EditPopUpEnum(DiagramElement diagramElement) {
        for(ClassContent cc: ((InterClass)diagramElement).getList())
            originalnaListaCC.add(cc);
        for(ClanEnuma ce: ((InterClass)diagramElement).getNEnum())
            originalnaListaE.add(ce);
        this.oldName = diagramElement.getName();
        packageView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
        this.diagramElement = diagramElement;
        add(new Label("Naziv:"));
        nazivLabela = new Label(diagramElement.getName());
        nazivLabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    showTextFieldNaziv(nazivLabela, nazivField, 1);
                }
            }
        });
        nazivField = new TextField();
        nazivField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    updateLabelNaziv(nazivLabela, nazivField, 1);
                }
            }
        });
        add(nazivLabela);
        add(new Label("Odaberite clanove enuma koje biste zeleli da obrisete"));
        listaCheckova = new ArrayList<>();
        for(ClanEnuma ce: ((InterClass)diagramElement).getNEnum()){
            listaCheckova.add(new JCheckBox(ce.getValue()));
            add(listaCheckova.get(c));
            c++;
        }
        JPanel dodavanja = new JPanel();
        dodavanja.setLayout(new FlowLayout());
        dodavanja.add(btnDodajClanEnuma);
        btnDodajClanEnuma.addActionListener(e -> dodajClanEnuma());
        add(dodavanja);
        Button b = new Button("Zatvori");
        add(b);
        b.addActionListener(e -> handleButtonClick());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(350, 300);
        setLocationRelativeTo(getParent());
        setVisible(true);
    }

    private void dodajClanEnuma() {
        String value = JOptionPane.showInputDialog("Novi clan:");
        ClanEnuma ce = new ClanEnuma(value);
        ((InterClass)diagramElement).getNEnum().add(ce);
    }

    private void handleButtonClick() {
        for(int i = 0; i < c; i++){
            if(listaCheckova.get(i).isSelected()){
                ClanEnuma izbaci = null;
                for(ClanEnuma enCL: ((InterClass)diagramElement).getNEnum())
                    if(enCL.getValue().equals(listaCheckova.get(i).getText()))
                        izbaci = enCL;
                ((InterClass)diagramElement).getNEnum().remove(izbaci);
            }
        }
        DiagramView dv = (DiagramView) packageView.getTabbedPane().getSelectedComponent();
        dv.getCommandManager().addCommand(new EditICCommand(packageView, dv, (InterClass) diagramElement, originalnaListaCC, originalnaListaE, oldName, ""));
        packageView.repaint();
        dispose();
    }
    private void showTextFieldNaziv(Label lb, TextField tf, int index) {
        remove(lb);
        tf.setText(lb.getText());
        add(tf, index);
        tf.requestFocus();
        revalidate();
        repaint();
    }

    private void updateLabelNaziv(Label lb, TextField tf, int index) {
        remove(tf);
        boolean flag = true;
        for(ClassyNode cn: ((ClassyNodeComposite)diagramElement.getParent()).getChildren()){
            if(cn.getName().equalsIgnoreCase(tf.getText())){
                flag = false;
                break;
            }
        }
        if(tf.getText().length() > 0) {
            if (flag) {
                lb.setText(tf.getText());
                diagramElement.setName(tf.getText());
                packageView.repaint();
                MainFrame.getInstance().getClassyTree().update();
            } else {
                ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.NAME_ALREADY_EXISTS);
            }
        }
        else{
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.NAME_CANNOT_BE_EMPTY);
        }
        add(lb, index);
        revalidate();
        repaint();
    }
}

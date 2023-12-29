package raf.dsw.popUps;

//import lombok.var;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;
import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.components.ClanEnuma;
import raf.dsw.components.ClassContent;
import raf.dsw.components.InterClass;
import raf.dsw.core.ApplicationFramework;
import raf.dsw.message.PossibleErrors;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.WorkSpaceImplementation;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUpEnumDetails extends JDialog {
    private InterClass noviElement;
    JTextField naziv = new JTextField();
    JButton addClan = new JButton("Dodaj clan enuma");
    JButton napravi = new JButton("Napravi");
    public PopUpEnumDetails(PopUpChooseIC parent, InterClass noviElement){
        super(parent, "Dodavanje novog enuma", true);
        this.noviElement = noviElement;
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
        addClan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonClick1();
            }
        });
        napravi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                napraviEnum();
            }
        });
        setSize(300, 200);
        setLocationRelativeTo(getParent());
        setVisible(true);
    }
    public void handleButtonClick1(){
        //dispose();
        String value = JOptionPane.showInputDialog("Novi clan:");
        ClanEnuma ce = new ClanEnuma(value);
        addToListE(ce);
        //System.out.println(ce.toString());
    }
    public void addToListE(ClanEnuma cc){
        noviElement.addToListE(cc);
    }
    public void napraviEnum(){
        PackageView packageView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
        Diagram currDiagram = ((DiagramView) packageView.getTabbedPane().getSelectedComponent()).getDiagram();
        boolean flag = true;
        if(naziv.getText().length() == 0){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.NAME_CANNOT_BE_EMPTY);
            noviElement.setName("");
            dispose();
            return;
        }
        for(ClassyNode cn: ((ClassyNodeComposite)currDiagram).getChildren()){
            if(cn.getName().equalsIgnoreCase(naziv.getText())){
                flag = false;
                break;
            }
        }
        if(flag) {
            noviElement.setName(naziv.getText());
        }
        else{
            noviElement.setName("");
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.NAME_ALREADY_EXISTS);
        }
        dispose();
    }
}
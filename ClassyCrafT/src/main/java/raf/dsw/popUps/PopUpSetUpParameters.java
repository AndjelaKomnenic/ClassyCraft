package raf.dsw.popUps;

import lombok.Getter;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;
import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.components.ClassContent;
import raf.dsw.components.InterClass;
import raf.dsw.components.Klasa;
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

@Getter
public class PopUpSetUpParameters extends JDialog {
    String s;
    private InterClass noviElement;
    JTextField naziv = new JTextField();
    JComboBox<String> vidljivost = new JComboBox<>();
    JButton addAtribut = new JButton("Dodaj atribut");
    JButton addMetodu = new JButton("Dodaj metodu");
    JButton napravi = new JButton("Napravi");
    JCheckBox isAbstract = new JCheckBox("Apstraktna klasa");
    public PopUpSetUpParameters(String s, PopUpChooseIC parent, InterClass noviElement){
        super(MainFrame.getInstance(), "Podesavanje izgleda elementa", true);
        this.s = s;
        this.noviElement = noviElement;
        setUp();
    }
    public void setUp(){
        vidljivost.addItem("private");
        vidljivost.addItem("public");
        vidljivost.addItem("protected");
        vidljivost.addItem("package");
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gridbag);
        naziv.setSize(200, 50);
        vidljivost.setSize(200, 50);
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
        if(s.equalsIgnoreCase("klasa")) {
            Label lb2 = new Label("Vidljivost:");
            c.gridx = 2;
            c.gridy = 0;
            gridbag.setConstraints(lb2, c);
            add(lb2);
            c.gridx = 3;
            c.gridy = 0;
            gridbag.setConstraints(vidljivost, c);
            add(vidljivost);
            c.gridx = 0;
            c.gridy = 1;
            c.gridwidth = 2;
            gridbag.setConstraints(isAbstract, c);
            add(isAbstract);
            c.gridx = 2;
            c.gridy = 2;
            gridbag.setConstraints(addAtribut, c);
            add(addAtribut);
        }
        else{
            Label lb2 = new Label("");
            c.gridx = 2;
            c.gridy = 0;
            gridbag.setConstraints(lb2, c);
            add(lb2);
            Label lb3 = new Label("");
            c.gridx = 3;
            c.gridy = 0;
            gridbag.setConstraints(lb3, c);
            add(lb3);
        }
        /////
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        Dimension d = new Dimension(120, 20);
        addAtribut.setPreferredSize(d);
        addMetodu.setPreferredSize(d);
        gridbag.setConstraints(addMetodu, c);
        add(addMetodu);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        gridbag.setConstraints(napravi, c);
        add(napravi);
        addMetodu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonClick1();
            }
        });
        addAtribut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonClick2();
            }
        });
        napravi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonClick3();
            }
        });
        setSize(300, 200);
        setLocationRelativeTo(getParent());
        setVisible(true);
    }
    public void handleButtonClick1(){
        PopUpMetoda popMet = new PopUpMetoda(this);
    }
    public void handleButtonClick2(){
        PopUpAtribut popMet = new PopUpAtribut(this);
    }
    public void handleButtonClick3(){
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
            String odabranaVidljivost = (String)vidljivost.getSelectedItem();
            if(noviElement instanceof Klasa){
                ((Klasa) noviElement).setApstraktna(isAbstract.isSelected());
            }
            noviElement.setVidljivost(odabranaVidljivost);

            ClassyTreeItem myParent = findClassyTreeItem(MainFrame.getInstance().getClassyTree().getRoot(), currDiagram);
            if(myParent != null)
                MainFrame.getInstance().getClassyTree().addChildToDiag(myParent, noviElement);
            else
                System.out.println(currDiagram.getName() + " nije nadjen");
        }
        else{
            noviElement.setName("");
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.NAME_ALREADY_EXISTS);
        }
        dispose();
    }
    public ClassyTreeItem findClassyTreeItem(ClassyTreeItem root, ClassyNode targetNode) {
        if (root.getClassyNode().getName().equalsIgnoreCase(targetNode.getName())) {
            return root;
        } else {
            for (ClassyTreeItem child : root.getChildren()) {
                ClassyTreeItem result = findClassyTreeItem(child, targetNode);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
    public void addToList(ClassContent c){
        noviElement.addToList(c);
    }
}

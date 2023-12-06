package raf.dsw.popUps;

//import lombok.var;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.components.ClanEnuma;
import raf.dsw.components.ClassContent;
import raf.dsw.components.InterClass;
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
        String name = JOptionPane.showInputDialog("Novi clan:");
        ClanEnuma ce = new ClanEnuma(name);
        addToList(ce);
    }
    public void addToList(ClassContent cc){
        noviElement.addToList(cc);
    }
    public void napraviEnum(){
        noviElement.setName(naziv.getText());
        PackageView packageView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
        Diagram currDiagram = ((DiagramView) packageView.getTabbedPane().getSelectedComponent()).getDiagram();
        ClassyTreeItem myParent = findClassyTreeItem(MainFrame.getInstance().getClassyTree().getRoot(), currDiagram);
        if(myParent != null)
            MainFrame.getInstance().getClassyTree().addChildToDiag(myParent, noviElement);
        else
            System.out.println(currDiagram.getName() + " nije nadjen");
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
}

package raf.dsw.popUps;

import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;
import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.components.AbstractFactory;
import raf.dsw.components.Connection;
import raf.dsw.components.DiagramElement;
import raf.dsw.components.InterClass;
import raf.dsw.paint.ElementPainter;
import raf.dsw.state.State;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.WorkSpaceImplementation;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUpChooseCon extends JDialog {
    String rbResult = "";
    private JTextField naziv = new JTextField();
    ButtonGroup buttonGroup = new ButtonGroup();
    JRadioButton radioButton1 = new JRadioButton("Agregacija");
    JRadioButton radioButton2 = new JRadioButton("Kompozicija");
    JRadioButton radioButton3 = new JRadioButton("Zavisnost");
    JRadioButton radioButton4 = new JRadioButton("Generalizacija");
    private int sx, sy, fx, fy;
    private State calledFrom;
    //private ClassyNodeComposite parentDiagram;
    public PopUpChooseCon(int sx, int sy, int fx, int fy, State calledFrom){
        super(MainFrame.getInstance(), "Dodavanje nove veze", true);
        this.fx = fx;
        this.fy = fy;
        this.sx = sx;
        this.sy = sy;
        this.calledFrom = calledFrom;
        if(connectsTwoItems())
            setUp();
        else
            calledFrom.neispravnoCrtanje();
    }
    public void setUp(){
        setLayout(new GridLayout(4, 1));
        JLabel label = new JLabel("Odaberite tip veze koju biste dodali");
        JPanel radioButtons = new JPanel(new GridLayout(1, 4));
        JPanel buttonHolder = new JPanel(new GridLayout(1, 3));
        JPanel nameInsertion = new JPanel(new GridLayout(1, 2));
        nameInsertion.add(new Label("Uneiste naziv veze: "), 0);
        nameInsertion.add(naziv, 1);
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);
        buttonGroup.add(radioButton4);
        radioButtons.add(radioButton1, 0);
        radioButtons.add(radioButton2, 1);
        radioButtons.add(radioButton3, 2);
        radioButtons.add(radioButton4, 3);
        JButton addElement = new JButton("Dodaj");
        addElement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonClick();
            }
        });
        buttonHolder.add(new Label(), 0);
        buttonHolder.add(addElement, 1);
        buttonHolder.add(new Label(), 2);
        add(label, 0);
        add(radioButtons, 1);
        add(nameInsertion, 2);
        add(buttonHolder, 3);
        setSize(450, 170);
        setLocationRelativeTo(MainFrame.getInstance());
        setVisible(true);
    }
    public void handleButtonClick(){
        if(radioButton1.isSelected())
            rbResult = "Agregacija";
        else if(radioButton2.isSelected())
            rbResult = "Kompozicija";
        else if(radioButton3.isSelected())
            rbResult = "Zavisnost";
        else if(radioButton4.isSelected())
            rbResult = "Generalizacija";
        AbstractFactory factory = new AbstractFactory();
        PackageView packageView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
        Diagram currDiagram = ((DiagramView) packageView.getTabbedPane().getSelectedComponent()).getDiagram();
        Connection noviElement = factory.newConnection(rbResult, currDiagram, naziv.getText());
        noviElement.setToX(fx);
        noviElement.setToY(fy);
        noviElement.setFromX(sx);
        noviElement.setFromY(sy);
        ClassyTreeItem myParent = findClassyTreeItem(MainFrame.getInstance().getClassyTree().getRoot(), currDiagram);
        if(myParent != null) {
            MainFrame.getInstance().getClassyTree().addChildToDiag(myParent, noviElement);
            //parentDiagram = (ClassyNodeComposite) myParent.getClassyNode();
        }
        else
            System.out.println(currDiagram.getName() + " nije nadjen");
        calledFrom.zavrsenaSelekcija(noviElement, packageView);
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
    public boolean connectsTwoItems(){
        DiagramElement el1 = null;
        DiagramElement el2 = null;
        PackageView packageView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
        DiagramView currDiagram = ((DiagramView) packageView.getTabbedPane().getSelectedComponent());
        for(ElementPainter ep: currDiagram.getPainters()){
            if(ep.elementAt(sx, sy)){
                el1 = ep.getDgElement();
                break;
            }
        }
        for(ElementPainter ep: currDiagram.getPainters()){
            if(ep.elementAt(fx, fy)){
                el2 = ep.getDgElement();
                break;
            }
        }
        if(el1 == null || el2 == null)
            return false;
        if(el1 == el2)
            return false;
        return true;
    }
}

package raf.dsw.popUps;

import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;
import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.components.AbstractFactory;
import raf.dsw.components.Connection;
import raf.dsw.components.DiagramElement;
import raf.dsw.components.InterClass;
import raf.dsw.core.ApplicationFramework;
import raf.dsw.message.PossibleErrors;
import raf.dsw.paint.ClassPainter;
import raf.dsw.paint.ElementPainter;
import raf.dsw.paint.InterClassPainter;
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
import java.awt.geom.Point2D;
import java.util.List;

public class EditPopUpConnection extends JDialog {
    String rbResult = "";
    private JTextField naziv = new JTextField();
    ButtonGroup buttonGroup = new ButtonGroup();
    JRadioButton radioButton1 = new JRadioButton("Agregacija");
    JRadioButton radioButton2 = new JRadioButton("Kompozicija");
    JRadioButton radioButton3 = new JRadioButton("Zavisnost");
    JRadioButton radioButton4 = new JRadioButton("Generalizacija");
    private int sx, sy, fx, fy;
    private int noviStartx, noviStarty, noviFinishx, noviFinishy;
    private State calledFrom;
    private ElementPainter painter1, painter2;
    private Point2D startingPoint, endingPoint;
    ////////////////////
    private Connection trenutnaVeza;
    public EditPopUpConnection(State calledFrom, Connection trenutnaVeza){
        super(MainFrame.getInstance(), "Izmena odabrane veze", true);
        this.trenutnaVeza = trenutnaVeza;
        this.calledFrom = calledFrom;
        setUp();
    }
    public void setUp(){
        setLayout(new GridLayout(4, 1));
        JLabel label = new JLabel("Odaberite novi tip veze, ukoliko zelite da ga promenite");
        JPanel radioButtons = new JPanel(new GridLayout(1, 4));
        JPanel buttonHolder = new JPanel(new GridLayout(1, 3));
        JPanel nameInsertion = new JPanel(new GridLayout(1, 2));
        nameInsertion.add(new Label("Uneiste novi naziv veze: "), 0);
        nameInsertion.add(naziv, 1);
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);
        buttonGroup.add(radioButton4);
        radioButtons.add(radioButton1, 0);
        radioButtons.add(radioButton2, 1);
        radioButtons.add(radioButton3, 2);
        radioButtons.add(radioButton4, 3);
        JButton addElement = new JButton("Izmeni");
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
        PackageView packageView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
        Diagram currDiagram = ((DiagramView) packageView.getTabbedPane().getSelectedComponent()).getDiagram();
        if(radioButton1.isSelected())
            rbResult = "Agregacija";
        else if(radioButton2.isSelected())
            rbResult = "Kompozicija";
        else if(radioButton3.isSelected())
            rbResult = "Zavisnost";
        else if(radioButton4.isSelected())
            rbResult = "Generalizacija";
        AbstractFactory factory = new AbstractFactory();
        boolean flag = true;
        if(naziv.getText().length() > 0) {
            for (ClassyNode cn : currDiagram.getChildren()) {
                if (cn.getName().equalsIgnoreCase(naziv.getText())) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                trenutnaVeza.setName(naziv.getText());
                MainFrame.getInstance().getClassyTree().update();
            } else {
                ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.NAME_ALREADY_EXISTS);
            }
        }
        Connection proba = factory.newConnection(rbResult, currDiagram, trenutnaVeza.getName());
        if(proba.getClass()!=(trenutnaVeza.getClass())){
            proba.setToX(trenutnaVeza.getToX());
            proba.setToY(trenutnaVeza.getToY());
            proba.setFromX(trenutnaVeza.getFromX());
            proba.setFromY(trenutnaVeza.getFromY());
            ClassyTreeItem myParent = findClassyTreeItem(MainFrame.getInstance().getClassyTree().getRoot(), currDiagram);
            ClassyTreeItem trenutnaVezaTreeItem =  findClassyTreeItem(MainFrame.getInstance().getClassyTree().getRoot(), trenutnaVeza);
            if(myParent != null) {
                ((ClassyNodeComposite)(trenutnaVeza.getParent())).removeChild(trenutnaVeza);
                MainFrame.getInstance().getClassyTree().deleteChild(trenutnaVezaTreeItem);
                MainFrame.getInstance().getClassyTree().addChildToDiag(myParent, proba);
            }
            else
                System.out.println(currDiagram.getName() + " nije nadjen");
            calledFrom.zavrsenaSelekcija(proba, packageView);
        }
        dispose();
    }
    public ClassyTreeItem findClassyTreeItem(ClassyTreeItem root, ClassyNode targetNode) {
        if (root.getClassyNode().getName().equalsIgnoreCase(targetNode.getName()) && root.getClassyNode().getClass() == targetNode.getClass()) {
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
            if(ep.elementAt(sx, sy) && ep instanceof ClassPainter){
                el1 = ep.getDgElement();
                painter1 = ep;
                break;
            }
        }
        for(ElementPainter ep: currDiagram.getPainters()){
            if(ep.elementAt(fx, fy) && ep instanceof ClassPainter){
                el2 = ep.getDgElement();
                painter2 = ep;
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

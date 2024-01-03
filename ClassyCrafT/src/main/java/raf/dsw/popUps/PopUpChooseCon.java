package raf.dsw.popUps;

import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;
import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.components.*;
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

public class PopUpChooseCon extends JDialog {
    String rbResult = "";
    private JTextField naziv = new JTextField();
    ButtonGroup buttonGroup = new ButtonGroup();
    JRadioButton radioButton1 = new JRadioButton("Agregacija");
    JRadioButton radioButton2 = new JRadioButton("Kompozicija");
    JRadioButton radioButton3 = new JRadioButton("Zavisnost");
    JRadioButton radioButton4 = new JRadioButton("Generalizacija");
    private int noviStartx, noviStarty, noviFinishx, noviFinishy;
    private State calledFrom;
    private InterClass from, to;
    private Point2D startingPoint, endingPoint;
    public PopUpChooseCon(InterClass from, InterClass to, State calledFrom){
        super(MainFrame.getInstance(), "Dodavanje nove veze", true);
        this.from = from;
        this.to = to;
        this.calledFrom = calledFrom;
        setUp();
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
        else{
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.TYPE_OF_CON_NOT_SELECTED);
            return;
        }
        AbstractFactory factory = new AbstractFactory();
        PackageView packageView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
        Diagram currDiagram = ((DiagramView) packageView.getTabbedPane().getSelectedComponent()).getDiagram();
        Connection noviElement = factory.newConnection(rbResult, currDiagram, naziv.getText(), from, to);
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
        /*if(flag) {
            ClassyTreeItem myParent = MainFrame.getInstance().getClassyTree().getRoot().findClassyTreeItem(currDiagram);
            if (myParent != null) {
                MainFrame.getInstance().getClassyTree().addChildToDiag(myParent, noviElement);
            } else
                System.out.println(currDiagram.getName() + " nije nadjen");
            calledFrom.zavrsenaSelekcija(noviElement, packageView);
        }
        else{
            noviElement.setName("");
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.NAME_ALREADY_EXISTS);
        }*/
        if(flag) {
            if(noviElement instanceof Agregacija || noviElement instanceof Kompozicija) {
                AdditionalConPop popUp = new AdditionalConPop(this, noviElement);
            }
            else{
                noviElement.setKardinalnost("0");
                noviElement.setVidljivost(null);
            }
            calledFrom.zavrsenaSelekcija(noviElement, packageView);
        }
        dispose();
    }

   /* public void twoClosestDots(){
        double curr = 0, max = Integer.MAX_VALUE;
        for(Point2D point1 :((ClassPainter)painter1).getRectangleCoordinates()){
            for(Point2D point2: ((ClassPainter)painter2).getRectangleCoordinates()){
                curr = Math.sqrt((point1.getX() - point2.getX())*(point1.getX() - point2.getX())
                        + (point1.getY() - point2.getY())*(point1.getY() - point2.getY()));
                if(curr < max) {
                    max = curr;
                    startingPoint = point1;
                    endingPoint = point2;
                }
            }
        }
    }*/
}
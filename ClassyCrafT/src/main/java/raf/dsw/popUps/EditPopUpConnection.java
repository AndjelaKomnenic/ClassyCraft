package raf.dsw.popUps;

import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;
import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.commands.EditConCommand;
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
        Connection noviElement = factory.newConnection(rbResult, currDiagram, naziv.getText(), trenutnaVeza.getFrom(), trenutnaVeza.getTo());
        /*ClassyTreeItem myParent = MainFrame.getInstance().getClassyTree().getRoot().findClassyTreeItem(currDiagram);
        if(myParent != null) {
            MainFrame.getInstance().getClassyTree().deleteNode(trenutnaVeza);
            MainFrame.getInstance().getClassyTree().addChildToDiag(myParent, noviElement);

        }
        else
            System.out.println(currDiagram.getName() + " nije nadjen");*/

        if(noviElement instanceof Agregacija || noviElement instanceof Kompozicija) {
            AdditionalConPop popUp = new AdditionalConPop(this, noviElement);
        }
        else{
            noviElement.setKardinalnost("0");
            noviElement.setVidljivost(null);
        }
        DiagramView dv = (DiagramView) packageView.getTabbedPane().getSelectedComponent();
        dv.getCommandManager().addCommand(new EditConCommand(trenutnaVeza, noviElement, packageView, dv));
        //calledFrom.zavrsenaSelekcija(noviElement, packageView);
        dispose();
    }
}
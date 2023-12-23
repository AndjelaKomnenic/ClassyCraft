package raf.dsw.workspace.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.classyrepository.implementation.Package;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;
import raf.dsw.classyrepository.implementation.Project;
import raf.dsw.components.DiagramElement;
import raf.dsw.observer.ISubscriber;
import raf.dsw.paint.ElementPainter;
import raf.dsw.state.StateManager;
import raf.dsw.components.Connection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class PackageView extends JPanel implements ISubscriber{

    private JLabel lpackagePName = new JLabel("");
    private JLabel lAuthor = new JLabel("");
    private String packagePName = "";
    private String author = "";
    private ClassyNode packageP = null;

    private JTabbedPane tabbedPane = new JTabbedPane();
    private List<DiagramView> tabs = new ArrayList<>();

    Map<Diagram, List<ElementPainter>> paintersForDiagram = new HashMap<>();
    List<DiagramElement> selectedComponents = new ArrayList<>();

    private StateManager stateManager;

    public PackageView() {
        stateManager = new StateManager();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(lpackagePName);
        add(lAuthor);
        add(tabbedPane);
    }

    public void updateWorkspace(ClassyNode selectedPackage){
        if (selectedPackage == null || selectedPackage.getParent() == null) {
            return;
        }

        if (this.packageP != null)
            this.packageP.removeSubscriber(this);

        selectedPackage.addSubscriber(this);
        selectedPackage.getParent().addSubscriber(this);

        tabbedPane.removeAll();
        tabs.clear();

        this.packageP = selectedPackage;
        this.packagePName = selectedPackage.getName();

        if (selectedPackage.getParent() instanceof Project) {
            this.author = ((Project) selectedPackage.getParent()).getAuthor();
        }

        this.lpackagePName.setText(selectedPackage.getName());
        this.lAuthor.setText(author);

        for (ClassyNode child : ((ClassyNodeComposite) selectedPackage).getChildren()) {
            if (child instanceof Diagram){
                DiagramView tab = new DiagramView((Diagram) child);
                child.addSubscriber(tab);
                tabs.add(tab);

                paintersForDiagram.putIfAbsent((Diagram) child, new ArrayList<>());
            }

        }

        for(DiagramView tab : tabs){
            tab.setLayout(new GridLayout(1, 1));
            tabbedPane.addTab(tab.getDiagram().getName(), null, tab);
        }
    }

    @Override
    public void update(Object notification) {
        SwingUtilities.updateComponentTreeUI(this);

        if (notification.equals("ADD_AUTHOR")){
            this.author = ((Project)packageP.getParent()).getAuthor();
            this.lAuthor.setText(author);
        }
        else if (notification.equals("NEW")){
            ClassyNodeComposite mpcpackageP = (ClassyNodeComposite) packageP;

            for (ClassyNode child : mpcpackageP.getChildren()) {
                if (child instanceof Diagram) {
                    DiagramView tab = new DiagramView((Diagram) child);
                    child.addSubscriber(tab);
                    paintersForDiagram.putIfAbsent((Diagram) child, new ArrayList<>());

                    if (!tabs.contains(tab)) {
                        tabs.add(tab);

                        JComponent panelForDiagram = new JPanel();
                        panelForDiagram.setLayout(new GridLayout(1, 1));
                        tabbedPane.addTab(tab.getDiagram().getName(), null, tab);
                    }
                }
            }

        }
        else if (notification.equals("RENAME") && packageP instanceof Package){
            this.packagePName = packageP.getName();
            this.lpackagePName.setText(packagePName);
        }
        else if (notification.equals("REMOVE")){
            this.packageP = null;
            packagePName = "";
            author = "";
            lpackagePName.setText(packagePName);
            lAuthor.setText(author);
            tabs.clear();
            getTabbedPane().removeAll();

        }
        else if(notification.equals("REPAINT")) {
            DiagramView currDiagramView = tabs.get(tabbedPane.getSelectedIndex());
            currDiagramView.setPainters(paintersForDiagram.get(currDiagramView.getDiagram()));
        }
        this.revalidate();
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(tabbedPane == null || tabs == null || tabs.isEmpty()) return;
        DiagramView currDiagramView = (DiagramView) tabbedPane.getSelectedComponent();
        currDiagramView.setPainters(paintersForDiagram.get(currDiagramView.getDiagram()));
        currDiagramView.revalidate();
        currDiagramView.repaint();
    }


    public void startDodavanjeState(){
        this.stateManager.setNewDodavanjeState();
    }

    public void startBrisanjeState(){
        this.stateManager.setNewBrisanjeState();
    }
    public void startDuplicateState(){this.stateManager.setDupliranjeState();}
    public void startMoveState(){
        this.stateManager.setNewMoveState();
    }
    public void startZoomInState(){
        this.stateManager.setNewZoomInState();
    }
    public void startZoomOutState(){
        this.stateManager.setNewZoomOutState();
    }
    public void addPainterForCurrent(ElementPainter painter){
        Diagram currDiagram = ((DiagramView)tabbedPane.getSelectedComponent()).getDiagram();
        paintersForDiagram.get(currDiagram).add(painter);
        currDiagram.addChild(painter.getDgElement());
        //dodati u stablo
        repaint();
    }
    public void removePainter(ElementPainter painter){
        Diagram currDiagram = ((DiagramView)tabbedPane.getSelectedComponent()).getDiagram();
        paintersForDiagram.get(currDiagram).remove(painter);
        currDiagram.removeChild(painter.getDgElement());
        //dodati u stablo
        repaint();
    }
    public ElementPainter getPainter(DiagramElement de){
        Diagram currDiagram = ((DiagramView)tabbedPane.getSelectedComponent()).getDiagram();
        for(ElementPainter ep: paintersForDiagram.get(currDiagram)){
            if(ep.getDgElement().equals(de))
                return ep;
        }
        return null;
    }

    public void repaintAll(){
        for(DiagramView diagramView : tabs){
            tabbedPane.setSelectedComponent(diagramView);
            diagramView.getPainters().addAll(paintersForDiagram.get(diagramView.getDiagram()));
            diagramView.repaint();
        }
    }
}
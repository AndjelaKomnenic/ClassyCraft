package raf.dsw.workspace.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.classyrepository.implementation.Package;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;
import raf.dsw.classyrepository.implementation.Project;
import raf.dsw.observer.ISubscriber;
import raf.dsw.state.StateManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

    /*Map<Diagram, List<ElementPainter>> paintersForMap = new HashMap<>();
    List<ElementPainter> selectedComponents = new ArrayList<>();*/
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

    }
    /*
<<<<<<< HEAD
    public void startBrisanjeState(){
        //stateManager.s
=======*/

    //samo ga zove ovde
    public void startMisKliknut(int x, int y, Diagram currDiagram){
        this.stateManager.setNewDodavanjeState();
    }


    void startState(){
        //stateManager.getCurrState().misKliknut();
    }
}

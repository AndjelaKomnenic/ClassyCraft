package raf.dsw.workspace;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.composite.ClassyNode;
import raf.dsw.composite.ClassyNodeComposite;
import raf.dsw.composite.Diagram;
import raf.dsw.composite.Project;
import raf.dsw.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PackageView extends JPanel implements ISubscriber {

    private JLabel lProjectName = new JLabel();
    private JLabel lAuthor = new JLabel();
    private String projectName = "";
    private String author = "";
    private ClassyNode project = null;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private List<DiagramView> tabs = new ArrayList<>();

    public PackageView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(lProjectName);
        add(lAuthor);
        add(tabbedPane);
    }

    public void updateWorkspace(ClassyNode selectedPackage){
        if(this.project != null) this.project.removeSubscriber(this);
        selectedPackage.addSubscriber(this);

        tabbedPane.removeAll();
        tabs.clear();

        /*this.project = selectedPackage;
        this.projectName = selectedPackage.getName();
        this.author = ((Package) selectedPackage).getAuthor();
        this.lProjectName.setText(selectedPackage.getName());
        this.lAuthor.setText(author);*/

        for (ClassyNode child : ((ClassyNodeComposite) selectedPackage).getChildren()) {
            DiagramView tab = new DiagramView((Diagram) child);
            child.addSubscriber(tab);
            tabs.add(tab);

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
            this.author = ((Project)project).getAuthor();
            this.lAuthor.setText(author);
        }
        else if (notification.equals("NEW")){
            ClassyNodeComposite mpcProject = (ClassyNodeComposite)project;
            ClassyNode newDiagram = mpcProject.getChildren().get(mpcProject.getChildren().size() - 1);

            DiagramView tab = new DiagramView((Diagram) newDiagram);
            newDiagram.addSubscriber(tab);
            tabs.add(tab);

            JComponent panelForDiagram = new JPanel();
            panelForDiagram.setLayout(new GridLayout(1, 1));
            tabbedPane.addTab(tab.getDiagram().getName(), null, tab);
        }
        else if (notification.equals("RENAME")){
            this.projectName = project.getName();
            this.lProjectName.setText(projectName);
        }
        else if (notification.equals("REMOVE")){
            this.project = null;
            projectName = "";
            author = "";
            lProjectName.setText(projectName);
            lAuthor.setText(author);
            tabs.clear();
            getTabbedPane().removeAll();
        }
    }
}

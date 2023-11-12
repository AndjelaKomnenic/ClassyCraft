package raf.dsw.workspace.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.observer.ISubscriber;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.IWorkspace;
import raf.dsw.workspace.WorkSpaceImplementation;

import javax.swing.*;
import java.util.Objects;

@Getter
@Setter
public class DiagramView extends JPanel implements ISubscriber {

    private Diagram diagram;

    public DiagramView(Diagram diagram){
        this.diagram = diagram;
    }

    @Override
    public void update(Object notification) {
        IWorkspace workspace = MainFrame.getInstance().getWorkspace();
        PackageView ourProjectView = ((WorkSpaceImplementation) workspace).getPackageView();
        int indexOfOurDiagram = ourProjectView.getTabbedPane().indexOfComponent(this);

        if (indexOfOurDiagram != -1) {
            if (notification.equals("REMOVE")) {
                ourProjectView.getTabs().remove(this);
                ourProjectView.getTabbedPane().remove(indexOfOurDiagram);
            } else if (notification.equals("RENAME")) {
                ourProjectView.getTabbedPane().setTitleAt(indexOfOurDiagram, diagram.getName());
            }
        }



        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DiagramView other = (DiagramView) obj;
        return Objects.equals(this.getDiagram(), other.getDiagram());
    }
}

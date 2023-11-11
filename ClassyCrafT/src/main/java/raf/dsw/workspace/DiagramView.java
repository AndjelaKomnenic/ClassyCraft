package raf.dsw.workspace;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.composite.Diagram;
import raf.dsw.observer.ISubscriber;
import raf.dsw.view.MainFrame;

import javax.swing.*;

@Getter
@Setter
public class DiagramView extends JPanel implements ISubscriber {

    private Diagram diagram;

    public DiagramView(Diagram diagram){
        this.diagram = diagram;
        /*this.addMouseListener(new MouseGraphicEvent());
        this.addMouseMotionListener(new MouseGraphicEvent());           //za drag*/
    }

    @Override
    public void update(Object notification) {
        IWorkspace workspace = MainFrame.getInstance().getWorkspace();
        PackageView ourProjectView = ((WorkSpaceImplementation) workspace).getPackageView();
        int indexOfOurDiagram = ourProjectView.getTabbedPane().indexOfComponent(this);

        if(notification.equals("REMOVE")){
            ourProjectView.getTabs().remove(this);
            ourProjectView.getTabbedPane().remove(indexOfOurDiagram);
        }
        else if(notification.equals("RENAME")){
            ourProjectView.getTabbedPane().setTitleAt(indexOfOurDiagram, diagram.getName());
        }
    }
}

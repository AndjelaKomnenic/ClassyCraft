package raf.dsw.state;

import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.components.DiagramElement;
import raf.dsw.components.Enum;
import raf.dsw.components.InterClass;
import raf.dsw.components.Interfejs;
import raf.dsw.components.Klasa;
import raf.dsw.core.ApplicationFramework;
import raf.dsw.message.PossibleErrors;
import raf.dsw.paint.*;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

public class DupliranjeState implements State{
    @Override
    public void misKliknut(int x, int y, DiagramView currDiagramView, PackageView pkg) {
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView currDiagram, PackageView pkg) {

    }

    @Override
    public void misPrevucen(int x, int y, DiagramView currDiagram, PackageView pkg) {

    }

    @Override
    public void zavrsenaSelekcija(DiagramElement inter, PackageView pkg) {

    }

    @Override
    public void neispravnoCrtanje() {}
    public void duplikacija(DiagramElement de, int x, int y, int w, int h, PackageView pkg){
        if(de instanceof InterClass){
            InterClass ic = (InterClass) de;
            ic.setY(y);
            ic.setX(x);
            ElementPainter elementPainter;
            Diagram currDiagram = ((DiagramView) pkg.getTabbedPane().getSelectedComponent()).getDiagram();
            ClassyTreeItem myParent = MainFrame.getInstance().getClassyTree().getRoot().findClassyTreeItem(currDiagram);
            if(myParent != null)
                MainFrame.getInstance().getClassyTree().addChildToDiag(myParent, ic);
            else
                System.out.println(currDiagram.getName() + " nije nadjen");
            elementPainter = new InterClassPainter(ic, w, h, x, y);
            pkg.addPainterForCurrent(elementPainter);

        }else{
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.WRONG_SELECTION_FOR_DUPLICATE);
        }
    }
}
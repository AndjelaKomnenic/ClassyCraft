package raf.dsw.commands;

import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.components.InterClass;
import raf.dsw.paint.ElementPainter;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

public class DuplicateCommand extends AbstractCommand{
    InterClass duplikat;
    ElementPainter painter;
    PackageView pkgView;
    DiagramView dgView;
    public DuplicateCommand(PackageView pv, DiagramView dv, InterClass duplikat, ElementPainter painter){
        this.painter = painter;
        this.duplikat = duplikat;
        dgView = dv;
        pkgView = pv;
    }
    @Override
    public void doCommand() {
        Diagram currDiagram = ((DiagramView) pkgView.getTabbedPane().getSelectedComponent()).getDiagram();
        ClassyTreeItem myParent = MainFrame.getInstance().getClassyTree().getRoot().findClassyTreeItem(dgView.getDiagram());
        if(myParent != null)
            MainFrame.getInstance().getClassyTree().addChildToDiag(myParent, duplikat);
        else
            System.out.println(currDiagram.getName() + " nije nadjen");
        pkgView.addPainterForCurrent(painter);
    }

    @Override
    public void undoCommand() {
        pkgView.removePainter(painter);
        ClassyTreeItem treeItemZaBrsianje = MainFrame.getInstance().getClassyTree().getRoot().findClassyTreeItem(painter.getDgElement());
        if (treeItemZaBrsianje != null)
            MainFrame.getInstance().getClassyTree().deleteChild(treeItemZaBrsianje);
        else
            System.out.println("Nije nadjen");
    }

}

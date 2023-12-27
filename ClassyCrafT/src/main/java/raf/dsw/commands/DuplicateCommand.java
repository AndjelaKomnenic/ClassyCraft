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
        ClassyTreeItem myParent = findClassyTreeItem(MainFrame.getInstance().getClassyTree().getRoot(), currDiagram);
        if(myParent != null)
            MainFrame.getInstance().getClassyTree().addChildToDiag(myParent, duplikat);
        else
            System.out.println(currDiagram.getName() + " nije nadjen");
        pkgView.addPainterForCurrent(painter);
    }

    @Override
    public void undoCommand() {
        pkgView.removePainter(painter);
        ClassyTreeItem treeItemZaBrsianje = findClassyTreeItem(MainFrame.getInstance().getClassyTree().getRoot(), duplikat);
        if (treeItemZaBrsianje != null)
            MainFrame.getInstance().getClassyTree().deleteChild(treeItemZaBrsianje);
        else
            System.out.println("Nije nadjen");
    }
    public ClassyTreeItem findClassyTreeItem(ClassyTreeItem root, ClassyNode targetNode) {
        if (root.getClassyNode().getName().equalsIgnoreCase(targetNode.getName())) {
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
}

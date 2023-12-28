package raf.dsw.commands;

import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.components.Connection;
import raf.dsw.paint.ConnectionPainter;
import raf.dsw.paint.ElementPainter;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

public class EditConCommand extends AbstractCommand{
    private Connection oldC, newC;
    PackageView pkgView;
    DiagramView dgView;
    Diagram currDiagram;
    public EditConCommand(Connection o, Connection n, PackageView pv, DiagramView dv){
        oldC = o;
        newC = n;
        pkgView = pv;
        dgView = dv;
        currDiagram = dgView.getDiagram();
    }
    @Override
    public void doCommand() {
        ClassyTreeItem myParent = findClassyTreeItem(MainFrame.getInstance().getClassyTree().getRoot(), currDiagram);
        ClassyTreeItem myConn = findClassyTreeItem(MainFrame.getInstance().getClassyTree().getRoot(), oldC);
        if(myParent != null) {
            MainFrame.getInstance().getClassyTree().deleteChild(myConn);
            MainFrame.getInstance().getClassyTree().addChildToDiag(myParent, newC);
        }
        else
            System.out.println(currDiagram.getName() + " nije nadjen");
        oldC.setToX(0);
        oldC.setToY(0);
        oldC.setFromX(0);
        oldC.setFromY(0);
        ElementPainter conPain = new ConnectionPainter(newC);
        pkgView.removePainter(pkgView.getPainter(oldC));
        pkgView.addPainterForCurrent(conPain);
        MainFrame.getInstance().getClassyTree().update();
    }

    @Override
    public void undoCommand() {
        ClassyTreeItem myParent = findClassyTreeItem(MainFrame.getInstance().getClassyTree().getRoot(), currDiagram);
        ClassyTreeItem myConn = findClassyTreeItem(MainFrame.getInstance().getClassyTree().getRoot(), newC);
        if(myParent != null) {
            //System.out.println("Ispis iz command, child = " + newC.getName() + " njegov .getParent = " + myConn.getParent());
            MainFrame.getInstance().getClassyTree().deleteChild(myConn);
            MainFrame.getInstance().getClassyTree().addChildToDiag(myParent, oldC);
        }
        else
            System.out.println(currDiagram.getName() + " nije nadjen");
        newC.setToX(0);
        newC.setToY(0);
        newC.setFromX(0);
        newC.setFromY(0);
        pkgView.removePainter(pkgView.getPainter(newC));
        ElementPainter conPain = new ConnectionPainter(oldC);
        pkgView.addPainterForCurrent(conPain);
        MainFrame.getInstance().getClassyTree().update();
    }
    public ClassyTreeItem findClassyTreeItem(ClassyTreeItem root, ClassyNode targetNode) {
        if (root.getClassyNode().getName().equalsIgnoreCase(targetNode.getName()) && root.getClassyNode().getClass() == targetNode.getClass()) {
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

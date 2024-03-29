package raf.dsw.commands;

import jdk.jshell.Diag;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.components.Connection;
import raf.dsw.components.InterClass;
import raf.dsw.paint.ElementPainter;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

public class NewConnectionCommand extends AbstractCommand{
    private InterClass from;
    private InterClass to;
    private Connection connection;
    private ElementPainter painter;
    private PackageView pkgView;
    private DiagramView dgView;
    public NewConnectionCommand(Connection connection, InterClass from, InterClass to, ElementPainter painter, PackageView pv, DiagramView dv){
        this.connection = connection;
        this.from = from;
        this.to = to;
        this.painter = painter;
        pkgView = pv;
        dgView = dv;
    }
    @Override
    public void doCommand() {
        ClassyTreeItem myParent = findClassyTreeItem(MainFrame.getInstance().getClassyTree().getRoot(), dgView.getDiagram());
        if (myParent != null) {
            MainFrame.getInstance().getClassyTree().addChildToDiag(myParent, connection);
        } else
            System.out.println(dgView.getDiagram().getName() + " nije nadjen");
        pkgView.addPainterForCurrent(painter);
        connection.getTo().addToListVeza(connection);
        connection.getFrom().addToListVeza(connection);
    }

    @Override
    public void undoCommand() {
        pkgView.removePainter(pkgView.getPainter(connection));
        ClassyTreeItem treeItemZaBrsianje = findClassyTreeItem(MainFrame.getInstance().getClassyTree().getRoot(), connection);
        if(treeItemZaBrsianje != null)
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

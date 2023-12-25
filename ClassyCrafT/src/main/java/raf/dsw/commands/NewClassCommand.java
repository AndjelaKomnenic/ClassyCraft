package raf.dsw.commands;

import raf.dsw.Main;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.components.Connection;
import raf.dsw.components.InterClass;
import raf.dsw.paint.ElementPainter;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import javax.swing.*;

public class NewClassCommand extends AbstractCommand{
    private PackageView pkgView;
    private DiagramView dgView;
    private ElementPainter thisClassPainter;
    private InterClass addedInterClass;
    private int x,y;

    public NewClassCommand(PackageView pkgView, DiagramView dgView, int x, int y) {
        this.pkgView = pkgView;
        this.dgView = dgView;
        this.x = x;
        this.y = y;
    }
    public NewClassCommand(PackageView pkg, DiagramView dv, InterClass addedInterClass, ElementPainter elementPainter){
        pkgView = pkg;
        dgView = dv;
        this.addedInterClass = addedInterClass;
        thisClassPainter = elementPainter;
    }

    @Override
    public void doCommand() {
        dgView.getDiagram().addChild(addedInterClass);
        ClassyTreeItem myParent = findClassyTreeItem(MainFrame.getInstance().getClassyTree().getRoot(), dgView.getDiagram());
        if(myParent != null)
            MainFrame.getInstance().getClassyTree().addChildToDiag(myParent, addedInterClass);
        else
            System.out.println("Nije nadjen");
        pkgView.addPainterForCurrent(thisClassPainter);
    }

    @Override
    public void undoCommand() {
        pkgView.removePainter(pkgView.getPainter(addedInterClass));
        for (Connection c : ((InterClass)addedInterClass).getListVeza()) {
            if (pkgView.getPainter(c) != null) {
                pkgView.removePainter(pkgView.getPainter(c));
                ClassyTreeItem treeItemZaBrsianje = findClassyTreeItem(MainFrame.getInstance().getClassyTree().getRoot(), c);

                if (treeItemZaBrsianje != null)
                    MainFrame.getInstance().getClassyTree().deleteChild(treeItemZaBrsianje);
                else
                    System.out.println("Nije nadjen");
            }
            else{
            }
        }
        ClassyTreeItem treeItemZaBrsianje = findClassyTreeItem(MainFrame.getInstance().getClassyTree().getRoot(), addedInterClass);
        if(treeItemZaBrsianje != null) {
            MainFrame.getInstance().getClassyTree().deleteChild(treeItemZaBrsianje);
        }
        else
            System.out.println("Nije nadjen");
        dgView.getDiagram().getChildren().remove(addedInterClass);
        //MainFrame.getInstance().getClassyTree().deleteNode(addedInterClass);
    }
    public ClassyTreeItem findClassyTreeItem(ClassyTreeItem root, ClassyNode targetNode) {
        if (root.getClassyNode().equals(targetNode)) {
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

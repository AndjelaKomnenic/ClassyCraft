package raf.dsw.commands;

import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.components.Connection;
import raf.dsw.components.DiagramElement;
import raf.dsw.components.InterClass;
import raf.dsw.components.Klasa;
import raf.dsw.paint.ClassPainter;
import raf.dsw.paint.ElementPainter;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import java.util.ArrayList;
import java.util.List;

public class DeleteCommand extends AbstractCommand{
    private PackageView pkgView;
    private DiagramView dgView;
    private List<DiagramElement> listOfDeleted = new ArrayList<>();
    private List<ElementPainter> listOfPainters = new ArrayList<>();
    private List<DiagramElement> alistOfDeleted = new ArrayList<>();
    private List<ElementPainter> alistOfPainters = new ArrayList<>();
    public DeleteCommand(PackageView pkg, DiagramView dv, List<DiagramElement> listOfDeleted, List<ElementPainter> listOfPainters){
        pkgView = pkg;
        dgView = dv;
        for(DiagramElement del: listOfDeleted)
            this.listOfDeleted.add(del);
        for(ElementPainter elp: listOfPainters)
            this.listOfPainters.add(elp);
    }
    @Override
    public void doCommand() {
        for(DiagramElement ep: listOfDeleted){
            ep.setSelected(false);
            pkgView.removePainter(pkgView.getPainter(ep));
            if(ep instanceof InterClass) {
                for (Connection c : ((InterClass)ep).getListaVeza()) {
                    if (pkgView.getPainter(c) != null) {
                        if(pkgView.getPainter(c) != null) {
                            alistOfPainters.add(pkgView.getPainter(c));
                        }
                        pkgView.removePainter(pkgView.getPainter(c));
                        if(!alistOfDeleted.contains(c))
                            alistOfDeleted.add(c);
                        ClassyTreeItem treeItemZaBrsianje = MainFrame.getInstance().getClassyTree().getRoot().findClassyTreeItem(c);
                        if (treeItemZaBrsianje != null)
                            MainFrame.getInstance().getClassyTree().deleteChild(treeItemZaBrsianje);
                        else
                            System.out.println("Nije nadjen");
                    }
                    else{
                    }
                }
            }
            ClassyTreeItem treeItemZaBrsianje = MainFrame.getInstance().getClassyTree().getRoot().findClassyTreeItem(ep);
            if(treeItemZaBrsianje != null)
                MainFrame.getInstance().getClassyTree().deleteChild(treeItemZaBrsianje);
            else
                System.out.println("Nije nadjen");
        }
        pkgView.getSelectedComponents().clear();
    }

    @Override
    public void undoCommand() {
        for(DiagramElement dgEl: listOfDeleted) {
            dgView.getDiagram().addChild(dgEl);
            ClassyTreeItem myParent = MainFrame.getInstance().getClassyTree().getRoot().findClassyTreeItem(dgView.getDiagram());
            if (myParent != null)
                MainFrame.getInstance().getClassyTree().addChildToDiag(myParent, dgEl);
            else
                System.out.println("Nije nadjen");
        }
        for(DiagramElement dgEl: alistOfDeleted) {
            dgView.getDiagram().addChild(dgEl);
            ClassyTreeItem myParent = MainFrame.getInstance().getClassyTree().getRoot().findClassyTreeItem(dgView.getDiagram());
            if (myParent != null)
                MainFrame.getInstance().getClassyTree().addChildToDiag(myParent, dgEl);
            else
                System.out.println("Nije nadjen");
        }
        for(ElementPainter elp: listOfPainters)
            pkgView.addPainterForCurrent(elp);
        for(ElementPainter elp: alistOfPainters)
            pkgView.addPainterForCurrent(elp);
    }

}

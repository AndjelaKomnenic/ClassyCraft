package raf.dsw.state;

import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.components.Connection;
import raf.dsw.components.DiagramElement;
import raf.dsw.components.InterClass;
import raf.dsw.core.ApplicationFramework;
import raf.dsw.paint.ConnectionPainter;
import raf.dsw.paint.ElementPainter;
import raf.dsw.popUps.EditPopUpClass;
import raf.dsw.popUps.EditPopUpConnection;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import java.awt.*;

public class DodavanjeSadrzajaState implements State {
    private DiagramElement selektovani;

    @Override
    public void misKliknut(int x, int y, DiagramView currDiagram, PackageView pkg) {
        selektovani = null;
        for(ElementPainter ep: currDiagram.getPainters()){
            if(ep.elementAt(x, y)) {
                selektovani = ep.getDgElement();
            }
        }
        if(selektovani != null){
            if(selektovani instanceof Connection) {
                EditPopUpConnection editPop = new EditPopUpConnection(this, (Connection)selektovani);
            }
            else {
                EditPopUpClass editPop = new EditPopUpClass(selektovani);
            }
        }

    }

    @Override
    public void misOtpusten(int x, int y, DiagramView currDiagram, PackageView pkg) {

    }

    @Override
    public void misPrevucen(int x, int y, DiagramView currDiagram, PackageView pkg) {

    }
    public void zavrsenaSelekcija(DiagramElement inter, PackageView pkg){
        Connection original = (Connection) selektovani;
        Connection c = (Connection) inter;
        original.setToX(0);
        original.setToY(0);
        original.setFromX(0);
        original.setFromY(0);
        ElementPainter conPain = new ConnectionPainter(c);
        //pkg.removePainter();
        pkg.addPainterForCurrent(conPain);
        MainFrame.getInstance().getClassyTree().update();
    }

    @Override
    public void neispravnoCrtanje() {}

    @Override
    public void duplikacija(DiagramElement de, int x, int y, int w, int h, PackageView pkg) {

    }
}
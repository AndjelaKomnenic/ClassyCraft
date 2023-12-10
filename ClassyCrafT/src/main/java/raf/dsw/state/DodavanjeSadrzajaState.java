package raf.dsw.state;

import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.components.DiagramElement;
import raf.dsw.components.InterClass;
import raf.dsw.paint.ElementPainter;
import raf.dsw.popUps.EditPopUpClass;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import java.awt.*;

public class DodavanjeSadrzajaState implements State {

    @Override
    public void misKliknut(int x, int y, DiagramView currDiagram, PackageView pkg) {
        DiagramElement selektovani = null;
        for(ElementPainter ep: currDiagram.getPainters()){
            if(ep.elementAt(x, y)) {
                selektovani = ep.getDgElement();
                System.out.println(selektovani.getName());
            }
        }
        if(selektovani != null){
            EditPopUpClass editPop = new EditPopUpClass(selektovani);
        }

    }

    @Override
    public void misOtpusten(int x, int y, DiagramView currDiagram, PackageView pkg) {

    }

    @Override
    public void misPrevucen(int x, int y, DiagramView currDiagram, PackageView pkg) {

    }
    public void zavrsenaSelekcija(DiagramElement noviElement, PackageView pkg){}
    @Override
    public void neispravnoCrtanje() {}
}

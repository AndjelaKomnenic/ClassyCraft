package raf.dsw.commands;

import raf.dsw.components.InterClass;
import raf.dsw.state.Tacka;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import java.util.HashMap;

public class MoveCommand extends AbstractCommand{
    private PackageView pkgView;
    private DiagramView dgView;
    HashMap<InterClass, Tacka> originalneTacke = new HashMap<>();
    HashMap<InterClass, Tacka> noveTacke = new HashMap<>();

    public MoveCommand(PackageView pv, DiagramView dv, HashMap<InterClass, Tacka> originalneTacke, HashMap<InterClass, Tacka> noveTacke){
        pkgView = pv;
        dgView = dv;
        for(InterClass klasa: originalneTacke.keySet())
            this.originalneTacke.put(klasa, originalneTacke.get(klasa));
        for(InterClass klasa: noveTacke.keySet())
            this.noveTacke.put(klasa, noveTacke.get(klasa));
    }
    @Override
    public void doCommand() {
        for(InterClass klasa: originalneTacke.keySet()){
            klasa.setX((noveTacke.get(klasa)).getX());
            klasa.setY((noveTacke.get(klasa)).getY());
        }
    }

    @Override
    public void undoCommand() {
        for(InterClass klasa: originalneTacke.keySet()){
            klasa.setX((originalneTacke.get(klasa)).getX());
            klasa.setY((originalneTacke.get(klasa)).getY());
        }
    }
}

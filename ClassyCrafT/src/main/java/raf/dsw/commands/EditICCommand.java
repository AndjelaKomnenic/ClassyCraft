package raf.dsw.commands;

import raf.dsw.components.ClanEnuma;
import raf.dsw.components.ClassContent;
import raf.dsw.components.InterClass;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import java.util.ArrayList;
import java.util.List;

public class EditICCommand extends AbstractCommand{
    List<ClassContent> listOFDeletedCC = new ArrayList<>();
    List<ClassContent> listOFAddedCC = new ArrayList<>();
    List<ClanEnuma> listOFDeletedE = new ArrayList<>();
    List<ClanEnuma> listOFAddedE = new ArrayList<>();
    InterClass editedElement;
    PackageView pkgView;
    DiagramView dgView;
    String oldName;
    String newName;
    public EditICCommand(PackageView pv, DiagramView dv, InterClass editedElement, List<ClassContent> listOFDeletedCC,
                         List<ClassContent> listOFAddedCC, List<ClanEnuma> listOFDeletedE, List<ClanEnuma> listOFAddedE,
                         String oldName, String newName){
        pkgView = pv;
        dgView = dv;
        this.editedElement = editedElement;
        for(ClassContent clCOn: listOFAddedCC)
            this.listOFAddedCC.add(clCOn);
        for(ClassContent clCOn: listOFDeletedCC)
            this.listOFDeletedCC.add(clCOn);
        for(ClanEnuma clCOn: listOFAddedE)
            this.listOFAddedE.add(clCOn);
        for(ClanEnuma clCOn: listOFDeletedE)
            this.listOFDeletedE.add(clCOn);
    }

    @Override
    public void doCommand() {
        if(editedElement instanceof InterClass)
            doCommandInterClass();
        else
            doCommandEnum();

    }
    public void doCommandInterClass(){
        editedElement.setName(newName);
        for(ClassContent clCOn: listOFDeletedCC)
            editedElement.getCl().remove(clCOn);
        for(ClassContent clCOn: listOFAddedCC)
            editedElement.getCl().add(clCOn);
    }
    public void doCommandEnum(){

    }

    @Override
    public void undoCommand() {

    }
}

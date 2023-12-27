package raf.dsw.commands;

import raf.dsw.components.ClanEnuma;
import raf.dsw.components.ClassContent;
import raf.dsw.components.InterClass;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import java.util.ArrayList;
import java.util.List;

public class EditICCommand extends AbstractCommand{
    List<ClassContent> originalListOfCC = new ArrayList<>();
    List<ClassContent> editedListOfCC = new ArrayList<>();
    List<ClanEnuma> originalListOfE = new ArrayList<>();
    List<ClanEnuma> editedListOfE = new ArrayList<>();
    InterClass editedElement;
    PackageView pkgView;
    DiagramView dgView;
    String oldName;
    String newName;
    String oldVidljivost, newVidljivost;
    public EditICCommand(PackageView pv, DiagramView dv, InterClass editedElement, List<ClassContent> originalListOfCC,
                         List<ClanEnuma> originalListOfE, String oldName, String oldVidljivost){
        pkgView = pv;
        dgView = dv;
        this.editedElement = editedElement;
        for(ClassContent clCOn: originalListOfCC)
            this.originalListOfCC.add(clCOn);
        for(ClassContent clCOn: editedElement.getList())
            this.editedListOfCC.add(clCOn);
        for(ClanEnuma clCOn: originalListOfE)
            this.originalListOfE.add(clCOn);
        for(ClanEnuma clCOn: editedElement.getNEnum())
            this.editedListOfE.add(clCOn);
        this.oldName = oldName;
        this.newName = editedElement.getName();
        this.oldVidljivost = oldVidljivost;
        this.newVidljivost = editedElement.getVidljivost();
    }

    @Override
    public void doCommand() {
        editedElement.setName(newName);
        editedElement.getList().clear();
        editedElement.getNEnum().clear();
        for(ClassContent clCOn: editedListOfCC)
            editedElement.getCl().add(clCOn);
        for(ClanEnuma clEn: editedListOfE)
            editedElement.getNEnum().add(clEn);
        editedElement.setVidljivost(newVidljivost);
        MainFrame.getInstance().getClassyTree().update();
    }
    @Override
    public void undoCommand() {
        editedElement.setName(oldName);
        editedElement.getList().clear();
        editedElement.getNEnum().clear();
        for(ClassContent clCOn: originalListOfCC)
            editedElement.getCl().add(clCOn);
        for(ClanEnuma clEn: originalListOfE)
            editedElement.getNEnum().add(clEn);
        editedElement.setVidljivost(oldVidljivost);
        MainFrame.getInstance().getClassyTree().update();
    }
}

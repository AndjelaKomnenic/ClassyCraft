package raf.dsw.controller.sidebar;

import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.components.*;
import raf.dsw.components.Enum;
import raf.dsw.controller.AbstractClassyAction;
import raf.dsw.core.ApplicationFramework;
import raf.dsw.message.PossibleErrors;
import raf.dsw.paint.ElementPainter;
import raf.dsw.paint.InterClassPainter;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.WorkSpaceImplementation;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicateAction extends AbstractClassyAction {
    public DuplicateAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/duplicate.png"));
        putValue(NAME, "Duplicate");
        putValue(SHORT_DESCRIPTION, "Duplicate");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramElement selectedForDupl;
        int reqWidth, reqHeight, startingX, startingY;
        MainFrame.getInstance().getWorkspace().getPackageView().startDuplicateState();
        PackageView packageView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
        Diagram currDiagram = ((DiagramView) packageView.getTabbedPane().getSelectedComponent()).getDiagram();
        //System.out.println("Selected Components: " + packageView.getSelectedComponents().size());

        if(packageView.getSelectedComponents().size() != 1)
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.WRONG_SELECTION_FOR_DUPLICATE);
        else {
            selectedForDupl = packageView.getSelectedComponents().get(0);
            ElementPainter ep = packageView.getPainter(selectedForDupl);
            reqWidth = ep.getRequiredWidth() + 15;
            reqHeight = ep.getRequiredHeight();
            if(selectedForDupl instanceof InterClass) {
                startingX = (int) ((InterClass) selectedForDupl).getX() + reqWidth + 5;
                startingY = (int) ((InterClass) selectedForDupl).getY();
                boolean canFitNextTo = true;
                InterClassPainter icp = new InterClassPainter(selectedForDupl);
                Map<Diagram, List<ElementPainter>> paintersForDiagram = packageView.getPaintersForDiagram();
                for(ElementPainter elementP: paintersForDiagram.get(currDiagram)){
                    if(icp.doTheyOverlap(elementP.getXCoord(), elementP.getYCoord(), startingX, startingY,
                            elementP.getRequiredWidth(), reqWidth, elementP.getRequiredHeight(), reqHeight) ||
                            (icp.doTheyOverlap(startingX, startingY, elementP.getXCoord(), elementP.getYCoord(),
                                    reqWidth, elementP.getRequiredWidth(), reqHeight, elementP.getRequiredHeight()))){
                        canFitNextTo = false;
                        System.out.println("Nema mesta za dodavanje duplikata, molimo pomerite element  " + elementP.getDgElement().getName());
                        break;
                    }
                }
                if(canFitNextTo){
                    InterClass kopija;
                    String s = selectedForDupl.getName() + "(D)";
                    for(ElementPainter elemp: packageView.getPaintersForDiagram().get(currDiagram)){
                        if(elemp.getDgElement().getName().equalsIgnoreCase(s)){
                            s+="(D)";
                            reqWidth += 15;
                        }
                    }
                    if(selectedForDupl instanceof Klasa){
                        kopija = new Klasa(s, currDiagram, startingX, startingY);
                        kopija.setVidljivost(((Klasa) selectedForDupl).getVidljivost());
                    }
                    else if(selectedForDupl instanceof Interfejs){
                        kopija = new Interfejs(s, currDiagram, startingX, startingY);
                    }
                    else{
                        kopija = new Enum(s, currDiagram, startingX, startingY);
                        for(ClanEnuma clanEnuma: ((Enum)selectedForDupl).getNEnum()){
                            kopija.addToListE(clanEnuma);
                        }
                    }
                    kopija.getCl().addAll(((InterClass)selectedForDupl).getList());
                    MainFrame.getInstance().getWorkspace().getPackageView().getStateManager().getCurrState().duplikacija(kopija, startingX, startingY, reqWidth, reqHeight, packageView);

                }
            }
        }
    }
}
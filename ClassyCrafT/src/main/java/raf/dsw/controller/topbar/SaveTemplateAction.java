package raf.dsw.controller.topbar;

import raf.dsw.controller.AbstractClassyAction;
import raf.dsw.core.ApplicationFramework;
import raf.dsw.message.PossibleErrors;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SaveTemplateAction extends AbstractClassyAction {

    public SaveTemplateAction(){
        putValue(NAME, "Save Template");
        putValue(SHORT_DESCRIPTION, "Save Template");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PackageView pkgView = MainFrame.getInstance().getWorkspace().getPackageView();
        DiagramView diagramView = (DiagramView) pkgView.getTabbedPane().getSelectedComponent();
        if(diagramView == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.NODE_NOT_SELECTED);
            return;
        }

        String templateName = JOptionPane.showInputDialog(new JFrame(), "Unesite ime za template", "Template Name", JOptionPane.PLAIN_MESSAGE);
        if(templateName == null || templateName.trim().isEmpty()){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.NAME_CANNOT_BE_EMPTY);
            return;
        }

        ApplicationFramework.getInstance().getSerializer().saveTemplate(diagramView.getDiagram(), templateName);
    }
}

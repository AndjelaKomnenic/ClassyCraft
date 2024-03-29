package raf.dsw.controller.topbar;

import raf.dsw.controller.AbstractClassyAction;
import raf.dsw.core.ApplicationFramework;
import raf.dsw.message.PossibleErrors;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.view.PackageView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class OpenTemplateAction extends AbstractClassyAction {

    public OpenTemplateAction() {
        putValue(NAME, "Open Templates");
        putValue(SHORT_DESCRIPTION, "Open templates");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();

        if (selected != null && selected.getClassyNode() instanceof raf.dsw.classyrepository.implementation.Package) {
            JFileChooser jfc = new JFileChooser();
            String workingDirectory = System.getProperty("user.dir");
            String templatesPath = workingDirectory + "./src/main/resources/templates/";

            jfc.setCurrentDirectory(new File(templatesPath));
            if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = jfc.getSelectedFile();
                    ApplicationFramework.getInstance().getSerializer().loadTemplate(file);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.NO_NODE_SELECTED_FOR_ADD_CHILD);
        }
    }
}

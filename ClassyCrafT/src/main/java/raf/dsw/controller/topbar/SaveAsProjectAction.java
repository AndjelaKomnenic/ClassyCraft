package raf.dsw.controller.topbar;

import raf.dsw.classyrepository.implementation.Project;
import raf.dsw.controller.AbstractClassyAction;
import raf.dsw.core.ApplicationFramework;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.view.PackageView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class SaveAsProjectAction extends AbstractClassyAction {

    public SaveAsProjectAction() {
        putValue(NAME, "SaveAs Project");
        putValue(SHORT_DESCRIPTION, "SaveAs project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        PackageView pkgView = MainFrame.getInstance().getWorkspace().getPackageView();

        Project project = (Project) pkgView.getPackageP().getParent();
        if(project == null) return;

        File projectFile = null;

        if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            projectFile = jfc.getSelectedFile();
            project.setFilePath(projectFile.getPath());
        }else
        {
            return;
        }

        project.setParent(null);
        ApplicationFramework.getInstance().getSerializer().saveProject(project);
        project.resetChanged();
    }
}
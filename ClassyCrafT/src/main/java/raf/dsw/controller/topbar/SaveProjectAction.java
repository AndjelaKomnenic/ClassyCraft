package raf.dsw.controller.topbar;

import raf.dsw.classyrepository.implementation.Package;
import raf.dsw.classyrepository.implementation.Project;
import raf.dsw.controller.AbstractClassyAction;
import raf.dsw.core.ApplicationFramework;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.WorkSpaceImplementation;
import raf.dsw.workspace.view.PackageView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class SaveProjectAction extends AbstractClassyAction {

    public SaveProjectAction() {
        putValue(NAME, "Save Project");
        putValue(SHORT_DESCRIPTION, "Save project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        PackageView pkgView = MainFrame.getInstance().getWorkspace().getPackageView();
        Project project = (Project) pkgView.getPackageP().getParent();
        if(project == null) return;

        File projectFile = null;


        project.setParent(null);
        if (project.getFilePath() == null || project.getFilePath().trim().equals("")) {
            if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                projectFile = jfc.getSelectedFile();
                project.setFilePath(projectFile.getPath());
            }
        }
        ApplicationFramework.getInstance().getSerializer().saveProject(project);

    }
}

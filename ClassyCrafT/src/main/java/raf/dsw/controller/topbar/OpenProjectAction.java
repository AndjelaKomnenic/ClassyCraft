package raf.dsw.controller.topbar;

import raf.dsw.classyrepository.implementation.Project;
import raf.dsw.controller.AbstractClassyAction;
import raf.dsw.core.ApplicationFramework;
import raf.dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class OpenProjectAction extends AbstractClassyAction {

    public OpenProjectAction(){
        putValue(NAME, "Open Project");
        putValue(SHORT_DESCRIPTION, "Open project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();

        if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
            try {
                File file = jfc.getSelectedFile();
                Project p = ApplicationFramework.getInstance().getSerializer().loadProject(file);
                if(p == null)
                {
                    //Greska pri ucitavanju
                    return;
                }
                p.resetChanged();
                MainFrame.getInstance().getClassyTree().loadProject(p);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
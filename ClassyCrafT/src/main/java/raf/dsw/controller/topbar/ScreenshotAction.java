package raf.dsw.controller.topbar;

import raf.dsw.controller.AbstractClassyAction;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.WorkSpaceImplementation;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScreenshotAction extends AbstractClassyAction {

    public ScreenshotAction() {
        putValue(SMALL_ICON, loadIcon("/images/screenshot.png"));
        putValue(NAME, "Save picture");
        putValue(SHORT_DESCRIPTION, "Save diagram as picture");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PackageView pkgView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
        DiagramView currDiagramView = (DiagramView) pkgView.getTabbedPane().getSelectedComponent();

        JFileChooser jfc = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG files", "png");
        jfc.setFileFilter(filter);

        if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File filePicture = jfc.getSelectedFile();

            if (!filePicture.getName().toLowerCase().endsWith(".png")) {
                filePicture = new File(filePicture.getAbsolutePath() + ".png");
            }

            BufferedImage image = currDiagramView.createImage(pkgView.getTabbedPane().getSelectedComponent().getSize().width,
                    pkgView.getTabbedPane().getSelectedComponent().getSize().height);
            System.out.println(image);

            try {
                ImageIO.write(image, "png", filePicture);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

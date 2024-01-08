package raf.dsw.view;

import raf.dsw.controller.topbar.*;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {

    public MyMenuBar(){
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        ExitAction ea = new ExitAction();
        AddNodeAction an = new AddNodeAction();
        DeleteNodeAction dn = new DeleteNodeAction();
        RenameAction ra = new RenameAction();
        AddAuthorAction aa = new AddAuthorAction();
        PackagePackageAction pp = new PackagePackageAction();
        SaveProjectAction sv = new SaveProjectAction();
        SaveAsProjectAction sav = new SaveAsProjectAction();
        OpenProjectAction op = new OpenProjectAction();
        SaveTemplateAction st = new SaveTemplateAction();
        OpenTemplateAction ot = new OpenTemplateAction();
        fileMenu.add(ea);
        fileMenu.add(an);
        fileMenu.add(dn);
        fileMenu.add(ra);
        fileMenu.add(aa);
        fileMenu.add(pp);
        fileMenu.add(sv);
        fileMenu.add(sav);
        fileMenu.add(op);
        fileMenu.add(st);
        fileMenu.add(ot);
        add(fileMenu);
    }

}
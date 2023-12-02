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
        fileMenu.add(ea);
        fileMenu.add(an);
        fileMenu.add(dn);
        fileMenu.add(ra);
        fileMenu.add(aa);
        fileMenu.add(pp);
        add(fileMenu);
    }

}
package raf.dsw.controller.topbar;

import raf.dsw.codegenerator.Generator;
import raf.dsw.controller.AbstractClassyAction;
import raf.dsw.tree.ClassyTreeImplementation;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.tree.view.ClassyTreeView;
import raf.dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class CodeGeneratorAction extends AbstractClassyAction {
    private Generator generator;
    public CodeGeneratorAction(){
        putValue(SMALL_ICON, loadIcon("/images/code.png"));
        putValue(NAME, "CodeGenerator");
        putValue(SHORT_DESCRIPTION, "CodeGenerator");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ClassyTreeView treeView = ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree()).getTreeView();
        ClassyTreeItem root = (ClassyTreeItem) treeView.getModel().getRoot();
        System.out.println(root);

        File baseDirectory = new File("src/main/resources/generatedCode/");

        Generator generator = new Generator();
        generator.createStructure(root, baseDirectory);
    }
}

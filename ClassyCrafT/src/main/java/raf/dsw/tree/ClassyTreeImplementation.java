package raf.dsw.tree;

import raf.dsw.composite.ClassyNode;
import raf.dsw.composite.ClassyNodeComposite;
import raf.dsw.composite.Project;
import raf.dsw.composite.ProjectExplorer;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.tree.view.ClassyTreeView;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.Random;

public class ClassyTreeImplementation implements ClassyTree{
    private ClassyTreeView treeView;
    private DefaultTreeModel treeModel;
    @Override
    public ClassyTreeView generateTree(ProjectExplorer projectExplorer) {
        ClassyTreeItem root = new ClassyTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new ClassyTreeView(treeModel);
        return treeView;
    }

    @Override
    public void addChild(ClassyTreeItem parent) {

        if (!(parent.getClassyNode() instanceof ClassyNodeComposite))
            return;
        //ispis greske da nemoze da se doda dete ne composite cvoru
        ClassyNode child = null;
        //ClassyNode child = createChild(parent.getClassyNode());
        //napisati dobar createChild
        parent.add(new ClassyTreeItem(child));
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public ClassyTreeItem getSelectedNode() {
        return (ClassyTreeItem) treeView.getLastSelectedPathComponent();
    }

    /*private ClassyNode createChild(ClassyNode parent) {
        if (parent instanceof ProjectExplorer)
            return  new Project("Project" +new Random().nextInt(100), parent);
        return null;
    }*/
    @Override
    public void deleteChild(ClassyTreeItem child){

    }

}

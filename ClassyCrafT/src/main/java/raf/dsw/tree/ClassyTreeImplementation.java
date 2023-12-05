package raf.dsw.tree;

import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;
import raf.dsw.classyrepository.implementation.ProjectExplorer;
import raf.dsw.classyrepository.factoryMethod.FactoryUtils;
import raf.dsw.classyrepository.factoryMethod.NodeFactory;
import raf.dsw.classyrepository.factoryMethod.PackageFactory;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.tree.view.ClassyTreeView;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

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
        FactoryUtils utils = new FactoryUtils();
        NodeFactory factory = utils.nodeFactory(parent);
        ClassyTreeItem child = factory.createNode(parent);
        parent.add(child);
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(child.getClassyNode());
        treeView.expandPath(treeView.getSelectionPath());
        update();
    }
    public void addChildToDiag(ClassyNodeComposite p, ClassyNode c){
        ClassyTreeItem parent = new ClassyTreeItem(p);
        ClassyTreeItem child = new ClassyTreeItem(c);
        parent.add(child);
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(child.getClassyNode());
        treeView.expandPath(treeView.getSelectionPath());
        update();
    }

    @Override
    public ClassyTreeItem getSelectedNode() {
        return (ClassyTreeItem) treeView.getLastSelectedPathComponent();
    }

    @Override
    public void deleteChild(ClassyTreeItem child){
        /*treeView.repaint();
        SwingUtilities.updateComponentTreeUI(treeView);*/
        ClassyTreeItem parent = (ClassyTreeItem) child.getParent();
        ((ClassyNodeComposite)parent.getClassyNode()).removeChild(child.getClassyNode());
        parent.remove(child);
        treeModel.nodeStructureChanged(parent);
        update();
    }
    public void update(){
        SwingUtilities.updateComponentTreeUI(treeView);
    }
    public void addPP(ClassyTreeItem parent){
        PackageFactory factory = new PackageFactory();
        ClassyTreeItem child = factory.createNode(parent);
        parent.add(child);
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(child.getClassyNode());
        treeView.expandPath(treeView.getSelectionPath());
        update();
    }

}

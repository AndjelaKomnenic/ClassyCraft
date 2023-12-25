package raf.dsw.tree;


import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;
import raf.dsw.classyrepository.implementation.Project;
import raf.dsw.classyrepository.implementation.ProjectExplorer;
import raf.dsw.classyrepository.factoryMethod.FactoryUtils;
import raf.dsw.classyrepository.factoryMethod.NodeFactory;
import raf.dsw.classyrepository.factoryMethod.PackageFactory;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.tree.view.ClassyTreeView;
import raf.dsw.view.MainFrame;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

@Getter
@Setter
public class ClassyTreeImplementation implements ClassyTree{
    private ClassyTreeView treeView;
    private DefaultTreeModel treeModel;
    private ClassyTreeItem root;
    @Override
    public ClassyTreeView generateTree(ProjectExplorer projectExplorer) {
        root = new ClassyTreeItem(projectExplorer);
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
        parent.getChildren().add(child);
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
        ClassyTreeItem parent  = (ClassyTreeItem) (child.getParent());
        if(parent != null) {
            ((ClassyNodeComposite) parent.getClassyNode()).removeChild(child.getClassyNode());
            parent.remove(child);
            treeModel.nodeStructureChanged(parent);
        }
        else
            System.out.println("nema parent");
        update();
    }
    @Override
    public void deleteNode(ClassyNode child){
        ClassyTreeItem parent  = findTreeItemByNode(child, root);
        if(parent == null)
        {
            return;
        }
        ClassyNodeComposite composite = (ClassyNodeComposite) parent.getClassyNode();
        composite.removeChild(child);
        treeModel.nodeStructureChanged(parent);
        update();
    }

    @Override
    public void loadProject(Project node) {
        ClassyTreeItem loadProject = new ClassyTreeItem(node);
        root.add(loadProject);

        ((ClassyNodeComposite) root.getClassyNode()).addChild(node);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    public ClassyTreeItem findTreeItemByNode(ClassyNode node, ClassyTreeItem parent){
        if(parent == null || !(parent.getClassyNode() instanceof ClassyNodeComposite))
            return null;
        ClassyNodeComposite composite = (ClassyNodeComposite) parent.getClassyNode();
        if(composite.getChildren().contains(node))
        {
            return parent;
        }
        for(ClassyTreeItem  childItem: parent.getChildren())
        {
            ClassyTreeItem found = findTreeItemByNode(node, childItem);
            if (found == null)
            {
                continue;
            }
            return found;
        }
        return null;
    }


    public void update(){
        SwingUtilities.updateComponentTreeUI(treeView);
    }
    public void addPP(ClassyTreeItem parent){
        PackageFactory factory = new PackageFactory();
        ClassyTreeItem child = factory.createNode(parent);
        parent.add(child);
        parent.getChildren().add(child);
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(child.getClassyNode());
        treeView.expandPath(treeView.getSelectionPath());
        update();
    }
    public void addChildToDiag(ClassyTreeItem p, ClassyNode c){
        ClassyTreeItem child = new ClassyTreeItem(c);
        p.add(child);
        p.getChildren().add(child);
        ((ClassyNodeComposite) p.getClassyNode()).addChild(child.getClassyNode());
        treeModel.nodeStructureChanged(p);
        treeView.expandPath(new TreePath(p.getPath()));
        //System.out.println("a getParent(): " + child.getParent());
        update();
    }
    public ClassyTreeItem getRoot(){
        return root;
    }
}
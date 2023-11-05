package raf.dsw.tree;

import raf.dsw.composite.ProjectExplorer;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.tree.view.ClassyTreeView;

public interface ClassyTree {
    ClassyTreeView generateTree(ProjectExplorer projectExplorer);
    void addChild(ClassyTreeItem parent);
    ClassyTreeItem getSelectedNode();

}

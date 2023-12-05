package raf.dsw.tree;

import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;
import raf.dsw.classyrepository.implementation.ProjectExplorer;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.tree.view.ClassyTreeView;

public interface ClassyTree {
    ClassyTreeView generateTree(ProjectExplorer projectExplorer);
    void addChild(ClassyTreeItem parent);
    void addChildToDiag(ClassyNodeComposite p, ClassyNode c);
    ClassyTreeItem getSelectedNode();
    void deleteChild(ClassyTreeItem child);
    void update();
    void addPP(ClassyTreeItem parent);
}

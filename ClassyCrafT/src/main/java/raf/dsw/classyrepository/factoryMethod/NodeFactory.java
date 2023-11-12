package raf.dsw.classyrepository.factoryMethod;

import raf.dsw.tree.model.ClassyTreeItem;

public abstract class NodeFactory {
    public abstract ClassyTreeItem createNode(ClassyTreeItem parent);
}

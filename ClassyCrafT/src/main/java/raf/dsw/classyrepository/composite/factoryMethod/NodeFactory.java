package raf.dsw.classyrepository.composite.factoryMethod;

import raf.dsw.tree.model.ClassyTreeItem;

public abstract class NodeFactory {
    public abstract ClassyTreeItem createNode(ClassyTreeItem parent);
}

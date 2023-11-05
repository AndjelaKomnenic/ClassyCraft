package raf.dsw.tree.model;

import raf.dsw.composite.ClassyNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class ClassyTreeItem extends DefaultMutableTreeNode {
    private ClassyNode classyNode;
    public ClassyTreeItem(ClassyNode classyNode){
        this.classyNode = classyNode;
    }
    @Override
    public String toString(){
        return classyNode.getName();
    }
    public void setName(String name){
        classyNode.setName(name);
    }
    public ClassyNode getClassyNode(){
        return this.classyNode;
    }
}

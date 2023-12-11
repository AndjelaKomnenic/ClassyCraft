package raf.dsw.tree.model;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyNode;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ClassyTreeItem extends DefaultMutableTreeNode {
    private ClassyNode classyNode;
    private List<ClassyTreeItem> children = new ArrayList<>();
    public ClassyTreeItem(ClassyNode classyNode){
        this.classyNode = classyNode;
    }
    @Override
    public String toString(){
        return classyNode.getName();
    }
}

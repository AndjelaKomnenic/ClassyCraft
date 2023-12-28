package raf.dsw.tree.model;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;

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
        if(classyNode instanceof ClassyNodeComposite)
        {
            ClassyNodeComposite nodeComposite = (ClassyNodeComposite) classyNode;
            for (ClassyNode child : nodeComposite.getChildren())
            {
                ClassyTreeItem childTreeItem = new ClassyTreeItem(child);
                this.add(childTreeItem);
                children.add(childTreeItem);
            }
        }
    }

    public ClassyTreeItem findClassyTreeItem(ClassyNode targetNode) {
        if (this.getClassyNode().getName().equalsIgnoreCase(targetNode.getName())) {
            return this;
        } else {
            for (ClassyTreeItem child : this.getChildren()) {
                ClassyTreeItem result = child.findClassyTreeItem(targetNode);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return classyNode.getName();
    }
}
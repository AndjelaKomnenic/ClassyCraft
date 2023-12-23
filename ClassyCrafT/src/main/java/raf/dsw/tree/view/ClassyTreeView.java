package raf.dsw.tree.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.observer.ISubscriber;
import raf.dsw.tree.controller.ClassyTreeCellEditor;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

@Getter
@Setter
public class ClassyTreeView extends JTree implements ISubscriber {
    private ClassyTreeCellEditor ourTreeEditor;
    public ClassyTreeView(DefaultTreeModel defaultTreeModel){
        setModel(defaultTreeModel);
        ClassyTreeCellRenderer classyTreeCellRenderer = new ClassyTreeCellRenderer();
        ourTreeEditor = new ClassyTreeCellEditor(this, classyTreeCellRenderer);
        setCellEditor(new ClassyTreeCellEditor(this, classyTreeCellRenderer));
        setCellRenderer(classyTreeCellRenderer);
        setEditable(true);

    }
    @Override
    public void update(Object notification) {
        SwingUtilities.updateComponentTreeUI(this);
    }
}

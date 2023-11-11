package raf.dsw.tree.view;

import raf.dsw.observer.ISubscriber;
import raf.dsw.tree.controller.ClassyTreeCellEditor;
import raf.dsw.tree.controller.ClassyTreeSelectionListener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class ClassyTreeView extends JTree implements ISubscriber {
    public ClassyTreeView(DefaultTreeModel defaultTreeModel){
        setModel(defaultTreeModel);
        ClassyTreeCellRenderer ruTreeCellRenderer = new ClassyTreeCellRenderer();
        addTreeSelectionListener(new ClassyTreeSelectionListener());
        setCellEditor(new ClassyTreeCellEditor(this, ruTreeCellRenderer));
        setCellRenderer(ruTreeCellRenderer);
        setEditable(true);

    }
    @Override
    public void update(Object notification) {
        SwingUtilities.updateComponentTreeUI(this);
    }
}

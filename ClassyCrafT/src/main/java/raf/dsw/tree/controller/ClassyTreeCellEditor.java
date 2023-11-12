package raf.dsw.tree.controller;

import raf.dsw.classyrepository.composite.ClassyNodeComposite;
import raf.dsw.core.ApplicationFramework;
import raf.dsw.message.PossibleErrors;
import raf.dsw.tree.model.ClassyTreeItem;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class ClassyTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {
    private Object clickedOn =null;
    private JTextField edit=null;

    public ClassyTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
        super(arg0, arg1);
    }
    @Override
    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {
        super.getTreeCellEditorComponent(arg0,arg1,arg2,arg3,arg4,arg5);
        clickedOn =arg1;
        edit=new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
    }
    public boolean isCellEditable(EventObject arg0) {
        if (arg0 instanceof MouseEvent)
            if (((MouseEvent)arg0).getClickCount()==3){
                return true;
            }
        return false;
    }


    public void actionPerformed(ActionEvent e){

        if (!(clickedOn instanceof ClassyTreeItem))
            return;

        String newName = e.getActionCommand();
        if(((ClassyNodeComposite)((ClassyTreeItem) clickedOn).getClassyNode().getParent()).cotainsSameNameComponent(newName)) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.EXISTS_SAME_NAME_COMPONENT); // DODATI ERROR NIJE ISPISANO ZA NJEGA NIS
            return;
        }
        ((ClassyTreeItem) clickedOn).getClassyNode().setName(e.getActionCommand());
    }

}

package raf.dsw.controller.topbar;

import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;
import raf.dsw.classyrepository.implementation.ProjectExplorer;
import raf.dsw.controller.AbstractClassyAction;
import raf.dsw.core.ApplicationFramework;
import raf.dsw.message.PossibleErrors;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteNodeAction extends AbstractClassyAction {
    public DeleteNodeAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/remove.png"));
        putValue(NAME, "RemoveNode");
        putValue(SHORT_DESCRIPTION, "RemoveNode");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selectedWrapper = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selectedWrapper == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.NODE_NOT_SELECTED);
            return;
        }

        ClassyNode selected = selectedWrapper.getClassyNode();
        if(selected instanceof ProjectExplorer){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.CANNOT_DELETE_PROJECTEXPLORER);
            return;
        }

        if(selected.getParent() != null)
        {
            ((ClassyNodeComposite)selected.getParent()).removeChild(selected);
        }
        MainFrame.getInstance().getClassyTree().deleteChild(MainFrame.getInstance().getClassyTree().getSelectedNode());
    }
}
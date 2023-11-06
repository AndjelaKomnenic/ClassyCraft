package raf.dsw.message;

import raf.dsw.observer.CSubject;

import java.sql.Timestamp;

public class MessageGeneratorImplementation extends CSubject implements MessageGenerator{
    @Override
    public void createMessage(PossibleErrors possibleErrors) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Message message = null;

        switch (possibleErrors){
            case NODE_CANNOT_BE_DELETED:
                message = new Message("Node cannot be deleted", "ERROR", timestamp);
                break;
            //todo
            case CANT_ADD_CHILD:
                message = new Message("Cannot add a child to the selected node", "ERROR", timestamp);
                break;
            case NAME_ALREADY_EXISTS:
                message = new Message("Node with this name already exists", "ERROR", timestamp);
                break;
            case NO_NODE_SELECTED_FOR_ADD_CHILD:
                message = new Message("No node was selected for adding child", "ERROR", timestamp);
                break;
            case PP_ACTION_FOR_NOTPP:
                message = new Message("Project as a child of a project can only be executed if project is selected", "WARNING", timestamp);
                break;
            case AUTHOR_ACTION_FOR_NOT_PROJECT:
                message = new Message("Author can only be set up for a project", "WARNING", timestamp);
                break;
        }

        notifySubscriber(message);
    }
}
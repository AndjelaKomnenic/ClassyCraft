package raf.dsw.message;

import raf.dsw.observer.CPublisher;

import java.sql.Timestamp;

public class MessageGeneratorImplementation extends CPublisher implements MessageGenerator{
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
            //
            case NODE_NOT_SELECTED:
                message = new Message("You did not select any component", "INFORMATION", timestamp);
                break;
            case NAME_CANNOT_BE_EMPTY:
                message = new Message("Name cannot be empty", "ERROR", timestamp);
                break;
            case EXISTS_SAME_NAME_COMPONENT:
                message = new Message("Cannot have same name on the same level", "ERROR", timestamp);
                break;
            case CANNOT_DELETE_PROJECTEXPLORER:
                message = new Message("Cannot delete ProjectExplorer", "ERROR", timestamp);
                break;
            case TYPE_OF_IC_NOT_SELECTED:
                message = new Message("Type of interClass was not selected", "ERROR", timestamp);
                break;
            case NO_NAME_FOR_DIAGRAM_ELEMENT:
                message = new Message("Name cannot be null", "ERROR", timestamp);
                break;
            case TYPE_OF_CON_NOT_SELECTED:
                message = new Message("Type of connection not selected", "ERROR", timestamp);
                break;
            case WRONG_SELECTION_FOR_DUPLICATE:
                message = new Message("It is only possible to duplicate one InterClass item", "WARNING", timestamp);
                break;
            case OVERLAP:
                message = new Message("Overlap", "ERROR", timestamp);
                break;
        }

        notifySubscriber(message);
    }
}
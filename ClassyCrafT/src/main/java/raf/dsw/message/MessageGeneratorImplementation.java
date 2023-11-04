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
        }

        notifySubscriber(message);
    }
}

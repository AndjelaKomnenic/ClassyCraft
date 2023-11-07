package raf.dsw.message;

import raf.dsw.observer.IPublisher;

public interface MessageGenerator extends IPublisher {
    void createMessage(PossibleErrors possibleErrors);
}
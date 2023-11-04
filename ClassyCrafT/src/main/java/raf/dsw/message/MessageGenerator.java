package raf.dsw.message;

import raf.dsw.observer.ISubject;

public interface MessageGenerator extends ISubject {
    void createMessage(PossibleErrors possibleErrors);
}
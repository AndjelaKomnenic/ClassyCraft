package raf.dsw.observer;

// u stvr publisher
public interface ISubject {

    void addSubscriber(IObserver subscriber);
    void removeSubscriber(IObserver subscriber);
    void notifySubscriber(Object notification);
}
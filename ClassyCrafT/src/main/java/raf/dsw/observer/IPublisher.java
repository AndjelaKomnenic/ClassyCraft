package raf.dsw.observer;

// u stvr publisher
public interface IPublisher {

    void addSubscriber(ISubscriber subscriber);
    void removeSubscriber(ISubscriber subscriber);
    void notifySubscriber(Object notification);
}
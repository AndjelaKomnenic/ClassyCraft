package raf.dsw.factory;

import raf.dsw.observer.ISubscriber;

public interface Logger extends ISubscriber {
    void update(Object object);
}

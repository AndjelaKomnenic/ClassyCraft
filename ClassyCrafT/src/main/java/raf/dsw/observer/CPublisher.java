package raf.dsw.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CPublisher implements IPublisher {
    private List<ISubscriber> subscribers;

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        if (subscriber == null)
            return;
        if (subscribers == null)
            subscribers = new ArrayList<>();
        if (subscribers.contains(subscriber))
            return;
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        /*if (subscribers == null || subscribers.isEmpty() || !subscribers.contains(subscriber))
            return;
        subscribers.remove(subscriber);*/

        subscribers = subscribers.stream()
                .filter(s -> !Objects.equals(s, subscriber))
                .collect(Collectors.toList());
    }

    @Override
    public void notifySubscriber(Object notification) {
        if (notification == null || subscribers == null || subscribers.isEmpty())
            return;

        subscribers.forEach(s -> s.update(notification));
    }
}
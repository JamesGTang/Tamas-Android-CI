package tamas.ecse321.ca.tamas.util;

import java.util.HashSet;
import java.util.Set;

public class Observer {

    public interface ObserverThread {
        void onObservableChanged();
    }

    // this is the object we will be synchronizing on ("the monitor")
    private final Object MONITOR = new Object();

    private Set<Observer> mObservers;

    public void registerObserver(Observer observer) {
        if (observer == null) return;

        synchronized(MONITOR) {
            if (mObservers == null) {
                mObservers = new HashSet<>(1);
            }
            if (mObservers.add(observer) && mObservers.size() == 1) {
                 // some initialization when first observer added
            }
        }
    }

    public void unregisterObserver(Observer observer) {
        if (observer == null) return;

        synchronized(MONITOR) {
            if (mObservers != null && mObservers.remove(observer) && mObservers.isEmpty()) {
                 // some cleanup when last observer removed
            }
        }
    }

    private void notifyObservers() {
        Set<Observer> observersCopy;

        synchronized(MONITOR) {
            if (mObservers == null) return;
            observersCopy = new HashSet<>(mObservers);
        }

        for (Observer observer : observersCopy) {

        }
    }
}
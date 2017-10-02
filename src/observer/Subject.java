package observer;

import java.util.ArrayList;
import java.util.List;
import interfaces.*;

import javax.swing.text.StyledDocument;

public class Subject {

    private List<Observable> observers;

    public Subject() {
        observers = new ArrayList();
    }

    public void register(Observable observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void unregister(Observable observer) {
        observers.remove(observer);
    }

    public void notify(String message, int offset, int length) {
        for(Observable observer: observers) {
            observer.update(message, offset, length);
        }
    }
}

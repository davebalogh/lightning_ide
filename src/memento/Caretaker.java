package memento;
import exceptions.MementoNotFoundException;

import java.util.*;

public class Caretaker {

    private ArrayList<Memento> savedStates = new ArrayList<Memento>();
    private int currentStateNumber = -1;

    public  int getCurrentStateNumber() {
        return currentStateNumber;
    }

    public void addMemento(Memento m) {
        currentStateNumber++;
        if(currentStateNumber != savedStates.size()){
            int savedStatesSizeAux = savedStates.size();
            for(int x = savedStatesSizeAux-1; x>=currentStateNumber-1; x--){
                savedStates.remove(x);
            }

            currentStateNumber = currentStateNumber - 1;
        }
        savedStates.add(m);
    }

    private Memento getMemento(int index) {
        return savedStates.get(index);
    }

    public Memento getLastStageAddedFromMemento() {
        Memento savedState = savedStates.get(savedStates.size()-1);

        return savedState;
    }

    public Memento getLastStageFromMemento() {
        Memento savedState = new Memento("");
        if((currentStateNumber - 1) >= 0){
            currentStateNumber = currentStateNumber - 1;
            savedState = savedStates.get(currentStateNumber);
        }

        return savedState;
    }

    public Memento getNextStageFromMemento() throws MementoNotFoundException {
        Memento savedState = null;
        if(currentStateNumber >= 0 && (currentStateNumber + 1) < savedStates.size()){
            currentStateNumber = currentStateNumber + 1;
            savedState = savedStates.get(currentStateNumber);
        }
        else{
            throw new MementoNotFoundException("MementoNotFoundException");
        }

        return savedState;
    }
}

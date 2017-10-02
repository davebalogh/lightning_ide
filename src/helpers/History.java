package helpers;
import memento.*;
import exceptions.*;

public class History {

    private Caretaker caretaker = new Caretaker();
    private Originator originator = new Originator();

    public History(){
        this("");
    }

    public History(String originalState){
        originator.set(originalState);
        caretaker.addMemento( originator.saveToMemento() );
    }

    public void save(String newStage){
        originator.set(newStage);
        caretaker.addMemento( originator.saveToMemento() );
    }

    public String getUndo(){
        originator.restoreFromMemento( caretaker.getLastStageFromMemento() );
        return originator.getState();
    }

    public String getRedo() throws MementoNotFoundException{
        Memento nextStage =  caretaker.getNextStageFromMemento();
        originator.restoreFromMemento( nextStage );

        return originator.getState();
    }
}

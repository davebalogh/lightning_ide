package helpers;
import memento.*;
import exceptions.*;

public class History {

    private Caretaker caretaker = new Caretaker();
    private Originator originator = new Originator();
    private boolean isRunning;

    public History(){
        this("");
    }

    public boolean getIsRunning(){
        if(isRunning){
            isRunning = false;
            return true;
        }
        else{
            return false;
        }
    }

    public void setIsRunning(boolean newIsRunning){
        isRunning = newIsRunning;
    }

    public History(String originalState){
        isRunning = false;
        originator.set(originalState);
        caretaker.addMemento( originator.saveToMemento() );
    }

    public void save(String newStage){
        isRunning = false;
        originator.set(newStage);
        caretaker.addMemento( originator.saveToMemento() );
    }

    public String getUndo(){
        isRunning = true;
        originator.restoreFromMemento( caretaker.getLastStageFromMemento() );
        return originator.getState();
    }

    public String getRedo() throws MementoNotFoundException{
        isRunning = true;
        Memento nextStage =  caretaker.getNextStageFromMemento();
        originator.restoreFromMemento( nextStage );

        return originator.getState();
    }
}

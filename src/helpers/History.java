package helpers;
import memento.*;
import exceptions.*;

public class History {
    public enum LastAction {
        ADD, UNDO, REDO, NONE
    }
    private Caretaker caretaker = new Caretaker();
    private Originator originator = new Originator();
    private boolean isRunning;
    private LastAction lastAction;

    public History(){
        this("");
    }

    public LastAction getLastAction() {
        return lastAction;
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
        lastAction = LastAction.NONE;
    }

    public String getLastStageAdded(){
        return caretaker.getLastStageAddedFromMemento().getSavedState();
    }

    public void save(String newStage){
        isRunning = false;
        originator.set(newStage);
        caretaker.addMemento( originator.saveToMemento() );
        lastAction = LastAction.ADD;
    }

    public String getUndo(){
        isRunning = true;
        originator.restoreFromMemento( caretaker.getLastStageFromMemento() );
        lastAction = LastAction.UNDO;
        return originator.getState();
    }

    public String getRedo() throws MementoNotFoundException{
        isRunning = true;
        Memento nextStage =  caretaker.getNextStageFromMemento();
        originator.restoreFromMemento( nextStage );
        lastAction = LastAction.REDO;
        return originator.getState();
    }
}

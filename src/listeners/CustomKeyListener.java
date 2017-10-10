package listeners;

import exceptions.MementoNotFoundException;
import helpers.CustomJTextPane;
import helpers.History;
import memento.Memento;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CustomKeyListener implements KeyListener {

    private CustomJTextPane textPane;
    private History history;

    public CustomKeyListener(CustomJTextPane newTextPane, String originalState){
        textPane = newTextPane;
        textPane.addKeyListener(this);
        history = new History(originalState);
    }

    public CustomKeyListener(CustomJTextPane newTextPane){
        this(newTextPane, "");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        textPane.setWasEdited(true);

        if ((e.getKeyCode() == KeyEvent.VK_Z) && ((e.getModifiers() & KeyEvent.CTRL_MASK ) != 0)) {
            textPane.setText(history.getUndo());
        }
        else if ((e.getKeyCode() == KeyEvent.VK_Y) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {

            try {
                textPane.setText(history.getRedo());
            } catch (MementoNotFoundException e1) {
                e1.printStackTrace();
            }

        }else if(e.getKeyChar() != '\uFFFF'){
            history.save(textPane.getText() + e.getKeyChar());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

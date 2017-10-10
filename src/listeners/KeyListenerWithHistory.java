package listeners;

import exceptions.MementoNotFoundException;
import helpers.CustomJTextPane;
import helpers.History;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerWithHistory implements KeyListener {

    private CustomJTextPane textPane;
    private History history;
    private int caretPosition;
    public KeyListenerWithHistory(CustomJTextPane newTextPane, History savedHistory){
        textPane = newTextPane;
        history = savedHistory;
        caretPosition = 0;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        textPane.setWasEdited(true);

        if ((e.getKeyCode() == KeyEvent.VK_Z) && ((e.getModifiers() & KeyEvent.CTRL_MASK ) != 0)) {
            textPane.setText(history.getUndo());

            textPane.setCaretPosition(caretPosition+1);
            caretPosition--;
        }
        else if ((e.getKeyCode() == KeyEvent.VK_Y) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {

            try {
                textPane.setText(history.getRedo());
                caretPosition++;
                textPane.setCaretPosition(caretPosition+2);

            } catch (MementoNotFoundException e1) {
                e1.printStackTrace();
            }

        }else if(e.getKeyChar() != '\uFFFF'){
            int keyLocation = textPane.getCaretPosition();
            caretPosition = keyLocation - 1;

            String allText = textPane.getText();
            allText = new StringBuilder(allText).insert(keyLocation, e.getKeyChar()).toString() ;
            history.save(allText);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

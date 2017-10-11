package listeners;

import exceptions.FileErrorException;
import exceptions.MementoNotFoundException;
import exceptions.SaveFileException;
import components.CustomJTextPane;
import helpers.History;
import components.JScrollPaneDocument;

import javax.swing.*;
import java.awt.*;
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

        if ((e.getKeyCode() == KeyEvent.VK_Z) && (((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) || ((e.getModifiers() & KeyEvent.VK_META) != 0)) ) {

            if(history.getLastAction() == History.LastAction.ADD){
                String textToSave = textPane.getText();
                history.save(textToSave);
            }

            textPane.setText(history.getUndo());

            textPane.setCaretPosition(caretPosition+1);
            caretPosition--;
        }
        else if ((e.getKeyCode() == KeyEvent.VK_Y) && (((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) || ((e.getModifiers() & KeyEvent.VK_META) != 0)) ) {

            try {
                textPane.setText(history.getRedo());
                caretPosition++;
                textPane.setCaretPosition(caretPosition+2);

            } catch (MementoNotFoundException e1) {
                System.out.println(e1.getMessage());
            }

        }else if ((e.getKeyCode() == KeyEvent.VK_S) && (((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) || ((e.getModifiers() & KeyEvent.VK_META) != 0)) ) {
            Container auxContainer = textPane.getParent().getParent().getParent();
            if(auxContainer instanceof JScrollPaneDocument){
                JScrollPaneDocument jScrollPaneDocument = (JScrollPaneDocument)auxContainer;
                try {
                    jScrollPaneDocument.saveAndCloseFile();
                } catch (SaveFileException e1) {
                    JOptionPane.showMessageDialog(null, "Error saving the file");
                } catch (FileErrorException e1) {
                    JOptionPane.showMessageDialog(null, "Error closing the file");
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Error. Try again later");
            }

        }else if(e.getKeyChar() != '\uFFFF'){
            int keyLocation = textPane.getCaretPosition();
            caretPosition = keyLocation - 1;
            String textToSave = textPane.getText();
            history.save(textToSave);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

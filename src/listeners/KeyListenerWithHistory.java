package listeners;

import components.JTabbedPaneCustom;
import exceptions.FileErrorException;
import exceptions.MementoNotFoundException;
import exceptions.NotOpenDocumentException;
import exceptions.SaveFileException;
import components.JTextPaneCustom;
import helpers.DocumentManager;
import helpers.History;
import components.JScrollPaneCustom;
import interfaces.Documentable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerWithHistory implements KeyListener {

    private JTextPaneCustom textPane;
    private History history;
    private int caretPosition;
    private DocumentManager documentManager;

    public KeyListenerWithHistory(JTextPaneCustom newTextPane, History savedHistory, DocumentManager instanceOfDocumentManager){
        textPane = newTextPane;
        history = savedHistory;
        documentManager = instanceOfDocumentManager;
        caretPosition = 0;
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_Z) && (((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) || ((e.getModifiers() & KeyEvent.VK_META) != 0)) ) {

            if(history.getLastAction() == History.LastAction.ADD){
                String textToSave = textPane.getText();
                history.save(textToSave);
            }

            textPane.setText(history.getUndo());

            try {
                textPane.setCaretPosition(caretPosition+1);
                caretPosition--;
            }catch (IllegalArgumentException ex){
                System.out.println("IllegalArgumentException");
            }

        }
        else if ((e.getKeyCode() == KeyEvent.VK_Y) && (((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) || ((e.getModifiers() & KeyEvent.VK_META) != 0)) ) {

            try {
                textPane.setText(history.getRedo());
                caretPosition++;
                textPane.setCaretPosition(caretPosition+2);

            } catch (MementoNotFoundException e1) {
                System.out.println(e1.getMessage());
            }catch (IllegalArgumentException ex){
                System.out.println("IllegalArgumentException");
            }

        }else if ((e.getKeyCode() == KeyEvent.VK_S) && (((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) || ((e.getModifiers() & KeyEvent.VK_META) != 0)) ) {
            Container auxContainer = textPane.getParent().getParent().getParent();
            if(auxContainer instanceof JScrollPaneCustom){
                JScrollPaneCustom jScrollPaneDocument = (JScrollPaneCustom)auxContainer;
                try {
                    jScrollPaneDocument.getFile().setText(textPane.getText());
                    documentManager.saveDocument(jScrollPaneDocument.getFile());
                    textPane.setWasEdited(false);
                    textPane.setSelectedTabTitle(jScrollPaneDocument.getFile().getName());
                } catch (NotOpenDocumentException e1) {
                    e1.printStackTrace();
                }

            }
            else {
                JOptionPane.showMessageDialog(null, "Error. Try again later");
            }

        }else if ((e.getKeyCode() == KeyEvent.VK_O) && (((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) || ((e.getModifiers() & KeyEvent.VK_META) != 0)) ) {
            documentManager.openDocumentAndAddToJTabbedPane();

        }else if ((e.getKeyCode() == KeyEvent.VK_T) && (((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) || ((e.getModifiers() & KeyEvent.VK_META) != 0)) ) {
            Container auxContainer = textPane.getParent().getParent().getParent().getParent();
            Documentable newDocument = documentManager.createEmptyDocumentAndNewTab();
            documentManager.getTabbedPane().addTabFromFile(newDocument);
            documentManager.getTabbedPane().getjScrollPaneCustom().get(documentManager.getTabbedPane().getjScrollPaneCustom().size()-1).setIsNewDocument(true);

        }else if(e.getKeyChar() != '\uFFFF'){
            int keyLocation = textPane.getCaretPosition();
            caretPosition = keyLocation - 1;
            String textToSave = textPane.getText();
            history.save(textToSave);
            textPane.setWasEdited(true);

            Container auxContainer = textPane.getParent().getParent().getParent();
            if(auxContainer instanceof JScrollPaneCustom){
                JScrollPaneCustom jScrollPaneDocument = (JScrollPaneCustom)auxContainer;

                try {
                    jScrollPaneDocument.getFile().setText(textToSave);
                } catch (NotOpenDocumentException e1) {
                    e1.printStackTrace();
                }
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

package listeners;

import components.JTabbedPaneCustom;
import components.JTextPaneCustom;
import exceptions.OpenFileException;
import helpers.FileManager;
import helpers.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerForProgram  implements KeyListener {

    private JTabbedPaneCustom jTabbedPaneCustom;

    public KeyListenerForProgram(JTabbedPaneCustom newTextPane){
        jTabbedPaneCustom = newTextPane;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_O) && (((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) || ((e.getModifiers() & KeyEvent.VK_META) != 0)) ) {
            try {
                FileManager.openFileAndAddToJTabbedPane(jTabbedPaneCustom);
            } catch (OpenFileException e1) {
                Messages.showError("Error opening file. Try again later.");
            }
        }else if ((e.getKeyCode() == KeyEvent.VK_T) && (((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) || ((e.getModifiers() & KeyEvent.VK_META) != 0)) ) {
            jTabbedPaneCustom.createNewEmptyTab();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

package listeners;

import helpers.DocumentManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerForProgram  implements KeyListener {
    private DocumentManager documentManager;

    public KeyListenerForProgram(DocumentManager instanceOfDocumentManager){
        documentManager = instanceOfDocumentManager;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_O) && (((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) || ((e.getModifiers() & KeyEvent.VK_META) != 0)) ) {
            documentManager.openDocumentAndAddToJTabbedPane();
        }else if ((e.getKeyCode() == KeyEvent.VK_T) && (((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) || ((e.getModifiers() & KeyEvent.VK_META) != 0)) ) {
            documentManager.createEmptyDocumentAndNewTab();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

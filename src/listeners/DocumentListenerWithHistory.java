package listeners;

import helpers.CustomJTextPane;
import helpers.History;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DocumentListenerWithHistory implements DocumentListener {
    private CustomJTextPane textPane;
    private History history;

    public DocumentListenerWithHistory(CustomJTextPane newTextPane, History savedHistory){
        textPane = newTextPane;
        history = savedHistory;
    }

    public void insertUpdate(DocumentEvent e) {

    }

    public void removeUpdate(DocumentEvent e) {

    }

    public void changedUpdate(DocumentEvent e) {
    }
}

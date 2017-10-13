package listeners;

import components.JTextPaneCustom;
import helpers.History;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DocumentListenerWithHistory implements DocumentListener {
    private JTextPaneCustom textPane;
    private History history;

    public DocumentListenerWithHistory(JTextPaneCustom newTextPane, History savedHistory){
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

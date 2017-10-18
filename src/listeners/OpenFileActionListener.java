package listeners;

import components.JTabbedPaneCustom;
import exceptions.OpenFileException;
import helpers.DocumentManager;
import helpers.Messages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenFileActionListener implements ActionListener {
    private JTabbedPaneCustom tabbedPane;
    private DocumentManager documentManager;

    public OpenFileActionListener(JTabbedPaneCustom instanceOfTabbedPane){
        tabbedPane = instanceOfTabbedPane;
        documentManager = tabbedPane.getDocumentManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        documentManager.openDocumentAndAddToJTabbedPane();
    }
}

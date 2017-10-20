package listeners;

import components.JTabbedPaneCustom;
import helpers.DocumentManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenFileActionListener implements ActionListener {
    private DocumentManager documentManager;

    public OpenFileActionListener(DocumentManager documentManager){
        this.documentManager = documentManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        documentManager.openDocumentAndAddToJTabbedPane();
    }
}

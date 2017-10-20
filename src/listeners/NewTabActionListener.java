package listeners;

import components.JTabbedPaneCustom;
import helpers.DocumentManager;
import interfaces.Documentable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewTabActionListener implements ActionListener {
    private DocumentManager documentManager;

    public NewTabActionListener(DocumentManager documentManager){
        this.documentManager = documentManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Documentable newDocument = documentManager.createEmptyDocumentAndNewTab();
        documentManager.getTabbedPane().addTabFromFile(newDocument);
        documentManager.getTabbedPane().getjScrollPaneCustom().get(documentManager.getTabbedPane().getjScrollPaneCustom().size()-1).setIsNewDocument(true);
    }
}

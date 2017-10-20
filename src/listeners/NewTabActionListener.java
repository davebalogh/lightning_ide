package listeners;

import components.JTabbedPaneCustom;
import helpers.DocumentManager;
import interfaces.Documentable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewTabActionListener implements ActionListener {
    private JTabbedPaneCustom tabbedPane;
    private DocumentManager documentManager;

    public NewTabActionListener(JTabbedPaneCustom instanceOfTabbedPane){
        tabbedPane = instanceOfTabbedPane;
        documentManager = tabbedPane.getDocumentManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Documentable newDocument = documentManager.createEmptyDocumentAndNewTab();
        tabbedPane.addTabFromFile(newDocument);
        tabbedPane.getjScrollPaneCustom().get(tabbedPane.getjScrollPaneCustom().size()-1).setIsNewDocument(true);
    }
}

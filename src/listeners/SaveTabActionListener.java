package listeners;

import components.JTabbedPaneCustom;
import components.JScrollPaneCustom;
import helpers.DocumentManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveTabActionListener implements ActionListener {
    private JTabbedPaneCustom tabbedPane;
    private DocumentManager documentManager;

    public SaveTabActionListener(DocumentManager instanceOfDocumentManager) {
        documentManager = instanceOfDocumentManager;
        tabbedPane = documentManager.getTabbedPane();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component selectedComponent = tabbedPane.getSelectedComponent();
        if (selectedComponent instanceof JScrollPaneCustom) {
            JScrollPaneCustom selectedDocument = (JScrollPaneCustom) selectedComponent;
            documentManager.saveDocument(selectedDocument.getFile());
        }
    }
}

package listeners;

import components.JTabbedPaneCustom;
import exceptions.FileErrorException;
import exceptions.SaveFileException;
import components.JScrollPaneCustom;
import helpers.DocumentManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveTabActionListener implements ActionListener {
    private JTabbedPaneCustom tabbedPane;
    private DocumentManager documentManager;

    public SaveTabActionListener(JTabbedPaneCustom instanceOfTabbedPane, DocumentManager instanceOfDocumentManager) {
        tabbedPane = instanceOfTabbedPane;
        documentManager = instanceOfDocumentManager;
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

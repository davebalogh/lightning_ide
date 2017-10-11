package listeners;

import components.JTabbedPaneCustom;
import exceptions.FileErrorException;
import exceptions.SaveFileException;
import helpers.JScrollPaneDocument;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveTabActionListener implements ActionListener {
    private JTabbedPaneCustom tabbedPane;

    public SaveTabActionListener(JTabbedPaneCustom instanceOfTabbedPane) {
        tabbedPane = instanceOfTabbedPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Component selectedComponent = tabbedPane.getSelectedComponent();
        if (selectedComponent instanceof JScrollPaneDocument) {
            JScrollPaneDocument selectedDocument = (JScrollPaneDocument) selectedComponent;

            try {
                selectedDocument.saveAndCloseFile();
            } catch (SaveFileException e1) {
                JOptionPane.showMessageDialog(null, "Error saving the file");
            } catch (FileErrorException e1) {
                JOptionPane.showMessageDialog(null, "Error closing the file");
            }
        }

    }
}

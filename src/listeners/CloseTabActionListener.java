package listeners;

import components.JTabbedPaneCustom;
import exceptions.FileErrorException;
import exceptions.SaveFileException;
import components.JScrollPaneCustom;
import helpers.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseTabActionListener implements ActionListener {
    private JTabbedPaneCustom tabbedPane;

    public CloseTabActionListener(JTabbedPaneCustom instanceOfTabbedPane) {
        tabbedPane = instanceOfTabbedPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component selectedComponent = tabbedPane.getSelectedComponent();
        if (selectedComponent instanceof JScrollPaneCustom) {
            JScrollPaneCustom selectedTab = (JScrollPaneCustom) selectedComponent;

            if (selectedTab.getTextPane().getWasEdited() && !selectedTab.getIsNewDocument()) {
                String message = "Do you want to save changes?";
                int answer = JOptionPane.showConfirmDialog(null, message);
                if (answer == JOptionPane.NO_OPTION) {
                    tabbedPane.removeTabAt(tabbedPane.getSelectedIndex());
                } else if (answer == JOptionPane.YES_OPTION) {
                    try {
                        selectedTab.saveFileToDisk();
                        tabbedPane.removeTabAt(tabbedPane.getSelectedIndex());
                    } catch (SaveFileException e1) {
                        Messages.showError("Error saving the file");
                    } catch (FileErrorException e1) {
                        Messages.showError("Error closing the file");
                    }
                }
            } else {
                tabbedPane.removeTabAt(tabbedPane.getSelectedIndex());
            }
        }
    }
}

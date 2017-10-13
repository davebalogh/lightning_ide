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

            if (selectedTab.getTextPane().getWasEdited()) {
                String message = "Do you want to save changes?";
                int answer = JOptionPane.showConfirmDialog(null, message);
                if (answer == JOptionPane.NO_OPTION) {
                    if(tabbedPane.getjScrollPaneCustom(tabbedPane.getSelectedIndex()) != null){
                        if(selectedTab.getIsNewDocument()){
                            tabbedPane.removeCustomTabAt(tabbedPane.getSelectedIndex(), true);
                        }
                        else {
                            tabbedPane.removeCustomTabAt(tabbedPane.getSelectedIndex(), false);
                        }
                    }


                } else if (answer == JOptionPane.YES_OPTION) {
                    try {
                        selectedTab.saveFileToDisk(true);
                        tabbedPane.removeTabAt(tabbedPane.getSelectedIndex());
                    } catch (SaveFileException e1) {
                        Messages.showError("Error saving the file");
                    } catch (FileErrorException e1) {
                        Messages.showError("Error closing the file");
                    }
                }
            } else {
                if(selectedTab.getIsNewDocument()){
                    tabbedPane.removeCustomTabAt(tabbedPane.getSelectedIndex(), true);
                }
                else {
                    tabbedPane.removeCustomTabAt(tabbedPane.getSelectedIndex(), false);
                }
            }
        }
    }
}

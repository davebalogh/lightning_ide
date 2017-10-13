package listeners;

import components.JPanelForTab;
import components.JTabbedPaneCustom;
import exceptions.FileErrorException;
import exceptions.SaveFileException;
import components.JScrollPaneCustom;
import helpers.Messages;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
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
        Object source = e.getSource();

        Component selectedComponent = null;
        int selectedIndex = 0;

        if(source instanceof JMenuItem){
            selectedComponent = tabbedPane.getSelectedComponent();
            selectedIndex = tabbedPane.getSelectedIndex();
        }

        if(source instanceof JButton){
            JButton clickedButton = (JButton)source;

            Container jPanelTab = clickedButton.getParent();
            selectedIndex = tabbedPane.indexOfTab(((JPanelForTab)jPanelTab).getTitle());
            selectedComponent = tabbedPane.getComponentAt(selectedIndex);
        }


        if (selectedComponent != null && selectedComponent instanceof JScrollPaneCustom) {
            JScrollPaneCustom selectedTab = (JScrollPaneCustom) selectedComponent;

            if (selectedTab.getTextPane().getWasEdited()) {
                String message = "Do you want to save changes?";
                int answer = JOptionPane.showConfirmDialog(null, message);
                if (answer == JOptionPane.NO_OPTION) {
                    if(tabbedPane.getjScrollPaneCustom(selectedIndex) != null){
                        if(selectedTab.getIsNewDocument()){
                            tabbedPane.removeCustomTabAt(selectedIndex, true);
                        }
                        else {
                            tabbedPane.removeCustomTabAt(selectedIndex, false);
                        }
                    }


                } else if (answer == JOptionPane.YES_OPTION) {
                    try {
                        selectedTab.saveFileToDisk(true);
                        tabbedPane.removeTabAt(selectedIndex);
                    } catch (SaveFileException e1) {
                        Messages.showError("Error saving the file");
                    } catch (FileErrorException e1) {
                        Messages.showError("Error closing the file");
                    }
                }
            } else {
                if(selectedTab.getIsNewDocument()){
                    tabbedPane.removeCustomTabAt(selectedIndex, true);
                }
                else {
                    tabbedPane.removeCustomTabAt(selectedIndex, false);
                }
            }
        }
    }
}

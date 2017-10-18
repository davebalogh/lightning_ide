package listeners;

import components.JPanelForTab;
import components.JTabbedPaneCustom;
import exceptions.*;
import components.JScrollPaneCustom;
import helpers.DocumentManager;
import helpers.Messages;
import interfaces.Documentable;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseTabActionListener implements ActionListener {
    private JTabbedPaneCustom tabbedPane;
    private DocumentManager documentManager;

    public CloseTabActionListener(JTabbedPaneCustom instanceOfTabbedPane) {
        tabbedPane = instanceOfTabbedPane;
        documentManager = tabbedPane.getDocumentManager();
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

            try {
                if (selectedTab.getFile().getIsEdited()) {
                    boolean response = documentManager.getDocumentHashMap().get(selectedTab.getFile().getUniqueName()).closeModifiedDocument();

                    if (response) {
                        documentManager.deleteDocument(selectedTab.getFile());
                        tabbedPane.removeTabAt(selectedIndex);
                    }
                } else {
                    if(selectedTab.getFile().getIsNewDocument()){
                        documentManager.getDocumentHashMap().remove(selectedTab.getFile().getUniqueName());
                        tabbedPane.removeTabAt(selectedIndex);
                    }
                    else {
                        tabbedPane.removeTabAt(selectedIndex);
                    }
                }
            } catch (NotOpenDocumentException e1) {
                e1.printStackTrace();
            } catch (CloseDocumentException e1) {
                e1.printStackTrace();
            } catch (SaveDocumentException e1) {
                e1.printStackTrace();
            }
        }
    }
}

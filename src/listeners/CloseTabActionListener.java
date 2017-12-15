package listeners;

import components.JPanelForTab;
import components.JPanelWithTree;
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

    public CloseTabActionListener(DocumentManager documentManager) {
        this.documentManager = documentManager;
        tabbedPane = documentManager.getTabbedPane();
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
            //JPanelWithTree jPanelWithTree = (JPanelWithTree)selectedComponent;
            JScrollPaneCustom selectedTab = (JScrollPaneCustom) selectedComponent; //jPanelWithTree.getjScrollPaneCustom();
            String textModified = selectedTab.getTextPane().getText();
            boolean response = documentManager.closeDocumentAndTab(selectedTab.getFile(), textModified);

            if(response){
                tabbedPane.getjPanelForTabList().remove(tabbedPane.getjPanelForTab(selectedIndex));
                tabbedPane.remove(selectedIndex);
            }
        }
    }
}

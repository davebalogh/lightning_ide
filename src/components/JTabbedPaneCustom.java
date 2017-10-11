package components;

import helpers.CustomBasicTabbedPaneUI;
import helpers.JScrollPaneDocument;
import listeners.ChangeListenerForTabbedPane;

import javax.swing.*;
import java.awt.*;

public class JTabbedPaneCustom extends JTabbedPane {
    public JTabbedPaneCustom(){
        super();
        this.setBackground( new Color(49, 52, 64));
        this.setUI(new CustomBasicTabbedPaneUI(this));
        this.addChangeListener(new ChangeListenerForTabbedPane());
    }

    public void createNewEmptyTab(){
        JScrollPaneDocument jsPane = new JScrollPaneDocument();
        int tabCount = this.getTabCount() + 1;
        String tabName = "Tab-" + tabCount;
        jsPane.setName(tabName);
        this.addTab(tabName, jsPane);
        this.setSelectedIndex(tabCount-1);
        //scrollPaneList.add(jsPane);
        this.repaint();
    }
}

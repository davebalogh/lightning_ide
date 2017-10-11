package listeners;

import components.JTabbedPaneCustom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewTabActionListener implements ActionListener {
    private JTabbedPaneCustom tabbedPane;

    public NewTabActionListener(JTabbedPaneCustom instanceOfTabbedPane){
        tabbedPane = instanceOfTabbedPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tabbedPane.createNewEmptyTab();
    }
}

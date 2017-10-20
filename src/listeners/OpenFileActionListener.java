package listeners;

import components.JTabbedPaneCustom;
import helpers.DocumentManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenFileActionListener implements ActionListener {
    private JTabbedPaneCustom tabbedPane;
    private DocumentManager documentManager;

    public OpenFileActionListener(JTabbedPaneCustom instanceOfTabbedPane){
        tabbedPane = instanceOfTabbedPane;
        documentManager = tabbedPane.getDocumentManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        documentManager.openDocumentAndAddToJTabbedPane();
    }
}

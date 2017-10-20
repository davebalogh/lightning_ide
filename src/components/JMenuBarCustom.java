package components;

import helpers.DocumentManager;
import listeners.CloseTabActionListener;
import listeners.NewTabActionListener;
import listeners.OpenFileActionListener;
import listeners.SaveTabActionListener;

import javax.swing.*;

public class JMenuBarCustom extends JMenuBar {
    private JTabbedPaneCustom tabbedPane;
    private DocumentManager documentManager;

    public JMenuBarCustom(JTabbedPaneCustom instanceOfTabbedPane, DocumentManager instanceOfDocumentManager) {
        super();
        documentManager = instanceOfDocumentManager;
        tabbedPane = instanceOfTabbedPane;

        JMenu fileMenu = new JMenu("File");

        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(new OpenFileActionListener(tabbedPane));
        fileMenu.add(openItem);

        JMenuItem newItem = new JMenuItem("New");
        newItem.addActionListener(new NewTabActionListener(tabbedPane));
        fileMenu.add(newItem);

        JMenuItem closeTabItem = new JMenuItem("Close Tab");
        closeTabItem.addActionListener(new CloseTabActionListener(tabbedPane));
        fileMenu.add(closeTabItem);

        JMenuItem saveTabItem = new JMenuItem("Save Tab");
        saveTabItem.addActionListener(new SaveTabActionListener(documentManager));
        fileMenu.add(saveTabItem);

        this.add(fileMenu);
    }
}

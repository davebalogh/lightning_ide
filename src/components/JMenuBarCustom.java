package components;

import helpers.DocumentManager;
import listeners.CloseTabActionListener;
import listeners.NewTabActionListener;
import listeners.OpenFileActionListener;
import listeners.SaveTabActionListener;

import javax.swing.*;

public class JMenuBarCustom extends JMenuBar {
    private DocumentManager documentManager;

    public JMenuBarCustom(DocumentManager instanceOfDocumentManager) {
        super();
        documentManager = instanceOfDocumentManager;

        JMenu fileMenu = new JMenu("File");

        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(new OpenFileActionListener(documentManager));
        fileMenu.add(openItem);

        JMenuItem newItem = new JMenuItem("New");
        newItem.addActionListener(new NewTabActionListener(documentManager));
        fileMenu.add(newItem);

        JMenuItem closeTabItem = new JMenuItem("Close Tab");
        closeTabItem.addActionListener(new CloseTabActionListener(documentManager));
        fileMenu.add(closeTabItem);

        JMenuItem saveTabItem = new JMenuItem("Save Tab");
        saveTabItem.addActionListener(new SaveTabActionListener(documentManager));
        fileMenu.add(saveTabItem);

        this.add(fileMenu);
    }
}

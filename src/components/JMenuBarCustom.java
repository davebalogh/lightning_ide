package components;

import listeners.CloseTabActionListener;
import listeners.NewTabActionListener;
import listeners.OpenFileActionListener;
import listeners.SaveTabActionListener;

import javax.swing.*;

public class JMenuBarCustom extends JMenuBar {
    private JTabbedPaneCustom tabbedPane;
    public JMenuBarCustom(JTabbedPaneCustom instanceOfTabbedPane) {
        super();

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
        saveTabItem.addActionListener(new SaveTabActionListener(tabbedPane));
        fileMenu.add(saveTabItem);

        this.add(fileMenu);
    }
}

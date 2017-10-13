import java.awt.*;
import javax.swing.*;
import components.JMenuBarCustom;
import components.JTabbedPaneCustom;
import helpers.*;
import listeners.KeyListenerForProgram;

class LightningIDE extends JFrame{
    public LightningIDE() {
        super("Lightning IDE");

        Configuration.initializeSettings(this);

        JTabbedPaneCustom tabbedPane = new JTabbedPaneCustom();
        tabbedPane.createNewEmptyTab();

        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        setJMenuBar(new JMenuBarCustom(tabbedPane));
        setAlwaysOnTop(true);
        setAlwaysOnTop(false);

        addKeyListener(new KeyListenerForProgram(tabbedPane));
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setVisible(true);
    }
}

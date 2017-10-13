import java.awt.*;
import java.net.URISyntaxException;
import javax.swing.*;
import components.JMenuBarCustom;
import components.JTabbedPaneCustom;
import helpers.*;
import listeners.CloseWindowAdapter;
import listeners.KeyListenerForProgram;

class LightningIDE extends JFrame{
    JTabbedPaneCustom tabbedPane;

    public JTabbedPaneCustom getTabbedPane() {
        return tabbedPane;
    }

    public LightningIDE() {
        super("Lightning IDE");

        Configuration.initializeSettings(this);

        tabbedPane = new JTabbedPaneCustom();

        Configuration.loadOpenTabs(tabbedPane);

        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        this.addWindowListener(new CloseWindowAdapter(tabbedPane));

        setJMenuBar(new JMenuBarCustom(tabbedPane));
        setAlwaysOnTop(true);
        setAlwaysOnTop(false);

        addKeyListener(new KeyListenerForProgram(tabbedPane));
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        setVisible(true);
    }
}

import java.awt.*;
import javax.swing.*;
import components.JMenuBarCustom;
import components.JTabbedPaneCustom;
import helpers.*;

class LightningIDE extends JFrame{
    public LightningIDE() {
        super("Lightning IDE");

        Configurations.initializeSettings(this);

        JTabbedPaneCustom tabbedPane = new JTabbedPaneCustom();
        tabbedPane.createNewEmptyTab();

        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        setJMenuBar(new JMenuBarCustom(tabbedPane));

        setVisible(true);
    }
}

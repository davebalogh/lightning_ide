import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.apple.eawt.Application;
import com.sun.tools.javac.util.List;
import components.JMenuBarCustom;
import components.JTabbedPaneCustom;
import exceptions.FileErrorException;
import exceptions.SaveFileException;
import helpers.*;
import listeners.*;

class LightningIDE extends JFrame{

    JTabbedPaneCustom tabbedPane;

    public LightningIDE() {
        super("Lightning IDE");

        Configurations.initializeSettings(this);

        tabbedPane = new JTabbedPaneCustom();
        tabbedPane.createNewEmptyTab();

        getContentPane().add(tabbedPane, BorderLayout.CENTER);


        setJMenuBar(new JMenuBarCustom(tabbedPane));

        setVisible(true);
    }
}

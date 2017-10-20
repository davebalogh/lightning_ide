package listeners;

import components.JScrollPaneCustom;
import components.JTabbedPaneCustom;
import exceptions.*;
import helpers.Configuration;
import helpers.DocumentManager;
import helpers.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CloseWindowAdapter extends WindowAdapter {
    JTabbedPaneCustom currentTabbedPane;
    private DocumentManager documentManager;

    public CloseWindowAdapter(JTabbedPaneCustom instanceOfJTabbedPaneCustom, DocumentManager instanceOfDocumentManager){
        currentTabbedPane = instanceOfJTabbedPaneCustom;
        documentManager = instanceOfDocumentManager;
    }

    @Override
    public void windowClosing(WindowEvent we)
    {
        for (int tabIndex = 0; tabIndex < currentTabbedPane.getTabCount(); tabIndex++){
            Component currentTab = currentTabbedPane.getComponentAt(tabIndex);
            if(currentTab instanceof JScrollPaneCustom){
                JScrollPaneCustom jScrollPaneCustom = (JScrollPaneCustom)currentTab;
                jScrollPaneCustom.getFile().setSaveAs(true);
                jScrollPaneCustom.getFile().setText(jScrollPaneCustom.getTextPane().getText());
                documentManager.closeDocument(jScrollPaneCustom.getFile());
            }
        }

        System.exit(0);
    }
}

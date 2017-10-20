import java.awt.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.swing.*;

import components.JBottomPanel;
import components.JMenuBarCustom;
import components.JTabbedPaneCustom;
import helpers.*;
import interfaces.Documentable;
import listeners.CloseWindowAdapter;
import listeners.KeyListenerForProgram;

class LightningIDE extends JFrame{
    public LightningIDE() {
        super("Lightning IDE");

        Configuration.initializeSettings(this);
        PanelManager panelManager = new PanelManager();
        JTabbedPaneCustom tabbedPane = new JTabbedPaneCustom();
        JBottomPanel contentBottom = new JBottomPanel(panelManager);

        panelManager.setContentCenterPane(tabbedPane);
        panelManager.setContentBottomPane(contentBottom);

        DocumentManager documentManager = new DocumentManager(panelManager, new FileDocumentImp());

        ArrayList<Documentable> documentList = documentManager.loadOpenTabs();
        for(Documentable document: documentList){
            tabbedPane.addTabFromFile(document);
            tabbedPane.getLastJSCrollPaneAdded().setIsLoadedDocument(true);
            tabbedPane.getLastJSCrollPaneAdded().setIsNewDocument(true);
            tabbedPane.getLastJSCrollPaneAdded().getTextPane().setWasEdited(true);
        }

        if(documentList.size() == 0){
            Documentable newDocument = documentManager.createEmptyDocumentAndNewTab();
            tabbedPane.addTabFromFile(newDocument);
            tabbedPane.getjScrollPaneCustom().get(tabbedPane.getjScrollPaneCustom().size()-1).setIsNewDocument(true);
        }

        getContentPane().add(panelManager.getContentCenterPane(), BorderLayout.CENTER);
        getContentPane().add(panelManager.getContentBottomPane(), BorderLayout.SOUTH);

        this.addWindowListener(new CloseWindowAdapter(documentManager));

        setJMenuBar(new JMenuBarCustom(documentManager));
        setAlwaysOnTop(true);
        setAlwaysOnTop(false);

        addKeyListener(new KeyListenerForProgram(documentManager));
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        setVisible(true);
    }
}

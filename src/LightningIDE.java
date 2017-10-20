import java.awt.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.swing.*;
import components.JMenuBarCustom;
import components.JTabbedPaneCustom;
import helpers.*;
import interfaces.Documentable;
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

        DocumentManager documentManager = new DocumentManager(tabbedPane, new FileDocumentImp());
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

        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        this.addWindowListener(new CloseWindowAdapter(tabbedPane, documentManager));

        setJMenuBar(new JMenuBarCustom(tabbedPane, documentManager));
        setAlwaysOnTop(true);
        setAlwaysOnTop(false);

        addKeyListener(new KeyListenerForProgram(documentManager));
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        setVisible(true);
    }
}

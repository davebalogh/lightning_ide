package components;

import exceptions.FileErrorException;
import exceptions.NotOpenDocumentException;
import exceptions.OpenFileException;
import helpers.Configuration;
import helpers.DocumentManager;
import helpers.Messages;
import interfaces.Documentable;
import listeners.ChangeListenerForTabbedPane;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class JTabbedPaneCustom extends JTabbedPane {
    JScrollPaneCustom lastJSCrollPaneAdded;
    ArrayList<JPanelForTab> jPanelForTabList;

    public DocumentManager getDocumentManager() {
        return documentManager;
    }

    public void setDocumentManager(DocumentManager documentManager) {
        this.documentManager = documentManager;
    }

    DocumentManager documentManager;

    public ArrayList<JScrollPaneCustom> getjScrollPaneCustom() {
        return jScrollPaneCustom;
    }

    ArrayList<JScrollPaneCustom> jScrollPaneCustom;


    public void setCustomTabComponentAt(int index, Component newComponent){
        if(newComponent instanceof JPanelForTab){
            jPanelForTabList.add((JPanelForTab)newComponent);
        }
        this.setTabComponentAt(index, newComponent);
    }


    public JPanelForTab getjPanelForTab(int index){
        if(jPanelForTabList.size() > index){
            return jPanelForTabList.get(index);
        }

        return null;
    }

    public ArrayList<JPanelForTab> getjPanelForTabList(){
        return jPanelForTabList;
    }

    public JScrollPaneCustom getjScrollPaneCustom(int index){
        if(jScrollPaneCustom.size() > index){
            return jScrollPaneCustom.get(index);
        }

        return null;
    }

    public JScrollPaneCustom getLastJSCrollPaneAdded(){
        return lastJSCrollPaneAdded;
    }

    public JTabbedPaneCustom(){
        super();
        this.setBackground( new Color(49, 52, 64));
        this.setUI(new BasicTabbedPaneUICustom(this));
        this.addChangeListener(new ChangeListenerForTabbedPane());
        jPanelForTabList = new ArrayList<>();
        jScrollPaneCustom = new ArrayList<>();
    }


    public JTabAndPane addTabFromFile(Documentable openFile) throws NotOpenDocumentException{
        lastJSCrollPaneAdded = new JScrollPaneCustom(openFile);
        lastJSCrollPaneAdded.setDocumentManager(documentManager);
        lastJSCrollPaneAdded.initialize(openFile.getText());

        jScrollPaneCustom.add(lastJSCrollPaneAdded);
        int tabCount = this.getTabCount() + 1;
        String tabName = openFile.getName();
        this.addTab(tabName, lastJSCrollPaneAdded);
        int selectedIndex = tabCount-1;
        this.setSelectedIndex(selectedIndex);

        JPanelForTab newTab = new JPanelForTab(this, tabName);
        this.setCustomTabComponentAt(selectedIndex, newTab);

        return new JTabAndPane(lastJSCrollPaneAdded, newTab);
    }
}

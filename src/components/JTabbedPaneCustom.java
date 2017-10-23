package components;

import helpers.DocumentManager;
import interfaces.Documentable;
import listeners.ChangeListenerForTabbedPane;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class JTabbedPaneCustom extends JTabbedPane {
    JScrollPaneCustom lastJSCrollPaneAdded;
    ArrayList<JPanelForTab> jPanelForTabList;
    ArrayList<JScrollPaneCustom> jScrollPaneCustomList;
    DocumentManager documentManager;
    ArrayList<JScrollPaneCustom> jScrollPaneCustom;

    public ArrayList<JScrollPaneCustom> getjScrollPaneCustomList() {
        return jScrollPaneCustomList;
    }


    public DocumentManager getDocumentManager() {
        return documentManager;
    }

    public void setDocumentManager(DocumentManager documentManager) {
        this.documentManager = documentManager;
    }

    public ArrayList<JScrollPaneCustom> getjScrollPaneCustom() {
        return jScrollPaneCustom;
    }

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

    public JScrollPaneCustom getSelectedJSCrollPane(){
        Component selectedComponent = this.getSelectedComponent();
        JScrollPaneCustom selectedDocument = null;
        if (selectedComponent instanceof JScrollPaneCustom) {
            selectedDocument = (JScrollPaneCustom) selectedComponent;
        }
        return selectedDocument;
    }

    public JTabbedPaneCustom(){
        super();
        this.setBackground( new Color(49, 52, 64));
        this.setUI(new BasicTabbedPaneUICustom(this));
        this.addChangeListener(new ChangeListenerForTabbedPane());
        jPanelForTabList = new ArrayList<>();
        jScrollPaneCustomList = new ArrayList<>();
        jScrollPaneCustom = new ArrayList<>();
    }


    public JTabAndPane addTabFromFile(Documentable openFile){
        JPanelWithTree jPanelWithTree = new JPanelWithTree(documentManager, openFile);
        lastJSCrollPaneAdded = jPanelWithTree.getjScrollPaneCustom();
        jScrollPaneCustomList.add(lastJSCrollPaneAdded);

        jScrollPaneCustom.add(lastJSCrollPaneAdded);
        int tabCount = this.getTabCount() + 1;
        String tabName = openFile.getName();
        this.addTab(tabName, jPanelWithTree);
        int selectedIndex = tabCount-1;
        this.setSelectedIndex(selectedIndex);

        JPanelForTab newTab = new JPanelForTab(this, tabName);
        this.setCustomTabComponentAt(selectedIndex, newTab);


        return new JTabAndPane(lastJSCrollPaneAdded, newTab);
    }
}

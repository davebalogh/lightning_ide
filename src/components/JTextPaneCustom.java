package components;

import helpers.DocumentManager;
import helpers.History;
import listeners.KeyListenerWithHistory;
import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import java.awt.*;

public class JTextPaneCustom extends JTextPane {
    private boolean wasEdited = false;
    private History history;
    DocumentManager documentManager;

    public boolean getWasEdited(){
        return wasEdited;
    }
    public void setSelectedTabTitle(String newTitle){
        Container tabContainer = getParent().getParent().getParent().getParent();

        if(tabContainer instanceof JTabbedPaneCustom){
            JTabbedPaneCustom tabbedPane = (JTabbedPaneCustom)tabContainer;
            if(tabbedPane.getjPanelForTab(tabbedPane.getSelectedIndex()) != null){
                tabbedPane.getjPanelForTab(tabbedPane.getSelectedIndex()).setTitle(newTitle);
                tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), newTitle);
            }
        }

    }

    public void setWasEdited(boolean newWasEdited){

        Container tabContainer = getParent().getParent().getParent().getParent();//.getParent();

        if(tabContainer instanceof JTabbedPaneCustom){
            JTabbedPaneCustom tabbedPane = (JTabbedPaneCustom)tabContainer;
            if(tabbedPane.getjPanelForTab(tabbedPane.getSelectedIndex()) != null){
                if(newWasEdited){
                    tabbedPane.getjPanelForTab(tabbedPane.getSelectedIndex()).setTabFont(new Font("Tahoma", Font.ITALIC, 11));
                }
                else{
                    tabbedPane.getjPanelForTab(tabbedPane.getSelectedIndex()).setTabFont(new Font("Tahoma", Font.PLAIN, 11));
                }
                tabbedPane.getjPanelForTab(tabbedPane.getSelectedIndex()).repaint();
                wasEdited = newWasEdited;
            }
        }

    }


    public JTextPaneCustom(DefaultStyledDocument doc, String originalState, DocumentManager documentManager){
        super(doc);
        this.documentManager = documentManager;
        initialize(originalState);
    }


    private void initialize(){
        initialize("");
    }

    private void initialize(String originalState){
        history = new History(originalState);
        this.setBackground(new Color(49, 52, 64));
        this.setForeground(Color.white);
        this.setCaretColor(Color.white);
        this.repaint();
        this.setFont(new Font("Tahoma", Font.PLAIN, 12));

        this.addKeyListener(new KeyListenerWithHistory(this, history, documentManager));
    }
}

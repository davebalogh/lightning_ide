package helpers;

import listeners.KeyListenerWithHistory;
import listeners.DocumentListenerWithHistory;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import java.awt.*;

public class CustomJTextPane extends JTextPane {
    private boolean wasEdited = false;
    private History history;

    public boolean getWasEdited(){
        return wasEdited;
    }
    public void setWasEdited(boolean newWasEdited){
        if(wasEdited != newWasEdited){
            Container tabContainer = getParent().getParent().getParent().getParent();

            if(tabContainer instanceof JTabbedPane){
                JTabbedPane tabbedPane = (JTabbedPane)tabContainer;
                tabbedPane.repaint();
            }
        }


        wasEdited = newWasEdited;
    }

    public CustomJTextPane(DefaultStyledDocument doc){
        super(doc);
        initialize();
    }

    public CustomJTextPane(DefaultStyledDocument doc, String originalState){
        super(doc);
        initialize(originalState);
    }

    public CustomJTextPane(){
        initialize();
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

        this.addKeyListener(new KeyListenerWithHistory(this, history));
    }
}

package helpers;

import listeners.CustomKeyListener;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import java.awt.*;

public class CustomJTextPane extends JTextPane {
    private boolean wasEdited = false;

    public boolean getWasEdited(){
        return wasEdited;
    }
    public void setWasEdited(boolean newWasEdited){
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
        this.setBackground(new Color(49, 52, 64));
        this.setForeground(Color.white);
        this.setCaretColor(Color.white);
        this.repaint();

        this.addKeyListener(new CustomKeyListener(this, originalState));
    }
}

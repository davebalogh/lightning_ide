package helpers;

import listeners.CustomKeyListener;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import java.awt.*;

public class CustomJTextPane extends JTextPane {

    public CustomJTextPane(DefaultStyledDocument doc){
        super(doc);
        initialize();
    }

    public CustomJTextPane(){
        initialize();
    }

    private void initialize(){
        this.setBackground(new Color(49, 52, 64));
        this.setForeground(Color.white);
        this.setCaretColor(Color.white);
        this.repaint();

        this.addKeyListener(new CustomKeyListener(this));
    }
}

package helpers;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.plaf.metal.MetalScrollBarUI;
import javax.swing.text.DefaultStyledDocument;
import java.awt.*;

public class JScrollPaneDocument  {


    public static JScrollPane getNew(){

        DefaultStyledDocument styledDocument = new CustomStyledDocument();

        JTextPane textPane = new CustomJTextPane(styledDocument);
        JScrollPane scpane = new JScrollPane(textPane);


        scpane.getVerticalScrollBar().setUI(new MetalScrollBarUI());
        scpane.getVerticalScrollBar().setBackground(new Color(38,40,49));

        scpane.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createMatteBorder(0,0,0,1, Color.black),BorderFactory.createMatteBorder(0,1,1,1, Color.white) ));

        TextLineNumber textLineNumber = new TextLineNumber(textPane);
        scpane.setRowHeaderView( textLineNumber );

        return scpane;
    }
}

package components;

import exceptions.FileErrorException;
import exceptions.NotOpenDocumentException;
import exceptions.SaveFileException;
import helpers.DocumentManager;
import helpers.Messages;
import interfaces.Documentable;

import javax.swing.*;
import javax.swing.plaf.metal.MetalScrollBarUI;
import javax.swing.text.DefaultStyledDocument;
import java.awt.*;
import java.io.*;

public class JScrollPaneCustom extends JScrollPane {
    DefaultStyledDocument styledDocument = new StyledDocumentCustom();
    JTextPaneCustom textPane;
    boolean isNewDocument;
    boolean isLoadedDocument;
    Documentable file;
    String name;
    JTabAndPane parentJTabAndPane;

    public DocumentManager getDocumentManager() {
        return documentManager;
    }

    public void setDocumentManager(DocumentManager documentManager) {
        this.documentManager = documentManager;
    }

    DocumentManager documentManager;

    public boolean getIsNewDocument(){
        return isNewDocument;
    }

    public boolean getIsLoadedDocument(){
        return isLoadedDocument;
    }

    public void setIsLoadedDocument(boolean newIsLoadedDocument){
        isLoadedDocument = newIsLoadedDocument;
    }

    public void setIsNewDocument(boolean newIsNewDocument){
        isNewDocument = newIsNewDocument;
    }

    public void setName(String newName){
        name = newName;
    }
    public String getName(){
        return name;
    }

    public Documentable getFile(){
        return file;
    }

    public JTextPaneCustom getTextPane(){
        return textPane;
    }

    public JScrollPaneCustom(){
        this("");
        isNewDocument = true;
    }

    public JScrollPaneCustom(Documentable existingFile){
        file = existingFile;
        name = existingFile.getName();
        isNewDocument = false;
    }

    public JScrollPaneCustom(String originalState){
        super();
        initialize(originalState);
    }

    public void initialize(String originalState){
        textPane = new JTextPaneCustom(styledDocument, originalState, documentManager);
        JPanel noWrapPanel = new JPanel( new BorderLayout() );
        noWrapPanel.add( textPane );

        this.setViewportView(noWrapPanel);

        this.getVerticalScrollBar().setUnitIncrement(12);
        this.getVerticalScrollBar().setUI(new MetalScrollBarUI());
        this.getVerticalScrollBar().setBackground(new Color(38,40,49));
        this.getHorizontalScrollBar().setPreferredSize(new Dimension(0,0));
        this.getHorizontalScrollBar().setUnitIncrement(12);

        this.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createMatteBorder(0,0,0,1, Color.black),BorderFactory.createMatteBorder(0,1,1,1, Color.white) ));

        TextLineNumber textLineNumber = new TextLineNumber(textPane);
        this.setRowHeaderView( textLineNumber );

        textPane.setText(originalState);
        textPane.setCaretPosition(0);
    }
}

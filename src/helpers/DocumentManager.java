package helpers;

import components.JTabbedPaneCustom;
import exceptions.*;
import interfaces.Documentable;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class DocumentManager {
    private Documentable documentable;
    private HashMap<String, Documentable> documentHashMap;
    private JTabbedPaneCustom tabbedPane;

    public DocumentManager(JTabbedPaneCustom instanceOfTabbedPane, Documentable documentableImplementation){
        tabbedPane = instanceOfTabbedPane;
        tabbedPane.setDocumentManager(this);
        documentable = documentableImplementation;
        documentHashMap = new HashMap();
    }

    public void deleteDocument(Documentable documentToDelete){
        try {
            documentHashMap.remove(documentToDelete.getUniqueName());
            documentToDelete.deleteDocument();
        } catch (DeleteDocumentException e) {
            JOptionPane.showMessageDialog(null, "Error removing document.");
        } catch (NotOpenDocumentException e) {
            JOptionPane.showMessageDialog(null, "Error. Document not opened.");
        }
    }

    public void saveDocument(Documentable documentToSave){
        try {
            documentToSave.saveDocument();
        } catch (SaveDocumentException e) {
            JOptionPane.showMessageDialog(null, "Error saving document.");
        } catch (NotOpenDocumentException e) {
            JOptionPane.showMessageDialog(null, "Error. Document not opened.");
        }
    }

    public void createEmptyDocumentAndNewTab(){

        Documentable newDocument = documentable.getNewInstance();
        try {
            newDocument.newDocument();
            documentHashMap.put(newDocument.getUniqueName(), newDocument);
            tabbedPane.addTabFromFile(newDocument);
            tabbedPane.getjScrollPaneCustom().get(tabbedPane.getjScrollPaneCustom().size()-1).setIsNewDocument(true);
        } catch (NewDocumentException e) {
            Messages.showError("Error creating file for new tab");
        } catch (NotOpenDocumentException e) {
            e.printStackTrace();
        }
    }

    public void openDocumentAndAddToJTabbedPane(){
        try {
            Documentable newDocument = documentable.getNewInstance();
            newDocument.openDocument();
            for(String tabUniqueName: documentHashMap.keySet()){
                if(tabUniqueName.equals(newDocument.getUniqueName())){
                    Messages.showError("The file is already open.");
                    return;
                }
            }
            documentHashMap.put(newDocument.getUniqueName(), newDocument);
            newDocument.setIsNewDocument(false);
            tabbedPane.addTabFromFile(newDocument);
        } catch (NotOpenDocumentException e) {
            Messages.showError("Error. Document not opened.");
        } catch (OpenDocumentException e) {
            Messages.showError("Error opening document.");;
        }
    }

    public void loadOpenTabs(){
        try {
            if(documentable.getOpenDocumentsList().length != 0){
                for (final File fileEntry : documentable.getOpenDocumentsList()) {
                    if (!fileEntry.isDirectory()) {
                        try {
                            Documentable newDocument = documentable.getNewInstance();
                            newDocument.openDocument(fileEntry);
                            newDocument.setIsNewDocument(true);
                            newDocument.setIsEdited(true);
                            documentHashMap.put(newDocument.getUniqueName(), newDocument);

                            tabbedPane.addTabFromFile(newDocument);
                            tabbedPane.getLastJSCrollPaneAdded().setIsLoadedDocument(true);
                            tabbedPane.getLastJSCrollPaneAdded().setIsNewDocument(true);
                            tabbedPane.getLastJSCrollPaneAdded().getTextPane().setWasEdited(true);
                        } catch (OpenDocumentException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }else{
                this.createEmptyDocumentAndNewTab();
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (NotOpenDocumentException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Documentable> getDocumentHashMap() {
        return documentHashMap;
    }
}

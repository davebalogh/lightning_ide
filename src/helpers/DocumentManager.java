package helpers;

import components.JTabbedPaneCustom;
import exceptions.*;
import interfaces.Documentable;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.*;

public class DocumentManager {
    private Documentable documentable;
    private HashMap<String, Documentable> documentHashMap;

    public PanelManager getPanelManager() {
        return panelManager;
    }

    private PanelManager panelManager;

    public JTabbedPaneCustom getTabbedPane() {
        return tabbedPane;
    }

    private JTabbedPaneCustom tabbedPane;

    public DocumentManager(PanelManager instanceOfPanelManager, Documentable documentableImplementation){
        this.panelManager = instanceOfPanelManager;
        tabbedPane = instanceOfPanelManager.getContentCenterPane();
        tabbedPane.setDocumentManager(this);
        documentable = documentableImplementation;
        documentHashMap = new HashMap();
    }

    public boolean closeDocumentAndTab(Documentable documentToClose, String textModified){
        try {
            if (documentToClose.getIsEdited()) {
                documentToClose.setText(textModified);
                boolean response = documentToClose.closeModifiedDocument();

                if (response == false) {
                    return false;
                }

            } else {
                if(documentToClose.getIsNewDocument()){
                    this.deleteDocument(documentToClose);
                }

                documentHashMap.remove(documentToClose.getUniqueName());
            }
        } catch (NotOpenDocumentException e1) {
            JOptionPane.showMessageDialog(null, "Error. Document not opened.");
        } catch (CloseDocumentException e1) {
            e1.printStackTrace();
        } catch (SaveDocumentException e1) {
            JOptionPane.showMessageDialog(null, "Error saving document.");
        } catch (DeleteDocumentException e1) {
            JOptionPane.showMessageDialog(null, "Error removing document.");
        }

        return true;
    }

    public void closeDocument(Documentable documentToClose){
        try {
            if (documentToClose.getIsEdited()) {

                if(documentToClose.getIsNewDocument()) {
                    documentToClose.saveDocument();
                }
                else {
                    boolean response = documentToClose.closeModifiedDocument();

                    if (response) {
                        documentHashMap.remove(documentToClose.getUniqueName());
                    }
                }
            }
        } catch (NotOpenDocumentException e1) {
            e1.printStackTrace();
        } catch (CloseDocumentException e1) {
            e1.printStackTrace();
        } catch (SaveDocumentException e1) {
            JOptionPane.showMessageDialog(null, "Error saving document.");
        } catch (DeleteDocumentException e) {
            e.printStackTrace();
        }
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

    public Documentable createEmptyDocumentAndNewTab(){

        Documentable newDocument = documentable.getNewInstance();
        try {
            newDocument.newDocument();
            documentHashMap.put(newDocument.getUniqueName(), newDocument);
        } catch (NewDocumentException e) {
            Messages.showError("Error creating file for new tab");
        } catch (NotOpenDocumentException e) {
            e.printStackTrace();
        }

        return newDocument;
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

    public ArrayList<Documentable> loadOpenTabs(){
        ArrayList<Documentable> documentList = new ArrayList<>();

        try {
            //documentList = documentable.getOpenDocumentsList();
            for (final File fileEntry : documentable.getOpenDocumentsList()) {
                if (!fileEntry.isDirectory()) {
                    try {
                        Documentable newDocument = documentable.getNewInstance();
                        newDocument.openDocument(fileEntry);
                        newDocument.setIsNewDocument(true);
                        newDocument.setIsEdited(true);
                        documentHashMap.put(newDocument.getUniqueName(), newDocument);
                        documentList.add(newDocument);
                    } catch (OpenDocumentException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (NotOpenDocumentException e) {
            e.printStackTrace();
        }

        return documentList;
    }

    public HashMap<String, Documentable> getDocumentHashMap() {
        return documentHashMap;
    }
}

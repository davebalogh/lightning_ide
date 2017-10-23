package interfaces;

import exceptions.*;

import java.io.File;

public interface Documentable {
    public boolean getIsEdited();
    public void setIsEdited(boolean isEdited);
    public boolean getIsNewDocument();
    public void setIsNewDocument(boolean isNewDocument);
    public void setSaveAs(boolean isSaveAs);
    public boolean getIsSaveAs();
    public File getFile();
    public String getText();
    public void setText(String textModified);
    public String getName();
    public String getUniqueName() throws NotOpenDocumentException;
    public File newDocument() throws NewDocumentException;
    public File openDocument() throws OpenDocumentException;
    public File openDocument(File document) throws OpenDocumentException;
    public boolean saveDocument(File documentToSave, String newTextToSave) throws SaveDocumentException;
    public boolean saveDocument() throws SaveDocumentException, NotOpenDocumentException;
    public boolean deleteDocument(File documentForDelete) throws DeleteDocumentException;
    public boolean deleteDocument() throws DeleteDocumentException, NotOpenDocumentException;
    public boolean closeModifiedDocument(File documentForClose, String newTextModified) throws CloseDocumentException, SaveDocumentException, DeleteDocumentException;
    public boolean closeModifiedDocument() throws CloseDocumentException, SaveDocumentException, NotOpenDocumentException, DeleteDocumentException;
    public File[] getOpenDocumentsList() throws DocumentException;
    public File[] getSiblingDocuments() throws NotOpenDocumentException;
    public File[] getSiblingDocuments(File file) throws NotOpenDocumentException;
    public Documentable getNewInstance();
    public boolean isBinaryDocument(File fileToCheck) throws BinaryDocumentException, OpenDocumentException;
}

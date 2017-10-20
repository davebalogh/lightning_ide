package interfaces;

import exceptions.*;

import java.io.File;

public interface Documentable {
    public boolean getIsEdited() throws NotOpenDocumentException;
    public void setIsEdited(boolean isEdited) throws NotOpenDocumentException;
    public boolean getIsNewDocument() throws NotOpenDocumentException;
    public void setIsNewDocument(boolean isNewDocument) throws NotOpenDocumentException;
    public void setSaveAs(boolean isSaveAs);
    public boolean getIsSaveAs();
    public File getFile() throws NotOpenDocumentException;
    public String getText();
    public void setText(String textModified) throws NotOpenDocumentException;
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
    public Documentable getNewInstance();
}

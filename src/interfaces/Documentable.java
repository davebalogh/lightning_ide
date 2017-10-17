package interfaces;

import exceptions.*;

import java.io.File;

public interface Documentable {
    public File newDocument() throws NewDocumentException;
    public File openDocument() throws OpenDocumentException;
    public boolean saveDocument(File documentToSave, String newTextToSave) throws SaveDocumentException;
    public boolean deleteDocument(File documentForDelete) throws DeleteDocumentException;
    public boolean closeDocument(File documentForClose, String newTextModified) throws CloseDocumentException, SaveDocumentException;
    public File[] getOpenDocumentsList() throws DocumentException;
}

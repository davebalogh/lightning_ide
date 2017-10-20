package helpers;

import exceptions.*;
import interfaces.*;

import javax.swing.*;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDocumentImp implements Documentable {

    private String text;
    private File file;
    private String name;
    private boolean isEdited = false;
    private boolean isNewDocument = false;
    private boolean isSaveAs;

    public static String getOpenFilesFolderName(){
        return "open_files";
    }

    public static Path getOpenFilesFolderPath() throws URISyntaxException {
        String openFilesPathString = Configuration.getOpenFileDirectory();
        return Paths.get(openFilesPathString);
    }

    public static String getOpenFileDirectory() throws URISyntaxException{
        String programPath = Configuration.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        String openFilesPathString = programPath + "/" + Configuration.getOpenFilesFolderName();
        return openFilesPathString;
    }

    @Override
    public boolean getIsEdited() throws NotOpenDocumentException {
        if(file != null){
            return isEdited;
        }
        else{
            throw new NotOpenDocumentException();
        }
    }

    @Override
    public void setIsEdited(boolean isEdited) throws NotOpenDocumentException {
        if(file != null){
            this.isEdited = isEdited;
        }
        else{
            throw new NotOpenDocumentException();
        }
    }

    @Override
    public boolean getIsNewDocument() throws NotOpenDocumentException {
        if(file != null){
            return this.isNewDocument;
        }
        else{
            throw new NotOpenDocumentException();
        }
    }

    @Override
    public void setIsNewDocument(boolean isNewDocument) throws NotOpenDocumentException {
        if(file != null){
            this.isNewDocument = isNewDocument;
        }
        else{
            throw new NotOpenDocumentException();
        }
    }

    @Override
    public void setSaveAs(boolean isSaveAs) {
        this.isSaveAs = isSaveAs;
    }

    @Override
    public boolean getIsSaveAs() {
        return this.isSaveAs;
    }

    @Override
    public File getFile() throws NotOpenDocumentException {
        if(file != null){
            return file;
        }
        else{
            throw new NotOpenDocumentException();
        }
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String textModified) throws NotOpenDocumentException {
        if(file != null){
            text = textModified;
            isEdited = true;
        }
        else{
            throw new NotOpenDocumentException();
        }
    }

    @Override
    public File newDocument() throws NewDocumentException {
        File newDocument = null;
        int generalLastNumber = 1;
        String tabName = "Tab-" + generalLastNumber;
        this.isNewDocument = true;
        try {
            File openFilesDirectory = new File(FileDocumentImp.getOpenFileDirectory());
            for (final File fileEntry : openFilesDirectory.listFiles()) {
                if (!fileEntry.isDirectory()) {
                    String nameOfFile = fileEntry.getName();
                    int positionOfPoint = nameOfFile.lastIndexOf(".");
                    if (positionOfPoint > 0) {
                        nameOfFile = nameOfFile.substring(0, positionOfPoint);
                    }

                    if(nameOfFile.equals(tabName)){
                        generalLastNumber++;
                        tabName = "Tab-" + generalLastNumber;
                    }
                }
            }

            newDocument = new File(Configuration.getOpenFileDirectory() + "/" + tabName);
            newDocument.createNewFile();
            name = tabName;

        } catch (URISyntaxException e) {
            throw new NewDocumentException();
        } catch (IOException e) {
            throw new NewDocumentException();
        }
        file = newDocument;
        return newDocument;
    }
    @Override
    public File openDocument(File document) throws OpenDocumentException {
        file = document;

        StringBuffer resultado = new StringBuffer();
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String s = null;
            while ((s = br.readLine()) != null) {
                resultado.append(s);
                resultado.append("\r\n");
            }
            name = file.getName();
        } catch (IOException e1) {
            throw new OpenDocumentException();
        } finally {
            try {
                fr.close();
                br.close();

            } catch (IOException e2) {
                throw new OpenDocumentException();
            }
        }

        text = String.valueOf(resultado);

        this.isEdited = false;
        this.isNewDocument = false;
        return file;
    }

    @Override
    public File openDocument() throws OpenDocumentException {
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();

            return openDocument(fileToOpen);
        }

        return null;
    }

    @Override
    public boolean saveDocument(File documentToSave, String newTextToSave) throws SaveDocumentException {
        if(this.isNewDocument && !this.isSaveAs){
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    Files.delete(Paths.get(file.getAbsolutePath()));
                } catch (IOException e) {
                    throw new SaveDocumentException();
                }
                file = fileChooser.getSelectedFile();
                this.name = file.getName();
                this.isNewDocument = false;
            }
            else{
                return false;
            }
        }

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter( new FileWriter( file.getAbsoluteFile()));
            writer.write( newTextToSave);
        }
        catch ( IOException e) {
            throw new SaveDocumentException();
        }
        finally {
            try {
                if ( writer != null) {
                    writer.close();
                }
            }
            catch ( IOException e) {
                throw new SaveDocumentException();
            }

            isEdited = false;
            file = documentToSave;
            return true;
        }
    }

    @Override
    public boolean saveDocument() throws SaveDocumentException, NotOpenDocumentException {
        if(file != null){
            return saveDocument(this.file, this.getText());
        }
        else{
            throw new NotOpenDocumentException();
        }
    }

    @Override
    public boolean deleteDocument(File documentForDelete) throws DeleteDocumentException {
        documentForDelete.delete();
        return true;
    }

    @Override
    public boolean deleteDocument() throws DeleteDocumentException, NotOpenDocumentException {
        if(file != null){
            return deleteDocument(this.file);
        }
        else{
            throw new NotOpenDocumentException();
        }
    }

    @Override
    public boolean closeModifiedDocument(File documentForClose, String newTextModified) throws CloseDocumentException, SaveDocumentException, DeleteDocumentException {
        String message = "Do you want to save changes?";
        int answer = JOptionPane.showConfirmDialog(null, message);
        if (answer == JOptionPane.YES_OPTION) {
            if(!this.isNewDocument){
                saveDocument(documentForClose, newTextModified);
            }
            else{
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    deleteDocument(documentForClose);
                    file = fileChooser.getSelectedFile();
                    saveDocument(file, newTextModified);
                }
            }


            return true;
        }
        else if (answer == JOptionPane.CANCEL_OPTION) {
            return false;
        }else{
            if(this.isNewDocument){
                deleteDocument(documentForClose);
            }
            return true;
        }
    }

    @Override
    public boolean closeModifiedDocument() throws CloseDocumentException, SaveDocumentException, NotOpenDocumentException, DeleteDocumentException{
        if(file != null){
            return closeModifiedDocument(this.file, this.getText());
        }
        else{
            throw new NotOpenDocumentException();
        }
    }

    @Override
    public File[] getOpenDocumentsList() throws DocumentException, SecurityException {
        File[] documentList = null;
        try {
            Path openFilesPath = FileDocumentImp.getOpenFilesFolderPath();
            File openFilesDirectory = new File(FileDocumentImp.getOpenFileDirectory());

            if (Files.notExists(openFilesPath)) {
                openFilesDirectory.mkdir();
            }

            documentList = openFilesDirectory.listFiles();

        } catch (URISyntaxException e) {
            throw new DocumentException();
        }

        return documentList;
    }

    @Override
    public Documentable getNewInstance() {
        return new FileDocumentImp();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUniqueName() throws NotOpenDocumentException {
        if(file != null){
            return this.file.getAbsolutePath();
        }
        else{
            throw new NotOpenDocumentException();
        }
    }
}

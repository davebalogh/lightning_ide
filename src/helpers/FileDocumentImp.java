package helpers;

import exceptions.*;
import interfaces.Documentable;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDocumentImp implements Documentable {

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
    public File newDocument() throws NewDocumentException {
        File newDocument = null;
        int generalLastNumber = 0;
        String tabName = "Tab-" + generalLastNumber;

        try {
            File openFilesDirectory = new File(FileDocumentImp.getOpenFileDirectory());
            for (final File fileEntry : openFilesDirectory.listFiles()) {
                if (!fileEntry.isDirectory()) {
                    String nameOfFile = fileEntry.getName();
                    int positionOfPoint = nameOfFile.lastIndexOf(".");
                    if (positionOfPoint > 0) {
                        nameOfFile = nameOfFile.substring(0, positionOfPoint);
                    }

                    if(nameOfFile == tabName){
                        generalLastNumber++;
                        tabName = "Tab-" + generalLastNumber;
                    }
                }
            }

            newDocument = new File(Configuration.getOpenFileDirectory() + "/" + tabName);
            newDocument.createNewFile();

        } catch (URISyntaxException e) {
            throw new NewDocumentException();
        } catch (IOException e) {
            throw new NewDocumentException();
        }

        return newDocument;
    }

    @Override
    public File openDocument() throws OpenDocumentException {
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            return file;
        }

        return null;
    }

    @Override
    public boolean saveDocument(File documentToSave, String newTextToSave) throws SaveDocumentException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter( new FileWriter( documentToSave.getAbsoluteFile()));
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

            return true;
        }
    }

    @Override
    public boolean deleteDocument(File documentForDelete) throws DeleteDocumentException {
        documentForDelete.delete();
        return true;
    }

    @Override
    public boolean closeDocument(File documentForClose, String newTextModified) throws CloseDocumentException, SaveDocumentException {
        String message = "Do you want to save changes?";
        int answer = JOptionPane.showConfirmDialog(null, message);
        if (answer == JOptionPane.YES_OPTION) {
            saveDocument(documentForClose, newTextModified);
            return true;
        }
        else {
            return false;
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
}

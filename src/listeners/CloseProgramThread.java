package listeners;

import components.JScrollPaneCustom;
import components.JTabbedPaneCustom;
import exceptions.FileErrorException;
import exceptions.OpenFileException;
import exceptions.SaveFileException;
import helpers.Configuration;
import helpers.Messages;

import java.awt.*;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CloseProgramThread extends Thread {

    JTabbedPaneCustom currentTabbedPane;

    public CloseProgramThread(JTabbedPaneCustom instanceOfJTabbedPaneCustom){
        currentTabbedPane = instanceOfJTabbedPaneCustom;
    }

    @Override
    public void run()
    {
        try {
            Path openFilesPath = Configuration.getOpenFilesFolderPath();
            File openFilesDirectory = new File(Configuration.getOpenFileDirectory());

            if (Files.notExists(openFilesPath)) {
                try {
                    openFilesDirectory.mkdir();
                }catch(SecurityException se){
                    Messages.showError("Error creating open files directory. Try again later.");
                    return;
                }
            }

            if(currentTabbedPane.getTabCount() != 0){
                for (int tabIndex = 0; tabIndex < currentTabbedPane.getTabCount(); tabIndex++){
                    Component currentTab = currentTabbedPane.getComponentAt(tabIndex);
                    if(currentTab instanceof JScrollPaneCustom){
                        if(((JScrollPaneCustom)currentTab).getIsNewDocument()){
                            try {
                                ((JScrollPaneCustom)currentTab).saveFileToDisk(false);
                            } catch (SaveFileException e) {
                                e.printStackTrace();
                            } catch (FileErrorException e) {
                                Messages.showError("Error saving file to disk");
                            }
                        }
                    }
                }

            }
        } catch (URISyntaxException e) {
            Messages.showError("Error saving open files");
        }

    }
}

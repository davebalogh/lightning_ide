package listeners;

import components.JScrollPaneCustom;
import components.JTabbedPaneCustom;
import exceptions.FileErrorException;
import exceptions.SaveFileException;
import helpers.Configuration;
import helpers.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CloseWindowAdapter extends WindowAdapter {
    JTabbedPaneCustom currentTabbedPane;

    public CloseWindowAdapter(JTabbedPaneCustom instanceOfJTabbedPaneCustom){
        currentTabbedPane = instanceOfJTabbedPaneCustom;
    }

    @Override
    public void windowClosing(WindowEvent we)
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
                        JScrollPaneCustom jScrollPaneCustom = (JScrollPaneCustom)currentTab;

                        if(jScrollPaneCustom.getTextPane().getWasEdited()){
                            if(jScrollPaneCustom.getIsNewDocument()){
                                try {
                                    jScrollPaneCustom.saveFileToDisk(false);
                                } catch (SaveFileException e) {
                                    Messages.showError("Error saving file to disk");
                                } catch (FileErrorException e) {
                                    Messages.showError("Error saving file to disk");
                                }
                            }
                            else {
                                String message = "Do you want to save changes of " + currentTabbedPane.getTitleAt(tabIndex) + "?";
                                int answer = JOptionPane.showConfirmDialog(currentTabbedPane, message);
                                if (answer == JOptionPane.YES_OPTION) {
                                    try {
                                        jScrollPaneCustom.saveFileToDisk(false);
                                    } catch (SaveFileException e) {
                                        Messages.showError("Error saving file to disk");
                                    } catch (FileErrorException e) {
                                        Messages.showError("Error saving file to disk");
                                    }
                                }else if (answer == JOptionPane.CANCEL_OPTION) {
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        } catch (URISyntaxException e) {
            Messages.showError("Error saving open files");
        }
        finally {
            System.exit(0);
        }
    }
}

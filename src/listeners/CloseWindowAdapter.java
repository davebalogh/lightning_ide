package listeners;

import components.JScrollPaneCustom;
import components.JTabbedPaneCustom;
import exceptions.*;
import helpers.Configuration;
import helpers.DocumentManager;
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
    private DocumentManager documentManager;

    public CloseWindowAdapter(JTabbedPaneCustom instanceOfJTabbedPaneCustom, DocumentManager instanceOfDocumentManager){
        currentTabbedPane = instanceOfJTabbedPaneCustom;
        documentManager = instanceOfDocumentManager;
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


                        try {
                            if (jScrollPaneCustom.getFile().getIsEdited()) {

                                if(jScrollPaneCustom.getFile().getIsNewDocument()) {
                                    documentManager.getDocumentHashMap().get(jScrollPaneCustom.getFile().getUniqueName()).saveDocument();
                                }
                                else {
                                    boolean response = documentManager.getDocumentHashMap().get(jScrollPaneCustom.getFile().getUniqueName()).closeModifiedDocument();

                                    if (response) {
                                        documentManager.getDocumentHashMap().remove(jScrollPaneCustom.getFile().getUniqueName());
                                    }
                                    else {
                                        return;
                                    }
                                }
                            }

                        } catch (NotOpenDocumentException e1) {
                            e1.printStackTrace();
                        } catch (CloseDocumentException e1) {
                            e1.printStackTrace();
                        } catch (SaveDocumentException e1) {
                            e1.printStackTrace();
                        } catch (DeleteDocumentException e) {
                            e.printStackTrace();
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

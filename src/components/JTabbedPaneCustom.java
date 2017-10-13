package components;

import exceptions.FileErrorException;
import exceptions.OpenFileException;
import helpers.Configuration;
import helpers.Messages;
import listeners.ChangeListenerForTabbedPane;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class JTabbedPaneCustom extends JTabbedPane {
    JScrollPaneCustom lastJSCrollPaneAdded;
    ArrayList<JPanelForTab> jPanelForTabList;
    ArrayList<JScrollPaneCustom> jScrollPaneCustom;

    public void removeCustomTabAt(int index, boolean removeFileFromDisk){
        if(removeFileFromDisk){
            jScrollPaneCustom.get(index).removeFileFromDisk();
        }
        jScrollPaneCustom.remove(index);
        jPanelForTabList.remove(index);
        this.removeTabAt(index);
    }

    public void setCustomTabComponentAt(int index, Component newComponent){
        if(newComponent instanceof JPanelForTab){
            jPanelForTabList.add((JPanelForTab)newComponent);
        }
        this.setTabComponentAt(index, newComponent);
    }


    public JPanelForTab getjPanelForTab(int index){
        if(jPanelForTabList.size() > index){
            return jPanelForTabList.get(index);
        }

        return null;
    }

    public JScrollPaneCustom getjScrollPaneCustom(int index){
        if(jScrollPaneCustom.size() > index){
            return jScrollPaneCustom.get(index);
        }

        return null;
    }

    public JScrollPaneCustom getLastJSCrollPaneAdded(){
        return lastJSCrollPaneAdded;
    }

    public JTabbedPaneCustom(){
        super();
        this.setBackground( new Color(49, 52, 64));
        this.setUI(new BasicTabbedPaneUICustom(this));
        this.addChangeListener(new ChangeListenerForTabbedPane());
        jPanelForTabList = new ArrayList<>();
        jScrollPaneCustom = new ArrayList<>();
    }

    public void createNewEmptyTab(){
        int generalLastNumber = 1;

        for(int tabIndex = 0; tabIndex < this.getTabCount(); tabIndex++){
            String tabIndexName = this.getTitleAt(tabIndex);

            int positionOfPoint = tabIndexName.lastIndexOf("-");
            if (positionOfPoint > 0) {
                String lastNumberAdded = tabIndexName.substring(positionOfPoint+1);
                int lastNumber = Integer.parseInt(lastNumberAdded);
                if(generalLastNumber < lastNumber){
                    generalLastNumber = lastNumber;
                }
            }
        }
        generalLastNumber = generalLastNumber + 1;
        String tabName = "Tab-" + generalLastNumber;

        try {
            File openFilesDirectory = new File(Configuration.getOpenFileDirectory());
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

            File createNewFileForTab = new File(Configuration.getOpenFileDirectory() + "/" + tabName);
            createNewFileForTab.createNewFile();
            addTabFromFile(createNewFileForTab);
            jScrollPaneCustom.get(jScrollPaneCustom.size()-1).setIsNewDocument(true);

        } catch (URISyntaxException e) {
            Messages.showError("Error creating new tab");
        } catch (OpenFileException e) {
            Messages.showError("Error opening previous tab");
        } catch (IOException e) {
            Messages.showError("Error creating file for new tab");
        }


    }

    public void addTabFromFile(File openFile) throws OpenFileException{
        try {
            lastJSCrollPaneAdded = new JScrollPaneCustom(openFile);
            jScrollPaneCustom.add(lastJSCrollPaneAdded);
            int tabCount = this.getTabCount() + 1;
            String tabName = openFile.getName();
            this.addTab(tabName, lastJSCrollPaneAdded);
            int selectedIndex = tabCount-1;
            this.setSelectedIndex(selectedIndex);

            JPanelForTab newTab = new JPanelForTab(this, tabName);
            this.setCustomTabComponentAt(selectedIndex, newTab);

        } catch (FileErrorException e1) {
            throw new OpenFileException();
        }
    }
}

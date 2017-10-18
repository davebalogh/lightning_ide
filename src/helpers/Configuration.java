package helpers;

import com.apple.eawt.Application;
import components.JScrollPaneCustom;
import components.JTabbedPaneCustom;
import exceptions.FileErrorException;
import exceptions.OpenFileException;
import exceptions.SaveFileException;
import listeners.CloseWindowAdapter;
import sun.misc.resources.Messages_es;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Configuration {

    public static String getOpenFilesFolderName(){
        return "open_files";
    }

    public static Path getOpenFilesFolderPath() throws URISyntaxException{
        String openFilesPathString = Configuration.getOpenFileDirectory();
        return Paths.get(openFilesPathString);
    }

    public static String getOpenFileDirectory() throws URISyntaxException{
            String programPath = Configuration.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            String openFilesPathString = programPath + "/" + Configuration.getOpenFilesFolderName();
            return openFilesPathString;
    }

    public static void initializeSettings(JFrame frameToApply){
        frameToApply.setSize(600, 600);
        frameToApply.setLocationRelativeTo(null);
        frameToApply.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);



        Container generalContainer = frameToApply.getContentPane();
        generalContainer.setBackground(new Color(38,40,49));

        System.setProperty("apple.laf.useScreenMenuBar", "true");

        //icons
        String pathToImageSortBy = "resources/code-text-edit-mode-100.png";
        ImageIcon SortByIcon = new ImageIcon(frameToApply.getClass().getClassLoader().getResource(pathToImageSortBy));
        ArrayList<Image> icons = new ArrayList<Image>();
        icons.add(SortByIcon.getImage());
        frameToApply.setIconImage(SortByIcon.getImage());
        frameToApply.setIconImages(icons);

        Application application = Application.getApplication();
        application.setDockIconImage(SortByIcon.getImage());
    }

}

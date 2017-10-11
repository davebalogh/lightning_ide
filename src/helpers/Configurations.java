package helpers;

import com.apple.eawt.Application;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Configurations {
    public static void initializeSettings(JFrame frameToApply){
        frameToApply.setSize(600, 600);
        frameToApply.setLocationRelativeTo(null);
        frameToApply.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

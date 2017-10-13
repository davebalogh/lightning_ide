package helpers;

import components.JScrollPaneCustom;
import components.JTabbedPaneCustom;
import exceptions.FileErrorException;
import exceptions.OpenFileException;

import javax.swing.*;
import java.io.File;

public class FileManager {
    public static void openFileAndAddToJTabbedPane(JTabbedPaneCustom tabbedPane) throws OpenFileException{
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            File archivo = new File(file.getAbsolutePath());

            JScrollPaneCustom jsPane = null;
            try {
                jsPane = new JScrollPaneCustom(archivo);
                int tabCount = tabbedPane.getTabCount() + 1;
                String tabName = file.getName();
                tabbedPane.addTab(tabName, jsPane);
                tabbedPane.setSelectedIndex(tabCount-1);

            } catch (FileErrorException e1) {
                throw new OpenFileException();
            }
        }
    }
}

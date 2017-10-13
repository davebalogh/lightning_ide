package listeners;

import components.JTabbedPaneCustom;
import exceptions.FileErrorException;
import components.JScrollPaneCustom;
import exceptions.OpenFileException;
import helpers.FileManager;
import helpers.Messages;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class OpenFileActionListener implements ActionListener {
    private JTabbedPaneCustom tabbedPane;

    public OpenFileActionListener(JTabbedPaneCustom instanceOfTabbedPane){
        tabbedPane = instanceOfTabbedPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            FileManager.openFileAndAddToJTabbedPane(tabbedPane);
        } catch (OpenFileException e1) {
            Messages.showError("Error opening file. Try again later.");
        }
    }
}

package listeners;

import components.JScrollPaneCustom;
import components.JTabbedPaneCustom;
import exceptions.FileErrorException;
import exceptions.OpenFileException;
import exceptions.SaveFileException;
import helpers.Configuration;
import helpers.Messages;

import javax.swing.*;
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
        //moved to CloseWindowAdapter
    }
}

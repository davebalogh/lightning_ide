package listeners;

import components.JTabbedPaneCustom;
import exceptions.FileErrorException;
import components.JScrollPaneCustom;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class OpenFileActionListener implements ActionListener {
    final JFileChooser fileChooser = new JFileChooser();
    private JTabbedPaneCustom tabbedPane;

    public OpenFileActionListener(JTabbedPaneCustom instanceOfTabbedPane){
        tabbedPane = instanceOfTabbedPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
                e1.printStackTrace();
            }
        } else {
            System.out.println("Open command cancelled by user.");
        }
    }
}

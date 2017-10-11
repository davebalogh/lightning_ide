import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

import com.apple.eawt.Application;
import com.sun.tools.javac.util.List;
import exceptions.FileErrorException;
import exceptions.SaveFileException;
import helpers.*;

class LightningIDE extends JFrame implements ActionListener {

    final JFileChooser fileChooser = new JFileChooser();
    JTabbedPane tabbedPane;
    ArrayList<JScrollPaneDocument> scrollPaneList = new ArrayList();


    public LightningIDE() {
        super("Lightning IDE");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container pane = getContentPane();
        pane.setBackground(new Color(38,40,49));

        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground( new Color(49, 52, 64));
        tabbedPane.setUI(new CustomBasicTabbedPaneUI(tabbedPane));

        JScrollPaneDocument jsPane = new JScrollPaneDocument();

        scrollPaneList.add(jsPane);
        tabbedPane.addTab("Tab-1", jsPane);

        pane.add(tabbedPane, BorderLayout.CENTER);



        //menu bar
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(this);
        fileMenu.add(openItem);

        JMenuItem newItem = new JMenuItem("New");
        newItem.addActionListener(this);
        fileMenu.add(newItem);

        JMenuItem closeTabItem = new JMenuItem("Close Tab");
        closeTabItem.addActionListener(this);
        fileMenu.add(closeTabItem);

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);


        //icons
        String pathToImageSortBy = "resources/code-text-edit-mode-100.png";
        ImageIcon SortByIcon = new ImageIcon(getClass().getClassLoader().getResource(pathToImageSortBy));
        ArrayList<Image> icons = new ArrayList<Image>();
        icons.add(SortByIcon.getImage());
        this.setIconImage(SortByIcon.getImage());
        this.setIconImages(icons);

        Application application = Application.getApplication();
        application.setDockIconImage(SortByIcon.getImage());


        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equalsIgnoreCase("Open")){
            int returnVal = fileChooser.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                File archivo = new File(file.getAbsolutePath());

                JScrollPaneDocument jsPane = null;
                try {
                    jsPane = new JScrollPaneDocument(archivo);
                    int tabCount = tabbedPane.getTabCount() + 1;
                    String tabName = file.getName();
                    tabbedPane.addTab(tabName, jsPane);
                    tabbedPane.setSelectedIndex(tabCount-1);

                    scrollPaneList.add(jsPane);
                } catch (FileErrorException e1) {
                    e1.printStackTrace();
                }


            } else {
                System.out.println("Open command cancelled by user.");
            }
        }else if(e.getActionCommand().equalsIgnoreCase("New")){
            JScrollPaneDocument jsPane = new JScrollPaneDocument();
            int tabCount = tabbedPane.getTabCount() + 1;
            String tabName = "Tab-" + tabCount;
            tabbedPane.addTab(tabName, jsPane);
            tabbedPane.setSelectedIndex(tabCount-1);
            scrollPaneList.add(jsPane);
        }else if(e.getActionCommand().equalsIgnoreCase("Close Tab")){

            JScrollPaneDocument selectedTab = scrollPaneList.get(tabbedPane.getSelectedIndex());

            if(selectedTab.getTextPane().getWasEdited() && !selectedTab.getIsNewDocument()){
                String message = "Do you want to save changes?";
                int answer = JOptionPane.showConfirmDialog(this, message);
                if (answer == JOptionPane.NO_OPTION) {
                    scrollPaneList.remove(tabbedPane.getSelectedIndex());
                    tabbedPane.removeTabAt(tabbedPane.getSelectedIndex());
                }else if (answer == JOptionPane.YES_OPTION) {

                    int selectedIndex = tabbedPane.getSelectedIndex();
                    JScrollPaneDocument selectedDocument = scrollPaneList.get(selectedIndex);

                    try {
                        selectedDocument.saveAndCloseFile();
                        scrollPaneList.remove(selectedIndex);
                        tabbedPane.removeTabAt(selectedIndex);
                    } catch (SaveFileException e1) {
                        JOptionPane.showMessageDialog(this, "Error saving the file");
                    } catch (FileErrorException e1) {
                        JOptionPane.showMessageDialog(this, "Error closing the file");
                    }

                }
            }
            else{
                scrollPaneList.remove(tabbedPane.getSelectedIndex());
                tabbedPane.removeTabAt(tabbedPane.getSelectedIndex());
            }
        }
    }
}

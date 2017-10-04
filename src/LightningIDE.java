import java.awt.*;
import java.awt.event.*;
import java.awt.geom.GeneralPath;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.text.*;

import com.apple.eawt.Application;
import helpers.*;
import memento.Caretaker;
import memento.Originator;

class LightningIDE extends JFrame implements ActionListener {

    final JFileChooser fc = new JFileChooser();


    public LightningIDE() {
        super("Lightning IDE");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container pane = getContentPane();
        pane.setBackground(new Color(38,40,49));

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground( new Color(49, 52, 64));
        tabbedPane.setUI(new CustomBasicTabbedPaneUI());

        JScrollPane jsPane = JScrollPaneDocument.getNew();

        tabbedPane.addTab("Tab 1", jsPane);
        tabbedPane.addTab("Tab 2", JScrollPaneDocument.getNew());

        pane.add(tabbedPane, BorderLayout.CENTER);



        //menu bar
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(this);
        fileMenu.add(openItem);
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
        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();

            System.out.println("Opening: " + file.getAbsolutePath());
        } else {
            System.out.println("Open command cancelled by user.");
        }
    }
}

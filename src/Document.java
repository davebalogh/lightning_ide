import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.*;

import com.apple.eawt.Application;
import helpers.CustomJTextPane;
import helpers.CustomStyledDocument;
import helpers.TextLineNumber;
import memento.Caretaker;
import memento.Originator;

class Document extends JFrame implements ActionListener {
    private JScrollPane scpane;
    private JTextPane textPane;

    private Caretaker caretaker = new Caretaker();
    private Originator originator = new Originator();

    public Document() {
        super("Text Editor");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        DefaultStyledDocument styledDocument = new CustomStyledDocument();

        Container pane = getContentPane();
        textPane = new CustomJTextPane(styledDocument);
        scpane = new JScrollPane(textPane);
        scpane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        TextLineNumber textLineNumber = new TextLineNumber(textPane);
        scpane.setRowHeaderView( textLineNumber );
        pane.add(scpane, BorderLayout.CENTER);


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

    }

    public static void main(String[] args)
    {
        new Document();
    }
}

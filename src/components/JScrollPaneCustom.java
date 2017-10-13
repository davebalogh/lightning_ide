package components;

import exceptions.FileErrorException;
import exceptions.SaveFileException;
import helpers.Messages;

import javax.swing.*;
import javax.swing.plaf.metal.MetalScrollBarUI;
import javax.swing.text.DefaultStyledDocument;
import java.awt.*;
import java.io.*;

public class JScrollPaneCustom extends JScrollPane {
    DefaultStyledDocument styledDocument = new StyledDocumentCustom();
    JTextPaneCustom textPane;
    boolean isNewDocument;
    File file;
    String name;

    public boolean getIsNewDocument(){
        return isNewDocument;
    }

    public void setName(String newName){
        name = newName;
    }
    public String getName(){
        return name;
    }

    public void saveFileToDisk() throws SaveFileException, FileErrorException {
        if(isNewDocument){
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
            }
            else{
                return;
            }
        }

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter( new FileWriter( file.getAbsoluteFile()));
            writer.write( textPane.getText());
            getTextPane().setWasEdited(false);

            isNewDocument = false;
            Container parentJTabbedPaneCustom = this.getParent();
            if(parentJTabbedPaneCustom instanceof JTabbedPaneCustom){
                JTabbedPaneCustom tabbedPaneCustom = (JTabbedPaneCustom)parentJTabbedPaneCustom;
                Component selectedTab = tabbedPaneCustom.getTabComponentAt(tabbedPaneCustom.getSelectedIndex());
                if(selectedTab instanceof JPanelForTab){
                    JPanelForTab tabPanel = (JPanelForTab)selectedTab;
                    tabPanel.setTitle(file.getName());
                }
                else{
                    Messages.showError("Something went wrong. Try again later.");
                    return;
                }
            }
            else{
                Messages.showError("Something went wrong. Try again later.");
                return;
            }

        }
        catch ( IOException e) {
            throw new SaveFileException();
        }
        finally {
            try {
                if ( writer != null) {
                    writer.close();
                }
            }
            catch ( IOException e) {
                throw new FileErrorException();
            }
        }

    }

    public File getFile(){
        return file;
    }

    public JTextPaneCustom getTextPane(){
        return textPane;
    }

    public JScrollPaneCustom(){
        this("");
        isNewDocument = true;
    }

    public JScrollPaneCustom(File existingFile) throws FileErrorException {
        file = existingFile;
        StringBuffer resultado = new StringBuffer();
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String s = null;
            while ((s = br.readLine()) != null) {
                resultado.append(s);
                resultado.append("\r\n");
            }
            name = file.getName();
        } catch (IOException e1) {
            throw new FileErrorException("Error al intentar abrir el archivo");
        } finally {
            try {
                fr.close();
                br.close();

            } catch (IOException e2) {
                throw new FileErrorException("Error al intentar cerrar el archivo");
            }
        }
        initialize(String.valueOf(resultado));
        isNewDocument = false;
    }

    public JScrollPaneCustom(String originalState){
        super();
        initialize(originalState);
    }

    private void initialize(String originalState){
        textPane = new JTextPaneCustom(styledDocument, originalState);
        JPanel noWrapPanel = new JPanel( new BorderLayout() );
        noWrapPanel.add( textPane );

        this.setViewportView(noWrapPanel);

        this.getVerticalScrollBar().setUnitIncrement(12);
        this.getVerticalScrollBar().setUI(new MetalScrollBarUI());
        this.getVerticalScrollBar().setBackground(new Color(38,40,49));
        this.getHorizontalScrollBar().setPreferredSize(new Dimension(0,0));
        this.getHorizontalScrollBar().setUnitIncrement(12);

        this.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createMatteBorder(0,0,0,1, Color.black),BorderFactory.createMatteBorder(0,1,1,1, Color.white) ));

        TextLineNumber textLineNumber = new TextLineNumber(textPane);
        this.setRowHeaderView( textLineNumber );

        textPane.setText(originalState);
        textPane.setCaretPosition(0);
    }
}

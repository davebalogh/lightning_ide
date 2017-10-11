package helpers;

import exceptions.FileErrorException;
import exceptions.SaveFileException;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.plaf.metal.MetalScrollBarUI;
import javax.swing.text.DefaultStyledDocument;
import java.awt.*;
import java.io.*;

public class JScrollPaneDocument extends JScrollPane {
    DefaultStyledDocument styledDocument = new CustomStyledDocument();
    CustomJTextPane textPane;
    boolean isNewDocument;
    File file;

    public boolean getIsNewDocument(){
        return isNewDocument;
    }

    public void saveAndCloseFile() throws SaveFileException, FileErrorException {
        if(!isNewDocument){
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter( new FileWriter( file.getAbsoluteFile()));
                writer.write( textPane.getText());

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
    }

    public File getFile(){
        return file;
    }

    public CustomJTextPane getTextPane(){
        return textPane;
    }

    public JScrollPaneDocument(){
        this("");
        isNewDocument = true;
    }

    public JScrollPaneDocument(File existingFile) throws FileErrorException {
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

    public JScrollPaneDocument(String originalState){
        super();
        initialize(originalState);
    }

    private void initialize(String originalState){
        textPane = new CustomJTextPane(styledDocument, originalState);
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

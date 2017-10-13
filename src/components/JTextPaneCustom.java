package components;

import helpers.History;
import listeners.KeyListenerWithHistory;
import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import java.awt.*;

public class JTextPaneCustom extends JTextPane {
    private boolean wasEdited = false;
    private History history;

    public boolean getWasEdited(){
        return wasEdited;
    }
    public void setWasEdited(boolean newWasEdited){
        if(wasEdited != newWasEdited){
            Container tabContainer = getParent().getParent().getParent().getParent();

            if(tabContainer instanceof JTabbedPane){
                JTabbedPane tabbedPane = (JTabbedPane)tabContainer;
                Component componentEdited = tabbedPane.getTabComponentAt(tabbedPane.getSelectedIndex());
                if(componentEdited instanceof JPanel){
                    JPanel container = (JPanel)componentEdited;
                    Component labelComponent = container.getComponent(0);
                    if(labelComponent instanceof JLabel){
                        if(newWasEdited){
                            ((JLabel)labelComponent).setFont(new Font("Tahoma", Font.ITALIC, 11));
                        }
                        else{
                            ((JLabel)labelComponent).setFont(new Font("Tahoma", Font.PLAIN, 11));
                        }
                    }
                }
            }
        }


        wasEdited = newWasEdited;
    }


    public JTextPaneCustom(DefaultStyledDocument doc, String originalState){
        super(doc);
        initialize(originalState);
    }


    private void initialize(){
        initialize("");
    }

    private void initialize(String originalState){
        history = new History(originalState);
        this.setBackground(new Color(49, 52, 64));
        this.setForeground(Color.white);
        this.setCaretColor(Color.white);
        this.repaint();
        this.setFont(new Font("Tahoma", Font.PLAIN, 12));

        this.addKeyListener(new KeyListenerWithHistory(this, history));
    }
}

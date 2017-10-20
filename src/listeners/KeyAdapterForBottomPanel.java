package listeners;

import components.JBottomPanel;
import components.JTextPaneCustom;
import helpers.Messages;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyAdapterForBottomPanel extends KeyAdapter {
    JBottomPanel jBottomPanel;
    boolean findFlag = false;
    int position = 0;
    public KeyAdapterForBottomPanel(JBottomPanel jBottomPanel){
        this.jBottomPanel = jBottomPanel;
    }

    @Override
    public void keyPressed(KeyEvent evt) {
        int code = evt.getKeyCode();
        JTextPaneCustom jTextPaneCustom = jBottomPanel.getPanelManager().getContentCenterPane().getSelectedJSCrollPane().getTextPane();
        int end = jTextPaneCustom.getSelectionEnd();
        jTextPaneCustom.setSelectionStart(end);
        jTextPaneCustom.setSelectionEnd(end);

        if(code == KeyEvent.VK_ESCAPE){
            position = 0;
            findFlag = false;
            jBottomPanel.getTextField().setText("");
            jBottomPanel.setVisible(false);
            jTextPaneCustom.requestFocus();
        }

        if(code == KeyEvent.VK_ENTER){
            String textToFind = jBottomPanel.getTextField().getText().toLowerCase();

            if (textToFind != null && textToFind.length() > 0) {
                Document documentWithText = jTextPaneCustom.getDocument();
                int findLength = textToFind.length();

                boolean found = false;
                if (position + findLength > documentWithText.getLength()) {
                    position = 0;
                }

                while (position + findLength <= documentWithText.getLength()) {
                    String match = null;
                    try {
                        match = documentWithText.getText(position, findLength).toLowerCase();
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }
                    if (match.equals(textToFind)) {
                        found = true;
                        break;
                    }
                    position++;
                }

                if (found) {
                    findFlag = true;
                    jTextPaneCustom.requestFocus();
                    Rectangle viewRect = null;
                    try {
                        viewRect = jTextPaneCustom.modelToView(position);
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }
                    jTextPaneCustom.scrollRectToVisible(viewRect);
                    jTextPaneCustom.setCaretPosition(position + findLength);
                    jTextPaneCustom.moveCaretPosition(position);

                    jBottomPanel.getTextField().requestFocus();
                    position += findLength;
                }

                //final
                if((position -1+findLength) == documentWithText.getLength()){
                    if(findFlag){
                        position = 0;
                        this.keyPressed(evt);
                    }
                    else{
                        position = 0;
                        Messages.showWarning("Text not found.");
                    }
                }
            }
        }
        else{
            findFlag = false;
            position = 0;
        }
    }
}

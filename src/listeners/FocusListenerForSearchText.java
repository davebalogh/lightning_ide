package listeners;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FocusListenerForSearchText implements FocusListener {
    JTextField textField;

    public FocusListenerForSearchText(JTextField textField){
        this.textField = textField;
    }

    @Override
    public void focusGained(FocusEvent fe){
        textField.setCaretPosition(textField.getDocument().getLength());
    }

    @Override
    public void focusLost(FocusEvent e) {

    }
}

package components;

import javax.swing.*;
import java.awt.*;

public class JBottomPanel extends JPanel {
    public JTextField getTextField() {
        return textField;
    }

    JTextField textField;
    public JBottomPanel(){
        textField = new JTextField(20);
        textField.setBackground(new Color(49, 52, 64));
        textField.setForeground(new Color(215,216,224));
        textField.setFont(new Font("Tahoma", Font.PLAIN, 10));
        this.setLayout(new GridLayout());
        this.setBorder(BorderFactory.createEmptyBorder());
        this.add(textField);
        this.setBackground(new Color(38,40,49));
        this.setVisible(false);
    }
}

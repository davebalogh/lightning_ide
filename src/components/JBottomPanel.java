package components;

import helpers.PanelManager;
import listeners.FocusListenerForSearchText;
import listeners.KeyAdapterForBottomPanel;
import javax.swing.*;
import java.awt.*;

public class JBottomPanel extends JPanel {
    PanelManager panelManager;
    JTextField textField;

    public PanelManager getPanelManager() {
        return panelManager;
    }

    public void setPanelManager(PanelManager panelManager) {
        this.panelManager = panelManager;
    }

    public JTextField getTextField() {
        return textField;
    }

    @Override
    public void setVisible(boolean visibility){
        super.setVisible(visibility);
        textField.requestFocus();
    }


    public JBottomPanel(PanelManager panelManager){
        this.panelManager = panelManager;
        textField = new JTextField(20);
        textField.setBackground(new Color(49, 52, 64));
        textField.setForeground(new Color(215,216,224));
        textField.setFont(new Font("Tahoma", Font.PLAIN, 10));
        this.setLayout(new GridLayout());
        this.setBorder(BorderFactory.createEmptyBorder());
        this.add(textField);
        this.setBackground(new Color(38,40,49));
        this.setVisible(false);

        textField.addKeyListener(new KeyAdapterForBottomPanel(this));
        textField.addFocusListener(new FocusListenerForSearchText(textField));
    }
}

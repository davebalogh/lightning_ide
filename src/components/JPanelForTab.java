package components;

import listeners.CloseTabActionListener;

import javax.swing.*;
import java.awt.*;

public class JPanelForTab extends JPanel {
    JLabel jLabel;

    public void setTitle(String title){
        jLabel.setText(title);
    }

    public void setColor(Color newColor){
        jLabel.setForeground(newColor);
    }

    public JPanelForTab(JTabbedPane parentJTabbedPane, String tabTitle){
        super();
        this.setBackground(new Color(49, 52, 64));

        jLabel = new JLabel(tabTitle);
        jLabel.setForeground(new Color(215,216,224));
        jLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
        jLabel.setBorder(BorderFactory.createEmptyBorder(0, 0,0,0));
        String pathToImageSortBy = "resources/close-icon-white-8.png";
        ImageIcon SortByIcon = new ImageIcon(getClass().getClassLoader().getResource(pathToImageSortBy));
        JButton closeButton = new JButton(SortByIcon);
        closeButton.setOpaque(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setBorder(BorderFactory.createEmptyBorder(0, 5,0,0));
        closeButton.setBorderPainted(false);
        closeButton.addActionListener(new CloseTabActionListener((JTabbedPaneCustom) parentJTabbedPane));

        this.add(jLabel);
        this.add(closeButton);
    }
}

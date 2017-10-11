package components;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicIconFactory;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.*;

public class CustomBasicTabbedPaneUI extends BasicTabbedPaneUI {
    JTabbedPane parentJTabbedPane;
    Rectangle xRect;

    public CustomBasicTabbedPaneUI(JTabbedPane instanceOfJTabbedPane){
        //super();
        parentJTabbedPane = instanceOfJTabbedPane;
    }

    @Override protected void paintTabBackground(
            Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h,
            boolean isSelected) {

        super.paintTabBackground(g, tabPlacement,  tabIndex,  x,  y,  w,  h, isSelected);
    }

    @Override
    protected void paintText(Graphics g, int tabPlacement, Font font,
                             FontMetrics metrics, int tabIndex, String title, Rectangle textRect,
                             boolean isSelected) {

        Color tabColor = new Color(215,216,224);

        Font tabFont = new Font("Tahoma", Font.PLAIN, 11);


        JPanel cont = new JPanel();

        cont.setBackground(new Color(49, 52, 64));

        JLabel jLabel = new JLabel(title);
        jLabel.setForeground(tabColor);
        jLabel.setFont(tabFont);
        jLabel.setBorder(BorderFactory.createEmptyBorder(0, 0,0,0));
        String pathToImageSortBy = "resources/close-icon-white-8.png";
        ImageIcon SortByIcon = new ImageIcon(getClass().getClassLoader().getResource(pathToImageSortBy));
        JButton closeButton = new JButton(SortByIcon);
        closeButton.setOpaque(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setBorder(BorderFactory.createEmptyBorder(0, 5,0,0));
        closeButton.setBorderPainted(false);

        cont.add(jLabel);
        cont.add(closeButton);
        parentJTabbedPane.setTabComponentAt(tabIndex, cont);

        //g.setFont(tabFont);
        //g.drawString(title, textRect.x + 3, textRect.y + metrics.getAscent() );
    }

    @Override protected void paintFocusIndicator(
            Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex,
            Rectangle iconRect, Rectangle textRect, boolean isSelected) {
    }
}

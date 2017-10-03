package helpers;

import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

public class CustomBasicTabbedPaneUI extends BasicTabbedPaneUI {
    @Override protected void paintTabBackground(
            Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h,
            boolean isSelected) {

        super.paintTabBackground(g, tabPlacement,  tabIndex,  x,  y,  w,  h, isSelected);
    }

    @Override
    protected void paintText(Graphics g, int tabPlacement, Font font,
                             FontMetrics metrics, int tabIndex, String title, Rectangle textRect,
                             boolean isSelected) {

        if (isSelected && tabPlacement == TOP) {
            g.setColor(new Color(215,216,224));
        } else {
            g.setColor(new Color(110,113,127));
        }

        Font tabFont = new Font("Tahoma", Font.PLAIN, 11);

        g.setFont(tabFont);
        g.drawString(title, textRect.x + 3, textRect.y + metrics.getAscent() );
    }

    @Override protected void paintFocusIndicator(
            Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex,
            Rectangle iconRect, Rectangle textRect, boolean isSelected) {
    }
}

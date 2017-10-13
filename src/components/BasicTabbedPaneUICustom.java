package components;

import listeners.CloseTabActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicIconFactory;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.*;

public class BasicTabbedPaneUICustom extends BasicTabbedPaneUI {
    JTabbedPane parentJTabbedPane;

    public BasicTabbedPaneUICustom(JTabbedPane instanceOfJTabbedPane){
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

        JPanelForTab newTab = new JPanelForTab(parentJTabbedPane, title);

        parentJTabbedPane.setTabComponentAt(tabIndex, newTab);
    }

    @Override protected void paintFocusIndicator(
            Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex,
            Rectangle iconRect, Rectangle textRect, boolean isSelected) {
    }
}

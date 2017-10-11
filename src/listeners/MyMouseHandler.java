package listeners;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseHandler extends MouseAdapter {
    public void mousePressed(MouseEvent e) {
        System.out.println(e);

            JTabbedPane tabPane = (JTabbedPane)e.getSource();
            //int tabIndex = super.tabForCoordinate(tabPane, e.getX(), e.getY());
            //tabPane.remove(tabIndex);

    }
}
package listeners;

import components.JPanelForTab;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ChangeListenerForTabbedPane implements ChangeListener {
    public void stateChanged(ChangeEvent changeEvent) {
        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
        int index = sourceTabbedPane.getSelectedIndex();

        for(int x = 0; x<sourceTabbedPane.getTabCount(); x++){
            Component comp = sourceTabbedPane.getTabComponentAt(x);
            if(comp instanceof JPanelForTab){
                JPanelForTab tab = (JPanelForTab)comp;
                if(x != index){
                    tab.setColor(new Color(110,113,127));
                }
                else{
                    tab.setColor(new Color(215,216,224));
                }
            }
        }
    }
}

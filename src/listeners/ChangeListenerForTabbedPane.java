package listeners;

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
            if(comp instanceof JPanel){
                JPanel container = (JPanel)comp;
                Component labelComponent = container.getComponent(0);
                if(labelComponent instanceof JLabel){
                    if(x != index){
                        ((JLabel)labelComponent).setForeground(new Color(110,113,127));
                    }
                    else{
                        ((JLabel)labelComponent).setForeground(new Color(215,216,224));
                    }
                }
            }
        }
    }
}

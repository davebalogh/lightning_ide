package helpers;

import components.*;
import javax.swing.*;

public class PanelManager {
    JTabbedPaneCustom contentCenterPane;

    public JTabbedPaneCustom getContentCenterPane() {
        return contentCenterPane;
    }

    public void setContentCenterPane(JTabbedPaneCustom contentCenterPane) {
        this.contentCenterPane = contentCenterPane;
    }

    public JBottomPanel getContentBottomPane() {
        return contentBottomPane;
    }

    public void setContentBottomPane(JBottomPanel contentBottomPane) {
        this.contentBottomPane = contentBottomPane;
    }

    JBottomPanel contentBottomPane;
}

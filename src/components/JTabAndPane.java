package components;

import interfaces.Documentable;

public class JTabAndPane {
    JScrollPaneCustom jScrollPaneCustom;
    JPanelForTab jPanelForTab;
    Documentable documentable;

    public JScrollPaneCustom getjScrollPaneCustom() {
        return jScrollPaneCustom;
    }

    public JPanelForTab getjPanelForTab() {
        return jPanelForTab;
    }

    public Documentable getDocumentable() {
        return documentable;
    }

    public void setDocumentable(Documentable documentable) {
        this.documentable = documentable;
    }


    public JTabAndPane(JScrollPaneCustom instanceOfJScrollPaneCustom, JPanelForTab instanceOfJPanelForTab){
        jScrollPaneCustom = instanceOfJScrollPaneCustom;
        jPanelForTab = instanceOfJPanelForTab;
    }
}

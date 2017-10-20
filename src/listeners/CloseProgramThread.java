package listeners;

import components.JTabbedPaneCustom;

public class CloseProgramThread extends Thread {

    JTabbedPaneCustom currentTabbedPane;

    public CloseProgramThread(JTabbedPaneCustom instanceOfJTabbedPaneCustom){
        currentTabbedPane = instanceOfJTabbedPaneCustom;
    }

    @Override
    public void run()
    {
        //moved to CloseWindowAdapter
    }
}

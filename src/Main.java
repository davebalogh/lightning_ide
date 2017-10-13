import listeners.CloseProgramThread;

public class Main {
    public static void main(String[] args) {
        LightningIDE program = new LightningIDE();
        Runtime.getRuntime().addShutdownHook(new CloseProgramThread(program.getTabbedPane()));
    }
}

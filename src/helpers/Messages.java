package helpers;

import javax.swing.*;

public class Messages {
    public static void showError(String message){
        JOptionPane.showMessageDialog(null, message, "Lightning IDE", JOptionPane.ERROR_MESSAGE);
    }

    public static void showWarning(String message){
        JOptionPane.showMessageDialog(null, message, "Lightning IDE", JOptionPane.WARNING_MESSAGE);
    }
}

import VendingMachine.Controller.MainFrame;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
//            AdminMainFrame adminMainFrame = new AdminMainFrame();
            MainFrame mainFrame = new MainFrame();
        });
    }
}

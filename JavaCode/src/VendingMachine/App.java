package VendingMachine;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
        });
//
//        Item item = new Item("item1",1.00F);
//        Item item1 = new Item("item2", 5.00F);
    }
}

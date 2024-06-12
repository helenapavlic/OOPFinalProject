package VendingMachine;

import javax.swing.*;
import javax.swing.border.Border;

public class ItemsPanel extends JPanel {
    public ItemsPanel(){
        borders();

    }

    private void borders() {
        Border inner = BorderFactory.createTitledBorder("Items");
        Border outer = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outer,inner));
    }
}

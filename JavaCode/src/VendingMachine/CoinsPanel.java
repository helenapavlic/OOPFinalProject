package VendingMachine;

import javax.swing.*;
import javax.swing.border.Border;

public class CoinsPanel extends JPanel {
    public CoinsPanel(){
        borders();

    }

    private void borders() {
        Border inner = BorderFactory.createTitledBorder("Coins");
        Border outer = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outer,inner));
    }
}

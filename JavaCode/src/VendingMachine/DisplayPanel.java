package VendingMachine;

import javax.swing.*;
import javax.swing.border.Border;

public class DisplayPanel extends JPanel {

    public DisplayPanel(){
        borders();

    }

    private void borders() {
        Border inner = BorderFactory.createTitledBorder("Display");
        Border outer = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outer,inner));
    }
}

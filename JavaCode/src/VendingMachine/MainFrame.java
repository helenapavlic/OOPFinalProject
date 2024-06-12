package VendingMachine;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private ItemsPanel itemsPanel;
    private CoinsPanel coinsPanel;
    private DisplayPanel displayPanel;

    public MainFrame(){
        super("Vending machine");
        initComps();
        layoutComps();
        activateFrame();

    }

    private void activateFrame() {

    }

    private void layoutComps() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;

        // Add the DisplayPanel to the main frame
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.8;
        gbc.weighty = 0.6;
        add(displayPanel, gbc);

        // Add the CoinsPanel to the main frame
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.8;
        gbc.weighty = 0.4;
        add(coinsPanel, gbc);

        // Add the DrinksAndSnacksPanel to the main frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.weightx = 1.2;
        gbc.weighty = 1.0;
        add(itemsPanel, gbc);

        // Set frame size, location, and make it visible
//        setSize(800, 800);
//        setLocationRelativeTo(null);
//        setVisible(true);

    }

    private void initComps() {
        setSize(800, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        itemsPanel = new ItemsPanel();
        coinsPanel = new CoinsPanel();
        displayPanel = new DisplayPanel();

    }
}

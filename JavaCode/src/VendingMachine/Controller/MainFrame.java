package VendingMachine.Controller;

import VendingMachine.Model.*;
import VendingMachine.View.CoinsPanel;
import VendingMachine.View.DisplayPanel;
import VendingMachine.View.ItemsPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private ItemsPanel itemsPanel;
    private CoinsPanel coinsPanel;
    private DisplayPanel displayPanel;

    public MainFrame() {
        super("Vending machine");
        initComps();
        layoutComps();
        activateFrame();
    }

    private void activateFrame() {
        coinsPanel.setCoinsPanelListener(new CoinsPanelListener() {
            @Override
            public void coinsPanelEventOccurred(CoinsPanelEvent coinsPanelEvent) {
                displayPanel.printAddedMoney(coinsPanelEvent.getTotalInputValue());
            }
        });

        displayPanel.setDisplayPanelListener(new DisplayPanelListener() {
            @Override
            public void displayPanelEventOccurred(DisplayPanelEvent displayPanelEvent) {



            }
        });


    }


    private void loginAdmin() {
        String userName;
        String password;

//        todo: ADMIN LOGIN FRAME
//        todo: ADMIN FRAME
    }

    private void layoutComps() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.8;
        gbc.weighty = 0.6;
        add(displayPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.8;
        gbc.weighty = 0.4;
        add(coinsPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.weightx = 1.2;
        gbc.weighty = 1.0;
        add(itemsPanel, gbc);
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

package VendingMachine.Controller;

import Admin.Controller.AdminLoginListener;
import Admin.Controller.AdminMainFrame;
import Admin.Model.AdminLoginEvent;
import Admin.View.AdminLoginFrame;
import VendingMachine.Model.*;
import VendingMachine.View.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private ItemsPanel itemsPanel;
    private CoinsPanel coinsPanel;
    private DisplayPanel displayPanel;

    public MainFrame() {
        super("Vending machine");
        setLogo();
        initComps();
        layoutComps();
        activateFrame();
    }

    private void setLogo() {
        ImageIcon imageIcon = new ImageIcon("Images/vending-logo-img.jpg");
        setIconImage(imageIcon.getImage());
    }

    private void activateFrame() {
        coinsPanel.setCoinsPanelListener(new CoinsPanelListener() {
            @Override
            public void coinsPanelEventOccurred(CoinsPanelEvent coinsPanelEvent) {
                displayPanel.printAddedMoney(coinsPanelEvent.getTotalInputValue());
                displayPanel.activateCancelTransaction();
            }
        });

        displayPanel.setDisplayPanelListener(new DisplayPanelListener() {
            @Override
            public void displayPanelEventOccurred(DisplayPanelEvent displayPanelEvent) {
                String action = displayPanelEvent.getAction();
                if (action.equalsIgnoreCase("ok")) {
                    int itemId = displayPanel.getItemId();
                    float moneyInput = displayPanel.getInputMoney();
                    Transaction transaction = new Transaction(itemId, moneyInput);
                    String transactionStatus = transaction.getTransactionStatus();
                    float change = transaction.getChange();
                    String formattedChange = String.format("%.2f", change);
                    if (transactionStatus.equalsIgnoreCase("success")) {
                        itemsPanel.updatePanel(transaction.getItem());
                        JOptionPane.showMessageDialog(MainFrame.this, "Successful transaction! \nChange: " + formattedChange + "€", "Successful transaction", JOptionPane.INFORMATION_MESSAGE);
                        displayPanel.reset();
                        coinsPanel.resetCoinsCounter();
                    } else if (transactionStatus.equalsIgnoreCase("stockError")) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Item is out of stock! \nChange: " + formattedChange + "€", "Item out of stock", JOptionPane.ERROR_MESSAGE);
                        displayPanel.reset();
                        coinsPanel.resetCoinsCounter();
                    } else if (transactionStatus.equalsIgnoreCase("moneyError")) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Not enough money for transaction!", "Not enough founds", JOptionPane.ERROR_MESSAGE);
                    } else if (transactionStatus.equalsIgnoreCase("itemError")) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Check input number!", "Item does not exist", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(MainFrame.this, "Unknown action!", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } else if (action.equalsIgnoreCase("del")) {
                    displayPanel.deleteLastNumIdInput();
                } else if (action.equalsIgnoreCase("cancel")) {
                    float inputMoney = displayPanel.getInputMoney();
                    Transaction transaction = new Transaction(inputMoney);
                    float change = transaction.getChange();
                    String formattedChange = String.format("%.2f", change);
                    JOptionPane.showMessageDialog(MainFrame.this, "Cancelled transaction! \nChange: " + formattedChange + "€", "Cancel transaction", JOptionPane.ERROR_MESSAGE);
                    displayPanel.reset();
                    coinsPanel.resetCoinsCounter();
                } else if (action.equalsIgnoreCase("admin")) {
                    AdminLoginFrame adminLoginFrame = new AdminLoginFrame();
                    adminLoginFrame.setAdminLoginListener(new AdminLoginListener() {
                        @Override
                        public void adminLoginEventOccurred(AdminLoginEvent adminLoginEvent) {
                            if (adminLoginEvent.isLoginSuccessful()) {
                                openAdminFrame();
                            }
                        }
                    });


                } else {
                    displayPanel.activateInactiveButtons();
                    displayPanel.printId(action);
                }
            }
        });


    }

    private void openAdminFrame() {
        new AdminMainFrame();
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

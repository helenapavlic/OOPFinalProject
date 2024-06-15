package VendingMachine.View;

import VendingMachine.Model.CoinsPanelEvent;
import VendingMachine.Controller.CoinsPanelListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CoinsPanel extends JPanel {
    private final String[] coinsArr = {"1c", "2c", "5c", "10c", "20c", "50c", "1€", "2€"};
    private JButton button;
    private ArrayList<JButton> buttons;
    private Font numbersFont = new Font("Calibri", Font.BOLD, 18);

    private CoinsPanelListener coinsPanelListener;

    public CoinsPanel() {
        initComponents();
        layoutComponents();
        activatePanel();
        borders();
    }

    public static float labelToFloat(String label) {
        float floatToReturn;
        if (label.endsWith("c")) {
            int cents = Integer.parseInt(label.substring(0, label.length() - 1));
            floatToReturn = cents / 100.0f;
        } else if (label.endsWith("€")) {
            int euros = Integer.parseInt(label.substring(0, label.length() - 1));
            floatToReturn = (float) euros;
        } else {
            System.out.println("no input money");
            floatToReturn = 0.00f;
        }
        return floatToReturn;
    }

    private void activatePanel() {
        for (JButton button : buttons) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String action = button.getActionCommand();
                    float valueOfCoin = labelToFloat(action);
                    CoinsPanelEvent coinsPanelEvent = new CoinsPanelEvent(this, action, valueOfCoin);
                    if (coinsPanelListener != null) {
                        coinsPanelListener.coinsPanelEventOccurred(coinsPanelEvent);
                    }
                }
            });
        }
    }

    public void resetCoinsCounter() {
        CoinsPanelEvent.setTotalInputValue(0);
    }

    private void layoutComponents() {
        setPreferredSize(new Dimension(0, 0));
        setLayout(new GridLayout(2, 4, 4, 4));

        for (JButton button : buttons) {
            add(button);
        }
    }

    private void initComponents() {
        buttons = new ArrayList<>();
        for (String string : coinsArr) {
            buttons.add(coinButton(string));
        }
    }

    private JButton coinButton(String textOnButton) {
        button = new JButton(textOnButton);
        button.setActionCommand(textOnButton);
        button.setFont(numbersFont);
        button.setFocusable(false);
        return button;
    }

    private void borders() {
        Border inner = BorderFactory.createTitledBorder("Coins");
        Border outer = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outer, inner));
    }

    public void setCoinsPanelListener(CoinsPanelListener coinsPanelListener) {
        this.coinsPanelListener = coinsPanelListener;
    }


}

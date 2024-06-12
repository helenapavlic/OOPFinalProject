package VendingMachine;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CoinsPanel extends JPanel{
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

    private void activatePanel() {
//        todo: ACTIVATE
        for (JButton button : buttons) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String action = button.getActionCommand();
                    float valueOfCoin = labelToFloat(action);
//                    System.out.println(valueOfCoin);
                    CoinsPanelEvent coinsPanelEvent = new CoinsPanelEvent(this,action,valueOfCoin);
                    if (coinsPanelListener != null){
                        coinsPanelListener.coinsPanelEventOccurred(coinsPanelEvent);
                    }
                }
            });
        }
    }

    public static float labelToFloat(String label) {
        if (label.endsWith("c")) {
            // Ukloni 'c' i pretvori ostatak u centi
            int cents = Integer.parseInt(label.substring(0, label.length() - 1));
            // Konvertiraj centi u eure
            return cents / 100.0f;
        } else if (label.endsWith("€")) {
            // Ukloni '€' i pretvori ostatak u eure
            int euros = Integer.parseInt(label.substring(0, label.length() - 1));
            return (float) euros;
        } else {
            throw new IllegalArgumentException("Nevažeća oznaka: " + label);
        }
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

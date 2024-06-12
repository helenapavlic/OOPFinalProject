package VendingMachine;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CoinsPanel extends JPanel implements ActionListener {
    private final String[] coinsArr = {"1c", "2c", "5c", "10c", "20c", "50c", "1€", "2€"};
    private JButton button;
    private ArrayList<JButton> buttons;
    private Font numbersFont = new Font("Calibri", Font.BOLD, 18);

    CoinsPanelListener coinsPanelListener;

    public CoinsPanel() {
        initComponents();
        layoutComponents();
        activatePanel();
        borders();
    }

    private void activatePanel() {
//        todo: ACTIVATE
        for (JButton button : buttons) {
            button.addActionListener(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (coinsPanelListener!=null){
            JButton clicked = (JButton) e.getSource();
            coinsPanelListener.coinsPanelEventOccurred(clicked.getActionCommand());
            System.out.println("clicked: " + clicked.getActionCommand());
        }

    }
}

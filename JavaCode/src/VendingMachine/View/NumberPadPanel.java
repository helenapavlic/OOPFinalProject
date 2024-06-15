package VendingMachine.View;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class NumberPadPanel extends JPanel {
    private String[] buttonLabels = {
            "7", "8", "9",
            "4", "5", "6",
            "1", "2", "3",
            "DEL", "0", "OK"
    };
    private JButton numberPadButton;
    private ArrayList<JButton> numPadButtons = new ArrayList<>();
    private Font numFont = new Font("Arial", Font.PLAIN, 20);


    public NumberPadPanel() {
        initComponents();
        layoutComponents();
        activateComponents();
    }

    private void activateComponents() {
        for (JButton button : numPadButtons) {
//            button.addActionListener(this);
        }
    }

    private void layoutComponents() {
        setLayout(new GridLayout(4, 3, 4, 4));
        for (JButton button : numPadButtons) {
            add(button);
        }
    }

    private void initComponents() {
        Dimension dims = getPreferredSize();
        dims.width = 200;
        dims.height = 230;
        setPreferredSize(dims);

        for (String label : buttonLabels) {
            numberPadButton = createNumButton(label);
            if (label.equalsIgnoreCase("del") || label.equalsIgnoreCase("ok")) {
                numberPadButton.setEnabled(false);
            }
        }
    }

    private JButton createNumButton(String label) {
        numberPadButton = new JButton(label);
        numberPadButton.setActionCommand(label);
        numberPadButton.setFocusable(false);
        numberPadButton.setFont(numFont);
        numPadButtons.add(numberPadButton);
        return numberPadButton;
    }

    public void activateInactiveButtons() {
        for (JButton button : numPadButtons) {
            if (!button.isEnabled()) {
                button.setEnabled(true);
            }
        }
    }

    public void deactivateDel() {
        for (JButton button : numPadButtons) {
            if (button.getActionCommand().equalsIgnoreCase("del")) {
                button.setEnabled(false);
            }
        }
    }

    public void reset() {
        for (JButton button : numPadButtons) {
            if (button.getActionCommand().equalsIgnoreCase("OK") || button.getActionCommand().equalsIgnoreCase("del")) {
                button.setEnabled(false);
            }
        }
    }
}

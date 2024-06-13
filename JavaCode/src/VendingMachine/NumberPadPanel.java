package VendingMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NumberPadPanel extends JPanel {
    private String[] buttonLabels = {
            "7", "8", "9",
            "4", "5", "6",
            "1", "2", "3",
            "DEL", "0", "OK"
    };
    private JButton numberButton;
    private ArrayList<JButton> numButtons = new ArrayList<>();
    private Font numFont = new Font("Arial", Font.PLAIN, 20);
    private DisplayPanelListener displayPanelListener;

    public NumberPadPanel() {
        initComponents();
        layoutComponents();
        activateComponents();
    }

    private void activateComponents() {
        for (JButton button : numButtons) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String action = button.getActionCommand();
                    boolean okButtonPressed = false;
                    boolean delButtonPressed = false;
                    boolean isIntNumberPressed = false;
                    if (action.equalsIgnoreCase("ok")) {
                        okButtonPressed = true;
                    } else if (action.equalsIgnoreCase("del")) {
                        delButtonPressed = true;
                    } else {
                        isIntNumberPressed = true;
                    }
                    NumberPadEvent numberPadEvent = new NumberPadEvent(this,action,okButtonPressed,delButtonPressed,isIntNumberPressed);
                    if (displayPanelListener != null) {
                        displayPanelListener.numberPadEventOccurred(numberPadEvent);
                    }
                }
            });
        }

    }

    private void layoutComponents() {
        setLayout(new GridLayout(4, 3, 4, 4));
        for (JButton button : numButtons) {
            add(button);
        }
    }

    private void initComponents() {
        Dimension dims = getPreferredSize();
        dims.width = 200;
        dims.height = 230;
        setPreferredSize(dims);

        for (String label : buttonLabels) {
            numberButton = createNumButton(label);
            if (label.equalsIgnoreCase("del") || label.equalsIgnoreCase("ok")) {
                numberButton.setEnabled(false);
            }
        }
    }

    private JButton createNumButton(String label) {
        numberButton = new JButton(label);
        numberButton.setActionCommand(label);
        numberButton.setFocusable(false);
        numberButton.setFont(numFont);
        numButtons.add(numberButton);
        System.out.println("added button: " + numberButton.getActionCommand());
        return numberButton;
    }

    public void activateInactiveButtons() {
        for (JButton button : numButtons) {
            if (!button.isEnabled()) {
                button.setEnabled(true);
            }
        }
    }

    public void deactivateDel() {
        for (JButton button : numButtons) {
            if (button.getActionCommand().equalsIgnoreCase("del")){
                button.setEnabled(false);
            }
        }
    }
    public void reset() {
        for (JButton button : numButtons) {
            if (button.getActionCommand().equalsIgnoreCase("OK") || button.getActionCommand().equalsIgnoreCase("del")) {
                button.setEnabled(false);
            }
        }
    }

    public void setDisplayPanelListener(DisplayPanelListener displayPanelListener) {
        this.displayPanelListener = displayPanelListener;
    }
}

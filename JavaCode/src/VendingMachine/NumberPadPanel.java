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
    private NumberPadListener numberPadListener;
    private Font numFont = new Font("Arial", Font.PLAIN, 20);

    public NumberPadPanel() {
        initComponents();
        layoutComponents();
        activateComponents();
    }

    private void activateComponents() {
        for (JButton button : numButtons){
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (numberPadListener != null){
                        numberPadListener.numberPadButtonPressed(button.getActionCommand());
                    }
                }
            });
        }

    }

    private void layoutComponents() {
        setLayout(new GridLayout(4,3,4,4));
        for (JButton button : numButtons){
            add(button);
        }
    }

    private void initComponents() {
        Dimension dims = getPreferredSize();
        dims.width = 200;
        dims.height = 230;
        setPreferredSize(dims);

        for (String label: buttonLabels){
            numberButton = createNumButton(label);
            if (label.equalsIgnoreCase("del") || label.equalsIgnoreCase("ok")){
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

    public void setNumberPadListener(NumberPadListener numberPadListener) {
        this.numberPadListener = numberPadListener;
    }

    public void activateInactiveButtons(){
        for (JButton button : numButtons){
           if (!button.isEnabled()){
               button.setEnabled(true);
           }
        }
    }

    public void reset(){
        for (JButton button : numButtons){
            if (button.getActionCommand().equalsIgnoreCase("OK") ||button.getActionCommand().equalsIgnoreCase("del")){
                button.setEnabled(false);
            }
        }
    }
}

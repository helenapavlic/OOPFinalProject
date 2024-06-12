package VendingMachine;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DisplayPanel extends JPanel {
    NumberPadPanel numberPadPanel;
    TextPanel textPanel;

    public DisplayPanel(){
        setPreferredSize(new Dimension(0, 0));
        initComponents();
        layoutComponents();
        activatePanel();
        borders();
    }

    private void activatePanel() {
        numberPadPanel.setNumberPadListener(new NumberPadListener() {
            @Override
            public void numberPadButtonPressed(String buttonAction) {
                if (!buttonAction.equalsIgnoreCase("ok")&&!buttonAction.equalsIgnoreCase("del")){
                    numberPadPanel.activateInactiveButtons();
                    System.out.println(buttonAction);
                } else if(buttonAction.equalsIgnoreCase("ok")){
                    numberPadPanel.reset();
                    System.out.println(buttonAction);
                } else {
                    System.out.println(buttonAction);
                }
            }
        });
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(4,4));
        add(numberPadPanel,BorderLayout.CENTER);
    }

    private void initComponents() {
        numberPadPanel = new NumberPadPanel();
    }

    private void borders() {
        Border inner = BorderFactory.createTitledBorder("Display");
        Border outer = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outer,inner));
    }
}

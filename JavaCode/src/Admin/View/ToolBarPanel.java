package Admin.View;


import VendingMachine.Controller.DisplayPanelListener;
import VendingMachine.Model.DisplayPanelEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBarPanel extends JPanel {
    private JButton adminButton;
    private JButton cancelButton;
    private Font numFont = new Font("Arial", Font.PLAIN, 18);
    private DisplayPanelListener displayPanelListener;


    public ToolBarPanel() {
        initComponents();
        layoutComponents();
        activateComponents();
    }

    private void activateComponents() {
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String action = adminButton.getActionCommand();
                DisplayPanelEvent displayPanelEvent = new DisplayPanelEvent(this, action);
                if (displayPanelEvent != null) {
                    displayPanelListener.displayPanelEventOccurred(displayPanelEvent);

                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String action = cancelButton.getActionCommand();
                DisplayPanelEvent displayPanelEvent = new DisplayPanelEvent(this, action);
                if (displayPanelEvent != null) {
                    displayPanelListener.displayPanelEventOccurred(displayPanelEvent);

                }
            }
        });
    }

    private void layoutComponents() {
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(adminButton);
        add(cancelButton);
    }

    private void initComponents() {
        adminButton = new JButton("Admin");
        adminButton.setFocusable(false);
        adminButton.setActionCommand("ADMIN");
        adminButton.setFont(numFont);

        cancelButton = new JButton("Cancel");
        cancelButton.setFocusable(false);
        cancelButton.setActionCommand("CANCEL");
        cancelButton.setEnabled(false);
        cancelButton.setFont(numFont);
    }

    public void activateCancel() {
        cancelButton.setEnabled(true);
    }

    public void deactivateCancel() {
        cancelButton.setEnabled(false);
    }

    public void setDisplayPanelListener(DisplayPanelListener displayPanelListener) {
        this.displayPanelListener = displayPanelListener;
    }
}

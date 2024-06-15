package VendingMachine.View;


import javax.swing.*;
import java.awt.*;

public class ToolBarPanel extends JPanel{
    private JButton adminButton;
    private JButton cancelButton;
    private Font numFont = new Font("Arial", Font.PLAIN, 18);


    public ToolBarPanel(){
        initComponents();
        layoutComponents();
        activateComponents();
    }

    private void activateComponents() {
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
        cancelButton.setFont(numFont);

    }

}

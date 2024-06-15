package VendingMachine.View;

import javax.swing.*;
import java.awt.*;

public class AdminMainFrame extends JFrame {
    private ViewPanel adminViewPanel;
    private FilterPanel adminFilterPanel;
    private AdminMenuBar adminMenuBar;

    public AdminMainFrame() {
        super("Admin");
        initComponents();
        layoutComponents();
        activateComponents();

    }

    private void activateComponents() {

    }

    private void layoutComponents() {
        setLayout(new BorderLayout(5, 5));
        add(adminMenuBar, BorderLayout.NORTH);
        add(adminViewPanel, BorderLayout.EAST);
        add(adminFilterPanel, BorderLayout.WEST);
    }

    private void initComponents() {
        setSize(900, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        adminMenuBar = new AdminMenuBar();
        adminViewPanel = new ViewPanel();
        adminFilterPanel = new FilterPanel();
    }
}


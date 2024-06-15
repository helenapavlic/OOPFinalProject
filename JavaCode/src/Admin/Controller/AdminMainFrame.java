package Admin.Controller;

import Admin.View.AdminMenuBar;
import Admin.View.FilterPanel;
import Admin.View.ViewPanel;

import javax.swing.*;
import java.awt.*;

public class AdminMainFrame extends JFrame {
    ViewPanel adminViewPanel;
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
        add(adminViewPanel, BorderLayout.CENTER);
        add(adminFilterPanel, BorderLayout.SOUTH);
    }

    private void initComponents() {
        setSize(900, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        adminMenuBar = new AdminMenuBar();
        adminViewPanel = new ViewPanel();
        adminFilterPanel = new FilterPanel();
    }
}


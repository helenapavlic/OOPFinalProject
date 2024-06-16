package Admin.View;

import Admin.Controller.AdminMenuBarListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminMenuBar extends JMenuBar implements ActionListener {
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenuItem exportDataItem;
    private JMenuItem importAllDataItems;
    private JMenuItem exitItem;
    private JMenuItem clearTableItem;
    private JMenuItem clearFilterItems;
    private AdminMenuBarListener adminMenuBarListener;
    private JMenuItem importSelectedDataItems;


    public AdminMenuBar() {
        initMenuBar();
        activateMenuBar();
    }

    private void activateMenuBar() {
        exportDataItem.addActionListener(this);
        importAllDataItems.addActionListener(this);
        importSelectedDataItems.addActionListener(this);
        exitItem.addActionListener(this);
        clearTableItem.addActionListener(this);
        clearFilterItems.addActionListener(this);
        // set ActionCommands
        exportDataItem.setActionCommand("EXPORT");
        importAllDataItems.setActionCommand("IMPORT_ALL");
        importSelectedDataItems.setActionCommand("IMPORT_SELECTED");
        exitItem.setActionCommand("EXIT");
        clearTableItem.setActionCommand("CLEAR_TABLE");
        clearFilterItems.setActionCommand("CLEAR_FILTER");
    }

    private void initMenuBar() {

        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");

        exportDataItem = new JMenuItem("Export Data");
        importSelectedDataItems = new JMenuItem("Import Data");
        importAllDataItems = new JMenuItem("Import All Data");
        exitItem = new JMenuItem("Exit");
        fileMenu.add(exportDataItem);
        fileMenu.add(importAllDataItems);
        fileMenu.add(importSelectedDataItems);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        add(fileMenu);

        // Get Mneumonics
        fileMenu.setMnemonic('F');
        editMenu.setMnemonic('E');
        // set accelerators
        exportDataItem.setAccelerator(KeyStroke.getKeyStroke("control E"));
        importAllDataItems.setAccelerator(KeyStroke.getKeyStroke("control O"));
        importSelectedDataItems.setAccelerator(KeyStroke.getKeyStroke("control I"));
        exitItem.setAccelerator(KeyStroke.getKeyStroke("control Q"));

        clearTableItem = new JMenuItem("Clear Table");
        clearFilterItems = new JMenuItem("Clear Filters");
        editMenu.add(clearFilterItems);
        editMenu.add(clearTableItem);
        add(editMenu);

        clearTableItem.setAccelerator(KeyStroke.getKeyStroke("control T"));
        clearFilterItems.setAccelerator(KeyStroke.getKeyStroke("control F"));
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == exportDataItem) {
            if (adminMenuBarListener != null) {
                adminMenuBarListener.menuBarEventOccurred(actionEvent.getActionCommand());
            }
        } else if (actionEvent.getSource() == importAllDataItems) {
            if (adminMenuBarListener != null) {
                adminMenuBarListener.menuBarEventOccurred(actionEvent.getActionCommand());
            }
        } else if(actionEvent.getSource() == importSelectedDataItems){
            if (adminMenuBarListener != null){
                adminMenuBarListener.menuBarEventOccurred(actionEvent.getActionCommand());
            }
        }else if (actionEvent.getSource() == exitItem) {
            if (adminMenuBarListener != null) {
                adminMenuBarListener.menuBarEventOccurred(actionEvent.getActionCommand());
            }
        } else if (actionEvent.getSource() == clearTableItem) {
            if (adminMenuBarListener != null) {
                adminMenuBarListener.menuBarEventOccurred(actionEvent.getActionCommand());
            }
        } else if (actionEvent.getSource() == clearFilterItems) {
            if (adminMenuBarListener != null) {
                adminMenuBarListener.menuBarEventOccurred(actionEvent.getActionCommand());
            }
        }
    }

    public void setMenuBarListener(AdminMenuBarListener listener) {
        this.adminMenuBarListener = listener;
    }
}


package VendingMachine.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminMenuBar extends JMenuBar implements ActionListener {

    private JMenu fileMenu;
    private JMenu editMenu;

    private JMenuItem exportDataItem;
    private JMenuItem importDataItem;
    private JMenuItem exitItem;

    private JMenuItem clearTextItem;
    private AdminMenuBarListener adminMenuBarListener;


    public AdminMenuBar(){

        initMenuBar();
        activateMenuBar();
    }

    private void activateMenuBar() {
        exportDataItem.addActionListener(this);
        importDataItem.addActionListener(this);
        exitItem.addActionListener(this);
        clearTextItem.addActionListener(this);
        // set ActionCommands
        exportDataItem.setActionCommand("Export Data");
        importDataItem.setActionCommand("Import Data");
        exitItem.setActionCommand("Exit");
        clearTextItem.setActionCommand("Clear Text");
    }

    private void initMenuBar() {

        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");

        exportDataItem = new JMenuItem("Export Data");
        importDataItem = new JMenuItem("Import Data");
        exitItem = new JMenuItem("Exit");
        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        add(fileMenu);

        // Get Mneumonics
        fileMenu.setMnemonic('F');
        editMenu.setMnemonic('E');
        // set accelerators
        exportDataItem.setAccelerator(KeyStroke.getKeyStroke("control E"));
        importDataItem.setAccelerator(KeyStroke.getKeyStroke("control I"));
        exitItem.setAccelerator(KeyStroke.getKeyStroke("control X"));

        clearTextItem = new JMenuItem("Clear Text");
        editMenu.add(clearTextItem);
        add(editMenu);
        // accelerator for clear text
        clearTextItem.setAccelerator(KeyStroke.getKeyStroke("control T"));

    }

    @Override
    public void actionPerformed(ActionEvent aev){

        if(aev.getSource() == exportDataItem){
            if(adminMenuBarListener != null){
                adminMenuBarListener.menuBarEventOccurred("Export Data");
            }
        } else if(aev.getSource() == importDataItem){
            if(adminMenuBarListener != null){
                adminMenuBarListener.menuBarEventOccurred("Import Data");
            }
        } else if(aev.getSource() == exitItem){
            if(adminMenuBarListener != null){
                adminMenuBarListener.menuBarEventOccurred("Exit");
            }
        } else if(aev.getSource() == clearTextItem){
            if(adminMenuBarListener != null){
                adminMenuBarListener.menuBarEventOccurred("Clear Text");
            }
        }

    }

    public void setMenuBarListener(AdminMenuBarListener listener) {
        this.adminMenuBarListener = listener;
    }
}


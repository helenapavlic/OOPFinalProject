package Admin.Controller;

import Admin.Model.ItemFilterPanelEvent;
import Admin.Model.TransactionFilterPanelEvent;
import Admin.View.AdminMenuBar;
import Admin.View.FilterPanel;
import Admin.View.ViewPanel;
import VendingMachine.Model.Transaction;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AdminMainFrame extends JFrame {
    private JFileChooser fileChooser1;
    private JFileChooser fileChooser2;
    private ViewPanel adminViewPanel;
    private FilterPanel adminFilterPanel;
    private AdminMenuBar adminMenuBar;
    private String filePath = "Data/transctions.csv";

    public AdminMainFrame() {
        super("Admin");

        setSize(900, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        initFileChoosers();
        initComponents();
        layoutComponents();
        activateComponents();
    }

    private void initFileChoosers() {
        fileChooser1 = new JFileChooser();
        fileChooser2 = new JFileChooser();
        customizeJFileChooser(fileChooser1, true);
        customizeJFileChooser(fileChooser2, false);
    }

    private void activateComponents() {
        adminFilterPanel.setFilterPanelListener(new FilterPanelListener() {
            @Override
            public void transactionFilterPanelEventOccurred(TransactionFilterPanelEvent transactionFilterPanelEvent) {
                String[] filters = transactionFilterPanelEvent.getFilters();
                System.out.println("Transaction Filter Panel Event Occurred:");
                for (int i = 0; i < filters.length; i++) {
                    System.out.println("Filter " + (i + 1) + ": " + filters[i]);
                }
            }

            @Override
            public void itemFilterPanelEventOccurred(ItemFilterPanelEvent itemFilterPanelEvent) {
                String[] filters = itemFilterPanelEvent.getFilters();
                System.out.println("Item Filter Panel Event Occurred:");
                for (int i = 0; i < filters.length; i++) {
                    System.out.println("Filter " + (i + 1) + ": " + filters[i]);
                }
            }
        });

        adminMenuBar.setMenuBarListener(new AdminMenuBarListener() {
            @Override
            public void menuBarEventOccurred(String menuBarActionCommandString) {
                System.out.println(menuBarActionCommandString);

                if (menuBarActionCommandString.equalsIgnoreCase("EXIT")) {
                    int val = JOptionPane.showConfirmDialog(AdminMainFrame.this, "Do you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
                    if (val == JOptionPane.YES_OPTION) {
                        AdminMainFrame.this.dispose();
                    }
                } else if (menuBarActionCommandString.equalsIgnoreCase("IMPORT_ALL")) {
                    ArrayList<Transaction> transactions = readTransactionsFromCsv();
                    adminViewPanel.loadDataFromCSV(filePath);
                    JOptionPane.showMessageDialog(AdminMainFrame.this, "Transactions loaded successfully from " + filePath, "Load Transactions", JOptionPane.INFORMATION_MESSAGE);
                } else if (menuBarActionCommandString.equalsIgnoreCase("IMPORT_SELECTED")) {
                    int returnValue = fileChooser1.showOpenDialog(AdminMainFrame.this);
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser1.getSelectedFile();
                        filePath = selectedFile.getAbsolutePath();
                        ArrayList<Transaction> transactions = readTransactionsFromCsv();
//                        adminViewPanel.clearData();
                        adminViewPanel.loadDataFromCSV(filePath);
                        JOptionPane.showMessageDialog(AdminMainFrame.this, "Transactions loaded successfully from " + filePath, "Load Transactions", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else if (menuBarActionCommandString.equalsIgnoreCase("EXPORT")) {
                    int returnValue = fileChooser2.showSaveDialog(AdminMainFrame.this);
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        File fileToSave = fileChooser2.getSelectedFile();
                        if (!fileToSave.getAbsolutePath().endsWith(".csv")) {
                            fileToSave = new File(fileToSave.getAbsolutePath() + ".csv");
                        }
                        exportDataToCSV(fileToSave.getAbsolutePath());
                        JOptionPane.showMessageDialog(AdminMainFrame.this, "Transactions saved successfully to " + fileToSave.getAbsolutePath(), "Save Transactions", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else if (menuBarActionCommandString.equalsIgnoreCase("CLEAR_TABLE")) {
                    adminViewPanel.clearData();
                    JOptionPane.showMessageDialog(AdminMainFrame.this, "Table data cleared.", "Clear Table", JOptionPane.INFORMATION_MESSAGE);
                } else if (menuBarActionCommandString.equalsIgnoreCase("CLEAR_FILTER")) {
                    adminFilterPanel.ResetAll();
                    JOptionPane.showMessageDialog(AdminMainFrame.this, "Filter reset.", "Reset Filter", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private void exportDataToCSV(String absolutePath) {
        try (FileWriter writer = new FileWriter(absolutePath)) {
            // Write header
            writer.write("ID,DATE AND TIME,STATUS,INPUT MONEY,CHANGE,ITEM ID,ITEM NAME,ITEM PRICE,QUANTITY\n");

            // Write data from the table
            List<Transaction> transactions = adminViewPanel.getTransactionsFromTable();
            for (Transaction transaction : transactions) {
                writer.write(transaction.getTransactionId() + "," +
                        transaction.getDateAndTime() + "," +
                        transaction.getTransactionStatus() + "," +
                        transaction.getInputMoney() + "," +
                        transaction.getChange() + "," +
                        transaction.getItemId() + "," +
                        transaction.getItemName() + "," +
                        transaction.getItemPrice() + "," +
                        transaction.getRemainingQuantity() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving transactions: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(5, 5));
        setJMenuBar(adminMenuBar);
        add(adminViewPanel, BorderLayout.CENTER);
        add(adminFilterPanel, BorderLayout.SOUTH);
    }

    private void initComponents() {
        adminMenuBar = new AdminMenuBar();
        adminViewPanel = new ViewPanel();
        adminFilterPanel = new FilterPanel();
    }

    private void customizeJFileChooser(JFileChooser fileChooser, boolean onlyCSV) {
        // Set default directory
        fileChooser.setCurrentDirectory(new File(filePath));
        // Set file filter
        FileNameExtensionFilter filterCSV = new FileNameExtensionFilter("CSV files", "csv");
        fileChooser.addChoosableFileFilter(filterCSV);
        if (!onlyCSV) {
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("All files", "*.*"));
        }
        fileChooser.setFileFilter(filterCSV);
    }

    public ArrayList<Transaction> readTransactionsFromCsv() {
        ArrayList<Transaction> transactions = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            // Skip header line
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] values = line.split(",");

                // Assuming the CSV file has columns: id, date, amount, description
                int transactionId = Integer.parseInt(values[0]);
                String dateAndTime = values[1];
                String transactionStatus = values[2];
                float inputMoney = Float.parseFloat(values[3]);
                float change = Float.parseFloat(values[4]);
                int itemId = Integer.parseInt(values[5]);
                String itemName = values[6];
                float itemPrice = Float.parseFloat(values[7]);
                int remainingQuantity = Integer.parseInt(values[8]);

                Transaction transaction = new Transaction(transactionId, dateAndTime, transactionStatus, inputMoney, change, itemId, itemName, itemPrice, remainingQuantity);
                transactions.add(transaction);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading transactions: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return transactions;
    }
}

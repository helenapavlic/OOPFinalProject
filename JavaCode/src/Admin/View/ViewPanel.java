package Admin.View;

import VendingMachine.Model.AUX_CLS;
import VendingMachine.Model.Transaction;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ViewPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;

    public ViewPanel() {
        InitComponents();
        layoutComponents();
        decorate();
    }

    private void decorate() {
        Border inner = BorderFactory.createTitledBorder("Transactions");
        Border outer = BorderFactory.createEmptyBorder(2, 2, 2, 2);
        setBorder(BorderFactory.createCompoundBorder(outer, inner));
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, gbc);
    }

    private void InitComponents() {
        String[] columnNames = {"ID", "DATE AND TIME", "STATUS", "INPUT MONEY", "CHANGE", "ITEM ID", "ITEM NAME", "ITEM PRICE", "QUANTITY"};

        int initialRowCount = 0;
        Object[][] data = new Object[initialRowCount][columnNames.length];
        model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        table.setFont(new Font("Arial", Font.PLAIN, 10));

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.BOLD, 12));

        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) tableHeader.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        setColumnWidths();

        customizeTableAppearance();
    }

    private void setColumnWidths() {
        int[] columnWidths = {50, 150, 100, 100, 90, 50, 140, 100, 65}; // Postavi širinu svakog stupca

        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnWidths.length; i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
            column.setMinWidth(columnWidths[i]);
            column.setMaxWidth(columnWidths[i]);
        }
    }

    private void customizeTableAppearance() {
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setRowHeight(25);

        Color backgroundColor = new Color(240, 240, 240);
        Color textColor = Color.BLACK;

        JTableHeader header = table.getTableHeader();
        header.setBackground(backgroundColor);
        header.setForeground(textColor);
        header.setPreferredSize(new Dimension(0, 30));
        header.setReorderingAllowed(false);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        cellRenderer.setBackground(backgroundColor);
        cellRenderer.setForeground(textColor);
        table.setDefaultRenderer(Object.class, cellRenderer);
    }

    public void loadDataFromCSV(String filePath) {
        List<String[]> records = AUX_CLS.readTransactionsFromCSV(filePath);
        for (String[] record : records) {
            model.addRow(record);
        }
    }

    public void clearData() {
        model.setRowCount(0);
    }

    public List<Transaction> getTransactionsFromTable() {
        List<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            int transactionId = Integer.parseInt(model.getValueAt(i, 0).toString());
            String dateAndTime = model.getValueAt(i, 1).toString();
            String transactionStatus = model.getValueAt(i, 2).toString();
            float inputMoney = Float.parseFloat(model.getValueAt(i, 3).toString());
            float change = Float.parseFloat(model.getValueAt(i, 4).toString());
            int itemId = Integer.parseInt(model.getValueAt(i, 5).toString());
            String itemName = model.getValueAt(i, 6).toString();
            float itemPrice = Float.parseFloat(model.getValueAt(i, 7).toString());
            int remainingQuantity = Integer.parseInt(model.getValueAt(i, 8).toString());

            Transaction transaction = new Transaction(transactionId, dateAndTime, transactionStatus, inputMoney, change, itemId, itemName, itemPrice, remainingQuantity);
            transactions.add(transaction);
        }
        return transactions;
    }

    public void addTransactions(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            model.addRow(new Object[]{
                    transaction.getTransactionId(),
                    transaction.getDateAndTime(),
                    transaction.getTransactionStatus(),
                    transaction.getInputMoney(),
                    transaction.getChange(),
                    transaction.getItemId(),
                    transaction.getItemName(),
                    transaction.getItemPrice(),
                    transaction.getRemainingQuantity()
            });
        }
    }
}

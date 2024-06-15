package Admin.View;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;
import java.awt.*;

public class ViewPanel extends JPanel {
    private JTable table;

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
        String[] columnNames = {"ID", "DATE AND TIME","STATUS", "INPUT MONEY", "CHANGE", "ITEM ID", "ITEM NAME","ITEM PRICE", "QUANTITY"};

        int initialRowCount = 0;
        Object[][] data = new Object[initialRowCount][columnNames.length];
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
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
//        int totalWidth = 850;
        int[] columnWidths = {50, 150, 100, 100, 100, 50, 150, 100, 69}; // Postavi širinu svakog stupca

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
}


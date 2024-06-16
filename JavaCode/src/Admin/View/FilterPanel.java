package Admin.View;

import Admin.Controller.FilterPanelListener;

import javax.swing.*;
import java.awt.*;

public class FilterPanel extends JPanel {
    private TransactionFilterPanel transactionFilterPanel;
    private ItemFilterPanel itemFilterPanel;
    private FilterPanelListener filterPanelListener;

    public FilterPanel() {
        InitComponents();
        layoutComponents();
        activateComponents();
    }

    private void activateComponents() {

    }


    private void layoutComponents() {
        setLayout(new BorderLayout(5, 5));
        add(itemFilterPanel, BorderLayout.CENTER);
        add(transactionFilterPanel, BorderLayout.WEST);
    }

    private void InitComponents() {
        setPreferredSize(new Dimension(getWidth(), 250));
        transactionFilterPanel = new TransactionFilterPanel();
        itemFilterPanel = new ItemFilterPanel();
    }

    public void setFilterPanelListener(FilterPanelListener filterPanelListener) {
        this.filterPanelListener = filterPanelListener;
        transactionFilterPanel.setFilterPanelListener(filterPanelListener);
        itemFilterPanel.setFilterPanelListener(filterPanelListener);
    }

    public void ResetAll() {
        itemFilterPanel.reset();
        transactionFilterPanel.reset();
    }

    public void resetItemFilters() {
        itemFilterPanel.reset();
    }

    public void resetTransactionFilters() {
        transactionFilterPanel.reset();
    }
}

package VendingMachine;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ItemsPanel extends JPanel {
    private static List<JPanel> itemsPanels;
    private final int NUM_PLACES = 12;
    JLabel productIdLabel;
    JLabel productNameLabel;
    JLabel priceLabel;
    JLabel quantityLabel;
    private JPanel itemPanel;
    private Item item;
    private Font numbersFont = new Font("Calibri", Font.BOLD, 30);
    private Font textFont = new Font("Calibri", Font.PLAIN, 20);
    private Font detailsFont = new Font("Calibri", Font.BOLD, 14);

    public ItemsPanel() {
        initComponents();
        layoutComponents();
        activatePanel();
        borders();

    }

    private void activatePanel() {
//        todo :ACTIVATE PANEL

    }

    private void initComponents() {
        List<allItems> items = allItems.getAllItems();
        itemsPanels = new ArrayList<>();
        for (allItems itemFromEnum : items) {
            item = new Item(itemFromEnum.getItemName(), (float) itemFromEnum.getPrice());
            if (item.getId() <= NUM_PLACES) {
                itemsPanels.add(createItemPanel());
            } else {
                System.out.println("there is no space for item in vending machine");
            }
        }
    }

    private void layoutComponents() {
        setLayout(new GridLayout(4, 3, 8, 8));
        setPreferredSize(new Dimension(0, 0));
        for (JPanel panel : itemsPanels) {
            add(panel);
        }
    }

    private JPanel createItemPanel() {
        itemPanel = new JPanel(new GridLayout(4, 1));
        itemPanel.setBorder(BorderFactory.createEtchedBorder());

        productIdLabel = new JLabel(String.valueOf(item.getId()));
        productNameLabel = new JLabel(item.getItemName());
        priceLabel = new JLabel("Price: " + item.getPrice() + " €");
        quantityLabel = new JLabel("Quantity: " + item.getQuantity());

        productIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
        productNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        quantityLabel.setHorizontalAlignment(SwingConstants.CENTER);

        styleComponents();
        itemPanel.add(productIdLabel);
        itemPanel.add(productNameLabel);
        itemPanel.add(priceLabel);
        itemPanel.add(quantityLabel);

        return itemPanel;
    }

    private void styleComponents() {
        quantityLabel.setFont(detailsFont);
        productIdLabel.setFont(numbersFont);
        productNameLabel.setFont(textFont);
        priceLabel.setFont(detailsFont);
    }

    private void borders() {
        Border inner = BorderFactory.createTitledBorder("Items");
        Border outer = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outer, inner));
    }
}

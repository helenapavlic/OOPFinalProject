package Admin.View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FilterPanel extends JPanel {
    private TransactionFilterFormPanel transactionFilterFormPanel;
    private ItemFilterFormPanel itemFilterFormPanel;
    public FilterPanel() {
        InitComponents();
        layoutComponents();
    }


    private void layoutComponents() {
        setLayout(new BorderLayout(5,5));


        add(itemFilterFormPanel,BorderLayout.CENTER);
        add(transactionFilterFormPanel,BorderLayout.WEST);

    }

    private void InitComponents() {
        setPreferredSize(new Dimension(getWidth(), 250));
        transactionFilterFormPanel = new TransactionFilterFormPanel();
        itemFilterFormPanel = new ItemFilterFormPanel();


    }

}

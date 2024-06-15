package VendingMachine.View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DisplayPanel extends JPanel {
    private NumberPadPanel numberPadPanel;
    private TextPanel textPanel;
    private ToolBarPanel toolBarPanel;


    public DisplayPanel() {
        setPreferredSize(new Dimension(0, 0));
        initComponents();
        layoutComponents();
        activatePanel();
        borders();
    }

    private void activatePanel() {


    }

    private void layoutComponents() {
        setLayout(new BorderLayout(4, 4));
        add(numberPadPanel, BorderLayout.CENTER);
        add(textPanel, BorderLayout.NORTH);
        add(toolBarPanel, BorderLayout.SOUTH);
    }

    private void initComponents() {
        numberPadPanel = new NumberPadPanel();
        textPanel = new TextPanel();
        toolBarPanel = new ToolBarPanel();
    }

    private void borders() {
        Border inner = BorderFactory.createTitledBorder("Display");
        Border outer = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outer, inner));
    }

    public void printAddedMoney(float totalMoney) {
        String formattedValue = String.format("%.2f", totalMoney);
        textPanel.showAddedMoney(formattedValue);
    }

    public void reset() {
        textPanel.reset();
        numberPadPanel.reset();
    }

    public float getInputMoney() {
        return textPanel.readTotalMoneyInputTextField();
    }

}

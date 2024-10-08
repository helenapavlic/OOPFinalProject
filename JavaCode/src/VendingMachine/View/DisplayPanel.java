package VendingMachine.View;

import Admin.View.ToolBarPanel;
import VendingMachine.Controller.DisplayPanelListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DisplayPanel extends JPanel {
    private NumberPadPanel numberPadPanel;
    private TextPanel textPanel;
    private ToolBarPanel toolBarPanel;
    private DisplayPanelListener displayPanelListener;


    public DisplayPanel() {
        setPreferredSize(new Dimension(0, 0));
        initComponents();
        layoutComponents();
        borders();
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

    public void printId(String num) {
        textPanel.setSelectedItemId(num);
    }

    public void reset() {
        textPanel.reset();
        numberPadPanel.reset();
        toolBarPanel.deactivateCancel();
    }

    public void activateInactiveButtons() {
        numberPadPanel.activateNumPadButtons();
        toolBarPanel.activateCancel();

    }

    public float getInputMoney() {
        return textPanel.readTotalMoneyInputTextField();
    }

    public int getItemId() {
        return textPanel.readItemIdTextFiled();
    }

    public void deleteLastNumIdInput() {
        int lenOfNewText = textPanel.deleteLastNumberIdInput();
        if (lenOfNewText < 1) {
            numberPadPanel.reset();
        }
    }

    public void setDisplayPanelListener(DisplayPanelListener displayPanelListener) {
        this.displayPanelListener = displayPanelListener;
        toolBarPanel.setDisplayPanelListener(displayPanelListener);
        numberPadPanel.setDisplayPanelListener(displayPanelListener);
    }

    public void activateCancelTransaction() {
        toolBarPanel.activateCancel();

    }
}

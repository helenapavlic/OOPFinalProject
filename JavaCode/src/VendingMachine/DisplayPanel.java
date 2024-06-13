package VendingMachine;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DisplayPanel extends JPanel {
    private NumberPadPanel numberPadPanel;
    private TextPanel textPanel;
    private DisplayPanelToolBar displayPanelToolBar;
    private DisplayPanelListener displayPanelListener;

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
        add(displayPanelToolBar, BorderLayout.SOUTH);
    }

    private void initComponents() {
        numberPadPanel = new NumberPadPanel();
        textPanel = new TextPanel();
        displayPanelToolBar = new DisplayPanelToolBar();
    }

    private void borders() {
        Border inner = BorderFactory.createTitledBorder("Display");
        Border outer = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outer, inner));
    }

    public void printAddedMoney(float totalMoney) {
        String formattedValue = String.format("%.2f", totalMoney);
        textPanel.printAddedMoney(formattedValue);
    }

    public void printInputNumber(String text) {
        textPanel.printNumSelection(text);
    }

    public void activateButtons() {
        numberPadPanel.activateInactiveButtons();
    }

    public void deleteLastInput() {
        int lenOfNewStr = textPanel.deleteLastInputNum();
        if (lenOfNewStr == 0) {
            numberPadPanel.deactivateDel();
        }
    }

    public void reset() {
        textPanel.clearTextArea();
        numberPadPanel.reset();
    }

    public int getUserInput() {
        return Integer.parseInt(textPanel.readInputTextField());
    }

    public float getInputMoney() {
        return textPanel.readTotalTextField();
    }

    public void setDisplayPanelListener(DisplayPanelListener displayPanelListener) {
        this.displayPanelListener = displayPanelListener;
        numberPadPanel.setDisplayPanelListener(displayPanelListener);
    }
}

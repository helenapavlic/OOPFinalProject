package Admin.View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ItemFilterFormPanel extends JPanel {
    private JTextField dateFrom;
    private JTextField dateTo;

    private JCheckBox isSuccessful;
    private JComboBox<String> transactionStatus;

    private JTextField transactionId;
    private JTextField moneyLowerBound;
    private JTextField moneyUpperBound;
    private JButton apply;
    private JButton reset;

    public ItemFilterFormPanel() {
        InitComponents();
        layoutComponents();
        decorate();
        activatePanel();
    }

    private void activatePanel() {

    }

    private void decorate() {
        Border inner = BorderFactory.createTitledBorder("Item filter");
        Border outer = BorderFactory.createEmptyBorder(2, 2, 2, 2);
        setBorder(BorderFactory.createCompoundBorder(outer, inner));

    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx = 0;
        gc.gridy = 0;

        gc.anchor = GridBagConstraints.LINE_START;

        gc.gridy++;
        add(new JLabel("Date from:"), gc);
        add(Box.createHorizontalStrut(20),gc);
        gc.gridx++;
        add(dateFrom, gc);

        add(Box.createVerticalStrut(40), gc);

        gc.gridx = 0;
        gc.gridy++;
        add(new JLabel("Date to: "),gc);
        gc.gridx++;
        add(dateTo,gc);

//        gc.gridy++;
//        add(Box.createVerticalStrut(25), gc);
//
//        gc.gridy++;
//        add(new JLabel("Product category:"), gc);
//        gc.gridy++;
//        add(productCatScrollPane, gc);
//
//        gc.gridy = 0;
//        gc.gridx++;
//        add(Box.createHorizontalStrut(25), gc);
//
//        gc.gridx++;
//        gc.gridy++;
////        add(Box.createVerticalStrut(5), gc);
//
//        gc.anchor = GridBagConstraints.FIRST_LINE_START;
//        add(new JLabel("Additional choices:"), gc);
//        gc.gridy++;
//        add(giftBoxCheckBox, gc);
//        gc.gridy++;
//        add(newsLetterCheckBox, gc);
//        gc.gridy++;
//        add(specialMessageCheckBox, gc);
//
//        gc.weighty = 0.5;
//        gc.gridy++;
//        gc.anchor = GridBagConstraints.CENTER;
//        add(specialMessageTextField, gc);
//
//        gc.gridy = 0;
//        gc.gridx++;
//        add(Box.createHorizontalStrut(25), gc);
//
//        gc.gridx++;
//        gc.gridy+=2;
//        add(expressRadioButton, gc);
//        gc.gridy++;
//        add(standardRadioButton, gc);
//        gc.gridy++;
//        add(Box.createVerticalStrut(20), gc);
//        gc.gridy++;
//        add(submitButton, gc);


    }

    private void InitComponents() {
        setPreferredSize(new Dimension(440, getHeight()));

        dateFrom = new JTextField(10);
        dateTo = new JTextField(10);
        isSuccessful = new JCheckBox("Show only successful transactions");
        isSuccessful.setActionCommand("IS_SUCCESSFUL");
//        isSuccessful.setSelected(true);

        transactionStatus = new JComboBox<>();
        DefaultComboBoxModel<String> transactionModel = new DefaultComboBoxModel<>();
        transactionModel.addElement("Successful transaction");
        transactionModel.addElement("Not enough money error");
        transactionModel.addElement("Item out of stock error");
        transactionModel.addElement("Cancelled transaction");
        transactionStatus.setModel(transactionModel);
        // set default selection none and disabled
        transactionStatus.setSelectedIndex(-1);

        transactionId = new JTextField(5);
        moneyLowerBound = new JTextField(5);
        moneyUpperBound = new JTextField(5);

        apply = new JButton("Apply");
        apply.setActionCommand("APPLY_TRANSACTION");






    }
}

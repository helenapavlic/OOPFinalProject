package Admin.View;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class TransactionFilterFormPanel extends JPanel {
    private JDatePickerImpl dateTo;
    private JDatePickerImpl dateFrom;
    private JComboBox<String> transactionStatus;

    private JTextField transactionId;
    private JButton apply;

    public TransactionFilterFormPanel() {
        InitComponents();
        layoutComponents();
        decorate();
        activatePanel();
    }

    private void activatePanel() {

    }

    private void decorate() {
        Border inner = BorderFactory.createTitledBorder("Transaction filter");
        Border outer = BorderFactory.createEmptyBorder(2, 2, 2, 2);
        setBorder(BorderFactory.createCompoundBorder(outer, inner));

    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = GridBagConstraints.LINE_START;

        gridBagConstraints.gridy++;
        add(new JLabel("Date from:"), gridBagConstraints);
        gridBagConstraints.gridx++;
        add(dateFrom, gridBagConstraints);

        add(Box.createVerticalStrut(40), gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        add(new JLabel("Date to: "), gridBagConstraints);
        gridBagConstraints.gridx++;
        add(dateTo, gridBagConstraints);

        add(Box.createVerticalStrut(40), gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        add(new JLabel("Transaction status: "), gridBagConstraints);
        gridBagConstraints.gridx++;
        add(transactionStatus, gridBagConstraints);

        add(Box.createVerticalStrut(40), gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        add(new JLabel("Transaction id: "), gridBagConstraints);
        gridBagConstraints.gridx++;
        add(transactionId, gridBagConstraints);

        add(Box.createVerticalStrut(40), gridBagConstraints);

        gridBagConstraints.gridy++;
        gridBagConstraints.gridx++;
        add(apply, gridBagConstraints);


    }

    private void InitComponents() {
        setPreferredSize(new Dimension(440, getHeight()));

        // Kreiranje modela za JDatePicker
        UtilDateModel modelFrom = new UtilDateModel();
        UtilDateModel modelTo = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        // Kreiranje JDatePanel za dateFrom i dateTo
        JDatePanelImpl datePanelFromDate = new JDatePanelImpl(modelFrom, p);
        JDatePanelImpl datePanelToDate = new JDatePanelImpl(modelTo, p);

        // Kreiranje JDatePicker instance za dateFrom i dateTo
        dateFrom = new JDatePickerImpl(datePanelFromDate, new DateLabelFormatter());
        dateTo = new JDatePickerImpl(datePanelToDate, new DateLabelFormatter());

        // Inicijalizacija ostalih komponenti
        transactionStatus = new JComboBox<>();
        DefaultComboBoxModel<String> transactionModel = new DefaultComboBoxModel<>();
        transactionModel.addElement("Successful transaction");
        transactionModel.addElement("Not enough money error");
        transactionModel.addElement("Item out of stock error");
        transactionModel.addElement("Item not found error");
        transactionModel.addElement("Cancelled transaction");
        transactionStatus.setModel(transactionModel);
        transactionStatus.setSelectedIndex(-1);

        transactionId = new JTextField(5);
//        new Dimension(200, 25);
        transactionId.setPreferredSize(new Dimension(10, 25));


        apply = new JButton("Apply");
        apply.setActionCommand("APPLY_TRANSACTION");
    }

    public static class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
        private String datePattern = "dd.MM.yyyy";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }
            return "";
        }
    }


}

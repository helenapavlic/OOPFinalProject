package VendingMachine;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class TextPanel extends JPanel {
    private JLabel selectedItemLabel;
    private JTextArea selectedItemTextArea;
    private JLabel inputLabel;
    private JTextArea totalTextArea;
    private Font bigFont = new Font("Arial", Font.BOLD, 30);
    private Font smallerFont = new Font("Arial", Font.PLAIN, 18);


    public TextPanel() {
        initComponents();
        layoutComponents();
    }

    private void layoutComponents() {
        setLayout(new GridLayout(4, 1, 4, 4));
        add(selectedItemLabel);
        add(selectedItemTextArea);
        add(inputLabel);
        add(totalTextArea);
    }

    private void initComponents() {
        selectedItemTextArea = new JTextArea(1, 10);
        selectedItemTextArea.setEditable(false);
        selectedItemTextArea.setFont(bigFont);

        selectedItemLabel = new JLabel("Selected item");
        selectedItemLabel.setFont(smallerFont);


        totalTextArea = new JTextArea(1, 10);
        totalTextArea.setEditable(false);
        totalTextArea.setFont(bigFont);

        inputLabel = new JLabel("Input");
        inputLabel.setFont(smallerFont);
    }

    public void printAddedMoney(String value) {
        totalTextArea.selectAll();
        totalTextArea.replaceSelection(value + " €");
    }
    public void printNumSelection(String text){
        selectedItemTextArea.append(text);
    }

    public void clearTextArea() {
        totalTextArea.selectAll();
        totalTextArea.replaceSelection(null);
        selectedItemTextArea.selectAll();
        selectedItemTextArea.replaceSelection(null);
    }

    public int deleteLastInputNum(){
        String modifiedString = selectedItemTextArea.getText().substring(0, selectedItemTextArea.getText().length() - 1);
        selectedItemTextArea.selectAll();
        selectedItemTextArea.replaceSelection(modifiedString);
        return modifiedString.length();
    }

    public String readInputTextField() {
        return selectedItemTextArea.getText();
    }

    public float readTotalTextField(){
        String text = totalTextArea.getText();
        float num = parseCurrency(text);
        return num;

//        return Float.parseFloat(totalTextArea.getText());
    }

    public static float parseCurrency(String input) {
        // Ukloni simbol valute i sve bijele prostore
        input = input.replace("€", "").trim();

        // Koristi NumberFormat za parsiranje broja sa zarezom kao decimalnim separatorom
        NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);
        try {
            Number number = format.parse(input);
            return number.floatValue();
        } catch (ParseException e) {
            System.err.println("Greška prilikom parsiranja valute: " + e.getMessage());
            return 0.0f;
        }
    }
}

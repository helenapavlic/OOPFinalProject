package VendingMachine.Utility;

import VendingMachine.Model.Transaction;

import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AUX_CLS_VENDING {

    public static void saveTransactions(ArrayList<Transaction> transactions, String filePath) {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(transactions);
            System.out.println("Transactions have been saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Transaction> loadTransactions(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            try (FileInputStream fileInputStream = new FileInputStream(file);
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                return (ArrayList<Transaction>) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    public static int getNextTransactionId(String filePath) {
        ArrayList<Transaction> transactions = null;
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            transactions = (ArrayList<Transaction>) in.readObject();
        } catch (FileNotFoundException e) {
            return 1;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (transactions != null && !transactions.isEmpty()) {
            Transaction lastTransaction = transactions.get(transactions.size() - 1);
            return lastTransaction.getTransactionId() + 1;
        } else {
            return 1;
        }
    }

    public static <T> void printListItems(ArrayList<T> list) {
        for (T listItem : list) {
            System.out.println(listItem);
        }
    }

    public static void saveTransactionsToCSV(ArrayList<Transaction> transactions, String filePath) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat decimalFormat = new DecimalFormat("0.00", symbols);
//        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write the CSV header
            writer.append("ID,DATE AND TIME,STATUS,INPUT MONEY,CHANGE,ITEM ID,ITEM NAME,ITEM PRICE,QUANTITY\n");

            // Write transaction data
            for (Transaction transaction : transactions) {
                writer.append(String.valueOf(transaction.getTransactionId())).append(",");
                writer.append(transaction.getDateAndTime()).append(",");
                writer.append(transaction.getTransactionStatus()).append(",");
                writer.append(decimalFormat.format(transaction.getInputMoney())).append(",");
                writer.append(decimalFormat.format(transaction.getChange())).append(",");
                writer.append(String.valueOf(transaction.getItemId())).append(",");
                writer.append(transaction.getItemName()).append(",");
                writer.append(decimalFormat.format(transaction.getItemPrice())).append(",");
                writer.append(String.valueOf(transaction.getRemainingQuantity())).append("\n");
            }

            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String[]> readTransactionsFromCSV(String filePath) {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] values = line.split(",");
                records.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }
}

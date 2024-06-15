package VendingMachine.Model;

import java.io.*;
import java.util.ArrayList;

public class AUX_CLS {

    public static boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }


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

    public <T> void printListItems(ArrayList<T> list) {
        for (T listItem : list) {
            System.out.println(listItem);
        }
    }


}

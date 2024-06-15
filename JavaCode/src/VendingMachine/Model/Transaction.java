package VendingMachine.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Transaction implements Serializable {

    private static final String filePath = "Data/transctions.bin";
    private static final String SUCCESSFUL_TRANSACTION = "success";
    private static final String CANCELLED_TRANSACTION = "cancelled";
    private static final String OUT_OF_STOCK_TRANSACTION = "stockError";
    private static final String ITEM_NOT_FOUND_TRANSACTION = "itemError";
    private static final String NOT_ENOUGH_TOTAL_MONEY_TRANSACTION = "moneyError";
    private static List<Transaction> transactions = new ArrayList<>();
    private static int cntId = 1;
    private int transactionId;
    private String transactionStatus;
    private String dateAndTime;
    private boolean isSuccessful;
    private float inputMoney;
    private float change;
    private Item item;
    private int remainingQuantity;
    private int itemId;
    private float itemPrice;
    private String itemName;
    private int itemQuantity;

    public Transaction(int userIdInput, float userMoneyInput) {
        this.transactionId = cntId++;
        this.dateAndTime = generateDateAndTime();
        item = Item.getItemById(userIdInput);
        this.inputMoney = userMoneyInput;

        if (item == null) {
            this.transactionStatus = ITEM_NOT_FOUND_TRANSACTION;
            this.isSuccessful = false;
            this.itemId = 0;
            this.itemName = "";
            this.itemPrice = 0.0f;
            this.itemQuantity = 0;
            this.remainingQuantity = 0;
            this.change = calculateChange();
        } else{
            this.itemId = item.getId();
            this.itemName = item.getItemName();
            this.itemPrice = item.getPrice();
            this.itemQuantity = item.getQuantity();

            if (!item.isAvailable()){
                this.transactionStatus = OUT_OF_STOCK_TRANSACTION;
                this.isSuccessful = false;
                this.change = inputMoney;
            } else if (userMoneyInput < itemPrice) {
                this.transactionStatus = NOT_ENOUGH_TOTAL_MONEY_TRANSACTION;
                this.isSuccessful = false;
                this.change = calculateChange();
            } else {
                this.transactionStatus = SUCCESSFUL_TRANSACTION;
                this.isSuccessful = true;
                this.remainingQuantity = --itemQuantity;
                item.setQuantity(remainingQuantity);
                this.change = calculateChange();
            }

        }

        transactions.add(this);
        printTransactions();

    }

    public Transaction(float inputMoney){
//        cancelled transaction
        this.transactionId = cntId++;
        this.dateAndTime = generateDateAndTime();
        this.inputMoney = inputMoney;
        this.isSuccessful = false;
        this.transactionStatus = CANCELLED_TRANSACTION;
        this.item = null;
        this.itemId = 0;
        this.itemQuantity = 0;
        this.itemName = "";
        this.itemPrice = 0.0f;
        this.remainingQuantity = 0;
        this.change = inputMoney;

        transactions.add(this);
        printTransactions();

    }


    private static void printTransactions() {
        System.out.println("--------------------------");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
        System.out.println();
    }

    public static List<Transaction> getTransactions() {
        return transactions;
    }

    private int generateNextTransactionId() {
        int nextId = 1;
        if (AUX_CLS.fileExists(filePath)) {
            Transaction lastTransaction = AUX_CLS.readObjectFromFile(filePath);
            int lastTransactionId = lastTransaction.getTransactionId();
            nextId = lastTransactionId;
        }
        return nextId;
    }

    private String generateDateAndTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
        return now.format(formatter);
    }

    public float calculateChange() {
        float change;
        if (isSuccessful) {
            change = inputMoney - itemPrice;
        } else {
            change = 0.0f;
        }
        return change;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public float getInputMoney() {
        return inputMoney;
    }

    public float getChange() {
        return change;
    }

    public Item getItem() {
        return item;
    }

    public int getRemainingQuantity() {
        return remainingQuantity;
    }

    public int getItemId() {
        return itemId;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }



    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transactionStatus='" + transactionStatus + '\'' +
                ", dateAndTime='" + dateAndTime + '\'' +
                ", inputMoney=" + inputMoney +
                ", change=" + change +
                ", remainingQuantity=" + remainingQuantity +
                ", itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                '}';
    }
}

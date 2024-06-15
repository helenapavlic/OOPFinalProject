package VendingMachine.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Transaction implements Serializable {

    private static final String filePath = "Data/transctions.bin";
    private static final String SUCCESSFUL_TRANSACTION = "success";
    private static final String CANCELLED_TRANSACTION = "cancelled";
    private static final String OUT_OF_STOCK_TRANSACTION = "stockError";
    private static final String ITEM_NOT_FOUND_TRANSACTION = "itemError";
    private static final String NOT_ENOUGH_TOTAL_MONEY_TRANSACTION = "moneyError";
    private static ArrayList<Transaction> transactions = AUX_CLS.loadTransactions(filePath);
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
        this.transactionId = AUX_CLS.getNextTransactionId(filePath);
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
        } else {
            this.itemId = item.getId();
            this.itemName = item.getItemName();
            this.itemPrice = item.getPrice();
            this.itemQuantity = item.getQuantity();

            if (!item.isAvailable()) {
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
        AUX_CLS.printListItems(transactions);
        AUX_CLS.saveTransactions(transactions, filePath);
    }

    public Transaction(float inputMoney) {
        this.transactionId = AUX_CLS.getNextTransactionId(filePath);
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
        AUX_CLS.saveTransactions(transactions, filePath);
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
        change = Math.round(change * 100.0f) / 100.0f;
        return change;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public float getChange() {
        return change;
    }

    public Item getItem() {
        return item;
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

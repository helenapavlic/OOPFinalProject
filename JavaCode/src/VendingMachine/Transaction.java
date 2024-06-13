package VendingMachine;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private static int cntId = 1;
    private int id;
    private String dateOfTransaction;
    private boolean isSuccessful;
    private Item item;
    private int itemID;
    private float itemPrice;
    private float inputMoney;
    private float change;
    private float remainingItemQuantity;

    public Transaction(Item item, float inputMoney) {
        this.id = cntId++;
        this.item = item;
        this.inputMoney = inputMoney;
        this.itemID = item.getId();
        this.itemPrice = item.getPrice();
        createTransaction();
    }

    private void createTransaction() {
        generateDateOfTransaction();
        if ((inputMoney >= itemPrice) && item.isAvailable()) {
            item.soldItem();
            isSuccessful = true;
        }
        remainingItemQuantity = item.getQuantity();
        calculateChange();
    }

    public void calculateChange() {
        if (inputMoney >= itemPrice) {
            change = inputMoney - itemPrice;
        } else {
            change = inputMoney;
        }
    }

    private void generateDateOfTransaction() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
        dateOfTransaction = now.format(formatter);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", dateOfTransaction='" + dateOfTransaction + '\'' +
                ", isSuccessful=" + isSuccessful +
                ", item=" + item +
                ", itemID=" + itemID +
                ", itemPrice=" + itemPrice +
                ", inputMoney=" + inputMoney +
                ", change=" + change +
                ", remainingItemQuantity=" + remainingItemQuantity +
                '}';
    }

//    todo: save tranaction to file
}

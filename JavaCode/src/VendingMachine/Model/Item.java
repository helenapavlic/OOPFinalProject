package VendingMachine.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Serializable {
    private static int cntId = 1;
    private static ArrayList<Item> items = new ArrayList<>();
    private final int INITIAL_QUANTITY = 4;
    private String itemName;
    private int id;
    private float price;
    private int quantity;

    public Item(String itemName, float price) {
        this.id = cntId++;
        this.itemName = itemName;
        this.price = price;
        this.quantity = INITIAL_QUANTITY;
        items.add(this);
    }

    public Item(int itemId, String itemName, float itemPrice, int itemQuantity) {
        this.id = itemId;
        this.itemName = itemName;
        this.price = itemPrice;
        this.quantity = itemQuantity;
    }

    public static Item getItemById(int userInput) {
        Item matchItem = null;
        for (Item item : items) {
            if (userInput == item.id) {
                matchItem = item;
            }
        }
        return matchItem;
    }

    public static ArrayList<Item> getItems() {
        return items;
    }

    public String getItemName() {
        return itemName;
    }

    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isAvailable() {
        return (quantity > 0);
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemName='" + itemName + '\'' +
                ", id=" + id +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

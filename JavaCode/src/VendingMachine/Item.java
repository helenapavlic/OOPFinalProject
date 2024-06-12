package VendingMachine;

import java.util.ArrayList;

public class Item {
    private static int cntId = 1;
    private final int INITIAL_QUANTITY = 10;
    private String itemName;
    private int id;
    private float price;
    private int quantity;
    private ArrayList<Item> items = new ArrayList<>();


    public Item(String itemName, float price){
        this.id = cntId++;
        this.itemName = itemName;
        this.price = price;
        this.quantity = INITIAL_QUANTITY;
        items.add(this);
        System.out.println("added item: " + this);


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

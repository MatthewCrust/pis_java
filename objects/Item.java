package objects;
import java.io.Serializable;

public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    private String itemName;
    private int itemId;
    private int stock;
    private int minStock;
    private double price;

    public Item(String itemName, int stock, int minStock, double price) {
        this.itemName = itemName;
        this.stock = stock;
        this.minStock = minStock;
        this.price = price;
    }

    // Gettery a settery
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMinStock() {
        return minStock;
    }

    public void setMinStock(int minStock) {
        this.minStock = minStock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
package objects;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ItemManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int nextId;
    private List<Item> items = new ArrayList<>();

    public ItemManager() {
        nextId = findHighestItemId() + 1;
    }

    private int findHighestItemId() {
        int highestId = -1;
        for (Item item : items) {
            if (item.getItemId() > highestId) {
                highestId = item.getItemId();
            }
        }
        return highestId;
    }

    // Metoda pro přidání nové položky
    public void addItem(Item item) {
        item.setItemId(nextId++);
        items.add(item);
    }

    public void removeItem(int itemId) {
        Item item = findItemById(itemId);
        if (item != null) {
            items.remove(item);
        }
    }

    // Metoda pro aktualizaci počtu kusů položky
    public void updateStock(int itemId, int stock) {
        for (Item item : items) {
            if (item.getItemId() == itemId) {
                item.setStock(stock);
                break;
            }
        }
    }

    // Metoda pro aktualizaci ceny položky
    public void updatePrice(int itemId, double price) {
        for (Item item : items) {
            if (item.getItemId() == itemId) {
                item.setPrice(price);
                break;
            }
        }
    }

    // Metoda pro kontrolu stavu zásob
    public List<Item> checkStock() {
        List<Item> lowStockItems = new ArrayList<>();
        for (Item item : items) {
            if (item.getStock() < item.getMinStock()) {
                lowStockItems.add(item);
            }
        }
        return lowStockItems;
    }

    // Metoda pro nalezení položky podle ID
    public Item findItemById(int itemId) {
        for (Item item : items) {
            if (item.getItemId() == itemId) {
                return item;
            }
        }
        return null;
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(nextId);
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        nextId = in.readInt();
    }
}
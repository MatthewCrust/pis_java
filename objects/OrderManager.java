package objects;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int nextId;
    private List<Order> orders = new ArrayList<>();

    public OrderManager() {
        nextId = findHighestOrderId() + 1;
    }

    private int findHighestOrderId() {
        int highestId = -1; // Start from -1 so that nextId starts from 0
        for (Order order : orders) {
            if (order.getId() > highestId) {
                highestId = order.getId();
            }
        }
        return highestId;
    }

    // Metoda pro přidání nové objednávky
    public void addOrder(Order order) {
        order.setId(nextId++);
        orders.add(order);
        recalculateTotalPrice(order);
    }

    // Metoda pro úpravu objednávky
    public void editOrder(int id, String name, String description, String status, Date receivedDate, Date dueDate) {
        Order order = findOrderById(id);
        if (order != null) {
            order.setName(name);
            order.setDescription(description);
            order.setStatus(status);
            order.setReceivedDate(receivedDate);
            order.setDueDate(dueDate);
            recalculateTotalPrice(order);
        }
    }

    // Metoda pro nalezení objednávky podle ID
    public Order findOrderById(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    // Metoda pro odebrání objednávky
    public void removeOrder(int id) {
        orders.removeIf(order -> order.getId() == id);
    }

    // Metoda pro získání seznamu všech objednávek
    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    // Metoda pro přidání položky do objednávky
    public void addItemToOrder(int orderId, int itemId, int quantity) {
        Order order = findOrderById(orderId);
        if (order != null) {
            order.addItem(itemId, quantity);
            recalculateTotalPrice(order);
        }
    }

    // Metoda pro aktualizaci množství položky v objednávce
    public void updateItemQuantityInOrder(int orderId, int itemId, int quantity) {
        Order order = findOrderById(orderId);
        if (order != null) {
            if (order.getOrderItems().containsKey(itemId)) {
                order.getOrderItems().put(itemId, quantity);
                recalculateTotalPrice(order);
            } else {
                System.out.println("Item ID: " + itemId + " not found in Order ID: " + orderId);
            }
        }
    }

    // Metoda pro odebrání položky z objednávky
    public void removeItemFromOrder(int orderId, int itemId) {
        Order order = findOrderById(orderId);
        if (order != null) {
            order.removeItem(itemId);
            recalculateTotalPrice(order);
        }
    }

    // Metoda pro přepočítání celkové ceny objednávky
    private void recalculateTotalPrice(Order order) {
        double totalPrice = 0.0;
        for (Map.Entry<Integer, Integer> entry : order.getOrderItems().entrySet()) {
            Item item = CompanySystem.getInstance().getItemManager().findItemById(entry.getKey());
            if (item != null) {
                totalPrice += item.getPrice() * entry.getValue();
            }
        }
        order.setTotalPrice(totalPrice);
    }

    // Metoda pro zpracování objednávky
    public void processOrder(int orderId) {
        Order order = findOrderById(orderId);
        if (order != null) {
            boolean allItemsAvailable = true;
            for (Map.Entry<Integer, Integer> entry : order.getOrderItems().entrySet()) {
                int itemId = entry.getKey();
                int quantity = entry.getValue();
                if (!checkProductAvailability(itemId, quantity)) {
                    allItemsAvailable = false;
                    break;
                }
            }

            if (allItemsAvailable) {
                for (Map.Entry<Integer, Integer> entry : order.getOrderItems().entrySet()) {
                    int itemId = entry.getKey();
                    int quantity = entry.getValue();
                    Item item = CompanySystem.getInstance().getItemManager().findItemById(itemId);
                    if (item != null) {
                        item.setStock(item.getStock() - quantity);
                    }
                }
                order.setStatus("Processed");
                System.out.println("Objednávka ID: " + orderId + " byla úspěšně vyřízena.");
            } else {
                order.setStatus("Failed due to insufficient stock");
                System.out.println("Objednávka ID: " + orderId + " nemohla být vyřízena kvůli nedostatku zásob.");
            }
        } else {
            System.out.println("Objednávka ID: " + orderId + " nebyla nalezena.");
        }
    }

    // Metoda pro kontrolu dostupnosti produktů na skladě
    public boolean checkProductAvailability(int itemId, int requiredStock) {
        Item item = CompanySystem.getInstance().getItemManager().findItemById(itemId);
        if (item != null && item.getStock() >= requiredStock) {
            return true;
        } else {
            System.out.println("Nedostatek zásob pro položku ID: " + itemId);
            return false;
        }
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
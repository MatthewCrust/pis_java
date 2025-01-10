package objects;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String description;
    private String status;
    private Date receivedDate;
    private Date dueDate;
    private Map<Integer, Integer> orderItems;
    private double totalPrice;

    public Order(String name, String description, Date receivedDate, Date dueDate) {
        this.name = name;
        this.description = description;
        this.status = "Pending";
        this.receivedDate = receivedDate;
        this.dueDate = dueDate;
        this.orderItems = new HashMap<>();
        this.totalPrice = 0.0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Map<Integer, Integer> getOrderItems() {
        return orderItems;
    }

    public void addItem(int itemId, int quantity) {
        this.orderItems.put(itemId, quantity);
    }

    public void removeItem(int itemId) {
        this.orderItems.remove(itemId);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
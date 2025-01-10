package objects;

import java.io.*;
import java.io.Serializable;

public class CompanySystem implements Serializable {
    private static final long serialVersionUID = 1L;
    private static CompanySystem instance;

    private ItemManager itemManager;
    private OrderManager orderManager;
    private EmployeeManager employeeManager;

    private CompanySystem() {
        itemManager = new ItemManager();
        orderManager = new OrderManager();
        employeeManager = new EmployeeManager();
    }

    public static CompanySystem getInstance() {
        if (instance == null) {
            instance = new CompanySystem();
        }
        return instance;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public OrderManager getOrderManager() {
        return orderManager;
    }

    public EmployeeManager getEmployeeManager() {
        return employeeManager;
    }

    public static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("companySystem.ser"))) {
            oos.writeObject(instance);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("companySystem.ser"))) {
            instance = (CompanySystem) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
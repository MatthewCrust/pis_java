package objects;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int nextId;
    private List<Employee> employees = new ArrayList<>();

    public EmployeeManager() {
        nextId = findHighestEmployeeId()+1;
    }


    private int findHighestEmployeeId() {
        int highestId = -1;
        for (Employee employee : employees) {
            if (employee.getId() > highestId) {
                highestId = employee.getId();
            }
        }
        return highestId;
    }

    // Metoda pro přidání nového zaměstnance
    public void addEmployee(Employee employee) {
        employee.setId(nextId++);
        employees.add(employee);
    }

    // Metoda pro editaci zaměstnance
    public void editEmployee(int id, String firstName, String lastName, String position, double salary) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employee.setFirstName(firstName);
                employee.setLastName(lastName);
                employee.setPosition(position);
                employee.setSalary(salary);
                break;
            }
        }
    }

    // Metoda pro odebrání zaměstnance
    public void removeEmployee(int id) {
        employees.removeIf(employee -> employee.getId() == id);
    }

    // Metoda pro nalezení zaměstnance podle ID
    public Employee findEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    // Metoda pro výpočet celkových mzdových nákladů firmy
    public double calculateTotalSalaryCost() {
        double totalCost = 0;
        for (Employee employee : employees) {
            totalCost += employee.getSalary();
        }
        return totalCost;
    }

    // Metoda pro získání seznamu všech zaměstnanců
    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(nextId);
    }

    // Metoda pro deserializaci nextId
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        nextId = in.readInt();
    }
}
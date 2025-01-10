package gui;
import javax.swing.*;
import java.awt.*;
import objects.*;

public class ViewAllEmployeesDialog extends JDialog {
    public ViewAllEmployeesDialog() {
        setTitle("All Employees");
        setSize(640, 480);
        setLocationRelativeTo(null);

        CompanySystem system = CompanySystem.getInstance();
        java.util.List<Employee> employees = system.getEmployeeManager().getEmployees();

        String[] columnNames = {"ID", "First Name", "Last Name", "Position", "Salary"};
        Object[][] data = new Object[employees.size()][5];

        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            data[i][0] = employee.getId();
            data[i][1] = employee.getFirstName();
            data[i][2] = employee.getLastName();
            data[i][3] = employee.getPosition();
            data[i][4] = employee.getSalary();
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
}
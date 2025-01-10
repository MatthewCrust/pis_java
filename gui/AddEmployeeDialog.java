package gui;
import objects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmployeeDialog extends JDialog {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField positionField;
    private JTextField salaryField;

    public AddEmployeeDialog() {
        setTitle("Add Employee");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setModal(true);

        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        add(firstNameField);

        add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        add(lastNameField);

        add(new JLabel("Position:"));
        positionField = new JTextField();
        add(positionField);

        add(new JLabel("Salary:"));
        salaryField = new JTextField();
        add(salaryField);

        JButton addButton = new JButton("Add Employee");
        add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String firstName = firstNameField.getText();
                    String lastName = lastNameField.getText();
                    String position = positionField.getText();
                    double salary = Double.parseDouble(salaryField.getText());

                    Employee employee = new Employee(firstName, lastName, position, salary);

                    CompanySystem.getInstance().getEmployeeManager().addEmployee(employee);
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AddEmployeeDialog.this, "Invalid input. Please check your entries.");
                }
            }
        });
    }
}

package gui;

import objects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindEmployeeDialog extends JDialog {
    private JTextField employeeIdField;
    private JButton searchButton, cancelButton;
    private CompanySystem system;
    private JTextArea outputArea;

    public FindEmployeeDialog(CompanySystem system, JTextArea outputArea) {
        this.system = system;
        this.outputArea = outputArea;
        setTitle("Najít zaměstnance");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("ID zaměstnance:"));
        employeeIdField = new JTextField();
        add(employeeIdField);

        searchButton = new JButton("Hledat");
        cancelButton = new JButton("Zrušit");

        add(searchButton);
        add(cancelButton);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int employeeId = Integer.parseInt(employeeIdField.getText());
                Employee employee = system.getEmployeeManager().findEmployeeById(employeeId);
                if (employee != null) {
                    outputArea.append("Zaměstnanec nalezen: " + employee.getFirstName() + " " + employee.getLastName() + "\n");
                } else {
                    outputArea.append("Zaměstnanec s ID " + employeeId + " nebyl nalezen.\n");
                }
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}

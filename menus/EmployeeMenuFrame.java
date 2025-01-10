package menus;

import gui.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class EmployeeMenuFrame extends JFrame {
    public EmployeeMenuFrame() {
        setTitle("Employee Menu");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton addButton = new JButton("Add Employee");
        JButton editButton = new JButton("Edit Employee");
        JButton removeButton = new JButton("Remove Employee");
        JButton viewButton = new JButton("View All Employees");

        Dimension buttonSize = new Dimension(280, 70);
        addButton.setPreferredSize(buttonSize);
        editButton.setPreferredSize(buttonSize);
        removeButton.setPreferredSize(buttonSize);
        viewButton.setPreferredSize(buttonSize);

        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        addButton.setFont(buttonFont);
        editButton.setFont(buttonFont);
        removeButton.setFont(buttonFont);
        viewButton.setFont(buttonFont);

        Color buttonColor = new Color(70, 130, 180);
        addButton.setBackground(buttonColor);
        editButton.setBackground(buttonColor);
        removeButton.setBackground(buttonColor);
        viewButton.setBackground(buttonColor);

        Color buttonTextColor = Color.WHITE;
        addButton.setForeground(buttonTextColor);
        editButton.setForeground(buttonTextColor);
        removeButton.setForeground(buttonTextColor);
        viewButton.setForeground(buttonTextColor);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddEmployeeDialog().setVisible(true);
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EditEmployeeDialog().setVisible(true);
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RemoveEmployeeDialog().setVisible(true);
            }
        });

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewAllEmployeesDialog().setVisible(true);
            }
        });

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(60, 63, 65));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(addButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonPanel.add(editButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(removeButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        buttonPanel.add(viewButton, gbc);

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        wrapperPanel.setBackground(new Color(60, 63, 65));
        wrapperPanel.add(buttonPanel, BorderLayout.CENTER);
        add(wrapperPanel, BorderLayout.CENTER);
    }

}
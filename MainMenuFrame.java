import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import objects.CompanySystem;
import menus.*;

public class MainMenuFrame extends JFrame {
    public MainMenuFrame() {
        setTitle("Company System");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Podnikový informační systém", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 70));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(60, 63, 65));

        JButton employeesButton = new JButton("Employees");
        JButton itemsButton = new JButton("Items");
        JButton ordersButton = new JButton("Orders");
        JButton revenuesButton = new JButton("Revenues");

        Dimension buttonSize = new Dimension(200, 50);
        employeesButton.setPreferredSize(buttonSize);
        itemsButton.setPreferredSize(buttonSize);
        ordersButton.setPreferredSize(buttonSize);
        revenuesButton.setPreferredSize(buttonSize);

        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        employeesButton.setFont(buttonFont);
        itemsButton.setFont(buttonFont);
        ordersButton.setFont(buttonFont);
        revenuesButton.setFont(buttonFont);

        Color buttonColor = new Color(70, 130, 180);
        employeesButton.setBackground(buttonColor);
        itemsButton.setBackground(buttonColor);
        ordersButton.setBackground(buttonColor);
        revenuesButton.setBackground(buttonColor);

        Color buttonTextColor = Color.WHITE;
        employeesButton.setForeground(buttonTextColor);
        itemsButton.setForeground(buttonTextColor);
        ordersButton.setForeground(buttonTextColor);
        revenuesButton.setForeground(buttonTextColor);

        employeesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EmployeeMenuFrame().setVisible(true);
            }
        });

        itemsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ItemMenuFrame().setVisible(true);
            }
        });

        ordersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new OrderMenuFrame().setVisible(true);
            }
        });

        revenuesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RevenueMenuFrame().setVisible(true);
            }
        });

        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(60, 63, 65));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(employeesButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(itemsButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonPanel.add(ordersButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        buttonPanel.add(revenuesButton, gbc);

        add(buttonPanel, BorderLayout.CENTER);

        CompanySystem.loadData();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                CompanySystem.saveData();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainMenuFrame().setVisible(true);
            }
        });
    }
}
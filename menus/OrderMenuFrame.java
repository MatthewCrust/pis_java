package menus;

import gui.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class OrderMenuFrame extends JFrame {
    public OrderMenuFrame() {
        setTitle("Order Menu");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel titleLabel = new JLabel("Order Menu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 50));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(60, 63, 65));

        JButton addButton = new JButton("Add Order");
        JButton processButton = new JButton("Process Order");
        JButton removeButton = new JButton("Remove Order");
        JButton viewButton = new JButton("View All Orders");
        JButton viewOrderItemsButton = new JButton("View Order Items");

        Dimension buttonSize = new Dimension(280, 70);
        addButton.setPreferredSize(buttonSize);
        processButton.setPreferredSize(buttonSize);
        removeButton.setPreferredSize(buttonSize);
        viewButton.setPreferredSize(buttonSize);
        viewOrderItemsButton.setPreferredSize(buttonSize);

        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        addButton.setFont(buttonFont);
        processButton.setFont(buttonFont);
        removeButton.setFont(buttonFont);
        viewButton.setFont(buttonFont);
        viewOrderItemsButton.setFont(buttonFont);

        Color buttonColor = new Color(70, 130, 180);
        addButton.setBackground(buttonColor);
        processButton.setBackground(buttonColor);
        removeButton.setBackground(buttonColor);
        viewButton.setBackground(buttonColor);
        viewOrderItemsButton.setBackground(buttonColor);

        Color buttonTextColor = Color.WHITE;
        addButton.setForeground(buttonTextColor);
        processButton.setForeground(buttonTextColor);
        removeButton.setForeground(buttonTextColor);
        viewButton.setForeground(buttonTextColor);
        viewOrderItemsButton.setForeground(buttonTextColor);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddOrderDialog().setVisible(true);
            }
        });

        processButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ProcessOrderDialog().setVisible(true);
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RemoveOrderDialog().setVisible(true);
            }
        });

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewAllOrdersDialog().setVisible(true);
            }
        });

        viewOrderItemsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewOrderItemsDialog().setVisible(true);
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
        buttonPanel.add(addButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonPanel.add(processButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(removeButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        buttonPanel.add(viewButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonPanel.add(viewOrderItemsButton, gbc);

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        wrapperPanel.setBackground(new Color(60, 63, 65));
        wrapperPanel.add(buttonPanel, BorderLayout.CENTER);
        add(wrapperPanel, BorderLayout.CENTER);
    }
}
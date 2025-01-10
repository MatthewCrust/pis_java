package gui;

import objects.CompanySystem;
import objects.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class AddOrderDialog extends JDialog {
    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField itemIdField;
    private JTextField itemQuantityField;
    private JTextArea orderItemsArea;
    private Order order;

    public AddOrderDialog() {
        setTitle("Add Order");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setModal(true);

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.add(new JLabel("Order Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        inputPanel.add(descriptionField);

        inputPanel.add(new JLabel("Item ID:"));
        itemIdField = new JTextField();
        inputPanel.add(itemIdField);

        inputPanel.add(new JLabel("Item Quantity:"));
        itemQuantityField = new JTextField();
        inputPanel.add(itemQuantityField);

        JButton addItemButton = new JButton("Add Item");
        inputPanel.add(addItemButton);

        add(inputPanel, BorderLayout.NORTH);

        orderItemsArea = new JTextArea();
        orderItemsArea.setEditable(false);
        add(new JScrollPane(orderItemsArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Order");
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int itemId = Integer.parseInt(itemIdField.getText());
                    int itemQuantity = Integer.parseInt(itemQuantityField.getText());
                    if (CompanySystem.getInstance().getOrderManager().checkProductAvailability(itemId, itemQuantity)) {
                        if (order == null) {
                            order = new Order(nameField.getText(), descriptionField.getText(), new Date(), addDays(new Date(), 7));
                        }
                        order.addItem(itemId, itemQuantity);
                        displayOrderItems();
                        itemIdField.setText("");
                        itemQuantityField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(AddOrderDialog.this, "Nedostatek zásob pro položku ID: " + itemId);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AddOrderDialog.this, "Invalid input. Please check your entries.");
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (order == null) {
                        order = new Order(nameField.getText(), descriptionField.getText(), new Date(), addDays(new Date(), 7));
                    }
                    CompanySystem.getInstance().getOrderManager().addOrder(order);
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AddOrderDialog.this, "Invalid input. Please check your entries.");
                }
            }
        });
    }

    private void displayOrderItems() {
        StringBuilder itemsText = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : order.getOrderItems().entrySet()) {
            itemsText.append("Item ID: ").append(entry.getKey())
                     .append(", Quantity: ").append(entry.getValue()).append("\n");
        }
        orderItemsArea.setText(itemsText.toString());
    }

    private Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

}
package gui;

import objects.CompanySystem;
import objects.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class ViewOrderItemsDialog extends JDialog {
    private JTextField orderIdField;
    private JTextArea orderItemsArea;

    public ViewOrderItemsDialog() {
        setTitle("View Order Items");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setModal(true);

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.add(new JLabel("Order ID:"));
        orderIdField = new JTextField();
        inputPanel.add(orderIdField);

        JButton viewButton = new JButton("View Items");
        inputPanel.add(viewButton);

        add(inputPanel, BorderLayout.NORTH);

        orderItemsArea = new JTextArea();
        orderItemsArea.setEditable(false);
        add(new JScrollPane(orderItemsArea), BorderLayout.CENTER);

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int orderId = Integer.parseInt(orderIdField.getText());
                    Order order = CompanySystem.getInstance().getOrderManager().findOrderById(orderId);
                    if (order != null) {
                        displayOrderItems(order);
                    } else {
                        JOptionPane.showMessageDialog(ViewOrderItemsDialog.this, "Order not found.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ViewOrderItemsDialog.this, "Invalid Order ID. Please enter a valid number.");
                }
            }
        });
    }

    private void displayOrderItems(Order order) {
        StringBuilder itemsText = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : order.getOrderItems().entrySet()) {
            itemsText.append("Item ID: ").append(entry.getKey())
                     .append(", Quantity: ").append(entry.getValue()).append("\n");
        }
        orderItemsArea.setText(itemsText.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ViewOrderItemsDialog().setVisible(true);
            }
        });
    }
}
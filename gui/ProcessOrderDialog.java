package gui;

import objects.CompanySystem;
import objects.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessOrderDialog extends JDialog {
    private JTextField orderIdField;
    private JTable pendingOrdersTable;
    private DefaultTableModel tableModel;

    public ProcessOrderDialog() {
        setTitle("Process Order");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setModal(true);

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.add(new JLabel("Order ID:"));
        orderIdField = new JTextField();
        inputPanel.add(orderIdField);

        JButton processButton = new JButton("Process Order");
        inputPanel.add(processButton);
        add(inputPanel, BorderLayout.NORTH);

        String[] columnNames = {"Order ID", "Name", "Description", "Received Date", "Due Date", "Total Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        pendingOrdersTable = new JTable(tableModel);
        add(new JScrollPane(pendingOrdersTable), BorderLayout.CENTER);

        loadPendingOrders();

        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int orderId = Integer.parseInt(orderIdField.getText());

                    CompanySystem.getInstance().getOrderManager().processOrder(orderId);
                    loadPendingOrders();
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ProcessOrderDialog.this, "Invalid input. Please check your entries.");
                }
            }
        });
    }

    private void loadPendingOrders() {
        List<Order> pendingOrders = CompanySystem.getInstance().getOrderManager().getOrders().stream()
                .filter(order -> "Pending".equals(order.getStatus()))
                .collect(Collectors.toList());

        tableModel.setRowCount(0);

        for (Order order : pendingOrders) {
            Object[] row = {
                    order.getId(),
                    order.getName(),
                    order.getDescription(),
                    order.getStatus(),
                    order.getReceivedDate(),
                    order.getDueDate(),
                    order.getTotalPrice()
            };
            tableModel.addRow(row);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProcessOrderDialog().setVisible(true);
            }
        });
    }
}
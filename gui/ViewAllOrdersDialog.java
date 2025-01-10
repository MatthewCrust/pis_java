package gui;

import javax.swing.*;
import java.awt.*;
import objects.CompanySystem;
import objects.Order;

public class ViewAllOrdersDialog extends JDialog {
    public ViewAllOrdersDialog() {
        setTitle("All Orders");
        setSize(640, 480);
        setLocationRelativeTo(null);

        CompanySystem system = CompanySystem.getInstance();
        java.util.List<Order> orders = system.getOrderManager().getOrders();

        String[] columnNames = {"ID", "Name", "Description", "Status", "Received Date", "Due Date", "Total Price"};
        Object[][] data = new Object[orders.size()][7];

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            data[i][0] = order.getId();
            data[i][1] = order.getName();
            data[i][2] = order.getDescription();
            data[i][3] = order.getStatus();
            data[i][4] = order.getReceivedDate();
            data[i][5] = order.getDueDate();
            data[i][6] = order.getTotalPrice();
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
}
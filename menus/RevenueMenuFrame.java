package menus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;
import objects.CompanySystem;
import objects.Order;


public class RevenueMenuFrame extends JFrame {
    private JTable revenueTable;
    private DefaultTableModel tableModel;

    public RevenueMenuFrame() {
        setTitle("Revenues");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel titleLabel = new JLabel("Revenues", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 50));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(60, 63, 65));

        String[] columnNames = {"Revenue", "Date"};
        tableModel = new DefaultTableModel(columnNames, 0);
        revenueTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(revenueTable);

        loadRevenues();

        double totalRevenue = calculateTotalRevenue();
        JLabel totalRevenueLabel = new JLabel("Total Revenue: " + totalRevenue + " Kč", SwingConstants.CENTER);
        totalRevenueLabel.setFont(new Font("Serif", Font.BOLD, 20));
        totalRevenueLabel.setForeground(Color.WHITE);
        totalRevenueLabel.setOpaque(true);
        totalRevenueLabel.setBackground(new Color(60, 63, 65));

        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(totalRevenueLabel, BorderLayout.SOUTH);

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        wrapperPanel.setBackground(new Color(60, 63, 65));
        wrapperPanel.add(scrollPane, BorderLayout.CENTER);
        add(wrapperPanel, BorderLayout.CENTER);
    }

    private void loadRevenues() {
        List<Order> orders = CompanySystem.getInstance().getOrderManager().getOrders();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        tableModel.setRowCount(0);

        for (Order order : orders) {
            if ("Processed".equals(order.getStatus())) {
                Object[] row = {
                        "+ " + order.getTotalPrice() + " Kč",
                        dateFormat.format(order.getReceivedDate())
                };
                tableModel.addRow(row);
            }
        }
    }

    private double calculateTotalRevenue() {
        List<Order> orders = CompanySystem.getInstance().getOrderManager().getOrders();
        double totalRevenue = 0.0;
        for (Order order : orders) {
            if ("Processed".equals(order.getStatus())) {
                totalRevenue += order.getTotalPrice();
            }
        }
        return totalRevenue;
    }
}
package gui;
import javax.swing.*;
import java.awt.*;
import objects.CompanySystem;
import objects.Item;

public class ViewAllItemsDialog extends JDialog {
    public ViewAllItemsDialog() {
        setTitle("All Items");
        setSize(640, 480);
        setLocationRelativeTo(null);

        CompanySystem system = CompanySystem.getInstance();
        java.util.List<Item> items = system.getItemManager().getItems();

        String[] columnNames = {"ID", "Name", "Stock", "Min Stock", "Price"};
        Object[][] data = new Object[items.size()][5];

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            data[i][0] = item.getItemId();
            data[i][1] = item.getItemName();
            data[i][2] = item.getStock();
            data[i][3] = item.getMinStock();
            data[i][4] = item.getPrice();
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
}
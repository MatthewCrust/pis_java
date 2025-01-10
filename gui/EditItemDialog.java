package gui;

import objects.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditItemDialog extends JDialog {
    private JTextField itemIdField;
    private JTextField itemNameField;
    private JTextField stockField;
    private JTextField minStockField;

    public EditItemDialog() {
        setTitle("Edit Item");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setModal(true);

        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Item ID:"));
        itemIdField = new JTextField();
        add(itemIdField);

        add(new JLabel("Item Name:"));
        itemNameField = new JTextField();
        add(itemNameField);

        add(new JLabel("Stock:"));
        stockField = new JTextField();
        add(stockField);

        add(new JLabel("Min Stock:"));
        minStockField = new JTextField();
        add(minStockField);

        JButton editButton = new JButton("Edit Item");
        add(editButton);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int itemId = Integer.parseInt(itemIdField.getText());
                    String itemName = itemNameField.getText();
                    int stock = Integer.parseInt(stockField.getText());
                    int minStock = Integer.parseInt(minStockField.getText());

                    Item item = CompanySystem.getInstance().getItemManager().findItemById(itemId);
                    if (item != null) {
                        item.setItemName(itemName);
                        item.setStock(stock);
                        item.setMinStock(minStock);
                    } else {
                        JOptionPane.showMessageDialog(EditItemDialog.this, "Item not found.");
                    }
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(EditItemDialog.this, "Invalid input. Please check your entries.");
                }
            }
        });
    }
}
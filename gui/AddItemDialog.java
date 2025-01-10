package gui;

import objects.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItemDialog extends JDialog {
    private JTextField itemNameField;
    private JTextField quantityField;
    private JTextField minStockField;
    private JTextField priceField;

    public AddItemDialog() {
        setTitle("Add Item");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setModal(true);

        setLayout(new GridLayout(5, 2, 10, 10));


        add(new JLabel("Item Name:"));
        itemNameField = new JTextField();
        add(itemNameField);

        add(new JLabel("Stock:"));
        quantityField = new JTextField();
        add(quantityField);

        add(new JLabel("Min Stock:"));
        minStockField = new JTextField();
        add(minStockField);

        add(new JLabel("Price:"));
        priceField = new JTextField();
        add(priceField);

        JButton addButton = new JButton("Add Item");
        add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String itemName = itemNameField.getText();
                    int quantity = Integer.parseInt(quantityField.getText());
                    int minStock = Integer.parseInt(minStockField.getText());
                    double price = Double.parseDouble(priceField.getText());

                    Item item = new Item(itemName, quantity, minStock, price);
                    CompanySystem.getInstance().getItemManager().addItem(item);
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AddItemDialog.this, "Invalid input. Please check your entries.");
                }
            }
        });
    }
}

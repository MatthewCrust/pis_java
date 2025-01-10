package gui;

import objects.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveItemDialog extends JDialog {
    private JTextField itemIdField;

    public RemoveItemDialog() {
        setTitle("Remove Item");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setModal(true);

        setLayout(new GridLayout(2, 2, 10, 10));

        add(new JLabel("Item ID:"));
        itemIdField = new JTextField();
        add(itemIdField);

        JButton removeButton = new JButton("Remove Item");
        add(removeButton);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int itemId = Integer.parseInt(itemIdField.getText());

                    Item item = CompanySystem.getInstance().getItemManager().findItemById(itemId);
                    if (item != null) {
                        CompanySystem.getInstance().getItemManager().removeItem(itemId);
                    } else {
                        JOptionPane.showMessageDialog(RemoveItemDialog.this, "Item not found.");
                    }
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(RemoveItemDialog.this, "Invalid input. Please check your entries.");
                }
            }
        });
    }
}

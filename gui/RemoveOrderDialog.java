package gui;
import objects.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveOrderDialog extends JDialog {
    private JTextField orderIdField;

    public RemoveOrderDialog() {
        setTitle("Remove Order");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setModal(true);

        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Order ID:"));
        orderIdField = new JTextField();
        add(orderIdField);

        JButton removeButton = new JButton("Remove Order");
        add(removeButton);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int orderId = Integer.parseInt(orderIdField.getText());
                    CompanySystem.getInstance().getOrderManager().removeOrder(orderId);
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(RemoveOrderDialog.this, "Invalid Order ID. Please check your entry.");
                }
            }
        });
    }
}

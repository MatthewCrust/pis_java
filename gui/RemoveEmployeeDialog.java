package gui;
import objects.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveEmployeeDialog extends JDialog {
    private JTextField idField;

    public RemoveEmployeeDialog() {
        setTitle("Remove Employee");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setModal(true);

        setLayout(new GridLayout(3, 1, 10, 10));

        add(new JLabel("Employee ID:"));
        idField = new JTextField();
        add(idField);

        JButton removeButton = new JButton("Remove Employee");
        add(removeButton);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());

                    CompanySystem.getInstance().getEmployeeManager().removeEmployee(id);
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(RemoveEmployeeDialog.this, "Invalid input. Please check your entries.");
                }
            }
        });
    }
}

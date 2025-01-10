package gui;
import objects.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditEmployeeDialog extends JDialog {
    private JTextField idField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField positionField;
    private JTextField salaryField;

    public EditEmployeeDialog() {
        setTitle("Edit Employee");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setModal(true);

        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel("Employee ID:"));
        idField = new JTextField();
        add(idField);

        add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        add(firstNameField);

        add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        add(lastNameField);

        add(new JLabel("Position:"));
        positionField = new JTextField();
        add(positionField);

        add(new JLabel("Salary:"));
        salaryField = new JTextField();
        add(salaryField);

        JButton editButton = new JButton("Edit Employee");
        add(editButton);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    String firstName = firstNameField.getText();
                    String lastName = lastNameField.getText();
                    String position = positionField.getText();
                    double salary = Double.parseDouble(salaryField.getText());

                    CompanySystem.getInstance().getEmployeeManager().editEmployee(id, firstName, lastName, position, salary);
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(EditEmployeeDialog.this, "Invalid input. Please check your entries.");
                }
            }
        });
    }
}

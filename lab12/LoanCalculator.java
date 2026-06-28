import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoanCalculator extends JFrame {

    JRadioButton monthRateBtn, annualRateBtn;
    ButtonGroup rateGroup;
    JTextField balanceField, yearField, interestField, totalField;
    JButton okButton, deleteButton;

    public LoanCalculator() {
        setTitle("Loan Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 5, 5));

        // Row 1: Radio buttons
        monthRateBtn  = new JRadioButton("Month Rate");
        annualRateBtn = new JRadioButton("Annual Rate", true);
        rateGroup = new ButtonGroup();
        rateGroup.add(monthRateBtn);
        rateGroup.add(annualRateBtn);
        add(monthRateBtn);
        add(annualRateBtn);

        // Row 2: Balance Amount
        add(new JLabel("Balance Amount", JLabel.RIGHT));
        balanceField = new JTextField();
        add(balanceField);

        // Row 3: Number of Year
        add(new JLabel("Number of Year", JLabel.RIGHT));
        yearField = new JTextField();
        add(yearField);

        // Row 4: Annual Interest Rate
        add(new JLabel("Annual Interest Rate", JLabel.RIGHT));
        interestField = new JTextField();
        add(interestField);

        // Row 5: Total Payment
        add(new JLabel("Total Payment", JLabel.RIGHT));
        totalField = new JTextField();
        totalField.setEditable(false);
        add(totalField);

        // Row 6: OK / Delete buttons
        okButton = new JButton("OK");
        deleteButton = new JButton("Delete");
        add(okButton);
        add(deleteButton);

        // Action listeners
        okButton.addActionListener(e -> calculate());
        deleteButton.addActionListener(e -> clearFields());

        setVisible(true);
    }

    void calculate() {
        try {
            double balance  = Double.parseDouble(balanceField.getText().trim());
            double years    = Double.parseDouble(yearField.getText().trim());
            double rate     = Double.parseDouble(interestField.getText().trim()) / 100;

            double total;
            if (annualRateBtn.isSelected()) {
                total = balance * Math.pow(1 + rate, years);
            } else {
                total = balance * Math.pow(1 + rate, years * 12);
            }

            totalField.setText(String.format("%.2f", total));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Зөв тоо оруулна уу!", "Алдаа", JOptionPane.ERROR_MESSAGE);
        }
    }

    void clearFields() {
        balanceField.setText("");
        yearField.setText("");
        interestField.setText("");
        totalField.setText("");
        annualRateBtn.setSelected(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoanCalculator::new);
    }
}

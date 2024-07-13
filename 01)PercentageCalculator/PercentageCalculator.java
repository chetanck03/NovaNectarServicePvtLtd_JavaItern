import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PercentageCalculator extends JFrame implements ActionListener {
    private JTextField inputField1, inputField2, resultField;
    private JButton calculateButton, clearButton;
    private JComboBox<String> calculationType;

    public PercentageCalculator() {
        setTitle("Percentage Calculator");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel for inputs
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(Color.DARK_GRAY);

        JLabel label1 = new JLabel("Enter the First Value:");
        label1.setForeground(Color.WHITE);
        inputField1 = new JTextField();
        JLabel label2 = new JLabel("Enter the Second Value:");
        label2.setForeground(Color.WHITE);
        inputField2 = new JTextField();

        JLabel calcTypeLabel = new JLabel("Calculation Type:");
        calcTypeLabel.setForeground(Color.WHITE);
        String[] calculations = {"Percentage", "Percentage Increase", "Percentage Decrease", "Whole Percentage"};
        calculationType = new JComboBox<>(calculations);

        inputPanel.add(label1);
        inputPanel.add(inputField1);
        inputPanel.add(label2);
        inputPanel.add(inputField2);
        inputPanel.add(calcTypeLabel);
        inputPanel.add(calculationType);

        // Bottom panel for buttons and result
        JPanel bottomPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.setBackground(Color.DARK_GRAY);

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        calculateButton.setBackground(Color.GREEN);
        calculateButton.setForeground(Color.WHITE);

        clearButton = new JButton("Clear");
        clearButton.setBackground(Color.RED);
        clearButton.setForeground(Color.WHITE);
        clearButton.addActionListener(e -> {
            inputField1.setText("");
            inputField2.setText("");
            resultField.setText("");
        });

        resultField = new JTextField();
        resultField.setEditable(false);
        resultField.setFont(new Font("Arial", Font.BOLD, 16));
        resultField.setBackground(Color.LIGHT_GRAY);

        JLabel resultLabel = new JLabel("Result:");
        resultLabel.setForeground(Color.WHITE);

        bottomPanel.add(resultLabel);
        bottomPanel.add(resultField);
        bottomPanel.add(calculateButton);
        bottomPanel.add(clearButton);

        // Add panels to frame
        add(inputPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double value1 = Double.parseDouble(inputField1.getText());
            double value2 = Double.parseDouble(inputField2.getText());
            double result = 0;
            String type = (String) calculationType.getSelectedItem();

            switch (type) {
                case "Percentage":
                    result = (value1 / value2) * 100;
                    break;
                case "Percentage Increase":
                    result = ((value2 - value1) / value1) * 100;
                    break;
                case "Percentage Decrease":
                    result = ((value1 - value2) / value1) * 100;
                    break;
                case "Whole Percentage":
                    result = (value1 / 100) * value2;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid calculation type");
            }
            resultField.setText(String.format("%.2f", result));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PercentageCalculator calculator = new PercentageCalculator();
            calculator.getContentPane().setBackground(Color.DARK_GRAY);
            calculator.setVisible(true);
        });
    }
}

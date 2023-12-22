import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureConverterGUI extends JFrame {
    private JLabel inputLabel;
    private JTextField inputField;
    private JButton convertButton;
    private JTextArea outputArea;

    public TemperatureConverterGUI() {
        setTitle("Temperature Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inputLabel = new JLabel("Enter temperature:");
        inputField = new JTextField(10);

        JPanel inputPanel = new JPanel();
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);

        convertButton = new JButton("Convert");
        outputArea = new JTextArea(10, 20);
        outputArea.setEditable(false);

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });

        JScrollPane scrollPane = new JScrollPane(outputArea);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(convertButton);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void convertTemperature() {
        try {
            double temperature = Double.parseDouble(inputField.getText());

            double fahrenheit = (temperature * 9 / 5) + 32;
            double kelvin = temperature + 273.15;

            String result = String.format("Fahrenheit: %.2f\nKelvin: %.2f", fahrenheit, kelvin);
            outputArea.setText(result);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid temperature!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TemperatureConverterGUI converter = new TemperatureConverterGUI();
            converter.setVisible(true);
        });
    }
}

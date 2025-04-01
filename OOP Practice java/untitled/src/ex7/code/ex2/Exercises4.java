package ex7.code.ex2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Exercises4 extends JFrame {
    private JTextField phoneField;
    private JLabel resultLabel;

    public Exercises4() {
        setTitle("Визначення оператора");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        phoneField = new JTextField();
        add(phoneField);

        JButton checkButton = new JButton("Перевірити оператора");
        add(checkButton);

        resultLabel = new JLabel("Оператор: ");
        add(resultLabel);

        checkButton.addActionListener((ActionEvent e) -> {
            String phoneNumber = phoneField.getText();
            resultLabel.setText("Оператор: " + getOperator(phoneNumber));
        });

        setVisible(true);
    }

    public static String getOperator(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll("[^0-9]", "");

        if (phoneNumber.length() < 10) {
            return "Невірний номер";
        }

        String prefix = phoneNumber.substring(0, 3);

        switch (prefix) {
            case "050": case "066": case "095":
                return "Vodafone";
            case "063": case "073":
                return "Lifecell";
            case "068": case "097":
                return "Kyivstar";
            case "093":
                if (phoneNumber.startsWith("0930") || phoneNumber.startsWith("0931")) {
                    return "Lifecell";
                } else {
                    return "Kyivstar";
                }
            default:
                return "Невідомий оператор";
        }
    }

    public static void main(String[] args) {
        new Exercises4();
    }
}

package ex7.code.ex2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

/**
 * Клас Exercises3 виконує обчислення суми двох чисел,
 * збереження та завантаження результату через графічний інтерфейс.
 */
public class Exercises3 extends JFrame {
    private static final String FILE_NAME = "resources/ex2/result.txt";
    private JTextField num1Field, num2Field;
    private JLabel resultLabel;

    public Exercises3() {
        setTitle("Калькулятор");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Число 1:"));
        num1Field = new JTextField();
        add(num1Field);

        add(new JLabel("Число 2:"));
        num2Field = new JTextField();
        add(num2Field);

        JButton calculateButton = new JButton("Обчислити");
        add(calculateButton);

        resultLabel = new JLabel("Результат:");
        add(resultLabel);

        JButton saveButton = new JButton("Зберегти");
        add(saveButton);

        JButton loadButton = new JButton("Завантажити");
        add(loadButton);

        calculateButton.addActionListener((ActionEvent e) -> calculateSum());
        saveButton.addActionListener((ActionEvent e) -> saveResult());
        loadButton.addActionListener((ActionEvent e) -> loadResult());

        setVisible(true);
    }

    private void calculateSum() {
        try {
            int num1 = Integer.parseInt(num1Field.getText());
            int num2 = Integer.parseInt(num2Field.getText());
            int sum = num1 + num2;
            resultLabel.setText("Результат: " + sum);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Помилка! Введіть числа.");
        }
    }

    private void saveResult() {
        try {
            String result = resultLabel.getText();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
                writer.write(result);
                resultLabel.setText("Результат збережено!");
            }
        } catch (IOException ex) {
            resultLabel.setText("Помилка збереження!");
        }
    }

    private void loadResult() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine();
            if (line != null) {
                resultLabel.setText(line);
            }
        } catch (IOException ex) {
            resultLabel.setText("Помилка завантаження!");
        }
    }

    public static void main(String[] args) {
        new Exercises3();
    }
}

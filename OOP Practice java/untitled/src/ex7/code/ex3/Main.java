package ex7.code.ex3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame {
    private JTextField num1Field;
    private JTextField num2Field;
    private JTextArea resultArea;
    private CalculationManager manager;

    public Main() {
        setTitle("Калькулятор");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        num1Field = new JTextField();
        num2Field = new JTextField();
        JButton calculateButton = new JButton("Обчислити");
        resultArea = new JTextArea();
        JButton saveButton = new JButton("Зберегти у файл");

        add(new JLabel("Перше число:"));
        add(num1Field);
        add(new JLabel("Друге число:"));
        add(num2Field);
        add(calculateButton);
        add(new JScrollPane(resultArea));
        add(saveButton);

        manager = new CalculationManager();

        calculateButton.addActionListener((ActionEvent e) -> {
            try {
                int num1 = Integer.parseInt(num1Field.getText());
                int num2 = Integer.parseInt(num2Field.getText());

                CalculationFactory sumFactory = new SumFactory();
                CalculationFactory productFactory = new ProductFactory();

                manager.addResult(sumFactory.createResult(num1, num2));
                manager.addResult(productFactory.createResult(num1, num2));

                resultArea.setText("");
                for (CalculationResult result : manager.getResults()) {
                    resultArea.append(result.getResultString() + "\n");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Введіть коректні числа!", "Помилка", JOptionPane.ERROR_MESSAGE);
            }
        });

        saveButton.addActionListener((ActionEvent e) -> manager.saveResultsToFile("results.txt"));

        setVisible(true);
    }

    interface CalculationResult {
        void display();
        String getResultString();
    }

    static class SumResult implements CalculationResult {
        private final int result;

        public SumResult(int a, int b) {
            this.result = a + b;
        }

        @Override
        public void display() {
            System.out.println("Результат суми: " + result);
        }

        @Override
        public String getResultString() {
            return "Результат суми: " + result;
        }
    }

    static class ProductResult implements CalculationResult {
        private final int result;

        public ProductResult(int a, int b) {
            this.result = a * b;
        }

        @Override
        public void display() {
            System.out.println("Результат добутку: " + result);
        }

        @Override
        public String getResultString() {
            return "Результат добутку: " + result;
        }
    }

    abstract static class CalculationFactory {
        public abstract CalculationResult createResult(int a, int b);
    }

    static class SumFactory extends CalculationFactory {
        @Override
        public CalculationResult createResult(int a, int b) {
            return new SumResult(a, b);
        }
    }

    static class ProductFactory extends CalculationFactory {
        @Override
        public CalculationResult createResult(int a, int b) {
            return new ProductResult(a, b);
        }
    }

    static class CalculationManager {
        private final List<CalculationResult> results = new ArrayList<>();

        public void addResult(CalculationResult result) {
            results.add(result);
        }

        public List<CalculationResult> getResults() {
            return results;
        }

        public void saveResultsToFile(String filename) {
            try (FileWriter writer = new FileWriter("resources/ex3/" + filename)) {
                for (CalculationResult result : results) {
                    writer.write(result.getResultString() + "\n");
                }
                JOptionPane.showMessageDialog(null, "Результати збережено у файл " + filename);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Помилка запису у файл: " + e.getMessage(), "Помилка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}

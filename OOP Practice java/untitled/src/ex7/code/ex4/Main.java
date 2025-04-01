package ex7.code.ex4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Інтерфейс для об'єктів, які відображають результати
 */
interface CalculationResult {
    void display();
    String getResultString();
}

/**
 * Базовий клас для представлення результату у вигляді таблиці
 */
abstract class TableResult implements CalculationResult {
    protected final int a, b, result;

    public TableResult(int a, int b, int result) {
        this.a = a;
        this.b = b;
        this.result = result;
    }

    @Override
    public void display() {
        System.out.println("+------------+------------+------------+");
        System.out.printf("| %10d | %10d | %10d |\n", a, b, result);
        System.out.println("+------------+------------+------------+");
    }
}

/**
 * Клас для результату суми
 */
class SumResult extends TableResult {
    public SumResult(int a, int b) {
        super(a, b, a + b);
    }

    @Override
    public String getResultString() {
        return String.format("Сума: %d + %d = %d", a, b, result);
    }
}

/**
 * Клас для результату добутку
 */
class ProductResult extends TableResult {
    public ProductResult(int a, int b) {
        super(a, b, a * b);
    }

    @Override
    public String getResultString() {
        return String.format("Добуток: %d * %d = %d", a, b, result);
    }
}

/**
 * Абстрактна фабрика для створення об'єктів результату
 */
abstract class CalculationFactory {
    public abstract CalculationResult createResult(int a, int b);
}

class SumFactory extends CalculationFactory {
    @Override
    public CalculationResult createResult(int a, int b) {
        return new SumResult(a, b);
    }
}

class ProductFactory extends CalculationFactory {
    @Override
    public CalculationResult createResult(int a, int b) {
        return new ProductResult(a, b);
    }
}

/**
 * Клас для управління обчисленнями та результатами
 */
class CalculationManager {
    private final List<CalculationResult> results = new ArrayList<>();

    public void addResult(CalculationResult result) {
        results.add(result);
    }

    public void displayResults() {
        for (CalculationResult result : results) {
            result.display();
        }
    }

    // Геттер для доступу до результатів
    public List<CalculationResult> getResults() {
        return results;
    }

    public void saveResultsToFile(String filename) {
        try (FileWriter writer = new FileWriter("resources/ex4/" + filename)) {
            for (CalculationResult result : results) {
                writer.write(result.getResultString() + "\n");
            }
            System.out.println("Результати збережено у файл " + filename);
        } catch (IOException e) {
            System.err.println("Помилка запису у файл: " + e.getMessage());
        }
    }
}

/**
 * Графічний інтерфейс для виконання обчислень
 */
public class Main {

    private static CalculationManager manager = new CalculationManager();

    public static void main(String[] args) {
        // Створення вікна програми
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 2));

        // Поля для введення чисел
        JTextField num1Field = new JTextField();
        JTextField num2Field = new JTextField();
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);

        // Кнопки для операцій
        JButton sumButton = new JButton("Сума");
        JButton productButton = new JButton("Добуток");
        JButton bothButton = new JButton("Обидві операції");
        JButton saveButton = new JButton("Зберегти в файл");

        // Обробка кнопок
        sumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performCalculation(num1Field, num2Field, "sum", resultArea);
            }
        });

        productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performCalculation(num1Field, num2Field, "product", resultArea);
            }
        });

        bothButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performCalculation(num1Field, num2Field, "both", resultArea);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.saveResultsToFile("results.txt");
                JOptionPane.showMessageDialog(frame, "Результати збережено у файл.");
            }
        });

        // Додавання компонентів до вікна
        frame.add(new JLabel("Перше число:"));
        frame.add(num1Field);
        frame.add(new JLabel("Друге число:"));
        frame.add(num2Field);
        frame.add(sumButton);
        frame.add(productButton);
        frame.add(bothButton);
        frame.add(saveButton);
        frame.add(new JScrollPane(resultArea));

        frame.setVisible(true);
    }

    private static void performCalculation(JTextField num1Field, JTextField num2Field, String operation, JTextArea resultArea) {
        try {
            int num1 = Integer.parseInt(num1Field.getText());
            int num2 = Integer.parseInt(num2Field.getText());

            // Очистка старих результатів
            manager = new CalculationManager();

            // Виконання операцій відповідно до вибору
            if ("sum".equals(operation) || "both".equals(operation)) {
                manager.addResult(new SumFactory().createResult(num1, num2));
            }
            if ("product".equals(operation) || "both".equals(operation)) {
                manager.addResult(new ProductFactory().createResult(num1, num2));
            }

            // Виведення результатів у текстове поле
            List<CalculationResult> results = manager.getResults();
            StringBuilder resultText = new StringBuilder();
            for (CalculationResult result : results) {
                resultText.append(result.getResultString()).append("\n");
            }
            resultArea.setText(resultText.toString());

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Будь ласка, введіть правильні числа.");
        }
    }
}

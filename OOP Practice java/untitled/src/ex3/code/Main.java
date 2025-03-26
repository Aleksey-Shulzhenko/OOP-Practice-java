package ex3.code;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // Інтерфейс для об'єктів, які відображають результати
    interface CalculationResult {
        void display();
        String getResultString();
    }

    // Конкретні класи, що реалізують інтерфейс
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

    // Фабричний метод
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

    // Клас, що керує колекцією результатів
    static class CalculationManager {
        private final List<CalculationResult> results = new ArrayList<>();

        public void addResult(CalculationResult result) {
            results.add(result);
        }

        public void displayResults() {
            for (CalculationResult result : results) {
                result.display();
            }
        }

        public void saveResultsToFile(String filename) {
            try (FileWriter writer = new FileWriter("resources/ex3/" + filename)) {
                for (CalculationResult result : results) {
                    writer.write(result.getResultString() + "\n");
                }
                System.out.println("Результати збережено у файл" + filename);
            } catch (IOException e) {
                System.err.println("Помилка запису у файл: " + e.getMessage());
            }
        }
    }

    // Головний метод
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CalculationManager manager = new CalculationManager();

        System.out.println("Введіть перше число: ");
        int num1 = scanner.nextInt();
        System.out.println("Введіть друге число: ");
        int num2 = scanner.nextInt();

        CalculationFactory sumFactory = new SumFactory();
        CalculationFactory productFactory = new ProductFactory();

        manager.addResult(sumFactory.createResult(num1, num2));
        manager.addResult(productFactory.createResult(num1, num2));

        manager.displayResults();
        manager.saveResultsToFile("results.txt");
    }
}

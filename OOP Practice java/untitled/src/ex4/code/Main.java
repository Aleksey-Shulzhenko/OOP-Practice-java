package ex4.code;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        System.out.printf("| %10d | %10d | %10d | ", a, b, result);
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
 * Головний клас програми
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CalculationManager manager = new CalculationManager();

        System.out.print("Введіть перше число: ");
        int num1 = scanner.nextInt();
        System.out.print("Введіть друге число: ");
        int num2 = scanner.nextInt();

        CalculationFactory sumFactory = new SumFactory();
        CalculationFactory productFactory = new ProductFactory();

        manager.addResult(sumFactory.createResult(num1, num2));
        manager.addResult(productFactory.createResult(num1, num2));

        manager.displayResults();
        manager.saveResultsToFile("results.txt");
    }
}

package ex2.code;

import java.io.*;
import java.util.Scanner;

/**
 * Клас TestCalculator виконує тестування арифметичних обчислень,
 * серіалізацію та десеріалізацію результатів у текстовий файл.
 */
public class Exercises3
{

    /**
     * Виконує обчислення суми двох чисел.
     *
     * @param a перше число
     * @param b друге число
     * @return сума чисел a і b
     */
    public static int add(int a, int b) {
        return a + b;
    }

    /**
     * Зберігає результат у файл.
     *
     * @param filename назва файлу
     * @param data     дані для запису
     */
    public static void saveToFile(String filename, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(data);
        } catch (IOException e) {
            System.err.println("Помилка запису у файл: " + e.getMessage());
        }
    }

    /**
     * Читає дані з файлу.
     *
     * @param filename назва файлу
     * @return дані з файлу
     */
    public static String readFromFile(String filename) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Помилка читання з файлу: " + e.getMessage());
        }
        return result.toString();
    }

    /**
     * Основний метод для запуску програми.
     *
     * @param args аргументи командного рядка
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть перше число: ");
        int num1 = scanner.nextInt();
        System.out.print("Введіть друге число: ");
        int num2 = scanner.nextInt();

        int sum = add(num1, num2);
        System.out.println("Результат: " + sum);

        String result = "Сума " + num1 + " + " + num2 + " = " + sum;
        String filePath = "resources/ex2/result.txt";
        saveToFile(filePath, result);

        System.out.println("Збережено у файл: " + filePath);
        System.out.println("Прочитано з файлу: " + readFromFile(filePath));
    }
}
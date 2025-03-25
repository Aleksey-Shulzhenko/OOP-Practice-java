package ex2.code;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

class CalculationData {
    private final double param1;
    private final double param2;
    private double result;

    public CalculationData(double param1, double param2) {
        this.param1 = param1;
        this.param2 = param2;
    }

    public double getParam1() { return param1; }
    public double getParam2() { return param2; }
    public double getResult() { return result; }
    public void setResult(double result) { this.result = result; }
}

class SolutionFinder {
    private CalculationData data;

    public SolutionFinder(CalculationData data) {
        this.data = data;
    }

    public void computeResult() {
        double result = data.getParam1() + data.getParam2();
        data.setResult(result);
    }

    public void saveToFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Param1: " + data.getParam1() + "\n");
            writer.write("Param2: " + data.getParam2() + "\n");
            writer.write("Result: " + data.getResult() + "\n");
        }
    }

    public void loadFromFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            double param1 = Double.parseDouble(reader.readLine().split(": ")[1]);
            double param2 = Double.parseDouble(reader.readLine().split(": ")[1]);
            double result = Double.parseDouble(reader.readLine().split(": ")[1]);

            data = new CalculationData(param1, param2);
            data.setResult(result);
        }
    }

    public CalculationData getData() {
        return data;
    }
}

public class Exercises1 {
    private static final Logger LOGGER = Logger.getLogger(Exercises1.class.getName());

    public static void main(String[] args) {
        try {
            CalculationData data = new CalculationData(5.5, 3.3);
            SolutionFinder finder = new SolutionFinder(data);

            finder.computeResult();
            System.out.println("Результат: " + finder.getData().getResult());

            // Создаём папку, если её нет
            Files.createDirectories(Paths.get("resources/ex2"));
            String fileName = "resources/ex2/calculation_data.txt";

            // Сохраняем в текстовый файл
            finder.saveToFile(fileName);
            System.out.println("Дані збережено у файл: " + fileName);

            // Загружаем из файла
            SolutionFinder newFinder = new SolutionFinder(null);
            newFinder.loadFromFile(fileName);

            System.out.println("Завантажений результат: " + newFinder.getData().getResult());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Помилка під час серіалізації", e);
        }
    }
}

package ex7.code.ex2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Exercises1 extends JFrame {
    private static final Logger LOGGER = Logger.getLogger(Exercises1.class.getName());
    private JTextField field1, field2;
    private JLabel resultLabel;
    private SolutionFinder finder;

    public Exercises1() {
        setTitle("Обчислення");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Число 1:"));
        field1 = new JTextField();
        add(field1);

        add(new JLabel("Число 2:"));
        field2 = new JTextField();
        add(field2);

        JButton calcButton = new JButton("Обчислити");
        add(calcButton);

        resultLabel = new JLabel("Результат: ");
        add(resultLabel);

        JButton saveButton = new JButton("Зберегти");
        add(saveButton);

        JButton loadButton = new JButton("Завантажити");
        add(loadButton);

        calcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double num1 = Double.parseDouble(field1.getText());
                    double num2 = Double.parseDouble(field2.getText());
                    CalculationData data = new CalculationData(num1, num2);
                    finder = new SolutionFinder(data);
                    finder.computeResult();
                    resultLabel.setText("Результат: " + finder.getData().getResult());
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Помилка! Введіть числа.");
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (finder != null) {
                    try {
                        Files.createDirectories(Paths.get("resources/ex2"));
                        String fileName = "resources/ex2/calculation_data.txt";
                        finder.saveToFile(fileName);
                        resultLabel.setText("Дані збережено!");
                    } catch (IOException ex) {
                        resultLabel.setText("Помилка збереження!");
                    }
                }
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String fileName = "resources/ex2/calculation_data.txt";
                    finder = new SolutionFinder(null);
                    finder.loadFromFile(fileName);
                    field1.setText(String.valueOf(finder.getData().getParam1()));
                    field2.setText(String.valueOf(finder.getData().getParam2()));
                    resultLabel.setText("Завантажено: " + finder.getData().getResult());
                } catch (IOException ex) {
                    resultLabel.setText("Помилка завантаження!");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Exercises1();
    }
}

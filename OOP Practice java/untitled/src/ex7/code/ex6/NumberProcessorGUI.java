package ex7.code.ex6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.List;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class NumberProcessorGUI extends JFrame {
    private final JTextField inputField;
    private final JTextArea resultArea;
    private final ExecutorService executor;

    public NumberProcessorGUI() {
        // Налаштування вікна
        setTitle("Number Processor");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Поле вводу
        inputField = new JTextField();
        add(inputField, BorderLayout.NORTH);

        // Кнопка "Обчислити"
        JButton calculateButton = new JButton("Обчислити");
        calculateButton.addActionListener(this::processNumbers);
        add(calculateButton, BorderLayout.SOUTH);

        // Поле виводу
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // Ініціалізація ExecutorService
        executor = Executors.newFixedThreadPool(4);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void processNumbers(ActionEvent e) {
        String input = inputField.getText();
        List<Integer> numbers;
        try {
            numbers = Arrays.stream(input.split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Введіть коректні числа через пробіл!", "Помилка", JOptionPane.ERROR_MESSAGE);
            return;
        }

        BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();

        // Додаємо завдання у чергу
        taskQueue.add(new WorkerThread(() -> processAndDisplay("Мінімум", numbers.stream().min(Integer::compare).orElseThrow())));
        taskQueue.add(new WorkerThread(() -> processAndDisplay("Максимум", numbers.stream().max(Integer::compare).orElseThrow())));
        taskQueue.add(new WorkerThread(() -> processAndDisplay("Середнє", numbers.stream().mapToInt(Integer::intValue).average().orElseThrow())));
        taskQueue.add(new WorkerThread(() -> processAndDisplay("Парні числа", numbers.stream().filter(n -> n % 2 == 0).collect(Collectors.toList()))));

        // Виконуємо завдання в потоках
        while (!taskQueue.isEmpty()) {
            executor.execute(taskQueue.poll());
        }
    }

    private void processAndDisplay(String label, Object value) {
        String result = label + ": " + value;
        writeToFile(result);
        SwingUtilities.invokeLater(() -> resultArea.append(result + "\n"));
    }

    private void writeToFile(String text) {
        File file = new File("resources/ex6/results.txt");
        file.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new NumberProcessorGUI();
    }
}

// Окремий клас для потокової обробки
class WorkerThread implements Runnable {
    private final Runnable task;

    public WorkerThread(Runnable task) {
        this.task = task;
    }

    @Override
    public void run() {
        task.run();
    }
}

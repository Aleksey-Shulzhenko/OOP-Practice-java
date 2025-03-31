package ex6.code;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

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

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть числа через пробіл:");
        List<Integer> numbers = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        scanner.close();

        ExecutorService executor = Executors.newFixedThreadPool(4);
        BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();

        taskQueue.add(new WorkerThread(() -> {
            int min = numbers.parallelStream().min(Integer::compare).orElseThrow();
            writeToFile("Мінімум: " + min);
            System.out.println("Мінімум: " + min);
        }));

        taskQueue.add(new WorkerThread(() -> {
            int max = numbers.parallelStream().max(Integer::compare).orElseThrow();
            writeToFile("Максимум: " + max);
            System.out.println("Максимум: " + max);
        }));

        taskQueue.add(new WorkerThread(() -> {
            double avg = numbers.parallelStream().mapToInt(Integer::intValue).average().orElseThrow();
            writeToFile("Середнє: " + avg);
            System.out.println("Середнє: " + avg);
        }));

        taskQueue.add(new WorkerThread(() -> {
            List<Integer> filtered = numbers.parallelStream().filter(n -> n % 2 == 0).collect(Collectors.toList());
            writeToFile("Парні числа: " + filtered);
            System.out.println("Парні числа: " + filtered);
        }));

        while (!taskQueue.isEmpty()) {
            executor.execute(taskQueue.poll());
        }

        executor.shutdown();
    }

    private static void writeToFile(String text) {
        File file = new File("resources/ex6/results.txt");
        file.getParentFile().mkdirs(); // Створює всі необхідні папки, якщо їх немає

        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package ex2.code;

import java.io.*;
import java.util.Scanner;

class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private transient String password; // transient поле не серіалізується

    public Person(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + '\'' + ", age=" + age + ", password='" + password + "'}";
    }
}

public class Exercises2 {
    private static final String FILE_NAME = "resources/ex2/person.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть ім'я: ");
        String name = scanner.nextLine();
        System.out.print("Введіть вік: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // очистка буфера
        System.out.print("Введіть пароль: ");
        String password = scanner.nextLine();

        Person person = new Person(name, age, password);
        System.out.println("До серіалізації: " + person);

        // Збереження даних у текстовий файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(name + "," + age + "," + password);
            System.out.println("Об'єкт збережено у файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Читання даних із файлу
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine();
            if (line != null) {
                String[] data = line.split(",");
                String loadedName = data[0];
                int loadedAge = Integer.parseInt(data[1]);
                String loadedPassword = data[2];
                Person deserializedPerson = new Person(loadedName, loadedAge, loadedPassword);
                System.out.println("Після десеріалізації: " + deserializedPerson);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

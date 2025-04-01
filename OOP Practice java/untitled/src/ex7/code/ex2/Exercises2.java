package ex7.code.ex2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

class Person {
    private String name;
    private int age;
    private transient String password; // transient поле не зберігається

    public Person(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getPassword() { return password; }

    @Override
    public String toString() {
        return "Ім'я: " + name + ", Вік: " + age + ", Пароль: " + password;
    }
}

public class Exercises2 extends JFrame {
    private static final String FILE_NAME = "resources/ex2/person.txt";
    private JTextField nameField, ageField;
    private JPasswordField passwordField;
    private JLabel resultLabel;

    public Exercises2() {
        setTitle("Збереження людини");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Ім'я:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Вік:"));
        ageField = new JTextField();
        add(ageField);

        add(new JLabel("Пароль:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton saveButton = new JButton("Зберегти");
        add(saveButton);

        JButton loadButton = new JButton("Завантажити");
        add(loadButton);

        resultLabel = new JLabel("Результат:");
        add(resultLabel);

        saveButton.addActionListener((ActionEvent e) -> savePerson());
        loadButton.addActionListener((ActionEvent e) -> loadPerson());

        setVisible(true);
    }

    private void savePerson() {
        try {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String password = new String(passwordField.getPassword());

            Person person = new Person(name, age, password);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
                writer.write(name + "," + age + "," + password);
                resultLabel.setText("Об'єкт збережено у файл.");
            }
        } catch (IOException | NumberFormatException ex) {
            resultLabel.setText("Помилка збереження!");
        }
    }

    private void loadPerson() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine();
            if (line != null) {
                String[] data = line.split(",");
                String loadedName = data[0];
                int loadedAge = Integer.parseInt(data[1]);
                String loadedPassword = data[2];

                nameField.setText(loadedName);
                ageField.setText(String.valueOf(loadedAge));
                passwordField.setText(loadedPassword);

                resultLabel.setText("Об'єкт завантажено!");
            }
        } catch (IOException ex) {
            resultLabel.setText("Помилка завантаження!");
        }
    }

    public static void main(String[] args) {
        new Exercises2();
    }
}

package ex7.code.ex1;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        String[] predefinedArgs = {"Hello", "World", "123"};

        // Створюємо головне вікно
        JFrame frame = new JFrame("Аргументи");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(predefinedArgs.length, 1)); // Відображаємо в стовпчик

        // Додаємо аргументи у вигляді написів
        for (String arg : predefinedArgs) {
            JLabel label = new JLabel(arg, SwingConstants.CENTER);
            frame.add(label);
        }

        frame.setVisible(true);
    }
}

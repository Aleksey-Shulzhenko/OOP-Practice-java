package ex2.code;

import java.util.Scanner;

public class Exercises4 {

    /**
     * Метод визначає мобільного оператора за номером.
     *
     * @param phoneNumber номер телефону
     * @return оператор, який надає послугу для цього номера
     */
    public static String getOperator(String phoneNumber) {
        // Видаляємо пробіли або інші символи, що можуть бути у номері
        phoneNumber = phoneNumber.replaceAll("[^0-9]", "");

        // Перевірка на правильність введеного номера
        if (phoneNumber.length() < 10) {
            return "Невірний номер телефону";
        }

        // Перша частина номера (префікс)
        String prefix = phoneNumber.substring(0, 3);

        // Визначення оператора за префіксом
        switch (prefix) {
            case "050":
            case "066":
            case "095":
                return "Vodafone";
            case "063":
            case "073":
                return "Lifecell";
            case "068":
            case "097":
                return "Kyivstar";
            case "093":
                // Для 093 можна додати додаткову логіку або уточнення
                if (phoneNumber.startsWith("0930") || phoneNumber.startsWith("0931")) {
                    return "Lifecell";
                } else {
                    return "Kyivstar"; // для інших випадків
                }
            default:
                return "Невідомий оператор";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть номер телефону: ");
        String phoneNumber = scanner.nextLine();

        String operator = getOperator(phoneNumber);
        System.out.println("Оператор: " + operator);
    }
}

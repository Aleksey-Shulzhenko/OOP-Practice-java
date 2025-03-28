package ex5.code;

import java.util.*;

// Singleton для керування колекцією та історією команд
class CollectionManager {
    private static CollectionManager instance;
    private List<String> collection = new ArrayList<>();
    private Stack<Command> history = new Stack<>();

    private CollectionManager() {}

    public static CollectionManager getInstance() {
        if (instance == null) {
            instance = new CollectionManager();
        }
        return instance;
    }

    public void addItem(String item) {
        collection.add(item);
        history.push(new AddCommand(item));
        System.out.println("Додано: " + item);
    }

    public void removeItem(String item) {
        if (collection.remove(item)) {
            history.push(new RemoveCommand(item));
            System.out.println("Видалено: " + item);
        } else {
            System.out.println("Елемент не знайдено.");
        }
    }

    public void showCollection() {
        System.out.println("Колекція: " + collection);
    }

    public void undo() {
        if (!history.isEmpty()) {
            Command lastCommand = history.pop();
            lastCommand.undo();
        } else {
            System.out.println("Немає команд для скасування.");
        }
    }

    // Додаємо методи для безпосереднього керування колекцією
    void removeDirectly(String item) {
        collection.remove(item);
    }

    void addDirectly(String item) {
        collection.add(item);
    }
}

// Інтерфейс команди
interface Command {
    void undo();
}

// Конкретні команди
class AddCommand implements Command {
    private String item;

    public AddCommand(String item) {
        this.item = item;
    }

    public void undo() {
        CollectionManager.getInstance().removeDirectly(item);
        System.out.println("Скасовано додавання: " + item);
    }
}

class RemoveCommand implements Command {
    private String item;

    public RemoveCommand(String item) {
        this.item = item;
    }

    public void undo() {
        CollectionManager.getInstance().addDirectly(item);
        System.out.println("Скасовано видалення: " + item);
    }
}

// Макрокоманда
class MacroCommand implements Command {
    private List<Command> commands = new ArrayList<>();

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void execute() {
        for (Command command : commands) {
            command.undo();
        }
    }

    public void undo() {
        Collections.reverse(commands);
        for (Command command : commands) {
            command.undo();
        }
    }
}

// Основний клас із діалоговим інтерфейсом
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CollectionManager manager = CollectionManager.getInstance();

        while (true) {
            System.out.println("Введіть команду (add/remove/show/undo/exit):");
            String command = scanner.next();

            switch (command) {
                case "add":
                    System.out.println("Введіть елемент:");
                    String itemToAdd = scanner.next();
                    manager.addItem(itemToAdd);
                    break;
                case "remove":
                    System.out.println("Введіть елемент:");
                    String itemToRemove = scanner.next();
                    manager.removeItem(itemToRemove);
                    break;
                case "show":
                    manager.showCollection();
                    break;
                case "undo":
                    manager.undo();
                    break;
                case "exit":
                    System.out.println("Вихід...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Невідома команда.");
            }
        }
    }
}

// Клас для тестування
class CollectionTest {
    public static void main(String[] args) {
        CollectionManager manager = CollectionManager.getInstance();

        System.out.println("=== Тестування почалося ===");
        manager.addItem("яблуко");
        manager.addItem("банан");
        manager.showCollection();

        manager.removeItem("яблуко");
        manager.showCollection();

        manager.undo();
        manager.showCollection();

        manager.undo();
        manager.showCollection();

        System.out.println("=== Тестування завершено ===");
    }
}

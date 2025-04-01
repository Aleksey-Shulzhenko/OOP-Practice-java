package ex7.code.ex5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class CollectionManager {
    private static CollectionManager instance;
    private final List<String> collection = new ArrayList<>();
    private final Stack<Command> history = new Stack<>();

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
    }

    public void removeItem(String item) {
        if (collection.remove(item)) {
            history.push(new RemoveCommand(item));
        }
    }

    public List<String> getCollection() {
        return collection;
    }

    public void undo() {
        if (!history.isEmpty()) {
            Command lastCommand = history.pop();
            lastCommand.undo();
        }
    }

    void removeDirectly(String item) {
        collection.remove(item);
    }

    void addDirectly(String item) {
        collection.add(item);
    }
}

interface Command {
    void undo();
}

class AddCommand implements Command {
    private final String item;

    public AddCommand(String item) {
        this.item = item;
    }

    public void undo() {
        CollectionManager.getInstance().removeDirectly(item);
    }
}

class RemoveCommand implements Command {
    private final String item;

    public RemoveCommand(String item) {
        this.item = item;
    }

    public void undo() {
        CollectionManager.getInstance().addDirectly(item);
    }
}

class MainGUI extends JFrame { // Прибрали public
    private final JTextField inputField;
    private final JTextArea displayArea;
    private final CollectionManager manager;

    public MainGUI() {
        manager = CollectionManager.getInstance();
        setTitle("Collection Manager");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inputField = new JTextField();
        add(inputField, BorderLayout.NORTH);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        add(panel, BorderLayout.SOUTH);

        JButton addButton = new JButton("Add Item");
        addButton.addActionListener(e -> {
            String item = inputField.getText();
            if (!item.isEmpty()) {
                manager.addItem(item);
                updateDisplay();
                inputField.setText("");
            }
        });
        panel.add(addButton);

        JButton removeButton = new JButton("Remove Item");
        removeButton.addActionListener(e -> {
            String item = inputField.getText();
            if (!item.isEmpty()) {
                manager.removeItem(item);
                updateDisplay();
                inputField.setText("");
            }
        });
        panel.add(removeButton);

        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(e -> {
            manager.undo();
            updateDisplay();
        });
        panel.add(undoButton);

        JButton showButton = new JButton("Show Collection");
        showButton.addActionListener(e -> updateDisplay());
        panel.add(showButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateDisplay() {
        List<String> collection = manager.getCollection();
        StringBuilder sb = new StringBuilder("Collection:\n");
        for (String item : collection) {
            sb.append(item).append("\n");
        }
        displayArea.setText(sb.toString());
    }
}

public class Main {
    public static void main(String[] args) {
        new MainGUI();
    }
}

package rvt;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TodoGui extends JFrame {

    private TodoDB db;
    private JTextArea taskArea;
    private JTextField taskInput;
    private JTextField idInput;

    public TodoGui() {
        this.db = new TodoDB();
        
        setTitle("To-Do List");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel taskLabel = new JLabel("Uzdevums:");
        this.taskInput = new JTextField(20);
        JButton addButton = new JButton("Pievienot");

        this.taskArea = new JTextArea(15, 30);
        this.taskArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taskArea);

        JLabel idLabel = new JLabel("Dzēst pēc ID:");
        this.idInput = new JTextField(5);
        JButton deleteButton = new JButton("Dzēst");

        add(taskLabel);
        add(taskInput);
        add(addButton);
        add(scrollPane);
        add(idLabel);
        add(idInput);
        add(deleteButton);

        atjaunotSarakstu();

        addButton.addActionListener(e -> {
            String teksts = taskInput.getText();
            if (!teksts.isEmpty()) {
                db.add(teksts);
                taskInput.setText("");
                atjaunotSarakstu();
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idInput.getText());
                db.removeById(id);
                idInput.setText("");
                atjaunotSarakstu();
            } catch (NumberFormatException ex) {
                System.out.println("Lūdzu ievadi skaitli!");
            }
        });
    }

    private void atjaunotSarakstu() {
        this.taskArea.setText("");
        ArrayList<String> visiUzdevumi = db.findAll();
        for (String uzdevums : visiUzdevumi) {
            this.taskArea.append(uzdevums + "\n");
        }
    }

    public static void main(String[] args) {
        TodoGui gui = new TodoGui();
        gui.setVisible(true);
    }
}
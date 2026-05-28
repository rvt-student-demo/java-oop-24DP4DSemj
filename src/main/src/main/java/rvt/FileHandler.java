package rvt;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILE_PATH = "data/students.csv";

    public static List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        File file = new File(FILE_PATH);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) parent.mkdirs();
        if (!file.exists()) {
            try { file.createNewFile(); } catch (IOException e) {
                System.err.println("Failed to create file: " + e.getMessage());
                return students;
            }
        }
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Student student = new Student(parts[0], parts[1], parts[2], parts[3], parts[4]);
                    if (parts[0].trim().equalsIgnoreCase("name") &&
                            parts[1].trim().equalsIgnoreCase("surname") &&
                            parts[2].trim().equalsIgnoreCase("email")) {
                        continue;
                    }
                    Student student = new Student(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(), parts[4].trim());
                    students.add(student);
                }
            }
        }
    }
}
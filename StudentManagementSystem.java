package Task5;


import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class StudentManagementSystem {
    private Map<Integer, Student> students;
    private static final String DATA_FILE = "students.txt";

    public StudentManagementSystem() {
        students = new HashMap<>();
        loadStudentData();
    }

    // Adds a new student to the system
    public void addStudent(Student student) {
        if (students.containsKey(student.getRollNumber())) {
            System.out.println("A student with this roll number already exists.");
        } else {
            students.put(student.getRollNumber(), student);
            System.out.println("Student added successfully.");
        }
    }

    // Removes a student from the system
    public void removeStudent(int rollNumber) {
        if (students.containsKey(rollNumber)) {
            students.remove(rollNumber);
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student with the given roll number does not exist.");
        }
    }

    // Searches for a student by roll number
    public void searchStudent(int rollNumber) {
        Student student = students.get(rollNumber);
        if (student != null) {
            System.out.println("Student found: " + student);
        } else {
            System.out.println("Student with the given roll number does not exist.");
        }
    }

    // Displays all students in the system
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("All students:");
            for (Student student : students.values()) {
                System.out.println(student);
            }
        }
    }

    // Saves student data to a file
    private void saveStudentData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Student student : students.values()) {
                writer.write(student.getName() + "," + student.getRollNumber() + "," + student.getGrade() + "\n");
            }
            System.out.println("Student data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }

    // Loads student data from a file
    private void loadStudentData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                int rollNumber = Integer.parseInt(data[1]);
                String grade = data[2];

                Student student = new Student(name, rollNumber, grade);
                students.put(rollNumber, student);
            }
            System.out.println("Student data loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading student data: " + e.getMessage());
        }
    }

    // Main method to run the student management system
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Management System:");
            System.out.println("1. Add a new student");
            System.out.println("2. Remove a student");
            System.out.println("3. Search for a student");
            System.out.println("4. Display all students");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter roll number: ");
                    int rollNumber = scanner.nextInt();

                    scanner.nextLine();  // Consume the newline character

                    System.out.print("Enter grade: ");
                    String grade = scanner.nextLine();

                    Student student = new Student(name, rollNumber, grade);
                    sms.addStudent(student);
                    break;

                case 2:
                    System.out.print("Enter roll number of the student to remove: ");
                    rollNumber = scanner.nextInt();
                    sms.removeStudent(rollNumber);
                    break;

                case 3:
                    System.out.print("Enter roll number of the student to search: ");
                    rollNumber = scanner.nextInt();
                    sms.searchStudent(rollNumber);
                    break;

                case 4:
                    sms.displayAllStudents();
                    break;

                case 5:
                    System.out.println("Exiting the system.");
                    sms.saveStudentData();
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }}
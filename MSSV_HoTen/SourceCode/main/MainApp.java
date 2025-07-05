package MSSV_HoTen.SourceCode.main;

import MSSV_HoTen.SourceCode.model.Student;
import MSSV_HoTen.SourceCode.service.StudentService;
import MSSV_HoTen.SourceCode.util.Validator;

import java.util.Scanner;

public class MainApp {
    private static final StudentService studentService = new StudentService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            printMenu();
            choice = Validator.readInt(scanner, "Choose: ");
            switch (choice) {
                case 1 -> handleAddStudent();
                case 2 -> handleDeleteStudent();
                case 3 -> handleSearchStudent();
                case 4 -> handleDisplayAllStudents();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private static void printMenu() {
        System.out.println("\n--- Student Management ---");
        System.out.println("1. Add Student");
        System.out.println("2. Delete Student");
        System.out.println("3. Search Student");
        System.out.println("4. Display All Students");
        System.out.println("0. Exit");
    }

    private static void handleAddStudent() {
        int id = Validator.readInt(scanner, "Enter Student ID: ");
        if (studentService.exists(id)) {
            System.out.println("Student ID already exists!");
            return;
        }
        scanner.nextLine(); // clear buffer
        String name = Validator.readString(scanner, "Enter Full Name (max 50 chars): ", 50);
        double gpa = Validator.readDouble(scanner, "Enter GPA (0.0–4.0): ", 0.0, 4.0);
        Student student = new Student(id, name, gpa);
        studentService.add(student);
        System.out.println("Student added successfully.");
    }

    private static void handleDeleteStudent() {
        int id = Validator.readInt(scanner, "Enter Student ID to delete: ");
        if (studentService.delete(id)) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void handleSearchStudent() {
        scanner.nextLine(); // clear buffer
        String keyword = Validator.readString(scanner, "Enter name keyword to search: ", 50);
        studentService.search(keyword).forEach(System.out::println);
    }

    private static void handleDisplayAllStudents() {
        studentService.displayAll().forEach(System.out::println);
    }
}

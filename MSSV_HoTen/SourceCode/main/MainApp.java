package main;

import model.Student;
import service.StudentService;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();
        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = Integer.parseInt(sc.nextLine());
            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter ID: ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter Full Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter GPA: ");
                        double gpa = Double.parseDouble(sc.nextLine());
                        service.addStudent(new Student(id, name, gpa));
                        System.out.println("Student added.");
                    }
                    case 2 -> {
                        System.out.print("Enter ID to delete: ");
                        int id = Integer.parseInt(sc.nextLine());
                        if (service.deleteStudent(id)) System.out.println("Deleted successfully.");
                        else System.out.println("Student not found.");
                    }
                    case 3 -> {
                        System.out.print("Enter name to search: ");
                        String namePart = sc.nextLine();
                        var found = service.searchByName(namePart);
                        if (found.isEmpty()) System.out.println("No matching students.");
                        else found.forEach(System.out::println);
                    }
                    case 4 -> service.displayAllStudents();
                    case 5 -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

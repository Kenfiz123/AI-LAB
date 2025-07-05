package service;

import model.Student;
import util.Validator;

import java.util.*;
import java.util.stream.Collectors;

public class StudentService {
    private final List<Student> studentList = new ArrayList<>();

    public void addStudent(Student student) throws IllegalArgumentException {
        if (studentList.stream().anyMatch(s -> s.getId() == student.getId())) {
            throw new IllegalArgumentException("Student ID already exists.");
        }
        if (!Validator.isValidName(student.getFullName()) || !Validator.isValidGPA(student.getGpa())) {
            throw new IllegalArgumentException("Invalid name or GPA.");
        }
        studentList.add(student);
    }

    public boolean deleteStudent(int id) {
        return studentList.removeIf(s -> s.getId() == id);
    }

    public List<Student> searchByName(String namePart) {
        return studentList.stream()
                .filter(s -> s.getFullName().toLowerCase().contains(namePart.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void displayAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.printf("%-10s %-30s %-5s\n", "ID", "Full Name", "GPA");
        studentList.forEach(System.out::println);
    }
}

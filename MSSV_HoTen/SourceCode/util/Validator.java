package util;

public class Validator {
    public static boolean isValidGPA(double gpa) {
        return gpa >= 0.0 && gpa <= 4.0;
    }

    public static boolean isValidName(String name) {
        return name != null && name.length() <= 50;
    }
}
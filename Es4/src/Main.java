import java.sql.*;
import java.util.ArrayList;

public class Main {
    final static String URL = "jdbc:mysql://localhost:3306/newdb";
    final static String USER = "root";
    final static String PASSWORD = "password";

    public static void main(String[] args) {
        try {
            Statement s = createDBStatement();
            createItalianView();
            createGermanView();
            ArrayList<Student> italianStudents = selectItalianStudents();
            ArrayList<Student> germanStudents = selectGermanStudents();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Statement createDBStatement() {
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            return c.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Main() {
    }

    public static void createItalianView() {
        String q = "CREATE VIEW italian_students AS SELECT student_name AS name, student_surname AS surname FROM students WHERE (UPPER(country)='Italy')";
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement s = c.createStatement();
        ) {
            s.executeUpdate(q);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void createGermanView() {
        String q = "CREATE VIEW german_students AS SELECT student_name AS name, student_surname AS surname FROM students WHERE (UPPER(country)='Germany')";
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement s = c.createStatement();
        ) {
            s.executeUpdate(q);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Student> selectItalianStudents() {
        String q = "SELECT * FROM italian_students";
        ArrayList<Student> italianStudents = new ArrayList<>();
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(q);
        ) {
            while(rs.next()) {
                italianStudents.add(new Student(rs.getString("name"), rs.getString("surname")));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return italianStudents;
    }
    private static ArrayList<Student> selectGermanStudents() {
        String q = "SELECT * FROM german_students";
        ArrayList<Student> germanStudents = new ArrayList<>();
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(q);
        ) {
            while(rs.next()) {
                germanStudents.add(new Student(rs.getString("name"), rs.getString("surname")));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return germanStudents;
    }
}

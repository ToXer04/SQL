import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    final static String URL = "jdbc:mysql://localhost:3306/newdb";
    final static String USER = "root";
    final static String PASSWORD = "password";

    public static void main(String[] args) {
        Student s1 = new Student("Simone", "Farina", 19);
        Student s2 = new Student("Paolo", "Rossi", 20);
        Student s3 = new Student("Giovanni", "Bianchi", 21);
        Student s4 = new Student("Marco", "Neri", 22);
        ArrayList<Student> students = new ArrayList<>(Arrays.asList(s1, s2, s3, s4));
        Class c1 = new Class(students, 120, "Programming");
        try {
            Statement s = createDBStatement();
            createTable();
            addStudents(c1);
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
    public static void createTable() {
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement s = c.createStatement();
            String q = "CREATE TABLE IF NOT EXISTS students (" +
                    "student_id INTEGER(10) NOT NULL AUTO_INCREMENT," +
                    "student_surname VARCHAR(30)," +
                    "student_name VARCHAR(30)," +
                    "PRIMARY KEY(student_id)" +
                    ");";
            s.executeUpdate(q);
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void addStudents(Class c1) {
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement s = c.createStatement();
            for(Student student : c1.getStudents()) {
                String q = "INSERT INTO students (student_surname, student_name) VALUES ('" + student.getSurname() + "', '" + student.getName() + "');";
                s.executeUpdate(q);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

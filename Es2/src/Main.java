import java.sql.*;
import java.util.ArrayList;

public class Main {
    final static String URL = "jdbc:mysql://localhost:3306/newdb";
    final static String USER = "root";
    final static String PASSWORD = "password";
    public static void main(String[] args) {
        try {
            Statement s = createDBStatement();
            System.out.println(getStudents());
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

    public static ArrayList<String> getStudents() {
        String q = "SELECT student_id, student_surname, student_name FROM students";
        ArrayList<String> surnames = new ArrayList<>();
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(q);
        ) {
            while(rs.next()) {
                System.out.println("Name: " + rs.getString("student_name"));
                surnames.add(rs.getString("student_surname"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return surnames;
    }
}

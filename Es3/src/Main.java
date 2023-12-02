import java.sql.*;

public class Main {
    final static String URL = "jdbc:mysql://localhost:3306/newdb";
    final static String USER = "root";
    final static String PASSWORD = "password";
    public static void main(String[] args) {
        try {
            Statement s = createDBStatement();
            addColumn();
            addValeus();
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

    public static void addColumn() {
        String q = "ALTER TABLE students ADD country VARCHAR(30)";
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement s = c.createStatement();
        ) {
            s.executeUpdate(q);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addValeus() {
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD);) {
            PreparedStatement ps = c.prepareStatement("UPDATE students SET country = ? WHERE student_id = ?");
            ps.setString(1, "Italy");
            ps.setInt(2,1);
            ps.executeUpdate();

            ps.setString(1, "Italy");
            ps.setInt(2,2);
            ps.executeUpdate();

            ps.setString(1, "Germany");
            ps.setInt(2,3);
            ps.executeUpdate();

            ps.setString(1, "Germany");
            ps.setInt(2,4);
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}

import java.util.ArrayList;

public class Class {
    private ArrayList<Student> students = new ArrayList<>();
    private int totalSeats;
    private String subject;

    public Class(ArrayList<Student> students, int totalSeats, String subject) {
        this.students = students;
        this.totalSeats = totalSeats;
        this.subject = subject;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}

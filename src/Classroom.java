import java.util.ArrayList;

public class Classroom {
    private String classroomID;
    private ArrayList <Student> students;

    public Classroom(String _classroomID) {
        this.classroomID = _classroomID;
        students = new ArrayList<Student>();
    }

    void addStudent(Student student) {
        students.add(student);
    }

    String getClassroomID() {
        return this.classroomID;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    
}

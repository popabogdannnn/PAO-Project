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

    void deleteStudent(Student student) {
        int index = -1;
        for(int i = 0; i < students.size(); i++) {
            if(students.get(i).getUsername().equals(student.getUsername())) {
                index = i;
            }
        }
        if(index != -1) {
            students.remove(index);
        }
    }

    String getClassroomID() {
        return this.classroomID;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    
}

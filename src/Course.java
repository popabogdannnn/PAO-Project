import java.util.ArrayList;

public class Course {
    private String courseTitle;
    private ArrayList <Classroom> classroomsAttendingThis;
    private Instructor instructorTeachingThis;

    public Course(String _courseTitle, Instructor _instructorTeachingThis) {
        this.courseTitle = _courseTitle;
        this.classroomsAttendingThis = new ArrayList<Classroom>();
        this.instructorTeachingThis = _instructorTeachingThis;
    }

    public void addClassroom(Classroom classroom) {
        this.classroomsAttendingThis.add(classroom);
    } 

    public void setInstructor(Instructor instructor) {
        this.instructorTeachingThis = instructor;
    }

    public Instructor getInstructorTeachingThis() {
        return this.instructorTeachingThis;
    }

    public String getCourseTitle() {
        return this.courseTitle;
    }

    public ArrayList<Classroom> getClassroomsAttendingThis() {
        return this.classroomsAttendingThis;
    }
    
}

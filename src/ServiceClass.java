import java.util.TreeSet;
import java.util.ArrayList;

public class ServiceClass {
    
    private Administrator admin;
    private TreeSet <User> userList;
    private ArrayList <Course> courseList;
    private ArrayList <Classroom> classroomList; 

    public ServiceClass() {
        userList = new TreeSet <User>(new UserCompare());
        courseList = new ArrayList <Course>();
        classroomList = new ArrayList<Classroom>();
        admin = new Administrator("admin", "admin", "admin", courseList, userList, classroomList);
    }

    public void printUsernames() {
        for(User user : userList) {
            System.out.println(user.getUsername());
        }
    }

    public void createStudent(String _username, String _firstName, String _lastName, String _classroomID) {
        Student stud = new Student(_username, _firstName, _lastName, _classroomID);
        admin.addUser(stud);
    }

    public void createInstructor(String _username, String _firstName, String _lastName) {
        Instructor inst = new Instructor(_username, _firstName, _lastName);
        admin.addUser(inst);
    }

    public void createClassroom(String classroomID) {
        Classroom classroom = new Classroom(classroomID);
        admin.addClassroom(classroom);
    }
    
    public void printClassrooms() {
        for(Classroom classroom : classroomList) {
            System.out.println(classroom.getClassroomID() + ":");
            for(Student stud : classroom.getStudents()) {
                System.out.println(stud.getUsername());
            }
        }
    }

    public void printCourses() {
        for(Course course : courseList) {
            System.out.println(course.getCourseTitle());
            for(Classroom classroom : course.getClassroomsAttendingThis()) {
                System.out.println(classroom.getClassroomID());
            }
        }
    }

    public void createCourse(String courseTitle, String instructorUsername) {
        Instructor inst = (Instructor)admin.getUserByUsername(instructorUsername);
        Course course = new Course(courseTitle, inst);
        admin.addCourse(course);
    }
    
    public void addClassroomToCourse(String classroomID, String courseTitle) {
        for(int i = 0; i < courseList.size(); i++) {
            if(courseList.get(i).getCourseTitle() == courseTitle) {
                admin.addClassroomToCourse(admin.getClassroomByID(classroomID), courseList.get(i));
            }
        }
    }

}

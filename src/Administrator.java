import java.util.ArrayList;
import java.util.TreeSet;

public class Administrator extends User {
    
    private ArrayList <Course> courseList;
    private TreeSet <User> userList;
    private ArrayList <Classroom> classroomList; 
    private ArrayList <Quiz> quizList;  

    public Administrator(String _username, String _firstName, String _lastName, ArrayList <Course> _courseList, TreeSet <User> _userList, ArrayList <Classroom> _classroomList, ArrayList <Quiz> _quizList) {
        super(_username, _firstName, _lastName);
        this.courseList = _courseList;
        this.userList = _userList;
        this.classroomList = _classroomList;
        this.quizList = _quizList;
    }   

    public void addUser(User user) {
        userList.add(user);

        if(user instanceof Student) {
            Student stud = (Student)user;
            for(int i = 0; i < classroomList.size(); i++) {
                if(classroomList.get(i).getClassroomID() == stud.getClassroomID()) {
                    classroomList.get(i).addStudent(stud);
                }
            }
        }
    }

    public User getUserByUsername(String username) {
        User floor = userList.floor(new User(username, "a", "a"));
        if(floor.getUsername() == username) {
            return floor;
        }
        return null;
    }

    public void addCourse(Course course) {
        courseList.add(course);
    }

    public void addClassroom(Classroom classroom) {
        classroomList.add(classroom);
    }

    public Classroom getClassroomByID(String classroomID) {
        for(int i = 0; i < classroomList.size(); i++) {
            if(classroomID == classroomList.get(i).getClassroomID()) {
                return classroomList.get(i);
            }
        }
        return null;
    }

    public void addClassroomToCourse(Classroom classroom, Course course) {
        course.getClassroomsAttendingThis().add(classroom);
    }

    public void addQuiz(Quiz quiz) {
        this.quizList.add(quiz);
    }

    public void addQuestionToQuiz(Question question, Quiz quiz) {
        quiz.addQuestion(question);
    }
}

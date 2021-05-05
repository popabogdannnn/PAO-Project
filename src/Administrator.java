import java.util.ArrayList;
import java.util.TreeSet;

public class Administrator extends User {
    
    private ArrayList <Course> courseList;
    private TreeSet <User> userList;
    private ArrayList <Classroom> classroomList; 
    private ArrayList <Quiz> quizList;
    Reader reader;

    public Administrator(String _username, String _firstName, String _lastName, ArrayList <Course> _courseList, TreeSet <User> _userList, ArrayList <Classroom> _classroomList, ArrayList <Quiz> _quizList) {
        super(_username, _firstName, _lastName);
        this.courseList = _courseList;
        this.userList = _userList;
        this.classroomList = _classroomList;
        this.quizList = _quizList;
        reader = Reader.getInstance();
    }   

    public void addUser(User user) {
        reader.audit("addUser");
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
        reader.audit("getUserByUsername");
        User floor = userList.floor(new User(username, "a", "a"));
        if(floor.getUsername() == username) {
            return floor;
        }
        return null;
    }

    public void addCourse(Course course) {
        reader.audit("addCourse");
        courseList.add(course);
    }

    public void addClassroom(Classroom classroom) {
        reader.audit("addClassroom");
        classroomList.add(classroom);
    }

    public Classroom getClassroomByID(String classroomID) {
        reader.audit("getClassroomByID");
        for(int i = 0; i < classroomList.size(); i++) {
            if(classroomID == classroomList.get(i).getClassroomID()) {
                return classroomList.get(i);
            }
        }
        return null;
    }

    public void addClassroomToCourse(Classroom classroom, Course course) {
        reader.audit("addClassroomToCourse");
        course.getClassroomsAttendingThis().add(classroom);
    }

    public void addQuiz(Quiz quiz) {
        reader.audit("addQuiz");
        this.quizList.add(quiz);
    }

    public void addQuestionToQuiz(Question question, Quiz quiz) {
        reader.audit("addQuestionToQuiz");
        quiz.addQuestion(question);
    }
}

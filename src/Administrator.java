import java.util.ArrayList;
import java.util.TreeSet;

public class Administrator extends User {
    
    private ArrayList <Course> courseList;
    private TreeSet <User> userList;
    private ArrayList <Classroom> classroomList; 
    private ArrayList <Quiz> quizList;
    private ArrayList <Question> questionList;
    ReaderWriter readerWriter;

    public Administrator(String _username, String _firstName, String _lastName, ArrayList <Course> _courseList, TreeSet <User> _userList, ArrayList <Classroom> _classroomList, ArrayList <Quiz> _quizList, ArrayList<Question> questionList) {
        super(_username, _firstName, _lastName);
        this.courseList = _courseList;
        this.userList = _userList;
        this.classroomList = _classroomList;
        this.quizList = _quizList;
        this.questionList = questionList;
        readerWriter = ReaderWriter.getInstance();
    }   

    public void deleteUser(User user) {
        readerWriter.audit("deleteUser");
        userList.remove(user);

        if(user instanceof Student) {
            Student stud = (Student) user;
            for(int i = 0; i < classroomList.size(); i++) {
                if(classroomList.get(i).getClassroomID() == stud.getClassroomID()) {
                    classroomList.get(i).deleteStudent(stud);
                }
            }
        }
    }

    public void addUser(User user) {
        readerWriter.audit("addUser");
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
        readerWriter.audit("getUserByUsername");
        User floor = userList.floor(new User(username, "a", "a"));
        if(floor.getUsername() == username) {
            return floor;
        }
        return null;
    }

    public void addCourse(Course course) {
        readerWriter.audit("addCourse");
        courseList.add(course);
    }

    public void addClassroom(Classroom classroom) {
        readerWriter.audit("addClassroom");
        classroomList.add(classroom);
    }

    public Classroom getClassroomByID(String classroomID) {
        readerWriter.audit("getClassroomByID");
        for(int i = 0; i < classroomList.size(); i++) {
            if(classroomID == classroomList.get(i).getClassroomID()) {
                return classroomList.get(i);
            }
        }
        return null;
    }

    public void addClassroomToCourse(Classroom classroom, Course course) {
        readerWriter.audit("addClassroomToCourse");
        course.getClassroomsAttendingThis().add(classroom);
    }

    public void addQuiz(Quiz quiz) {
        readerWriter.audit("addQuiz");
        this.quizList.add(quiz);
    }

    public void addQuestion(Question question) {
        readerWriter.audit("addQuestion");
        this.questionList.add(question);
    }

    public void addQuestionToQuiz(Question question, Quiz quiz) {
        readerWriter.audit("addQuestionToQuiz");
        quiz.addQuestion(question);
    }
}

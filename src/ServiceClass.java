import java.util.TreeSet;
import java.util.ArrayList;

public class ServiceClass {
    
    private Administrator admin;
    private TreeSet <User> userList;
    private ArrayList <Course> courseList;
    private ArrayList <Classroom> classroomList; 
    private ArrayList <Quiz> quizList;
    private ArrayList <Question> questionList;

    public ServiceClass() {
        userList = new TreeSet <User>(new UserCompare());
        courseList = new ArrayList <Course>();
        classroomList = new ArrayList<Classroom>();
        quizList = new ArrayList <Quiz>();
        questionList = new ArrayList<Question>();
        admin = new Administrator("admin", "admin", "admin", courseList, userList, classroomList, quizList, questionList);
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

    public void createQuiz() {
        Quiz quiz = new Quiz();
        admin.addQuiz(quiz);
    }


    public void addQuestionToQuiz(Question question, int quizID) {
        for(int i = 0; i < quizList.size(); i++) {
            if(quizList.get(i).getQuizID() == quizID) {
                admin.addQuestionToQuiz(question, quizList.get(i));
            }
        }
    }

    public void createQuestion(String statement, String correctAnswer) {
        Question question = new Question(statement, correctAnswer);
        admin.addQuestion(question);
    }

    public void loadData() {
        Reader reader = Reader.getInstance();

        ArrayList<String[]> r = reader.readClassrooms();
        for(String[] line : r) {
            this.createClassroom(line[0]);
        }

        r = reader.readInstructors();
        for(String[] line : r) {
            this.createInstructor(line[0], line[1], line[2]);
        }

        r = reader.readStudents();
        for(String[] line : r) {
            this.createStudent(line[0], line[1], line[2], line[3]);
        }

        r = reader.readQuestions();
        for(String[] line : r) {
            this.createQuestion(line[0], line[1]);
        }
    }

    public void saveData() {
        Reader reader = Reader.getInstance();

        ArrayList<String> csvClassroom = new ArrayList<String>();
        for(int i = 0; i < classroomList.size(); i++) {
            csvClassroom.add(classroomList.get(i).getClassroomID());
        }
        reader.writeClassrooms(csvClassroom);

        ArrayList<String> csvInstructor = new ArrayList<String>();
        ArrayList<String> csvStudent = new ArrayList<String>();
        for(User u : userList) {
            if(u instanceof Instructor) {
                csvInstructor.add(u.getUsername() + "," + u.getFirstName() + "," + u.getLastName());
            }
            if(u instanceof Student) {
                csvStudent.add(u.getUsername() + "," + u.getFirstName() + "," + u.getLastName() + "," + ((Student) u).getClassroomID());
            }
        }
        reader.writeInstructors(csvInstructor);
        reader.writeStudents(csvStudent);

        ArrayList<String> csvQuestions = new ArrayList<String>();
        for(Question q : questionList) {
            csvQuestions.add(q.getStatement() + "," + q.getCorrectAnswer());
        }
        reader.writeQuestions(csvQuestions);
    }


}

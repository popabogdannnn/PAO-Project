import java.sql.Connection;
import java.sql.DriverManager;
import java.util.TreeSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.*;

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


    public void loadDataFromDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/data";
            String user = "root";
            String password = "";
            Connection conn = DriverManager.getConnection(url, user, password);

            String query = "select * from ";

            var statement = conn.prepareStatement(query + "classroom");
            ResultSet r = statement.executeQuery();
            while(r.next()) {
                String id = r.getString("id");
                this.createClassroom(id);
            }

            statement = conn.prepareStatement(query + "instructor");
            r = statement.executeQuery();
            while(r.next()) {
                 String username = r.getString("username");
                 String nume = r.getString("nume");
                 String prenume = r.getString("prenume");
                 this.createInstructor(username, nume, prenume);
            }

            statement = conn.prepareStatement(query + "question");
            r = statement.executeQuery();
            while(r.next()) {
                String intrebare = r.getString("intrebare");
                String raspuns = r.getString("raspuns");
                this.createQuestion(intrebare, raspuns);
            }

            statement = conn.prepareStatement(query + "student");
            r = statement.executeQuery();
            while(r.next()) {
                String username = r.getString("username");
                String nume = r.getString("nume");
                String prenume = r.getString("prenume");
                String grupa_id = r.getString("grupa_id");
                this.createStudent(username, nume, prenume, grupa_id);
            }


        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveDataOnDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/data";
            String user = "root";
            String password = "";
            Connection conn = DriverManager.getConnection(url, user, password);


            String[] databases = {
                    "classroom",
                    "instructor",
                    "question",
                    "student"
            };

            for(String d : databases) {
                String query = "insert ignore into " + d + " VALUES ";
                if(d == "classroom") {
                    for(int i = 0; i < classroomList.size(); i++) {
                        var statement = conn.prepareStatement(query + "('" + classroomList.get(i).getClassroomID() + "')");
                        statement.execute();
                    }
                }

                if(d == "instructor") {
                    for(User u : userList) {
                        if(u instanceof Instructor) {
                            var statement = conn.prepareStatement(query + "('" + u.getUsername() + "','" + u.getFirstName() + "','" + u.getLastName() + "')");
                            statement.execute();
                        }
                    }
                }

                if(d == "student") {
                    for(User u : userList) {
                        if(u instanceof Student) {
                            var statement = conn.prepareStatement(query + "('" + u.getUsername() + "','" + u.getFirstName() + "','" + u.getLastName() + "','" + ((Student) u).getClassroomID() + "')");
                            statement.execute();
                        }
                    }
                }

                if(d == "question") {
                    for(Question q : questionList) {
                        var statement = conn.prepareStatement(query + "('" + q.getStatement() + "','" + q.getCorrectAnswer() + "')");
                        statement.execute();
                    }
                }
            }

        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

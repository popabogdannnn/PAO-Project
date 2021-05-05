import java.io.*;
import java.util.ArrayList;

public class Reader {

    private static Reader instance = null;

    private File fClassroom;
    private File fQuestion;
    private File fInstructor;
    private File fStudent;

    private Reader() {
        this.fClassroom = new File("csv/classroom.csv");
        this.fQuestion = new File("csv/question.csv");
        this.fInstructor = new File("csv/instructor.csv");
        this.fStudent = new File("csv/student.csv");
    }

    public static Reader getInstance() {
        if(instance == null) {
            instance = new Reader();
        }
        return instance;
    }


    private ArrayList <String> readFile(File f) {
        ArrayList <String> ret = new ArrayList<String>();
        try {
        BufferedReader fin = new BufferedReader(new FileReader(this.fClassroom));

            for (String line = fin.readLine(); fin != null; line = fin.readLine()) {
                ret.add(line);
            }
        } catch(Exception e) {
            System.out.println("Salut n-am deschis " + f.getName());
        }
        return ret;
    }

    public void readClassrooms() {
        this.readFile(this.fClassroom);
    }

    public void readQuestions() {
        this.readFile(this.fQuestion);
    }

    public void readInstructors() {
        this.readFile(this.fInstructor);
    }

    public void readStudents() {
        this.readFile(this.fStudent);
    }

    public void test() {
        try {
            BufferedReader fin = new BufferedReader(new FileReader(this.fClassroom));
            String x = fin.readLine();
            System.out.println(x);
        } catch (Exception e) {
            System.out.println("Salut n-am deschis");
        }
    }

}

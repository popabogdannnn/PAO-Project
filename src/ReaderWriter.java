import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReaderWriter {

    private static ReaderWriter instance = null;

    private File fClassroom;
    private File fQuestion;
    private File fInstructor;
    private File fStudent;
    private File fAudit;

    private ReaderWriter() {
        this.fClassroom = new File("csv/classroom.csv");
        this.fQuestion = new File("csv/question.csv");
        this.fInstructor = new File("csv/instructor.csv");
        this.fStudent = new File("csv/student.csv");
        this.fAudit = new File("csv/audit.csv");
    }

    public static ReaderWriter getInstance() {
        if(instance == null) {
            instance = new ReaderWriter();
        }
        return instance;
    }


    private ArrayList <String[]> readFile(File f) {
        ArrayList <String[]> ret = new ArrayList<String[]>();
        try {
        BufferedReader fin = new BufferedReader(new FileReader(f));

            for (String line = fin.readLine(); line != null; line = fin.readLine()) {
                String[] splitLine = line.split(",");
                ret.add(splitLine);
            }
        } catch(Exception e) {
            System.out.println("Exceptie la citire " + f.getName());
        }
        return ret;
    }

    public ArrayList<String[]> readClassrooms() {
        return this.readFile(this.fClassroom);
    }

    public ArrayList<String[]> readQuestions() {
        return this.readFile(this.fQuestion);
    }

    public ArrayList<String[]> readInstructors() {
        return this.readFile(this.fInstructor);
    }

    public ArrayList<String[]> readStudents() {
        return this.readFile(this.fStudent);
    }

    private void writeInFile(ArrayList<String> lines, File f) {
        try {
            FileWriter fw = new FileWriter("csv/" + f.getName());
            for(int i = 0; i < lines.size(); i++) {
                fw.write(lines.get(i) + "\n");
            }
            fw.close();
        } catch(Exception e) {
            System.out.println("Exceptie la scriere " + f.getName());
        }
    }

    public void writeClassrooms(ArrayList<String> lines) {
        this.writeInFile(lines, fClassroom);
    }

    public void writeQuestions(ArrayList<String> lines) {
        this.writeInFile(lines, fQuestion);
    }

    public void writeInstructors(ArrayList<String> lines) {
        this.writeInFile(lines, fInstructor);
    }

    public void writeStudents(ArrayList<String> lines) {
        this.writeInFile(lines, fStudent);
    }

    public void audit(String s) {
        try {
            FileWriter fw = new FileWriter("csv/audit.csv", true);
            Date date = new Date();
            fw.write(s + "," + new Timestamp(date.getTime()) + "\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("Exceptie la audit");
        }
    }


    public void test() {
        try {
            BufferedReader fin = new BufferedReader(new FileReader(this.fClassroom));
            String x = fin.readLine();
        } catch (Exception e) {
            System.out.println("Salut n-am deschis");
        }
    }

}

public class Student extends User {
    
    private String classroomID;

    public Student(String _username, String _firstName, String _lastName, String _classroomID) {
        super(_username, _firstName, _lastName);
        this.classroomID = _classroomID;
    }

    public String getClassroomID() {
        return this.classroomID;
    }

}

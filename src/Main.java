public class Main {

    
    public static void main(String[] args) throws Exception {
        ServiceClass service = new ServiceClass();
        
        service.createClassroom("Grupa 234");
        service.createStudent("sura", "alex", "beca", "Grupa 234");
        service.createStudent("nastut", "alex", "nasta", "Grupa 234");
        service.createInstructor("instructor", "Gigi", "Ionel");
        service.createCourse("Java", "instructor");
        service.addClassroomToCourse("Grupa 234", "Java");
        service.printUsernames();
        service.printClassrooms();
        service.printCourses();
        service.createQuiz();
        service.addQuestionToQuiz("Cat fac 1 + 1?", 0);
    }   

   

}

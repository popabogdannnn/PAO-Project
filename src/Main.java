import java.util.Locale;
import java.util.Scanner;

public class Main {


        public static void main(String[] args) throws Exception {
        ServiceClass service = new ServiceClass();

        service.loadDataFromDB();

        Scanner scanner = new Scanner(System.in);
        String read;
        do {

            System.out.println("insert: Insereaza cursant");
            System.out.println("update: Update cursant");
            System.out.println("delete: Sterge cursant");
            System.out.println("quit: Incheie executia");
            read = scanner.nextLine();


            if(read.equals(new String("insert"))) {
                System.out.print("username = ");
                String username = scanner.nextLine();
                System.out.println("nume = ");
                String nume = scanner.nextLine();
                System.out.println("prenume = ");
                String prenume = scanner.nextLine();
                System.out.println("grupa = ");
                String grupa = scanner.nextLine();

                service.createStudent(username, nume, prenume, grupa);
            }
            else if(read.equals(new String("update"))){
                System.out.print("username = ");
                String username = scanner.nextLine();
                System.out.println("nume = ");
                String nume = scanner.nextLine();
                System.out.println("prenume = ");
                String prenume = scanner.nextLine();
                System.out.println("grupa = ");
                String grupa = scanner.nextLine();
                service.updateStudent(username, nume, prenume, grupa);
            }
            else if(read.equals(new String("delete"))){
                System.out.print("username = ");
                String username = scanner.nextLine();
                service.deleteUser(username);
            }

        }while(!read.equals(new String("quit")));

        service.saveDataOnDB();
    }
}

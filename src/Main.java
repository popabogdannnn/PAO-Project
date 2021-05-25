
public class Main {
    public static void main(String[] args) throws Exception {
        ServiceClass service = new ServiceClass();

        service.loadDataFromDB();
        //service.loadData();
        //service.saveData();
        service.saveDataOnDB();
    }
}

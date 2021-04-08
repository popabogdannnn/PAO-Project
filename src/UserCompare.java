import java.util.Comparator;

public class UserCompare implements Comparator<User> {

    @Override
    public int compare(User a, User b) {
        return a.getUsername().compareTo(b.getUsername());
    }
    
}

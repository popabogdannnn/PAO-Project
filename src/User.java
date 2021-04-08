public class User {
    private String username;
    private String firstName;
    private String lastName;

    public User(String _username, String _firstName, String _lastName) {
        this.username = _username;
        this.firstName = _firstName;
        this.lastName = _lastName;
    }

    public String getUsername() {
        return this.username;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
    
}

package pl.coderslab.entity;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;

    public User(int id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public User() {}

    public void setId(int id) {
        this.id = id;
    }

    public String setUsername(String username) {
        this.username = username;
        return username;
    }

    public String setEmail(String email) {
        this.email = email;
        return email;
    }

    public String setPassword(String password) {
        this.password = password;
        return password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}


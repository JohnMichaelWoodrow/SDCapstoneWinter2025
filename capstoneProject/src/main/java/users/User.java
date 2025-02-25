package users;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public abstract class User {
    protected int id;
    protected String name;
    protected String email;
    protected static final String EMAIL_REGEX = "^[^@]+@[^@]+\\.com$";
    protected static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        if(isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address: " + email);
        } else
            this.email = email;
    }

    public abstract void displayUserInfo();

    public int getId() {
        return id;
    }

    public int setId(int id) {
        return this.id = id;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        return this.name = name;
    }

    public boolean isValidEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return !matcher.matches();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;
    }

    public void register() {
        // TODO: Logic for register? ****AFTER CONSOLE APPLICATION****
    }

    public void login() {
        // TODO: Logic for login? ****AFTER CONSOLE APPLICATION****
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Email: " + email;
    }
}

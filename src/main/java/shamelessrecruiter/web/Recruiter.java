package shamelessrecruiter.web;

public class Recruiter {

    public final String name;
    public final String email;
    public final String message;

    public Recruiter(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format(
                "Recruiter[email=%s]", email);
    }

}

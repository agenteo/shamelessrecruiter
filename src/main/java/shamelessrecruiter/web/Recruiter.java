package shamelessrecruiter.web;

import org.springframework.data.annotation.Id;

public class Recruiter {

    @Id
    private String id;
    private String email;


    public Recruiter() {}

    public Recruiter(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(
                "Recruiter[email=%s]", email);
    }

}

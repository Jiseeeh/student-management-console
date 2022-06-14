package Model;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends User {
    private static final List<Feedback> givenFeeds = new ArrayList<>();
    private static final List<Task> givenTasks = new ArrayList<>();

    public Teacher(UserBuilder builder) {
        this.setFirstName(builder.getFirstName());
        this.setLastName(builder.getLastName());
        this.setUsername(builder.getUsername());
        this.setPassword(builder.getPassword());
        this.setAge(builder.getAge());
        this.setType(builder.getType());
    }

    public List<Feedback> getGivenFeeds() {
        return givenFeeds;
    }

    public List<Task> getGivenTasks() {
        return givenTasks;
    }
}

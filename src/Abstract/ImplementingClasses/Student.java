package Abstract.ImplementingClasses;

import Abstract.User;
import Database.Feedback;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    //TODO add these:
    // -Student's own feedbackList/TaskList
    // -Methods to add/view it
    private final List<Feedback> myFeeds = new ArrayList<>();

    public Student(UserBuilder builder) {
        this.setFirstName(builder.getFirstName());
        this.setLastName(builder.getLastName());
        this.setUsername(builder.getUsername());
        this.setPassword(builder.getPassword());
        this.setAge(builder.getAge());
        this.setType(builder.getType());
    }

    public void acceptFeed(Feedback feedback) {
        myFeeds.add(feedback);
    }

    public void viewMyFeeds() {
        myFeeds.forEach(feedback -> {
            System.out.println(feedback.getFeedback(true));
        });
    }

    public void clearFeeds() {
        myFeeds.clear();
    }

}

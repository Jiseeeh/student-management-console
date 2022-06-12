package Model;

import Database.Feedback;
import Database.Task;
import Helper.ListHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student extends User {
    private final List<Feedback> myFeeds = new ArrayList<>();
    private final List<Task> myTasks = new ArrayList<>();


    public Student(UserBuilder builder) {
        this.setFirstName(builder.getFirstName());
        this.setLastName(builder.getLastName());
        this.setUsername(builder.getUsername());
        this.setPassword(builder.getPassword());
        this.setAge(builder.getAge());
        this.setType(builder.getType());
    }

    // for the student to also have a reference to the given task
    public void acceptTask(Task task) {
        myTasks.add(task);
    }

    // for the student to also have a reference to the given feedback
    public void acceptFeed(Feedback feedback) {
        myFeeds.add(feedback);
    }
    public List<Feedback> getMyFeeds() {
        return myFeeds;
    }

    public List<Task> getMyTasks() {
        return myTasks;
    }

}

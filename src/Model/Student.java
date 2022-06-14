package Model;

import Controller.StudentController;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private final List<Feedback> myFeeds = new ArrayList<>();
    private final List<Task> myTasks = new ArrayList<>();
    private StudentController myController;

    public Student(UserBuilder builder) {
        this.setFirstName(builder.getFirstName());
        this.setLastName(builder.getLastName());
        this.setUsername(builder.getUsername());
        this.setPassword(builder.getPassword());
        this.setAge(builder.getAge());
        this.setType(builder.getType());
    }

    public List<Feedback> getMyFeeds() {
        return myFeeds;
    }

    public List<Task> getMyTasks() {
        return myTasks;
    }

    public StudentController getMyController() {
        return myController;
    }

    public void setMyController(StudentController myController) {
        this.myController = myController;
    }
}

package Model;

import Helper.FileHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Teacher extends User {
    private static final File feedsCSV = new File("src/Database/CSV/feeds.csv");
    private static final File tasksCSV = new File("src/Database/CSV/tasks.csv");
    private final List<Feedback> givenFeeds = new ArrayList<>();
    private final List<Task> givenTasks = new ArrayList<>();

    public Teacher(UserBuilder builder) {
        this.setFirstName(builder.getFirstName());
        this.setLastName(builder.getLastName());
        this.setUsername(builder.getUsername());
        this.setPassword(builder.getPassword());
        this.setAge(builder.getAge());
        this.setType(builder.getType());
        FileHelper.checkForFeeds(feedsCSV, givenFeeds);
        FileHelper.checkForTasks(tasksCSV, givenTasks);
    }

    public List<Feedback> getGivenFeeds() {
        return givenFeeds;
    }

    public List<Task> getGivenTasks() {
        return givenTasks;
    }

    public File getFeedsCSV() {
        return feedsCSV;
    }

    public File getTasksCSV() {
        return tasksCSV;
    }

}

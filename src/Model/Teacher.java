package Model;

import Database.AccountsDB;
import Database.Feedback;
import Database.Task;
import Helper.FileHelper;
import Helper.ListHelper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Teacher extends User {
    private static final List<Feedback> givenFeeds = new ArrayList<>();
    private static final List<Task> givenTasks = new ArrayList<>();
    private static final File feedsCSV = new File("src/Database/CSV/feeds.csv");
    private static final File tasksCSV = new File("src/Database/CSV/tasks.csv");
    private final AccountsDB accountsDB = AccountsDB.INSTANCE;
    private final List<Student> studentList = accountsDB.getStudentList();
    private final Scanner scan = new Scanner(System.in);

    public Teacher(UserBuilder builder) {
        this.setFirstName(builder.getFirstName());
        this.setLastName(builder.getLastName());
        this.setUsername(builder.getUsername());
        this.setPassword(builder.getPassword());
        this.setAge(builder.getAge());
        this.setType(builder.getType());

    }

    // This loads the feeds and tasks csv if there are any contents present.
    public static void checkForTasksAndFeeds() {
        FileHelper.checkForFeeds(feedsCSV, givenFeeds);
        FileHelper.checkForTasks(tasksCSV, givenTasks);
    }
}

package Abstract.ImplementingClasses;

import Abstract.User;
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

    public static void checkForTasksAndFeeds() {
        FileHelper.checkForFeeds(feedsCSV, givenFeeds);
        FileHelper.checkForTasks(tasksCSV, givenTasks);
    }

    public void giveFeed() {
        Student student;
        int i = 0;

        if (!ListHelper.hasStudents(studentList, "feeds")) return;

        System.out.println("\nEnter the student's number");

        for (var user : studentList) {
            System.out.println(i + ": " + user.getFirstName());
            i++;
        }

        System.out.print(": ");
        int studentNumber = Integer.parseInt(scan.nextLine());

        student = studentList.get(studentNumber);

        System.out.print("Enter your feedback: ");
        String feed = scan.nextLine();

        Feedback feedback = new Feedback(student.getFirstName(), this.getFirstName(), feed);
        givenFeeds.add(feedback);
        student.acceptFeed(feedback);

        try (FileWriter feedsCSVWriter = new FileWriter(feedsCSV, true);) {
            feedsCSVWriter.append(feedback + "\n");
            feedsCSVWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void giveTask() {
        Student student;
        int i = 0;

        if (!ListHelper.hasStudents(studentList, "tasks")) return;

        System.out.println("\nEnter the student's number");

        for (var user : studentList) {
            System.out.println(i + ": " + user.getFirstName());
            i++;
        }

        System.out.print(": ");
        int studentNumber = Integer.parseInt(scan.nextLine());

        student = studentList.get(studentNumber);

        System.out.print("Enter the task: ");
        String givenTask = scan.nextLine();

        Task task = new Task(student.getFirstName(), this.getFirstName(), givenTask);
        givenTasks.add(task);
        student.acceptTask(task);

        try (FileWriter tasksCSVWriter = new FileWriter(tasksCSV, true)) {
            tasksCSVWriter.append(task.toString());
            tasksCSVWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void viewGivenFeeds() {
        if (!ListHelper.hasFeeds(givenFeeds)) return;
        givenFeeds.forEach(feedback -> System.out.println(feedback.getFeedback(false)));
    }

    public void viewGivenTasks() {
        if (!ListHelper.hasTasks(givenTasks)) return;
        givenTasks.forEach(task -> System.out.println(task.getTask(false)));
    }


    public void clearFeeds() {
        System.out.println("""
                                        
                |-----------------------------------|
                |* Done clearing your given Feeds! *|
                |-----------------------------------|
                """);

        givenFeeds.clear();
        FileHelper.clearCSVFile(feedsCSV, "StudentName,TeacherName,Feedback\n");
    }

    public void clearTasks() {
        System.out.println("""
                                        
                |-----------------------------------|
                |* Done clearing your given Tasks! *|
                |-----------------------------------|
                """);

        givenTasks.clear();
        FileHelper.clearCSVFile(tasksCSV, "StudentName,TeacherName,Task\n");
    }
}
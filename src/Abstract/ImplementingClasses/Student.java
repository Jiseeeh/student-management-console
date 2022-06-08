package Abstract.ImplementingClasses;

import Abstract.User;
import Database.Feedback;
import Database.Task;
import Helper.ListHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student extends User {
    private final List<Feedback> myFeeds = new ArrayList<>();
    private final List<Task> myTasks = new ArrayList<>();
    private final Scanner scan = new Scanner(System.in);

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
        if (ListHelper.hasFeeds(myFeeds)) return;

        myFeeds.forEach(feedback -> System.out.println(feedback.getFeedback(true)));
    }

    public void acceptTask(Task task) {
        myTasks.add(task);
    }

    public void markTask() {
        int i = 0;

        if (ListHelper.hasTasks(myTasks)) return;

        System.out.println("""
                Marking a task as done won't be saved when you exit,
                Make sure to clear your tasks after so that you don't need to mark it again.
                """);

        for (var task : myTasks) {
            System.out.println(i + ": " + task.getTask(true));
            i++;
        }

        System.out.print("Enter the task you want to mark as done: ");
        int index = Integer.parseInt(scan.nextLine());

        myTasks.get(index).markAsDone();
    }

    public void viewMyTasks() {
        if (ListHelper.hasTasks(myTasks)) return;

        myTasks.forEach(task -> System.out.println(task.getTask(true)));
    }
}

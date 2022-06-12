package Controller;

import Database.Feedback;
import Database.Task;
import Helper.ListHelper;
import Model.Student;
import Views.StudentView;

import java.util.List;
import java.util.Scanner;

public class StudentController {

    private final Student student;
    private final StudentView studentView = new StudentView();
    private final List<Feedback> myFeeds;
    private final List<Task> myTasks;
    private final Scanner scan;
    public StudentController (Student student, Scanner scan) {
        this.student = student;
        this.scan = scan;
        myFeeds = student.getMyFeeds();
        myTasks = student.getMyTasks();
    }

    public void start () {
        studentView.show(this);
    }

    public void viewMyFeeds() {
        if (!ListHelper.hasFeeds(myFeeds)) return;

        myFeeds.forEach(feedback -> System.out.println(feedback.getFeedback(true)));
    }



    public void viewMyTasks() {
        if (!ListHelper.hasTasks(myTasks)) return;

        myTasks.forEach(task -> System.out.println(task.getTask(true)));
    }

    public void markTask() {
        int i = 0;

        if (!ListHelper.hasTasks(myTasks)) return;

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

    public void viewMyInfo() {
        student.viewMyInfo();
    }
}

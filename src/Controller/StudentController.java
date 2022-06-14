package Controller;

import Helper.ListHelper;
import Model.Feedback;
import Model.Student;
import Model.Task;
import Views.StudentView;

import java.util.List;
import java.util.Scanner;

public class StudentController {
    private final Student student;
    private final StudentView studentView = new StudentView();
    private final List<Feedback> studentFeeds;
    private final List<Task> studentTasks;
    private final Scanner scan = new Scanner(System.in);

    public StudentController(Student student) {
        this.student = student;
        studentFeeds = student.getMyFeeds();
        studentTasks = student.getMyTasks();
        student.setMyController(this);
    }

    public void start() {
        chooseFromDashboard();
    }

    private void chooseFromDashboard() {
        while (true) {
            studentView.showMyDashboard();
            String input = scan.nextLine().trim();

            int choice = Integer.parseInt(input);

            switch (choice) {
                case 1 -> studentView.viewMyTasks(studentTasks);
                case 2 -> studentView.viewMyFeeds(studentFeeds);
                case 3 -> markTask();
                case 4 -> studentView.viewMyInfo(student);
                case 5 -> {
                    return;
                }
            }
        }
    }

    private void markTask() {
        int i = 0;

        if (!ListHelper.hasTasks(studentTasks)) return;

        System.out.println("""
                NOTE: Marking a task as done won't be saved when you exit.
                So when you log back again, you won't see (DONE) with it.
                """);

        for (var task : studentTasks) {
            System.out.println(i + ": " + task.getTask(true));
            i++;
        }

        System.out.print("Enter the task you want to mark as done: ");
        int index = Integer.parseInt(scan.nextLine());

        studentTasks.get(index).markAsDone();
    }

    // for the student to also have a reference to the given task
    public void acceptTask(Task task) {
        studentTasks.add(task);
    }

    // for the student to also have a reference to the given feedback
    public void acceptFeed(Feedback feedback) {
        studentFeeds.add(feedback);
    }
}

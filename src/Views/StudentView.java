package Views;

import Database.Access.AccountsOBJ;
import Database.Access.FeedbacksOBJ;
import Database.Access.TasksOBJ;
import Database.AccountsDB;
import Database.FeedbacksDB;
import Database.TasksDB;
import Prompt.StudentSystem;

import java.util.Scanner;

public class StudentView {
    private static final FeedbacksDB feedbacks = FeedbacksOBJ.INSTANCE;
    private static final TasksDB tasks = TasksOBJ.INSTANCE;
    private static final Scanner scan = new Scanner(System.in);
    private static final AccountsDB accounts = AccountsOBJ.INSTANCE;

    public static void show() {
        int choice = 0;

        while (choice != 5) {
            System.out.println("""
                    \nWhat do you want to do?
                    1 -> See tasks
                    2 -> See Feedbacks
                    3 -> Mark task as done
                    4 -> See my info
                    5 -> Logout
                    """);
            System.out.print(": ");
            choice = Integer.parseInt(scan.nextLine());

            if (choice == 5) StudentSystem.prompt();
            if (choice == 1) seeTasks();
            if (choice == 2) seeFeedBacks();
            if (choice == 3) markTask();
            if (choice == 4) seeInfo();
        }
    }

    private static void seeTasks() {
        tasks.listTasks();
    }

    private static void seeFeedBacks() {
        feedbacks.listFeedBacks();
    }

    private static void markTask() {
        tasks.markAsDone();
    }

    private static void seeInfo() {
        accounts.getAccountInfo();
    }
}

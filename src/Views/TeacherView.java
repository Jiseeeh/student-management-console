package Views;

import Database.Access.AccountsOBJ;
import Database.Access.FeedbacksOBJ;
import Database.Access.TasksOBJ;
import Database.AccountsDB;
import Database.FeedbacksDB;
import Database.TasksDB;
import Prompt.StudentSystem;

import java.util.Scanner;

public class TeacherView {
    private static final FeedbacksDB feedBacks = FeedbacksOBJ.INSTANCE;
    private static final TasksDB tasks = TasksOBJ.INSTANCE;
    private static final AccountsDB accounts = AccountsOBJ.INSTANCE;
    private static final Scanner scan = new Scanner(System.in);

    public static void show() {
        int choice = 0;

        while (choice != 6) {
            System.out.println("""
                    \nWhat do you want to do?
                    1 -> Give Feedbacks to students
                    2 -> Assign a Task
                    3 -> Remove a Task
                    4 -> Remove a Feedback
                    5 -> See my info
                    6 -> Logout
                    """);
            System.out.print(": ");
            choice = Integer.parseInt(scan.nextLine());

            if (choice == 6) StudentSystem.prompt();
            if (choice == 1) giveFeedBack();
            if (choice == 2) assignTask();
            if (choice == 3) removeTask();
            if (choice == 4) removeFeedback();
            if (choice == 5) seeInfo();
        }
    }

    private static void giveFeedBack() {
        feedBacks.giveFeedBack();
    }

    private static void removeFeedback() {
        feedBacks.removeFeedBack();
    }

    private static void assignTask() {
        tasks.addTask();
    }

    private static void removeTask() {
        tasks.removeTask();
    }

    private static void seeInfo() {
        accounts.getAccountInfo();
    }
}

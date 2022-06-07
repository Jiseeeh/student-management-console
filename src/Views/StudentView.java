package Views;

import Abstract.ImplementingClasses.Student;
import Database.Access.AccountsOBJ;
import Database.AccountsDB;
import Prompt.StudentSystem;

import java.util.Scanner;

public class StudentView {
    private static final Scanner scan = new Scanner(System.in);
    private static final AccountsDB accounts = AccountsOBJ.INSTANCE;

    public static void show(Student studentLogged) {
        int choice = 0;

        while (choice != 6) {
            System.out.println("""
                    \nWhat do you want to do?
                    1 -> See tasks
                    2 -> See Feedbacks
                    3 -> Mark task as done
                    4 -> See my info
                    5 -> Clear Feedbacks
                    6 -> Logout
                    """);
            System.out.print(": ");
            choice = Integer.parseInt(scan.nextLine());

            if (choice == 6) StudentSystem.prompt();
//            if (choice == 1) seeTasks();
            if (choice == 2) studentLogged.viewMyFeeds();
//            if (choice == 3) markTask();
            if (choice == 4) seeInfo();
            if (choice == 4) studentLogged.clearFeeds();
        }
    }

    private static void seeTasks() {
//        tasks.listTasks();
    }

    private static void markTask() {
//        tasks.markAsDone();
    }

    private static void seeInfo() {
        accounts.getAccountInfo();
    }
}

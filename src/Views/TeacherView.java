package Views;

import Abstract.ImplementingClasses.Teacher;
import Database.Access.AccountsOBJ;
import Database.AccountsDB;
import Prompt.StudentSystem;

import java.util.Scanner;

public class TeacherView {
    private static final AccountsDB accounts = AccountsOBJ.INSTANCE;
    private static final Scanner scan = new Scanner(System.in);

    public static void show(Teacher teacherLogged) {
        int choice = 0;

        while (choice != 7) {
            System.out.println("""
                    \nWhat do you want to do?
                    1 -> Give Feedbacks to students
                    2 -> Assign a Task
                    3 -> Remove a Task
                    4 -> Clear Feedbacks
                    5 -> See my info
                    6 -> See given feedbacks
                    7 -> Logout
                    """);
            System.out.print(": ");
            choice = Integer.parseInt(scan.nextLine());

            if (choice == 7) StudentSystem.prompt();
            if (choice == 1) teacherLogged.giveFeed();
//            if (choice == 2) assignTask();
//            if (choice == 3) removeTask();
            if (choice == 4) teacherLogged.clearFeeds();
            if (choice == 5) seeInfo();
            if (choice == 6) teacherLogged.viewGivenFeeds();
        }
    }

    private static void seeInfo() {
        accounts.getAccountInfo();
    }
}

package Views;

import Abstract.ImplementingClasses.Teacher;
import Prompt.StudentSystem;

import java.util.Scanner;

public class TeacherView {
    private static final Scanner scan = new Scanner(System.in);

    public static void show(Teacher teacherLogged) {
        int choice = 0;

        while (choice != 8) {
            System.out.println("""
                    \nWhat do you want to do?
                    1 -> Give Feedbacks to students
                    2 -> Assign a Task
                    3 -> Clear Tasks
                    4 -> Clear Feedbacks
                    5 -> See my info
                    6 -> See given feedbacks
                    7 -> See given feedbacks
                    8 -> Logout
                    """);
            System.out.print(": ");
            choice = Integer.parseInt(scan.nextLine());

            if (choice == 8) StudentSystem.prompt();
            if (choice == 1) teacherLogged.giveFeed();
            if (choice == 2) teacherLogged.giveTask();
            if (choice == 3) teacherLogged.clearTasks();
            if (choice == 4) teacherLogged.clearFeeds();
            if (choice == 5) teacherLogged.viewMyInfo();
            if (choice == 6) teacherLogged.viewGivenFeeds();
            if (choice == 7) teacherLogged.viewGivenTasks();
        }
    }
}

package Views;

import Abstract.ImplementingClasses.Student;
import Prompt.StudentSystem;

import java.util.Scanner;

public class StudentView {
    private static final Scanner scan = new Scanner(System.in);

    public static void show(Student studentLogged) {
        int choice = 0;

        while (choice != 7) {
            System.out.println("""
                    \nWhat do you want to do?
                    1 -> See tasks
                    2 -> See Feedbacks
                    3 -> Mark task as done
                    4 -> See my info
                    5 -> Clear Feedbacks
                    6 -> Clear Tasks
                    7 -> Logout
                    """);
            System.out.print(": ");
            choice = Integer.parseInt(scan.nextLine());

            if (choice == 7) StudentSystem.prompt();
            if (choice == 1) studentLogged.viewMyTasks();
            if (choice == 2) studentLogged.viewMyFeeds();
            if (choice == 3) studentLogged.markTask();
            if (choice == 4) studentLogged.viewMyInfo();
            if (choice == 5) studentLogged.clearFeeds();
            if (choice == 6) studentLogged.clearTasks();
        }
        scan.close();
    }
}

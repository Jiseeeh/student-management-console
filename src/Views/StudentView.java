package Views;

import Abstract.ImplementingClasses.Student;
import Helper.InputHelper;

import java.util.Scanner;

public class StudentView {
    private static final Scanner scan = new Scanner(System.in);

    public static void show(Student studentLogged) {

        while (true) {
            System.out.println("""
                    \nWhat do you want to do?
                    1 -> See tasks
                    2 -> See Feedbacks
                    3 -> Mark task as done
                    4 -> See my info
                    5 -> Logout
                    """);
            System.out.print(": ");
            String input = scan.nextLine().trim();

            if (InputHelper.hasLetterInput(input)) continue;

            int choice = Integer.parseInt(input);

            if (choice == 5) return;
            if (choice == 1) studentLogged.viewMyTasks();
            if (choice == 2) studentLogged.viewMyFeeds();
            if (choice == 3) studentLogged.markTask();
            if (choice == 4) studentLogged.viewMyInfo();
        }
    }
}

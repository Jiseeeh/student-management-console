package Views;

import Controller.StudentController;
import Model.Student;

import java.util.Scanner;

public class StudentView {
    private final Scanner scan = new Scanner(System.in);

    public void show (StudentController studentController){

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

            int choice = Integer.parseInt(input);

            if (choice == 5) return;
            if (choice == 1) studentController.viewMyTasks();
            if (choice == 2) studentController.viewMyFeeds();
            if (choice == 3) studentController.markTask();
            if (choice == 4) studentController.viewMyInfo();
        }
    }
}

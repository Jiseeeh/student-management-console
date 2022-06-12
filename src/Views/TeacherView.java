package Views;

import Controller.TeacherController;
import Model.Teacher;
import Helper.InputHelper;

import java.util.Scanner;

public class TeacherView {
    public void show(TeacherController teacherController, Scanner scan) {

        while (true) {
            System.out.println("""
                    \nWhat do you want to do?
                    1 -> Give Feedbacks to students
                    2 -> Assign a Task
                    3 -> Clear Tasks
                    4 -> Clear Feedbacks
                    5 -> See my info
                    6 -> See given feedbacks
                    7 -> See given Tasks
                    8 -> Logout
                    """);
            System.out.print(": ");
            String input = scan.nextLine();

            if (InputHelper.hasLetterInput(input)) continue;

            int choice = Integer.parseInt(input);

            if (choice == 8) return;
            if (choice == 1) teacherController.giveFeed();
            if (choice == 2) teacherController.giveTask();
            if (choice == 3) teacherController.clearTasks();
            if (choice == 4) teacherController.clearFeeds();
            if (choice == 5) teacherController.viewMyInfo();
            if (choice == 6) teacherController.viewGivenFeeds();
            if (choice == 7) teacherController.viewGivenTasks();
        }
    }
}

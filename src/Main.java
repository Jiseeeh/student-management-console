import Controller.TeacherController;
import Prompt.StudentSystem;

public class Main {
    public static void main(String[] args) {
        StudentSystem.loadAccounts();
        TeacherController.checkForTasksAndFeeds();
        StudentSystem.prompt();
    }
}
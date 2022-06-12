import Controller.TeacherController;
import Model.Teacher;
import Prompt.StudentSystem;

public class Main {
    public static void main(String[] args) {
        StudentSystem.loadAccounts();
        TeacherController.checkForTasksAndFeeds();
        StudentSystem.prompt();
    }
}
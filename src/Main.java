import Model.Teacher;
import Prompt.StudentSystem;

public class Main {
    public static void main(String[] args) {
        StudentSystem.loadAccounts();
//        Teacher.checkForTasksAndFeeds();
        StudentSystem.prompt();
    }
}
package Prompt;

import Controller.AdminController;
import Controller.StudentController;
import Controller.TeacherController;
import Database.AccountsDB;
import Helper.InputHelper;
import Model.Admin;
import Model.Student;
import Model.Teacher;
import Model.User;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class StudentSystem {
    private static final Scanner scan = new Scanner(System.in);
    private static final AccountsDB accounts = AccountsDB.INSTANCE;
    private static final List<User> users = accounts.getUsers();

    public static void prompt() {
        while (true) {
            System.out.println("""
                    \nWhat do you want to do?
                    1 -> login
                    2 -> exit
                    """);

            System.out.print(": ");
            String input = scan.nextLine().trim();

            if (InputHelper.hasLetterInput(input)) continue;

            int choice = Integer.parseInt(input);

            if (choice == 1) login();
            else if (choice == 2) return;
            else {
                System.out.println("""
                                                
                        |-------------------|
                        |* Invalid input!  *|
                        |-------------------|
                        """);
            }
        }
    }

    public static void login() {
        System.out.print("\nEnter your username: ");
        String username = scan.nextLine();
        System.out.print("Enter your password: ");
        String password = scan.nextLine();

        User account = null;
        String accountType = "";

        for (var user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                account = user;
                accountType = account.getType();
                break;
            }
        }
        switch (accountType) {
            case "admin" -> {
                var adminController = new AdminController((Admin) account, scan);
                adminController.start();
            }
            case "student" -> {
                var studentController = new StudentController((Student) account);
                studentController.start();
            }
            case "teacher" -> {
                var teacherController = new TeacherController((Teacher) account, scan);
                teacherController.start();
            }
            default -> {
                System.out.println("""
                                                
                        |-------------------|
                        |*Account not Found*|
                        |-------------------|
                        """);
                StudentSystem.prompt();
            }
        }
    }

    public static void loadAccounts() {
        try {
            accounts.hasAccounts();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

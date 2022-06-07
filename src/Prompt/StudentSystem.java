package Prompt;

import Abstract.ImplementingClasses.Student;
import Abstract.ImplementingClasses.Teacher;
import Abstract.User;
import Database.Access.AccountsOBJ;
import Database.AccountsDB;
import Views.AdminView;
import Views.StudentView;
import Views.TeacherView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentSystem {
    private static final Scanner scan = new Scanner(System.in);
    private static final AccountsDB accounts = AccountsOBJ.INSTANCE;
    private static final List<User> users = accounts.getUsers();
    private static final List<Student> studentsLogged = new ArrayList<>();
    private static final List<Teacher> teachersLogged = new ArrayList<>();

    //TODO:
    // - Store references who logged
    public static void prompt() {
        int choice = 0;
        while (choice != 2) {
            System.out.println("""
                    \nWhat do you want to do?
                    1 -> login
                    2 -> exit
                    """);

            System.out.print(": ");
            choice = Integer.parseInt(scan.nextLine());
            if (choice == 1) login();
        }
    }

    public static void login() {
        System.out.print("Enter your username: ");
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
            case "admin" -> AdminView.show();
            case "student" -> {
                studentsLogged.add((Student) account);
                StudentView.show((Student) account);
            }
            case "teacher" -> {
                teachersLogged.add((Teacher) account);
                TeacherView.show((Teacher) account);
            }
            default -> {
                System.out.println("Not found!");
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

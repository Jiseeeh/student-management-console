package Prompt;

import Database.Access.AccountsOBJ;
import Database.AccountsDB;
import Views.AdminView;
import Views.StudentView;
import Views.TeacherView;

import java.io.IOException;
import java.util.Scanner;

public class StudentSystem {
    private static final Scanner scan = new Scanner(System.in);
    private static final AccountsDB accounts = AccountsOBJ.INSTANCE;

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

        String accountType = accounts.checkAccount(username, password);

        switch (accountType) {
            case "admin" -> AdminView.show();
            case "student" -> StudentView.show();
            case "teacher" -> TeacherView.show();
            default -> {
                System.out.println(accountType);
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

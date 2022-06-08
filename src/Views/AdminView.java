package Views;

import Database.AccountsDB;
import Prompt.StudentSystem;

import java.util.Scanner;

public class AdminView {
    private static final AccountsDB accounts = AccountsDB.INSTANCE;
    private static final Scanner scan = new Scanner(System.in);

    public static void show() {
        int choice = 0;

        while (choice != 4) {
            System.out.println("""
                    \nWhat do you want to do?
                    1 -> Create account
                    2 -> Delete account
                    3 -> List accounts
                    4 -> Logout
                    """);
            System.out.print(": ");
            choice = Integer.parseInt(scan.nextLine());

            if (choice == 4) StudentSystem.prompt();
            if (choice == 1) createAccount();
            if (choice == 2) deleteAccount();
            if (choice == 3) listAccounts();
        }
        scan.close();
    }

    private static void createAccount() {
        accounts.createAccount();
    }

    private static void deleteAccount() {
        accounts.deleteAccount();
    }

    private static void listAccounts() {
        accounts.listAccounts();
    }
}

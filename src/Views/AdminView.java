package Views;

import Database.AccountsDB;
import Helper.InputHelper;

import java.util.Scanner;

public class AdminView {
    private static final AccountsDB accounts = AccountsDB.INSTANCE;
    private static final Scanner scan = new Scanner(System.in);

    public static void show() {
        while (true) {
            System.out.println("""
                    \nWhat do you want to do?
                    1 -> Create account
                    2 -> Delete account
                    3 -> List accounts
                    4 -> Logout
                    """);
            System.out.print(": ");
            String input = scan.nextLine().trim();

            if (InputHelper.hasLetterInput(input)) continue;

            int choice = Integer.parseInt(input);

            if (choice == 4) return;
            if (choice == 1) createAccount();
            if (choice == 2) deleteAccount();
            if (choice == 3) listAccounts();
        }
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

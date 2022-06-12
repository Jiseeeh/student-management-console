package Views;

import Controller.AdminController;
import Database.AccountsDB;
import Helper.InputHelper;

import java.util.Scanner;

public class AdminView {
    public void show(AdminController adminController , Scanner scan) {
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
            if (choice == 1) adminController.createAccount();
            if (choice == 2) adminController.deleteAccount();
            if (choice == 3) adminController.listAccounts();
        }
    }
}

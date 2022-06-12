package Controller;

import Database.AccountsDB;
import Views.AdminView;

import java.util.Scanner;

public class AdminController {
    private final AdminView adminView = new AdminView();
    private final AccountsDB accountsDB = AccountsDB.INSTANCE;
    private final Scanner scan;

    public AdminController(Scanner scan) {
        this.scan = scan;
    }

    public void start() {
        adminView.show(this, scan);
    }

    public void createAccount() {
        accountsDB.createAccount();
    }

    public void deleteAccount() {
        accountsDB.deleteAccount();
    }

    public void listAccounts() {
        accountsDB.listAccounts();
    }
}

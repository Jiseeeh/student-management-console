package Model;

import Database.AccountsDB;

public class Admin extends User {
    private final AccountsDB accountsDB = AccountsDB.INSTANCE;

    public Admin() {
        this.setUsername("admin");
        this.setPassword("admin");
        this.setType("admin");
    }

    public AccountsDB getAccountsDB() {
        return accountsDB;
    }
}

package Database.Access;

import Database.AccountsDB;

public class AccountsOBJ {

    public static final AccountsDB INSTANCE = new AccountsDB();

    // prevent instantiation
    private AccountsOBJ() {
    }

}

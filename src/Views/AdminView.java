package Views;

import Model.User;

import java.util.List;

public class AdminView {
    public void showDashboard() {
        System.out.println("""
                \nWhat do you want to do?
                1 -> Create account
                2 -> Delete account
                3 -> List accounts
                4 -> Logout
                """);
        System.out.print(": ");
    }

    public void listAccounts(List<User> accounts) {
        for (var user : accounts) {
            // skips the admin
            if (user.getUsername().equals("admin")) continue;

            System.out.printf("""
                    \n================
                    Username: %s
                    Password: %s
                    ================
                    """, user.getUsername(), user.getPassword());
        }
    }
}

package Database;

import Abstract.ImplementingClasses.Admin;
import Abstract.ImplementingClasses.Student;
import Abstract.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountsDB {
    private final static File accountsCSV = new File("src/Database/CSV/accounts.csv");
    private static FileWriter accountsCSVWriter;
    private final List<User> users = new ArrayList<>();
    private final List<Student> studentList = new ArrayList<>();
    private final Scanner scan = new Scanner(System.in);

    public AccountsDB() {
        users.add(new Admin());
    }

    public void createAccount() {
        System.out.println("Please answer the following.");
        System.out.println("""
                What type of account?
                0 -> Student
                1 -> Teacher
                """);

        int choice = Integer.parseInt(scan.nextLine());
        String type = (choice == 0) ? "student" : "teacher";

        System.out.print("What is your first name? ");
        String firstName = scan.nextLine();

        System.out.print("What is your last name? ");
        String lastName = scan.nextLine();

        System.out.print("What is your desired username: ");
        String username = scan.nextLine();

        System.out.print("What is your desired password? ");
        String password = scan.nextLine();

        System.out.print("What is your age? ");
        Integer age = Integer.parseInt(scan.nextLine());

        var newUser = new User.UserBuilder(firstName, lastName)
                .username(username)
                .password(password)
                .age(age)
                .build(type);

        if (type.equals("student")) studentList.add((Student) newUser);
        users.add(newUser);

        String info = "%s,%s,%s,%s,%d,%s\n";
        try {
            accountsCSVWriter.append(String.format(info, newUser.getFirstName(), newUser.getLastName(),
                    newUser.getUsername(), newUser.getPassword(), newUser.getAge(), newUser.getType()));
            accountsCSVWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteAccount() {
        File tempFile = new File("src/Database/CSV/accountsTEMP.csv");

        System.out.print("Enter the username of the account: ");
        String username = scan.nextLine();

        if (username.equals("admin")) {
            System.out.println("\n=======================");
            System.out.println("Cannot remove admin!");
            System.out.println("=======================");
            return;
        }

        boolean result = false;
        for (int i = 1; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                result = true;
                break;
            }
        }

        if (result) {
            System.out.println("\n=======================");
            System.out.println("Success!");
            System.out.println("=======================");
        } else {
            System.out.println("\n=======================");
            System.out.println("Not found!");
            System.out.println("=======================");
        }

        updateAccountsCSV(username, tempFile);
    }

    private void updateAccountsCSV(String username, File tempFile) {
        /// copies the content of the accounts CSV and skipping the deleted line
        try (Scanner scanAccountsCSV = new Scanner(accountsCSV)) {
            if (tempFile.createNewFile()) {
                FileWriter writer = new FileWriter(tempFile, true);
                while (scanAccountsCSV.hasNextLine()) {
                    String line = scanAccountsCSV.nextLine();
                    String[] lines = line.split(",");
                    if (lines.length == 0) return;

                    /// skips the account that needs to be deleted
                    if (lines[2].equals(username)) continue;

                    // write to temp file
                    writer.write(line + "\n");
                }
                writer.close();

                // rename temp file to the old file to update it.
                tempFile.renameTo(accountsCSV);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listAccounts() {
        System.out.println(users.size());
        users.forEach(user -> System.out.printf("""
                \n================
                Username: %s
                Password: %s
                ================
                """, user.getUsername(), user.getPassword()));
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public List<User> getUsers() {
        return users;
    }

    // for loading of accounts from CSV
    public void hasAccounts() throws IOException {
        // check if the DIR is created, then check if the file can also be created
        if (new File("src/Database/CSV").mkdir()) {
            if (canCreateFile()) writeHeader();
        }
        // else means the DIR is already existing
        // checks if the file can be created or already existing
        else {
            if (canCreateFile()) writeHeader();
            else {
                readAccounts();
                accountsCSVWriter = new FileWriter(accountsCSV, true);
            }
        }
    }

    private boolean canCreateFile() throws IOException {
        return accountsCSV.createNewFile();
    }

    private void writeHeader() throws IOException {
        accountsCSVWriter = new FileWriter(accountsCSV, true);
        // appends the header when the conditions are met
        accountsCSVWriter.append("FirstName,LastName,Username,Password,Age,Type\n");
        accountsCSVWriter.flush();
    }

    private void readAccounts() {
        try (Scanner scanAccount = new Scanner(accountsCSV)) {
            String header = scanAccount.nextLine();

            while (scanAccount.hasNextLine()) {
                String line = scanAccount.nextLine();

                if (line == null) return;
                String[] data = line.split(",");

                users.add(new User.UserBuilder(data[0], data[1])
                        .username(data[2])
                        .password(data[3])
                        .age(Integer.valueOf(data[4]))
                        .build(data[5]));

                users.forEach(user -> {
                    if (user.getType().equals("student")) studentList.add((Student) user);
                });
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}


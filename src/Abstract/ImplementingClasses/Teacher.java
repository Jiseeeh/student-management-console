package Abstract.ImplementingClasses;

import Abstract.User;
import Database.Access.AccountsOBJ;
import Database.AccountsDB;
import Database.Feedback;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Teacher extends User {
    //TODO add these:
    // -Teacher's own feedbackList/TaskList
    // -Methods to add/view/remove it
    private final List<Feedback> givenFeeds = new ArrayList<>();
    private final AccountsDB accountsDB = AccountsOBJ.INSTANCE;
    private final List<User> users = accountsDB.getUsers();
    private final Scanner scan = new Scanner(System.in);
    private final File feedsCSV = new File("src/Database/CSV/feeds.csv");
    private FileWriter writer;

    public Teacher(UserBuilder builder) {
        this.setFirstName(builder.getFirstName());
        this.setLastName(builder.getLastName());
        this.setUsername(builder.getUsername());
        this.setPassword(builder.getPassword());
        this.setAge(builder.getAge());
        this.setType(builder.getType());
        hasFeeds();
    }

    public void giveFeed() {
        Student student = null;
        System.out.println("Enter his/her name");
        for (var user : users) System.out.println(user.getFirstName());

        System.out.print(": ");
        String studentName = scan.nextLine();
        for (var user : users) {
            if (user.getFirstName() == null) continue;
            if (user.getFirstName().equals(studentName)) {
                student = (Student) user;
                break;
            }
        }

        System.out.print("Enter your feedback: ");
        String feed = scan.nextLine();
        Feedback feedback = new Feedback(student.getFirstName(), this.getFirstName(), feed);

        this.givenFeeds.add(feedback);
        student.acceptFeed(feedback);

        try {
            writer.append(feedback.toString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void hasFeeds() {
        try {
            if (feedsCSV.createNewFile()) {
                writer = new FileWriter(feedsCSV, true);
                writer.append("StudentName,TeacherName,Feedback\n");
                writer.flush();
            } else {
                readFeeds();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFeeds() {
        try (Scanner scanFeeds = new Scanner(feedsCSV)) {
            writer = new FileWriter(feedsCSV, true);

            String header = scanFeeds.nextLine();
            Student student = null;
            while (scanFeeds.hasNextLine()) {
                String line = scanFeeds.nextLine();
                if (line == null) return;

                String[] data = line.split(",");

                for (var user : users) {
                    if (user.getFirstName() == null) continue;
                    if (user.getFirstName().equals(data[0])) {
                        student = (Student) user;
                        break;
                    }
                }

                Feedback feedback = new Feedback(student.getFirstName(), data[1], data[2]);
                this.givenFeeds.add(feedback);
                student.acceptFeed(feedback);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewGivenFeeds() {
        givenFeeds.forEach(feedback -> {
            System.out.println(feedback.getFeedback(false));
        });
    }

    public void clearFeeds() {
        givenFeeds.clear();
        updateFeedsCSV();
    }

    private void updateFeedsCSV() {
        File tempFile = new File("src/Database/CSV/feedsTEMP.csv");
        String header = "StudentName,TeacherName,Feedback";
        /// copies the content of the accounts CSV and skipping the deleted line
        try (Scanner scanAccountsCSV = new Scanner(feedsCSV)) {
            if (tempFile.createNewFile()) {
                FileWriter tempWriter = new FileWriter(tempFile, true);
                while (scanAccountsCSV.hasNextLine()) {
                    String line = scanAccountsCSV.nextLine();
                    if (line == null) return;

                    /// skips the account that needs to be deleted
                    if (!line.equals(header)) continue;

                    // write to temp file
                    tempWriter.write(line + "\n");
                }
                tempWriter.flush();

                // rename temp file to the old file to update it.
                tempFile.renameTo(feedsCSV);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

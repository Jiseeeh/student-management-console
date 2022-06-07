package Abstract.ImplementingClasses;

import Abstract.User;
import Database.Access.AccountsOBJ;
import Database.AccountsDB;
import Database.Feedback;
import Database.Task;

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
    private final List<Task> givenTasks = new ArrayList<>();
    private final AccountsDB accountsDB = AccountsOBJ.INSTANCE;
    private final List<Student> studentList = accountsDB.getStudentList();
    private final Scanner scan = new Scanner(System.in);
    private final File feedsCSV = new File("src/Database/CSV/feeds.csv");
    private final File tasksCSV = new File("src/Database/CSV/tasks.csv");
    private FileWriter feedsCSVWriter;
    private FileWriter tasksCSVWriter;

    public Teacher(UserBuilder builder) {
        this.setFirstName(builder.getFirstName());
        this.setLastName(builder.getLastName());
        this.setUsername(builder.getUsername());
        this.setPassword(builder.getPassword());
        this.setAge(builder.getAge());
        this.setType(builder.getType());
        hasFeeds();
        hasTasks();
    }

    public void giveFeed() {
        Student student = null;
        System.out.println("Enter his/her name");
        for (var user : studentList) System.out.println(user.getFirstName());

        System.out.print(": ");
        String studentName = scan.nextLine();
        for (var user : studentList) {
            if (user.getFirstName().equals(studentName)) {
                student = user;
                break;
            }
        }

        System.out.print("Enter your feedback: ");
        String feed = scan.nextLine();
        Feedback feedback = new Feedback(student.getFirstName(), this.getFirstName(), feed);

        this.givenFeeds.add(feedback);
        student.acceptFeed(feedback);

        try {
            feedsCSVWriter.append(feedback.toString() + "\n");
            feedsCSVWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void giveTask() {
        Student student = null;
        System.out.println("Enter his/her name");
        for (var user : studentList) System.out.println(user.getFirstName());

        System.out.print(": ");
        String studentName = scan.nextLine();

        for (var user : studentList) {
            if (user.getFirstName().equals(studentName)) {
                student = user;
                break;
            }
        }

        System.out.print("Enter the task: ");
        String givenTask = scan.nextLine();
        Task task = new Task(student.getFirstName(), this.getFirstName(), givenTask);

        this.givenTasks.add(task);
        student.acceptTask(task);

        try {
            tasksCSVWriter.append(task.toString());
            tasksCSVWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void hasTasks() {
        try {
            if (tasksCSV.createNewFile()) {
                tasksCSVWriter = new FileWriter(tasksCSV, true);
                tasksCSVWriter.append("StudentName,TeacherName,Task\n");
                tasksCSVWriter.flush();
            } else {
                readTasks();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readTasks() {
        try (Scanner scanFeeds = new Scanner(tasksCSV)) {
            tasksCSVWriter = new FileWriter(tasksCSV, true);

            String header = scanFeeds.nextLine();
            Student student = null;

            while (scanFeeds.hasNextLine()) {
                String line = scanFeeds.nextLine();
                if (line == null) return;

                String[] data = line.split(",");

                for (var user : studentList) {
                    if (user.getFirstName().equals(data[0])) {
                        student = user;
                        break;
                    }
                }

                Task task = new Task(student.getFirstName(), data[1], data[2]);

                this.givenTasks.add(task);
                student.acceptTask(task);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateTasksCSV() {
        File tempFile = new File("src/Database/CSV/TasksTEMP.csv");
        String header = "StudentName,TeacherName,Task";
        /// copies the content of the accounts CSV and skipping the deleted line
        try (Scanner scanTasksCSV = new Scanner(tasksCSV)) {
            if (tempFile.createNewFile()) {
                FileWriter tempWriter = new FileWriter(tempFile, true);
                while (scanTasksCSV.hasNextLine()) {
                    String line = scanTasksCSV.nextLine();
                    if (line == null) return;

                    /// skips the account that needs to be deleted
                    if (!line.equals(header)) continue;

                    // write to temp file
                    tempWriter.write(line + "\n");
                }
                tempWriter.flush();

                // rename temp file to the old file to update it.
                tempFile.renameTo(tasksCSV);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void hasFeeds() {
        try {
            if (feedsCSV.createNewFile()) {
                feedsCSVWriter = new FileWriter(feedsCSV, true);
                feedsCSVWriter.append("StudentName,TeacherName,Feedback\n");
                feedsCSVWriter.flush();
            } else {
                readFeeds();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFeeds() {
        try (Scanner scanFeeds = new Scanner(feedsCSV)) {
            feedsCSVWriter = new FileWriter(feedsCSV, true);

            String header = scanFeeds.nextLine();
            Student student = null;
            while (scanFeeds.hasNextLine()) {
                String line = scanFeeds.nextLine();
                if (line == null) return;

                String[] data = line.split(",");

                for (var user : studentList) {
                    if (user.getFirstName().equals(data[0])) {
                        student = user;
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
        givenFeeds.forEach(feedback -> System.out.println(feedback.getFeedback(false)));
    }

    public void viewGivenTasks() {
        givenTasks.forEach(task -> System.out.println(task.getTask(false)));
    }

    public void clearFeeds() {
        givenFeeds.clear();
        updateFeedsCSV();
    }

    public void clearTasks() {
        givenTasks.clear();
        updateTasksCSV();
    }

    private void updateFeedsCSV() {
        File tempFile = new File("src/Database/CSV/feedsTEMP.csv");
        String header = "StudentName,TeacherName,Feedback";
        /// copies the content of the accounts CSV and skipping the deleted line
        try (Scanner scanFeedsCSV = new Scanner(feedsCSV)) {
            if (tempFile.createNewFile()) {
                FileWriter tempWriter = new FileWriter(tempFile, true);
                while (scanFeedsCSV.hasNextLine()) {
                    String line = scanFeedsCSV.nextLine();
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

package Helper;

import Abstract.ImplementingClasses.Student;
import Database.Access.AccountsOBJ;
import Database.Feedback;
import Database.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FileHelper {
    private static final List<Student> studentList = AccountsOBJ.INSTANCE.getStudentList();

    public static void clearCSVFile(File csvFile, String header) {
        FileWriter writer;
        try {
            if (csvFile.delete()) {
                csvFile.createNewFile();
                writer = new FileWriter(csvFile, true);
                writer.append(header);
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void checkForFeeds(File feedsCSV, List<Feedback> givenFeeds) {
        try {
            FileWriter feedsCSVWriter;
            if (feedsCSV.createNewFile()) {
                feedsCSVWriter = new FileWriter(feedsCSV, true);
                feedsCSVWriter.append("StudentName,TeacherName,Feedback\n");
                feedsCSVWriter.flush();
                feedsCSVWriter.close();
            } else {
                try (Scanner scanFeeds = new Scanner(feedsCSV)) {
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
                        givenFeeds.add(feedback);
                        student.acceptFeed(feedback);

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkForTasks(File tasksCSV, List<Task> givenTasks) {
        try {
            if (tasksCSV.createNewFile()) {
                FileWriter tasksCSVWriter = new FileWriter(tasksCSV, true);
                tasksCSVWriter.append("StudentName,TeacherName,Task\n");
                tasksCSVWriter.flush();
                tasksCSVWriter.close();
            } else {
                try (Scanner scanTasks = new Scanner(tasksCSV)) {
                    String header = scanTasks.nextLine();
                    Student student = null;

                    while (scanTasks.hasNextLine()) {
                        String line = scanTasks.nextLine();
                        if (line == null) return;

                        String[] data = line.split(",");

                        for (var user : studentList) {
                            if (user.getFirstName().equals(data[0])) {
                                student = user;
                                break;
                            }
                        }

                        Task task = new Task(student.getFirstName(), data[1], data[2]);

                        givenTasks.add(task);
                        student.acceptTask(task);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package Helper;

import Database.AccountsDB;
import Model.Feedback;
import Model.Student;
import Model.Task;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class FileHelper {
    private static final List<Student> studentList = AccountsDB.INSTANCE.getStudentList();

    public static void clearFile(File target, String header) {
        FileWriter writer;
        try {
            // deletes the file
            if (target.delete()) {
                // then create it again and append the header to simulate updating of file
                target.createNewFile();
                writer = new FileWriter(target, true);
                writer.append(header);
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void replaceFile(String pathOfOldFile, String pathOfNewFile) {
        String sCurrentLine = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(pathOfOldFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(pathOfNewFile));

            while ((sCurrentLine = br.readLine()) != null) {
                bw.write(sCurrentLine);
                bw.newLine();
            }

            br.close();
            bw.close();

            // delete the old file
            File org = new File(pathOfOldFile);
            org.delete();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeToFile(File target, String... contents) {
        try (FileWriter writer = new FileWriter(target, true)) {
            for (String content : contents) {
                writer.append(content);
            }
            writer.flush();
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
                    String line;

                    while (scanFeeds.hasNextLine()) {
                        line = scanFeeds.nextLine();

                        if (line == null) return;
                        if (studentList.size() == 0) return;

                        String[] data = line.split(",");

                        for (var user : studentList) {
                            if (user.getFirstName().equals(data[0])) {
                                student = user;
                                break;
                            }
                        }

                        // if no student matched from the line, we'll check next line
                        if (student == null) break;

                        Feedback feedback = new Feedback(student.getFirstName(), data[1], data[2]);
                        givenFeeds.add(feedback);
                        student.getMyController().acceptFeed(feedback);

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
                    String line;

                    while (scanTasks.hasNextLine()) {
                        line = scanTasks.nextLine();

                        if (line == null) return;
                        if (studentList.size() == 0) return;

                        String[] data = line.split(",");

                        for (var user : studentList) {
                            if (user.getFirstName().equals(data[0])) {
                                student = user;
                                break;
                            }
                        }

                        // if no student matched from the line, we'll check next line
                        if (student == null) break;

                        Task task = new Task(student.getFirstName(), data[1], data[2]);
                        givenTasks.add(task);
                        student.getMyController().acceptTask(task);
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

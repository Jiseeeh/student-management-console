package Database;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TasksDB {
    private final List<Task> tasks = new ArrayList<>();
    private final Scanner scan = new Scanner(System.in);

    public void addTask() {
        System.out.println("** Give Task to your students **");
        System.out.print(": ");
        String task = scan.nextLine();

        tasks.add(new Task(task));
    }

    public void removeTask() {
        int index;
        System.out.println("Enter the index you want to remove");
        this.listTasks();
        System.out.print(": ");
        index = scan.nextInt();

        tasks.remove(index);
    }

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("""
                    \n=======================
                    %d -> {
                    %s
                    }
                    =======================
                    """, i, tasks.get(i));
        }
    }

    public void markAsDone() {
        System.out.println("Enter the index of the task");
        this.listTasks();

        System.out.print(": ");
        int index = scan.nextInt();

        if (tasks.size() <= 0) {
            System.out.println("\n=======================");
            System.out.println("No tasks available.");
            System.out.println("=======================");
            return;
        }
        tasks.remove(index);
    }
}

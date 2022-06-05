package Database.Access;

import Database.TasksDB;

public class TasksOBJ {
    public static TasksDB INSTANCE = new TasksDB();

    // prevent instantiation
    private TasksOBJ() {
    }
}

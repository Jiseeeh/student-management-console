package Database;

public class Task {
    //TODO:
    // - Add properties
    //      - studentName or studentID
    //      - teacherName or teacherID
    // - Fix toString or add method for easy adding/removing in csv
    private final String task;

    public Task(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return task;
    }
}

package Database;

public class Task {
    //TODO:
    // - Add properties
    //      - studentName or studentID
    //      - teacherName or teacherID
    // - Fix toString or add method for easy adding/removing in csv
    private String task;
    private String studentName;
    private String teacherName;

    public Task(String studentName, String teacherName, String task) {
        this.studentName = studentName;
        this.teacherName = teacherName;
        this.task = task;
    }

    @Override
    public String toString() {
        return studentName + "," + teacherName + "," + task;
    }

    public String getTask(boolean isStudent) {
        return (isStudent) ? String.format("""
                From Mr/Ms %s: {
                %s
                }
                """, teacherName, task) : String.format("""
                Given to Mr/Ms %s: {
                %s
                }
                """, studentName, task);

    }

    public void markAsDone() {
        String done = " (Done)";
        if (!task.contains(done)) task += done;
        else System.out.println("Already DONE!");
    }
}

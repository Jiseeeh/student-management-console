package Database;

public class Task {
    private final String studentName;
    private final String teacherName;
    private String task;

    public Task(String studentName, String teacherName, String task) {
        this.studentName = studentName;
        this.teacherName = teacherName;
        this.task = task;
    }

    public String getTask(boolean isStudent) {
        return (isStudent) ? String.format("""
                \nFrom Mr/Ms %s: {
                %s
                }
                """, teacherName, task) : String.format("""
                \nGiven to Mr/Ms %s: {
                %s
                }
                """, studentName, task);

    }

    public void markAsDone() {
        String done = " (Done)";
        if (!task.contains(done)) task += done;
        else
            System.out.println("""
                                            
                    |-------------------------|
                    |* Task is already DONE! *|
                    |-------------------------|
                    """);

    }

    @Override
    public String toString() {
        return studentName + "," + teacherName + "," + task;
    }

}

package Database;

public class Feedback {
    private final String feedback;
    private final String studentName;
    private final String teacherName;

    public Feedback(String studentName, String teacherName, String feedback) {
        this.studentName = studentName;
        this.teacherName = teacherName;
        this.feedback = feedback;
    }

    public String getFeedback(boolean isStudent) {
        return (isStudent) ? String.format("""
                From Mr/Ms %s: {
                %s
                }
                """, teacherName, feedback) : String.format("""
                To Mr/Ms %s: {
                %s
                }
                """, studentName, feedback);

    }

    @Override
    public String toString() {
        return studentName + "," + teacherName + "," + feedback;
    }

}

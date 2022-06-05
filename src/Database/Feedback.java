package Database;

public class Feedback {
    private final String feedback;

    public Feedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return feedback;
    }

}

package Database.Access;

import Database.FeedbacksDB;

public class FeedbacksOBJ {
    public static final FeedbacksDB INSTANCE = new FeedbacksDB();

    // prevent instantiation
    private FeedbacksOBJ() {
    }

}

package Database;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FeedbacksDB {
    private final List<Feedback> feedBacks = new ArrayList<>();
    private final Scanner scan = new Scanner(System.in);

    public void giveFeedBack() {
        System.out.println("** Give Feedback to your students **");
        System.out.print(": ");
        String feedback = scan.nextLine();

        feedBacks.add(new Feedback(feedback));
    }

    public void removeFeedBack() {
        int index;
        System.out.println("Enter the index you want to remove");
        this.listFeedBacks();
        System.out.print(": ");
        index = scan.nextInt();

        feedBacks.remove(index);
    }

    public void listFeedBacks() {
        for (int i = 0; i < feedBacks.size(); i++) {
            System.out.printf("""
                    \n=======================
                    %d -> {
                    %s
                    }
                    =======================
                    """, i, feedBacks.get(i));
        }
    }
}

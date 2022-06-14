package Views;

import Helper.ListHelper;
import Model.Feedback;
import Model.Student;
import Model.Task;

import java.util.List;

public class StudentView {
    public void showMyDashboard() {
        System.out.println("""
                \nWhat do you want to do?
                1 -> See tasks
                2 -> See Feedbacks
                3 -> Mark task as done
                4 -> See my info
                5 -> Logout
                """);

        System.out.print(": ");
    }

    public void viewMyTasks(List<Task> studentTasks) {
        if (!ListHelper.hasTasks(studentTasks)) return;

        studentTasks.forEach(task -> System.out.println(task.getTask(true)));
    }

    public void viewMyFeeds(List<Feedback> studentFeedbacks) {
        if (!ListHelper.hasFeeds(studentFeedbacks)) return;

        studentFeedbacks.forEach(feedback -> System.out.println(feedback.getFeedback(true)));
    }

    public void viewMyInfo(Student student) {
        student.viewMyInfo();
    }
}

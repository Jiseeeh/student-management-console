package Views;

import Helper.ListHelper;
import Model.Feedback;
import Model.Task;
import Model.Teacher;

import java.util.List;

public class TeacherView {
    public void showMyDashboard() {
        System.out.println("""
                \nWhat do you want to do?
                1 -> Give Feedbacks to students
                2 -> Assign a Task
                3 -> Clear Tasks
                4 -> Clear Feedbacks
                5 -> See my info
                6 -> See given feedbacks
                7 -> See given Tasks
                8 -> Logout
                """);
        System.out.print(": ");
    }

    public void viewGivenFeeds(List<Feedback> teacherGivenFeedbacks) {
        if (!ListHelper.hasFeeds(teacherGivenFeedbacks)) return;
        teacherGivenFeedbacks.forEach(feedback -> System.out.println(feedback.getFeedback(false)));
    }

    public void viewGivenTasks(List<Task> teacherGivenTasks) {
        if (!ListHelper.hasTasks(teacherGivenTasks)) return;
        teacherGivenTasks.forEach(task -> System.out.println(task.getTask(false)));
    }

    public void viewMyInfo(Teacher teacher) {
        teacher.viewMyInfo();
    }
}

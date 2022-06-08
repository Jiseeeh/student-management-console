package Helper;

import Abstract.ImplementingClasses.Student;
import Database.Feedback;
import Database.Task;

import java.util.List;

public class ListHelper {

    public static boolean hasFeeds(List<Feedback> list) {
        if (list.size() == 0) {
            System.out.println("""
                                            
                    |---------------------------|
                    |* No Feedbacks for now!!  *|
                    |---------------------------|
                    """);
            return false;
        }
        return true;
    }

    public static boolean hasTasks(List<Task> list) {
        if (list.size() == 0) {
            System.out.println("""
                                            
                    |-----------------------|
                    |* No Tasks for now!!  *|
                    |-----------------------|
                    """);
            return false;
        }
        return true;
    }

    public static boolean hasStudents(List<Student> list, String type) {
        if (list.size() == 0) {
            System.out.printf("""
                                            
                    |------------------------------------------------------|
                    |* You can't give %s if you don't have students!!  *|
                    |------------------------------------------------------|
                    \n""", type);
            return false;
        }
        return true;
    }
}

package ProgressTracker;

import java.util.HashMap;
import java.util.Map;
import com.github.tahamostafa06.backend.api.Student;
import com.github.tahamostafa06.backend.courseservice.CourseItem;

public class ProgressTracker {
    private Student student ;

    public double getCourseCompletionPercentage(Student studentID, CourseItem course)  {
        int completed = student.getFinishedLessonsCount(course);
        int total = student.getLessonCount(course);
        return completed == 0 ? 0 : 100 * completed / total;
    }

}

package CompletionTracking;

import com.github.tahamostafa06.backend.auth.LoginToken;
import com.github.tahamostafa06.backend.courseservice.CourseItem;

public class CompletionTracker {

    public boolean isCourseCompleted(LoginToken token, CourseItem courseItem) {
        var course = courseItem.getCourse();
        // change type of getCourse to public
        var doneLessons = course.getStudentsAndLessonsDone().get(token.getUserId());

        if (doneLessons == null) return false;

        int totalLessons = course.getLessons().size();
        return doneLessons.size() == totalLessons;
    }
}



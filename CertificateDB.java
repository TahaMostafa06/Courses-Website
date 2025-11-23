package CompletionTracking;

import com.github.tahamostafa06.backend.auth.LoginToken;
import com.github.tahamostafa06.backend.courseservice.CourseItem;
import com.github.tahamostafa06.backend.database.coursedatabase.CourseDatabase;
import java.util.ArrayList;

public class CertificateDB {

    private CourseDatabase courseDb;

    public CertificateDB(CourseDatabase courseDb) {
        this.courseDb = courseDb;
    }

    public Certificate generateCertificate(LoginToken token, CourseItem courseItem) {
        var course = courseItem.getCourse();
        var studentId = token.getUserId();
        Certificate certificate = new Certificate(studentId, courseItem.getCourse().getTitle());
        
        course.addCertificate(certificate);
        try {
            courseDb.saveToFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return certificate;
    }
}

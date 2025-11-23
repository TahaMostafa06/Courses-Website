package CompletionTracking;

import com.github.tahamostafa06.backend.auth.LoginToken;
import com.github.tahamostafa06.backend.courseservice.CourseItem;

public class CompletionService {

    private CompletionTracker completionTracker;
    private CertificateDB certificateDB;

    public CompletionService(CertificateDB certificateDB) {
        this.completionTracker = new CompletionTracker();
        this.certificateDB = certificateDB;
    }

    public Certificate issueCertificateIfCompleted(LoginToken token, CourseItem courseItem) {
        boolean completed = completionTracker.isCourseCompleted(token, courseItem);
        if (!completed) {
            return null;
        }
        Certificate certificate = certificateDB.generateCertificate(token, courseItem);
        return certificate;
    }
}

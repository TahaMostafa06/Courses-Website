package CompletionTracking;

import java.time.LocalDateTime;
import java.util.UUID;

public class Certificate {
    
    private String certificateId;
    private String studentId;
    private String courseId;
    private String issueDate;

    public Certificate(String studentId, String courseId) {
        this.certificateId = UUID.randomUUID().toString();
        //UUID : creating an unique
        this.studentId = studentId;
        this.courseId = courseId;
        this.issueDate = LocalDateTime.now().toString();
    }
}

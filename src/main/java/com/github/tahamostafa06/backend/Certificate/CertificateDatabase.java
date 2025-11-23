package com.github.tahamostafa06.backend.Certificate;
import java.time.LocalDateTime;
import java.util.UUID;

public class CertificateDatabase {

    private String certificateId;
    private String studentId;
    private String courseId;
    private String issueDate;

    CertificateDatabase(String certificateId, String studentId, String courseId, String issueDate) {
        this.certificateId = UUID.randomUUID().toString();
        //UUID : creating an unique
        this.studentId = studentId;
        this.courseId = courseId;
        this.issueDate = LocalDateTime.now().toString();
    }
}
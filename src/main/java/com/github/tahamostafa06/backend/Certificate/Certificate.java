package com.github.tahamostafa06.backend.Certificate;

import java.time.LocalDateTime;
import java.util.UUID;

public class Certificate {
    private String certificateId;
    private String studentId;
    private String courseId;
    private String issueDate;

    
    public Certificate(String studentId, String courseId) {
        this.certificateId = UUID.randomUUID().toString(); 
        this.studentId = studentId;
        this.courseId = courseId;
        this.issueDate = LocalDateTime.now().toString(); 
    }

    
    public String getCertificateId() { 
        return certificateId; 
    }
    
    public String getStudentId() { 
        return studentId; 
    }
    
    public String getCourseId() { 
        return courseId; 
    }
    
    public String getIssueDate() { 
        return issueDate; 
    }
}


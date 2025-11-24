package com.github.tahamostafa06.backend.database.coursedatabase;

import java.time.LocalDateTime;
import java.util.UUID;

public class Certificate {
    private final String certificateId;
    private final String studentId;
    private final String issueDate;

    
    public Certificate(String studentId) {
        this.certificateId = UUID.randomUUID().toString(); 
        this.studentId = studentId;
        this.issueDate = LocalDateTime.now().toString(); 
    }

    
    public String getCertificateId() { 
        return certificateId; 
    }
    
    public String getStudentId() { 
        return studentId; 
    }
    
    public String getIssueDate() { 
        return issueDate; 
    }
}


package com.github.tahamostafa06.backend.Certificate;
import java.util.ArrayList;
import java.util.List;

public class CertificateDatabase {
    private final List<Certificate> certificates = new ArrayList<>();
    
    public Certificate createCourseCertificate(String studentId, String courseId) {
        Certificate certificate = new Certificate(studentId, courseId);
        certificates.add(certificate);
        return certificate;
    }

    public boolean checkCertificateExistence(String studentID, String courseID) {
        for (Certificate cert : certificates) {
            if (cert.getStudentId().equals(studentID) && cert.getCourseId().equals(courseID)) {
                return true;
            }
        }
        return false;
    }
}
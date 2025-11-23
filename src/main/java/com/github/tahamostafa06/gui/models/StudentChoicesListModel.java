package com.github.tahamostafa06.gui.models;

import javax.swing.AbstractListModel;

import com.github.tahamostafa06.backend.api.Student;
import com.github.tahamostafa06.backend.database.coursedatabase.Question;

public class StudentChoicesListModel extends AbstractListModel<String> {
    private Student student;
    private Question question;

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public StudentChoicesListModel(Student student, Question question) {
        this.student = student;
        this.question = question;
    }

    @Override
    public String getElementAt(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getElementAt'");
    }

    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSize'");
    }

}

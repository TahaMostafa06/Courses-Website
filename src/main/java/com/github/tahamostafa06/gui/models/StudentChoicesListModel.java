package com.github.tahamostafa06.gui.models;

import javax.swing.AbstractListModel;

import com.github.tahamostafa06.backend.courseservice.QuestionItem;

public class StudentChoicesListModel extends AbstractListModel<String> {
    private QuestionItem questionItem;

    public void setQuestionItem(QuestionItem questionItem) {
        this.questionItem = questionItem;
        fireContentsChanged(this, 0, getSize() - 1);
    }

    public StudentChoicesListModel(QuestionItem questionItem) {
        this.questionItem = questionItem;
    }

    @Override
    public String getElementAt(int index) {
        return questionItem.getQuestion().getChoices()[index];
    }

    @Override
    public int getSize() {
        if (questionItem == null)
            return 0;
        return questionItem.getQuestion().getChoices().length;
    }

}

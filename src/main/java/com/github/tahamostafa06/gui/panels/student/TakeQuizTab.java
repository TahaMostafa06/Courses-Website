package com.github.tahamostafa06.gui.panels.student;

import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;

import com.github.tahamostafa06.backend.api.Student;
import com.github.tahamostafa06.backend.courseservice.CourseItem;
import com.github.tahamostafa06.backend.courseservice.LessonItem;
import com.github.tahamostafa06.backend.courseservice.QuestionItem;
import com.github.tahamostafa06.backend.database.coursedatabase.StudentLessonProgress;
import com.github.tahamostafa06.gui.models.StudentChoicesListModel;
import com.github.tahamostafa06.gui.models.StudentQuestionListModel;

public class TakeQuizTab extends javax.swing.JPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> choiceListComponent;
    private javax.swing.JScrollPane choiceListScrollPane;
    private javax.swing.JLabel choicesTitleLabel;
    private javax.swing.JList<QuestionItem> questionListComponent;
    private javax.swing.JScrollPane questionListScrollPane;
    private javax.swing.JLabel questionsTitleLabel;
    private javax.swing.JToggleButton submitButton;
    // End of variables declaration//GEN-END:variables
    Student student;
    StudentLessonProgress progress;
    StudentQuestionListModel questionListModel;
    StudentChoicesListModel choicesListModel;
    ArrayList<String> selectedChoices = new ArrayList<>();
    CourseItem courseItem;
    LessonItem lessonItem;

    public TakeQuizTab() {
        initComponents();
        questionListComponent.addListSelectionListener(this::onQuestionSelectionChange);
        choiceListComponent.addListSelectionListener(this::onChoiceSelectionChange);
    }

    public void updateQuizView(Student student, CourseItem courseItem, LessonItem lessonItem,
            StudentLessonProgress progress) {
        this.student = student;
        this.progress = progress;
        this.courseItem = courseItem;
        this.lessonItem = lessonItem;
        var quiz = lessonItem.getQuiz();
        if (questionListModel == null) {
            questionListModel = new StudentQuestionListModel(lessonItem);
            questionListComponent.setModel(questionListModel);
        } else {
            questionListModel.setLessonItem(lessonItem);
        }
        if (choicesListModel == null) {
            choicesListModel = new StudentChoicesListModel(null);
            choiceListComponent.setModel(choicesListModel);
        }
        selectedChoices.clear();
        for (var i = 0; i < quiz.size(); i++) {
            selectedChoices.add(null);
        }
        submitButton.setEnabled(false);
    }

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_submitButtonActionPerformed
        student.submitQuiz(courseItem, lessonItem, (ArrayList<String>) selectedChoices.clone());
    }// GEN-LAST:event_submitButtonActionPerformed

    private void onQuestionSelectionChange(ListSelectionEvent evt) {
        var questionItem = questionListComponent.getSelectedValue();
        choicesListModel.setQuestionItem(questionItem);
    }

    private void onChoiceSelectionChange(ListSelectionEvent evt) {
        if (progress.isPassed()) return;
        submitButton.setEnabled(selectedChoices.stream().filter(s -> s != null).count() == lessonItem.getQuiz().size());
        questionListModel.markAsAnswered(questionListComponent.getSelectedIndex(), !choiceListComponent.isSelectionEmpty());
        if (choiceListComponent.isSelectionEmpty()) 
            return;
        var choice = choiceListComponent.getSelectedValue();
        selectedChoices.set(questionListComponent.getSelectedIndex(), choice);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        questionListScrollPane = new javax.swing.JScrollPane();
        questionListComponent = new javax.swing.JList<>();
        questionsTitleLabel = new javax.swing.JLabel();
        choicesTitleLabel = new javax.swing.JLabel();
        choiceListScrollPane = new javax.swing.JScrollPane();
        choiceListComponent = new javax.swing.JList<>();
        submitButton = new javax.swing.JToggleButton();

        questionListComponent.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        questionListScrollPane.setViewportView(questionListComponent);

        questionsTitleLabel.setFont(
                questionsTitleLabel.getFont().deriveFont(questionsTitleLabel.getFont().getStyle() | java.awt.Font.BOLD,
                        questionsTitleLabel.getFont().getSize() + 6));
        questionsTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        questionsTitleLabel.setText("Questions");

        choicesTitleLabel.setFont(
                choicesTitleLabel.getFont().deriveFont(choicesTitleLabel.getFont().getStyle() | java.awt.Font.BOLD,
                        choicesTitleLabel.getFont().getSize() + 6));
        choicesTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        choicesTitleLabel.setText("Choices");

        choiceListComponent.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        choiceListScrollPane.setViewportView(choiceListComponent);

        submitButton.setText("Submit");
        submitButton.addActionListener(this::submitButtonActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(questionListScrollPane, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
                        .addComponent(questionsTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(choiceListScrollPane)
                        .addComponent(submitButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(choicesTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(questionsTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(questionListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 152,
                                        Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(choicesTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(choiceListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 189,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)));
    }// </editor-fold>//GEN-END:initComponents
}

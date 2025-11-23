package com.github.tahamostafa06.gui.panels.student;

import com.github.tahamostafa06.backend.api.Student;
import com.github.tahamostafa06.backend.courseservice.CourseItem;
import com.github.tahamostafa06.gui.models.StudentLessonListModel;
import javax.swing.event.ListSelectionEvent;

public class ViewLessonsTab extends javax.swing.JPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel additionalResourcesLabel;
    private javax.swing.JLabel additionalResourcesTextLabel;
    private javax.swing.JLabel completionTitleLabel;
    private javax.swing.JLabel contentLabel;
    private javax.swing.JLabel contentTextLabel;
    private javax.swing.JLabel courseTitle;
    private javax.swing.JPanel lessonDetailsPanel;
    private javax.swing.JList<com.github.tahamostafa06.backend.courseservice.LessonItem> lessonListComponent;
    private javax.swing.JScrollPane lessonListScrollPane;
    private javax.swing.JLabel quizCompletionStatusLabel;
    private javax.swing.JLabel selectionPromptLabel;
    private javax.swing.JButton startQuizButton;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel titleTextLabel;
    // End of variables declaration//GEN-END:variables
    private Student student;
    private CourseItem courseItem;
    private StudentLessonListModel studentLessonListModel;

    public ViewLessonsTab() {
        initComponents();
        lessonListComponent.addListSelectionListener(this::onSelectionChange);
        setInfoPaneVisibility(false);
    }

    private void setInfoPaneVisibility(boolean visible) {
        contentTextLabel.setVisible(visible);
        contentLabel.setVisible(visible);
        titleLabel.setVisible(visible);
        titleTextLabel.setVisible(visible);
        additionalResourcesTextLabel.setVisible(visible);
        additionalResourcesLabel.setVisible(visible);
        startQuizButton.setVisible(visible);
        selectionPromptLabel.setVisible(!visible);
        quizCompletionStatusLabel.setVisible(visible);
        completionTitleLabel.setVisible(visible);
    }

    public void updateLessonView(Student student, CourseItem courseItem) {
        this.student = student;
        this.courseItem = courseItem;
        courseTitle.setText(courseItem.getTitle());
        if (studentLessonListModel == null) {
            studentLessonListModel = new StudentLessonListModel(student, courseItem);
            lessonListComponent.setModel(studentLessonListModel);
        } else {
            studentLessonListModel.setStudent(student);
            studentLessonListModel.setCourseItem(courseItem);
        }
        if (!lessonListComponent.isSelectionEmpty()) {
            var lessonItem = lessonListComponent.getSelectedValue();
            titleTextLabel.setText(lessonItem.getTitle());
            additionalResourcesTextLabel.setText(lessonItem.getAdditionalResource());
            contentTextLabel.setText(lessonItem.getContent());
            if (student.isLessonDone(courseItem, lessonItem)) {
                startQuizButton.setText("View Quiz");
            } else {
                startQuizButton.setText("Start Quiz");
            }
        }
    }

    private void startQuizButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_startQuizButtonActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_startQuizButtonActionPerformed

    private void onSelectionChange(ListSelectionEvent evt) {
        setInfoPaneVisibility(!lessonListComponent.isSelectionEmpty());
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lessonListScrollPane = new javax.swing.JScrollPane();
        lessonListComponent = new javax.swing.JList<>();
        lessonDetailsPanel = new javax.swing.JPanel();
        courseTitle = new javax.swing.JLabel();
        selectionPromptLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        titleTextLabel = new javax.swing.JLabel();
        contentLabel = new javax.swing.JLabel();
        contentTextLabel = new javax.swing.JLabel();
        additionalResourcesLabel = new javax.swing.JLabel();
        additionalResourcesTextLabel = new javax.swing.JLabel();
        startQuizButton = new javax.swing.JButton();
        completionTitleLabel = new javax.swing.JLabel();
        quizCompletionStatusLabel = new javax.swing.JLabel();

        lessonListScrollPane.setViewportView(lessonListComponent);

        lessonDetailsPanel.setLayout(new java.awt.GridBagLayout());

        courseTitle.setFont(courseTitle.getFont().deriveFont(courseTitle.getFont().getSize() + 4f));
        courseTitle.setText("Course Title");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        lessonDetailsPanel.add(courseTitle, gridBagConstraints);

        selectionPromptLabel.setText("Select a lesson to view its information");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 40, 0);
        lessonDetailsPanel.add(selectionPromptLabel, gridBagConstraints);

        titleLabel.setFont(titleLabel.getFont().deriveFont(titleLabel.getFont().getStyle() | java.awt.Font.BOLD));
        titleLabel.setText("Title");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 9, 4, 9);
        lessonDetailsPanel.add(titleLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(4, 9, 4, 9);
        lessonDetailsPanel.add(titleTextLabel, gridBagConstraints);

        contentLabel.setFont(contentLabel.getFont().deriveFont(contentLabel.getFont().getStyle() | java.awt.Font.BOLD));
        contentLabel.setText("Content");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 9, 4, 9);
        lessonDetailsPanel.add(contentLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(4, 9, 4, 9);
        lessonDetailsPanel.add(contentTextLabel, gridBagConstraints);

        additionalResourcesLabel.setFont(additionalResourcesLabel.getFont()
                .deriveFont(additionalResourcesLabel.getFont().getStyle() | java.awt.Font.BOLD));
        additionalResourcesLabel.setText("Additional Resources");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 9, 4, 9);
        lessonDetailsPanel.add(additionalResourcesLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(4, 9, 4, 9);
        lessonDetailsPanel.add(additionalResourcesTextLabel, gridBagConstraints);

        startQuizButton.setText("Start Quiz");
        startQuizButton.addActionListener(this::startQuizButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        lessonDetailsPanel.add(startQuizButton, gridBagConstraints);

        completionTitleLabel.setFont(completionTitleLabel.getFont()
                .deriveFont(completionTitleLabel.getFont().getStyle() | java.awt.Font.BOLD));
        completionTitleLabel.setText("Quiz Completion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 9, 4, 9);
        lessonDetailsPanel.add(completionTitleLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(4, 9, 4, 9);
        lessonDetailsPanel.add(quizCompletionStatusLabel, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lessonListScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lessonDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lessonListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 158,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lessonDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 268,
                                        Short.MAX_VALUE)));
    }// </editor-fold>//GEN-END:initComponents

}

package com.github.tahamostafa06.gui.panels.student;

import com.github.tahamostafa06.backend.api.Student;
import com.github.tahamostafa06.backend.courseservice.CourseItem;
import com.github.tahamostafa06.backend.courseservice.LessonItem;
import com.github.tahamostafa06.gui.models.StudentLessonListModel;
import javax.swing.event.ListSelectionEvent;

public class ViewLessonsTab extends javax.swing.JPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel additionalResourcesLabel;
    private javax.swing.JLabel additionalResourcesTextLabel;
    private javax.swing.JLabel contentLabel;
    private javax.swing.JLabel contentTextLabel;
    private javax.swing.JLabel courseTitle;
    private javax.swing.JPanel lessonDetailsPanel;
    private javax.swing.JList<com.github.tahamostafa06.backend.courseservice.LessonItem> lessonListComponent;
    private javax.swing.JScrollPane lessonListScrollPane;
    private javax.swing.JButton markDoneButton;
    private javax.swing.JLabel selectionPromptLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel titleTextLabel;
    // End of variables declaration//GEN-END:variables
    private Student student;
    private CourseItem courseItem;
    private StudentLessonListModel studentLessonListModel;

    public ViewLessonsTab() {
        initComponents();
        lessonListComponent.addListSelectionListener(this::onSelectionChange);
        contentTextLabel.setVisible(false);
        contentLabel.setVisible(false);
        titleLabel.setVisible(false);
        titleTextLabel.setVisible(false);
        additionalResourcesTextLabel.setVisible(false);
        additionalResourcesLabel.setVisible(false);
        markDoneButton.setVisible(false);
        selectionPromptLabel.setVisible(true);
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

    }

    private void markDoneButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_markDoneButtonActionPerformed
        if (!lessonListComponent.isSelectionEmpty()) {
            var lessonItem = lessonListComponent.getSelectedValue();
            student.completeLesson(courseItem, lessonItem);
            studentLessonListModel.update();
            markDoneButton.setEnabled(false);
        }
    }// GEN-LAST:event_markDoneButtonActionPerformed

    private void onSelectionChange(ListSelectionEvent evt) {
        contentTextLabel.setVisible(!lessonListComponent.isSelectionEmpty());
        contentLabel.setVisible(!lessonListComponent.isSelectionEmpty());
        titleLabel.setVisible(!lessonListComponent.isSelectionEmpty());
        titleTextLabel.setVisible(!lessonListComponent.isSelectionEmpty());
        additionalResourcesTextLabel.setVisible(!lessonListComponent.isSelectionEmpty());
        additionalResourcesLabel.setVisible(!lessonListComponent.isSelectionEmpty());
        markDoneButton.setVisible(!lessonListComponent.isSelectionEmpty());
        selectionPromptLabel.setVisible(lessonListComponent.isSelectionEmpty());
        if (!lessonListComponent.isSelectionEmpty()) {
            var lessonItem = lessonListComponent.getSelectedValue();
            titleTextLabel.setText(lessonItem.getTitle());
            additionalResourcesTextLabel.setText(lessonItem.getAdditionalResource());
            contentTextLabel.setText(lessonItem.getContent());
            markDoneButton.setEnabled(!student.isLessonDone(courseItem, lessonItem));
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
        markDoneButton = new javax.swing.JButton();

        lessonListScrollPane.setViewportView(lessonListComponent);

        lessonDetailsPanel.setLayout(new java.awt.GridBagLayout());

        courseTitle.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        courseTitle.setText("Course Title");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        lessonDetailsPanel.add(courseTitle, gridBagConstraints);

        selectionPromptLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        selectionPromptLabel.setText("Select a lesson to view its information");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 40, 0);
        lessonDetailsPanel.add(selectionPromptLabel, gridBagConstraints);

        titleLabel.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
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

        contentLabel.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
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

        additionalResourcesLabel.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
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

        markDoneButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        markDoneButton.setText("Complete Lesson");
        markDoneButton.addActionListener(this::markDoneButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        lessonDetailsPanel.add(markDoneButton, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lessonListScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(lessonDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lessonListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lessonDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

}

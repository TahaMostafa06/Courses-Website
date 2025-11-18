package com.github.tahamostafa06.gui.panels.student;

import com.github.tahamostafa06.backend.api.Student;
import com.github.tahamostafa06.backend.courseservice.StudentCourseItem;
import com.github.tahamostafa06.gui.models.StudentCourseListModel;
import javax.swing.event.ListSelectionEvent;

public class ViewAvailableCoursesPane extends javax.swing.JPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel courseDetailsPanel;
    private javax.swing.JLabel courseInformationTitle;
    private javax.swing.JList<StudentCourseItem> courseListComponent;
    private javax.swing.JScrollPane courseListScrollPane;
    private javax.swing.JLabel descriptionContentLabel;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JButton enrollButton;
    private javax.swing.JLabel enrolledStudentsCountLabel;
    private javax.swing.JLabel enrolledStudentsLabel;
    private javax.swing.JLabel instructorLabel;
    private javax.swing.JLabel instructorNameLabel;
    private javax.swing.JLabel lessonsCountLabel;
    private javax.swing.JLabel lessonsLabel;
    private javax.swing.JLabel selectionPromptLabel;
    // End of variables declaration//GEN-END:variables
    private Student student;
    private StudentCourseListModel studentCourseListModel;

    public ViewAvailableCoursesPane() {
        initComponents();
        courseListComponent.addListSelectionListener(this::onSelectionChange);
        descriptionContentLabel.setVisible(false);
        descriptionLabel.setVisible(false);
        enrolledStudentsCountLabel.setVisible(false);
        enrolledStudentsLabel.setVisible(false);
        instructorLabel.setVisible(false);
        instructorNameLabel.setVisible(false);
        lessonsCountLabel.setVisible(false);
        lessonsLabel.setVisible(false);
        enrollButton.setVisible(false);
        selectionPromptLabel.setVisible(true);
    }

    public void updateCourses(Student student) {
        this.student = student;
        if (studentCourseListModel == null) {
            studentCourseListModel = new StudentCourseListModel(student, StudentCourseListModel.EnrollmentFilter.AVAILABLE);
            courseListComponent.setModel(studentCourseListModel);
        } else {
            studentCourseListModel.setStudent(student);
            studentCourseListModel.setFilter(StudentCourseListModel.EnrollmentFilter.AVAILABLE);
        }

    }

    private void enrollButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enrollButtonActionPerformed
        if (!courseListComponent.isSelectionEmpty()) {
            var course = courseListComponent.getSelectedValue();
            student.enroll(course);
            studentCourseListModel.update();
            enrollButton.setText("Enrolled");
            enrollButton.setEnabled(false);
            enrolledStudentsCountLabel.setText(String.valueOf(student.getEnrolledStudentsCount(course)));
        }
    }//GEN-LAST:event_enrollButtonActionPerformed

    private void onSelectionChange(ListSelectionEvent evt) {
        descriptionContentLabel.setVisible(!courseListComponent.isSelectionEmpty());
        descriptionLabel.setVisible(!courseListComponent.isSelectionEmpty());
        enrolledStudentsCountLabel.setVisible(!courseListComponent.isSelectionEmpty());
        enrolledStudentsLabel.setVisible(!courseListComponent.isSelectionEmpty());
        instructorLabel.setVisible(!courseListComponent.isSelectionEmpty());
        instructorNameLabel.setVisible(!courseListComponent.isSelectionEmpty());
        lessonsCountLabel.setVisible(!courseListComponent.isSelectionEmpty());
        lessonsLabel.setVisible(!courseListComponent.isSelectionEmpty());
        enrollButton.setVisible(!courseListComponent.isSelectionEmpty());
        selectionPromptLabel.setVisible(courseListComponent.isSelectionEmpty());
        if (!courseListComponent.isSelectionEmpty()) {
            var course = courseListComponent.getSelectedValue();
            instructorNameLabel.setText(student.getInstructorName(course));
            enrolledStudentsCountLabel.setText(String.valueOf(student.getEnrolledStudentsCount(course)));
            lessonsCountLabel.setText(String.valueOf(student.getLessonCount(course)));
            descriptionContentLabel.setText(course.getDescription());
            courseInformationTitle.setText(course.getTitle());
            enrollButton.setText("Enroll");
            enrollButton.setEnabled(true);
        } else {
            courseInformationTitle.setText("Course Information");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        courseListScrollPane = new javax.swing.JScrollPane();
        courseListComponent = new javax.swing.JList<>();
        courseDetailsPanel = new javax.swing.JPanel();
        courseInformationTitle = new javax.swing.JLabel();
        selectionPromptLabel = new javax.swing.JLabel();
        instructorLabel = new javax.swing.JLabel();
        instructorNameLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        descriptionContentLabel = new javax.swing.JLabel();
        enrolledStudentsLabel = new javax.swing.JLabel();
        enrolledStudentsCountLabel = new javax.swing.JLabel();
        lessonsLabel = new javax.swing.JLabel();
        lessonsCountLabel = new javax.swing.JLabel();
        enrollButton = new javax.swing.JButton();

        courseListScrollPane.setViewportView(courseListComponent);

        courseDetailsPanel.setLayout(new java.awt.GridBagLayout());

        courseInformationTitle.setFont(courseInformationTitle.getFont().deriveFont(courseInformationTitle.getFont().getSize()+4f));
        courseInformationTitle.setText("Course Information");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        courseDetailsPanel.add(courseInformationTitle, gridBagConstraints);

        selectionPromptLabel.setText("Select a course to view its information");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 40, 0);
        courseDetailsPanel.add(selectionPromptLabel, gridBagConstraints);

        instructorLabel.setFont(instructorLabel.getFont().deriveFont(instructorLabel.getFont().getStyle() | java.awt.Font.BOLD));
        instructorLabel.setText("Instructor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 9, 4, 9);
        courseDetailsPanel.add(instructorLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(4, 9, 4, 9);
        courseDetailsPanel.add(instructorNameLabel, gridBagConstraints);

        descriptionLabel.setFont(descriptionLabel.getFont().deriveFont(descriptionLabel.getFont().getStyle() | java.awt.Font.BOLD));
        descriptionLabel.setText("Description");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 9, 4, 9);
        courseDetailsPanel.add(descriptionLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(4, 9, 4, 9);
        courseDetailsPanel.add(descriptionContentLabel, gridBagConstraints);

        enrolledStudentsLabel.setFont(enrolledStudentsLabel.getFont().deriveFont(enrolledStudentsLabel.getFont().getStyle() | java.awt.Font.BOLD));
        enrolledStudentsLabel.setText("Enrolled Students");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 9, 4, 9);
        courseDetailsPanel.add(enrolledStudentsLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(4, 9, 4, 9);
        courseDetailsPanel.add(enrolledStudentsCountLabel, gridBagConstraints);

        lessonsLabel.setFont(lessonsLabel.getFont().deriveFont(lessonsLabel.getFont().getStyle() | java.awt.Font.BOLD));
        lessonsLabel.setText("Lessons");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 9, 4, 9);
        courseDetailsPanel.add(lessonsLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(4, 9, 4, 9);
        courseDetailsPanel.add(lessonsCountLabel, gridBagConstraints);

        enrollButton.setText("Enroll");
        enrollButton.addActionListener(this::enrollButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        courseDetailsPanel.add(enrollButton, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(courseListScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(courseDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(courseListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(courseDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

}

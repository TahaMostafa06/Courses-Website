package com.github.tahamostafa06.gui.panels.student;

import com.github.tahamostafa06.backend.api.Student;
import com.github.tahamostafa06.backend.courseservice.CourseItem;
import com.github.tahamostafa06.gui.models.StudentCourseListModel;
import javax.swing.event.ListSelectionEvent;

public class ViewEnrolledCoursesTab extends javax.swing.JPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel courseDetailsPanel;
    private javax.swing.JLabel courseInformationTitle;
    private javax.swing.JList<CourseItem> courseListComponent;
    private javax.swing.JScrollPane courseListScrollPane;
    private javax.swing.JLabel descriptionContentLabel;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JLabel instructorLabel;
    private javax.swing.JLabel instructorNameLabel;
    private javax.swing.JLabel lessonsCountLabel;
    private javax.swing.JLabel lessonsLabel;
    private javax.swing.JLabel selectionPromptLabel;
    private javax.swing.JButton viewLessonsButton;
    // End of variables declaration//GEN-END:variables
    private Student student;
    private StudentCourseListModel studentCourseListModel;

    public ViewEnrolledCoursesTab() {
        initComponents();
        courseListComponent.addListSelectionListener(this::onSelectionChange);
        descriptionContentLabel.setVisible(false);
        descriptionLabel.setVisible(false);
        instructorLabel.setVisible(false);
        instructorNameLabel.setVisible(false);
        lessonsCountLabel.setVisible(false);
        lessonsLabel.setVisible(false);
        viewLessonsButton.setVisible(false);
        selectionPromptLabel.setVisible(true);
    }

    public void updateCourses(Student student) {
        this.student = student;
        if (studentCourseListModel == null) {
            studentCourseListModel = new StudentCourseListModel(student, StudentCourseListModel.EnrollmentFilter.ENROLLED);
            courseListComponent.setModel(studentCourseListModel);
        } else {
            studentCourseListModel.setStudent(student);
            studentCourseListModel.setFilter(StudentCourseListModel.EnrollmentFilter.ENROLLED);
        }

    }

    private void viewLessonsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewLessonsButtonActionPerformed
        if (!courseListComponent.isSelectionEmpty()) {
            var course = courseListComponent.getSelectedValue();
            studentCourseListModel.update();
            StudentDashboardPanel.showLessonViewer(course);
        }
    }//GEN-LAST:event_viewLessonsButtonActionPerformed

    private void onSelectionChange(ListSelectionEvent evt) {
        descriptionContentLabel.setVisible(!courseListComponent.isSelectionEmpty());
        descriptionLabel.setVisible(!courseListComponent.isSelectionEmpty());
        instructorLabel.setVisible(!courseListComponent.isSelectionEmpty());
        instructorNameLabel.setVisible(!courseListComponent.isSelectionEmpty());
        lessonsCountLabel.setVisible(!courseListComponent.isSelectionEmpty());
        lessonsLabel.setVisible(!courseListComponent.isSelectionEmpty());
        viewLessonsButton.setVisible(!courseListComponent.isSelectionEmpty());
        selectionPromptLabel.setVisible(courseListComponent.isSelectionEmpty());
        if (!courseListComponent.isSelectionEmpty()) {
            var course = courseListComponent.getSelectedValue();
            instructorNameLabel.setText(student.getInstructorName(course));
            lessonsCountLabel.setText(String.valueOf(student.getFinishedLessonsCount(course)) + " of " + String.valueOf(student.getLessonCount(course)));
            descriptionContentLabel.setText(course.getDescription());
            courseInformationTitle.setText(course.getTitle());
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
        lessonsLabel = new javax.swing.JLabel();
        lessonsCountLabel = new javax.swing.JLabel();
        viewLessonsButton = new javax.swing.JButton();

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

        lessonsLabel.setFont(lessonsLabel.getFont().deriveFont(lessonsLabel.getFont().getStyle() | java.awt.Font.BOLD));
        lessonsLabel.setText("Lesson Progress");
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

        viewLessonsButton.setText("View Lessons");
        viewLessonsButton.addActionListener(this::viewLessonsButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        courseDetailsPanel.add(viewLessonsButton, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(courseListScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(courseDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
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

package com.github.tahamostafa06.gui.panels.instructor;

import com.github.tahamostafa06.backend.api.Student;
import com.github.tahamostafa06.gui.models.StudentCourseListModel;
import javax.swing.event.ListSelectionEvent;

public class ManageCoursesTab extends javax.swing.JPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField courseDescriptionInputField;
    private javax.swing.JLabel courseDescriptionInputLabel;
    private javax.swing.JPanel courseDetailsPanel;
    private javax.swing.JLabel courseInformationTitle;
    private javax.swing.JList<CourseItem> courseListComponent;
    private javax.swing.JScrollPane courseListScrollPane;
    private javax.swing.JTextField courseTitleInputField;
    private javax.swing.JLabel courseTitleInputLabel;
    private javax.swing.JButton createNewButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel selectionPromptLabel;
    // End of variables declaration//GEN-END:variables
    private Student student;
    private StudentCourseListModel studentCourseListModel;

    public ManageCoursesTab() {
        initComponents();
        courseListComponent.addListSelectionListener(this::onSelectionChange);
        descriptionContentLabel.setVisible(false);
        courseDescriptionInputLabel.setVisible(false);
        enrolledStudentsCountLabel.setVisible(false);
        enrolledStudentsLabel.setVisible(false);
        courseTitleInputLabel.setVisible(false);
        instructorNameLabel.setVisible(false);
        lessonsCountLabel.setVisible(false);
        lessonsLabel.setVisible(false);
        saveButton.setVisible(false);
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

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        if (!courseListComponent.isSelectionEmpty()) {
            var course = courseListComponent.getSelectedValue();
            student.enroll(course);
            studentCourseListModel.update();
            saveButton.setText("Enrolled");
            saveButton.setEnabled(false);
            enrolledStudentsCountLabel.setText(String.valueOf(student.getEnrolledStudentsCount(course)));
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void onSelectionChange(ListSelectionEvent evt) {
        descriptionContentLabel.setVisible(!courseListComponent.isSelectionEmpty());
        courseDescriptionInputLabel.setVisible(!courseListComponent.isSelectionEmpty());
        enrolledStudentsCountLabel.setVisible(!courseListComponent.isSelectionEmpty());
        enrolledStudentsLabel.setVisible(!courseListComponent.isSelectionEmpty());
        courseTitleInputLabel.setVisible(!courseListComponent.isSelectionEmpty());
        instructorNameLabel.setVisible(!courseListComponent.isSelectionEmpty());
        lessonsCountLabel.setVisible(!courseListComponent.isSelectionEmpty());
        lessonsLabel.setVisible(!courseListComponent.isSelectionEmpty());
        saveButton.setVisible(!courseListComponent.isSelectionEmpty());
        selectionPromptLabel.setVisible(courseListComponent.isSelectionEmpty());
        if (!courseListComponent.isSelectionEmpty()) {
            var course = courseListComponent.getSelectedValue();
            instructorNameLabel.setText(student.getInstructorName(course));
            enrolledStudentsCountLabel.setText(String.valueOf(student.getEnrolledStudentsCount(course)));
            lessonsCountLabel.setText(String.valueOf(student.getLessonCount(course)));
            descriptionContentLabel.setText(course.getDescription());
            courseInformationTitle.setText(course.getTitle());
            saveButton.setText("Enroll");
            saveButton.setEnabled(true);
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
        courseTitleInputLabel = new javax.swing.JLabel();
        courseDescriptionInputLabel = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        courseTitleInputField = new javax.swing.JTextField();
        courseDescriptionInputField = new javax.swing.JTextField();
        deleteButton = new javax.swing.JButton();
        createNewButton = new javax.swing.JButton();

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

        courseTitleInputLabel.setFont(courseTitleInputLabel.getFont().deriveFont(courseTitleInputLabel.getFont().getStyle() | java.awt.Font.BOLD));
        courseTitleInputLabel.setText("Title");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 9, 4, 9);
        courseDetailsPanel.add(courseTitleInputLabel, gridBagConstraints);

        courseDescriptionInputLabel.setFont(courseDescriptionInputLabel.getFont().deriveFont(courseDescriptionInputLabel.getFont().getStyle() | java.awt.Font.BOLD));
        courseDescriptionInputLabel.setText("Description");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 9, 4, 9);
        courseDetailsPanel.add(courseDescriptionInputLabel, gridBagConstraints);

        saveButton.setText("Save");
        saveButton.addActionListener(this::saveButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 33;
        gridBagConstraints.insets = new java.awt.Insets(33, 21, 0, 21);
        courseDetailsPanel.add(saveButton, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        courseDetailsPanel.add(courseTitleInputField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        courseDetailsPanel.add(courseDescriptionInputField, gridBagConstraints);

        deleteButton.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        deleteButton.setForeground(javax.swing.UIManager.getDefaults().getColor("text"));
        deleteButton.setText("Delete");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 33;
        gridBagConstraints.insets = new java.awt.Insets(33, 21, 0, 21);
        courseDetailsPanel.add(deleteButton, gridBagConstraints);

        createNewButton.setText("Create New");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 33;
        gridBagConstraints.insets = new java.awt.Insets(23, 0, 0, 0);
        courseDetailsPanel.add(createNewButton, gridBagConstraints);

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

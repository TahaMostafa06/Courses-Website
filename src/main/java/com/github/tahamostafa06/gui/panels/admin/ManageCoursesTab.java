package com.github.tahamostafa06.gui.panels.admin;

import com.github.tahamostafa06.backend.api.Admin;
import com.github.tahamostafa06.gui.models.AdminCourseListModel;
import javax.swing.event.ListSelectionEvent;

public class ManageCoursesTab extends javax.swing.JPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField courseDescriptionInputField;
    private javax.swing.JLabel courseDescriptionInputLabel;
    private javax.swing.JPanel courseDetailsPanel;
    private javax.swing.JLabel courseInformationTitle;
    private javax.swing.JTextField courseInstructorInputField;
    private javax.swing.JLabel courseInstructorInputLabel;
    private javax.swing.JList<com.github.tahamostafa06.backend.courseservice.CourseItem> courseListComponent;
    private javax.swing.JScrollPane courseListScrollPane;
    private javax.swing.JComboBox<String> courseStatusComboBox;
    private javax.swing.JLabel courseStatusLabel;
    private javax.swing.JTextField courseTitleInputField;
    private javax.swing.JLabel courseTitleInputLabel;
    private javax.swing.JButton createNewButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editLessonsButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel selectionPromptLabel;
    // End of variables declaration//GEN-END:variables
    private Admin admin;
    private AdminCourseListModel adminCourseListModel;

    public ManageCoursesTab() {
        initComponents();
        courseListComponent.addListSelectionListener(this::onSelectionChange);
        updateEditingSpace(false);
    }

    private void updateEditingSpace(boolean showEditing) {
        courseDescriptionInputField.setVisible(showEditing);
        courseDescriptionInputLabel.setVisible(showEditing);
        courseTitleInputLabel.setVisible(showEditing);
        courseTitleInputField.setVisible(showEditing);
        saveButton.setVisible(showEditing);
        cancelButton.setVisible(showEditing);
        courseInstructorInputField.setVisible(showEditing);
        courseInstructorInputLabel.setVisible(showEditing);
        courseStatusComboBox.setVisible(showEditing);
        courseStatusLabel.setVisible(showEditing);
        selectionPromptLabel.setVisible(!showEditing);
        createNewButton.setVisible(!showEditing);
        saveButton.setEnabled(false);
        deleteButton.setVisible(false);
        editLessonsButton.setVisible(false);
        if (!showEditing) {
            if (!courseListComponent.isSelectionEmpty()) {
                courseListComponent.clearSelection();
            }
            courseInformationTitle.setText("Course Information");
            createNewButton.requestFocus();
        } else {
            if (courseListComponent.isSelectionEmpty()) {
                courseInformationTitle.setText("Create New Course");
                courseTitleInputField.setText("");
                courseDescriptionInputField.setText("");
            } else {
                courseInformationTitle.setText("Editing: " + courseListComponent.getSelectedValue().getTitle());
                courseTitleInputField.setText(courseListComponent.getSelectedValue().getTitle());
                courseDescriptionInputField.setText(courseListComponent.getSelectedValue().getDescription());
                courseInstructorInputField.setText(courseListComponent.getSelectedValue().getInstructor());
                courseStatusComboBox.setSelectedItem(courseListComponent.getSelectedValue().getStatus());
                deleteButton.setVisible(true);
                editLessonsButton.setVisible(true);
            }

            courseTitleInputField.requestFocus();
        }
    }

    public void updateCourses(Admin admin) {
        this.admin = admin;
        if (adminCourseListModel == null) {
            adminCourseListModel = new AdminCourseListModel(admin, AdminCourseListModel.StatusFilter.ALL);
            courseListComponent.setModel(adminCourseListModel);
        } else {
            adminCourseListModel.setAdmin(admin);
        }
    }

    private void createNewButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_createNewButtonActionPerformed
        courseListComponent.clearSelection();
        updateEditingSpace(true);
    }// GEN-LAST:event_createNewButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_saveButtonActionPerformed
        if (!courseListComponent.isSelectionEmpty()) {
            var course = courseListComponent.getSelectedValue();
            admin.setCourseTitle(course, courseTitleInputField.getText());
            admin.setCourseDescription(course, courseDescriptionInputField.getText());
            admin.setCourseInstructor(course, courseInstructorInputField.getText());
            admin.setCourseStatus(course, (String) courseStatusComboBox.getSelectedItem());
            updateEditingSpace(true);
            adminCourseListModel.update();
        } else {
            var newCourse = admin.createCourse(courseTitleInputField.getText(),
                    courseDescriptionInputField.getText(), courseInstructorInputField.getText(),
                    (String) courseStatusComboBox.getSelectedItem());
            adminCourseListModel.update();
            courseListComponent.setSelectedValue(newCourse, true);
            updateEditingSpace(true);
        }
    }// GEN-LAST:event_saveButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_deleteButtonActionPerformed
        if (!courseListComponent.isSelectionEmpty()) {
            var course = courseListComponent.getSelectedValue();
            admin.deleteCourse(course);
            adminCourseListModel.update();
            updateEditingSpace(false);
        }
    }// GEN-LAST:event_deleteButtonActionPerformed

    private void editLessonsButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_editLessonsButtonActionPerformed
        if (!courseListComponent.isSelectionEmpty()) {
            var course = courseListComponent.getSelectedValue();
            AdminDashboardPanel.showLessonViewer(course);
        }
    }// GEN-LAST:event_editLessonsButtonActionPerformed

    private void onSelectionChange(ListSelectionEvent evt) {
        updateEditingSpace(!courseListComponent.isSelectionEmpty());
    }

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancelButtonActionPerformed
        updateEditingSpace(false);
    }// GEN-LAST:event_cancelButtonActionPerformed

    private void courseDescriptionInputFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_courseDescriptionInputFieldActionPerformed
        if (saveButton.isEnabled())
            saveButton.requestFocus();
        else
            cancelButton.requestFocus();
    }// GEN-LAST:event_courseDescriptionInputFieldActionPerformed

    private void courseTitleInputFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_courseTitleInputFieldActionPerformed
        courseDescriptionInputField.requestFocus();
    }// GEN-LAST:event_courseTitleInputFieldActionPerformed

    private void checkSaveButton() {
        var inputTitle = courseTitleInputField.getText();
        var inputDescription = courseDescriptionInputField.getText();
        var inputInstructor = courseInstructorInputField.getText();
        var inputStatus = courseStatusComboBox.getSelectedItem();
        if (!courseListComponent.isSelectionEmpty()) {
            var course = courseListComponent.getSelectedValue();
            if (course.getTitle().equals(inputTitle) && course.getDescription().equals(inputDescription)
                    && course.getInstructor().equals(inputInstructor) && course.getStatus().equals(inputStatus))
                saveButton.setEnabled(false);
            else
                saveButton.setEnabled(true);
        } else {
            saveButton.setEnabled(inputDescription.length() > 0 && inputTitle.length() > 0);
        }
    }

    private void courseTitleInputFieldKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_courseTitleInputFieldKeyReleased
        checkSaveButton();
    }// GEN-LAST:event_courseTitleInputFieldKeyReleased

    private void courseDescriptionInputFieldKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_courseDescriptionInputFieldKeyReleased
        checkSaveButton();
    }// GEN-LAST:event_courseDescriptionInputFieldKeyReleased

    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
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
        cancelButton = new javax.swing.JButton();
        editLessonsButton = new javax.swing.JButton();
        courseInstructorInputLabel = new javax.swing.JLabel();
        courseInstructorInputField = new javax.swing.JTextField();
        courseStatusComboBox = new javax.swing.JComboBox<>();
        courseStatusLabel = new javax.swing.JLabel();

        courseListScrollPane.setViewportView(courseListComponent);

        courseDetailsPanel.setLayout(new java.awt.GridBagLayout());

        courseInformationTitle.setFont(courseInformationTitle.getFont().deriveFont(courseInformationTitle.getFont().getSize()+4f));
        courseInformationTitle.setText("Course Information");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        courseDetailsPanel.add(courseInformationTitle, gridBagConstraints);

        selectionPromptLabel.setText("Select a course to edit its information or create a new course");
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
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 9, 4, 9);
        courseDetailsPanel.add(courseTitleInputLabel, gridBagConstraints);

        courseDescriptionInputLabel.setFont(courseDescriptionInputLabel.getFont().deriveFont(courseDescriptionInputLabel.getFont().getStyle() | java.awt.Font.BOLD));
        courseDescriptionInputLabel.setText("Description");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 9, 4, 9);
        courseDetailsPanel.add(courseDescriptionInputLabel, gridBagConstraints);

        saveButton.setText("Save");
        saveButton.addActionListener(this::saveButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.ipadx = 33;
        gridBagConstraints.insets = new java.awt.Insets(33, 21, 0, 21);
        courseDetailsPanel.add(saveButton, gridBagConstraints);

        courseTitleInputField.addActionListener(this::courseTitleInputFieldActionPerformed);
        courseTitleInputField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                courseTitleInputFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        courseDetailsPanel.add(courseTitleInputField, gridBagConstraints);

        courseDescriptionInputField.addActionListener(this::courseDescriptionInputFieldActionPerformed);
        courseDescriptionInputField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                courseDescriptionInputFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        courseDetailsPanel.add(courseDescriptionInputField, gridBagConstraints);

        deleteButton.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        deleteButton.setForeground(javax.swing.UIManager.getDefaults().getColor("text"));
        deleteButton.setText("Delete");
        deleteButton.addActionListener(this::deleteButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.ipadx = 33;
        gridBagConstraints.insets = new java.awt.Insets(33, 21, 0, 21);
        courseDetailsPanel.add(deleteButton, gridBagConstraints);

        createNewButton.setText("Create New");
        createNewButton.addActionListener(this::createNewButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 33;
        gridBagConstraints.insets = new java.awt.Insets(23, 0, 0, 0);
        courseDetailsPanel.add(createNewButton, gridBagConstraints);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(this::cancelButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 33;
        gridBagConstraints.insets = new java.awt.Insets(33, 21, 0, 21);
        courseDetailsPanel.add(cancelButton, gridBagConstraints);

        editLessonsButton.setText("Edit Lessons");
        editLessonsButton.addActionListener(this::editLessonsButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(3, 6, 3, 6);
        courseDetailsPanel.add(editLessonsButton, gridBagConstraints);

        courseInstructorInputLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        courseInstructorInputLabel.setText("Instructor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 31, 0, 9);
        courseDetailsPanel.add(courseInstructorInputLabel, gridBagConstraints);

        courseInstructorInputField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        courseDetailsPanel.add(courseInstructorInputField, gridBagConstraints);

        courseStatusComboBox.setFont(courseStatusComboBox.getFont().deriveFont(courseStatusComboBox.getFont().getSize()+4f));
        courseStatusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PENDING", "APPROVED", "DECLINED" }));
        courseStatusComboBox.setToolTipText("");
        courseStatusComboBox.addActionListener(this::courseStatusComboBoxActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        courseDetailsPanel.add(courseStatusComboBox, gridBagConstraints);

        courseStatusLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        courseStatusLabel.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(8, 18, 8, 11);
        courseDetailsPanel.add(courseStatusLabel, gridBagConstraints);

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
                .addComponent(courseDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void courseStatusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseStatusComboBoxActionPerformed
        checkSaveButton();
    }//GEN-LAST:event_courseStatusComboBoxActionPerformed
}

package com.github.tahamostafa06.gui.panels.admin;

import com.github.tahamostafa06.gui.panels.admin.*;
import com.github.tahamostafa06.backend.api.Admin;
import com.github.tahamostafa06.backend.courseservice.CourseItem;
import com.github.tahamostafa06.backend.courseservice.LessonItem;
import com.github.tahamostafa06.gui.models.AdminLessonListModel;
import javax.swing.event.ListSelectionEvent;

public class ManageLessonsTab extends javax.swing.JPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField additionalResourcesInputField;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField contentInputField;
    private javax.swing.JLabel courseNameLabel;
    private javax.swing.JButton createNewButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel lessonAdditionalResourcesLabel;
    private javax.swing.JLabel lessonContentLabel;
    private javax.swing.JPanel lessonDetailsPanel;
    private javax.swing.JLabel lessonInformationTitle;
    private javax.swing.JList<LessonItem> lessonList;
    private javax.swing.JScrollPane lessonListScrollPane;
    private javax.swing.JTextField lessonTitleInputField;
    private javax.swing.JLabel lessonTitleInputLabel;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel selectionPromptLabel;
    // End of variables declaration//GEN-END:variables
    private Admin admin;
    private CourseItem courseItem;
    private AdminLessonListModel adminLessonListModel;

    public ManageLessonsTab() {
        initComponents();
        lessonList.addListSelectionListener(this::onSelectionChange);
        updateEditingSpace(false);
    }

    private void updateEditingSpace(boolean showEditing) {
        contentInputField.setVisible(showEditing);
        lessonContentLabel.setVisible(showEditing);
        lessonTitleInputLabel.setVisible(showEditing);
        lessonTitleInputField.setVisible(showEditing);
        additionalResourcesInputField.setVisible(showEditing);
        lessonAdditionalResourcesLabel.setVisible(showEditing);
        saveButton.setVisible(showEditing);
        cancelButton.setVisible(showEditing);
        selectionPromptLabel.setVisible(!showEditing);
        createNewButton.setVisible(!showEditing);
        saveButton.setEnabled(false);
        deleteButton.setVisible(false);
        if (!showEditing) {
            if (!lessonList.isSelectionEmpty()) {
                lessonList.clearSelection();
            }
            lessonInformationTitle.setText("Lesson Information");
            createNewButton.requestFocus();
        } else {
            if (lessonList.isSelectionEmpty()) {
                lessonInformationTitle.setText("Create New Lesson");
                lessonTitleInputField.setText("");
                contentInputField.setText("");
                additionalResourcesInputField.setText("");
            } else {
                var selectedLessonItem = lessonList.getSelectedValue();
                lessonInformationTitle.setText("Editing: " + selectedLessonItem.getTitle());
                lessonTitleInputField.setText(selectedLessonItem.getTitle());
                additionalResourcesInputField.setText(selectedLessonItem.getAdditionalResource());
                contentInputField.setText(selectedLessonItem.getContent());
                deleteButton.setVisible(true);
            }
            lessonTitleInputField.requestFocus();
        }
    }

    public void updateLessonView(Admin admin, CourseItem courseItem) {
        this.admin = admin;
        this.courseItem = courseItem;
        courseNameLabel.setText(courseItem.getTitle());
        if (adminLessonListModel == null) {
            adminLessonListModel = new AdminLessonListModel(admin, courseItem);
            lessonList.setModel(adminLessonListModel);
        } else {
            adminLessonListModel.setAdmin(admin);
            adminLessonListModel.setCourseItem(courseItem);
        }

    }

    private void createNewButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_createNewButtonActionPerformed
        lessonList.clearSelection();
        updateEditingSpace(true);
    }// GEN-LAST:event_createNewButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_saveButtonActionPerformed
        if (!lessonList.isSelectionEmpty()) {
            var lesson = lessonList.getSelectedValue();
            admin.setLessonTitle(courseItem, lesson, lessonTitleInputField.getText());
            admin.setLessonContent(courseItem, lesson, contentInputField.getText());
            admin.setLessonAdditionalResources(courseItem, lesson, additionalResourcesInputField.getText());
            updateEditingSpace(true);
            adminLessonListModel.update();
        } else {
            var newLesson = admin.addLesson(courseItem, lessonTitleInputField.getText(),
                    contentInputField.getText(), additionalResourcesInputField.getText());
            adminLessonListModel.update();
            lessonList.setSelectedValue(newLesson, true);
            updateEditingSpace(true);
        }
    }// GEN-LAST:event_saveButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_deleteButtonActionPerformed
        if (!lessonList.isSelectionEmpty()) {
            var lesson = lessonList.getSelectedValue();
            admin.removeLesson(courseItem, lesson);
            adminLessonListModel.update();
            updateEditingSpace(false);
        }
    }// GEN-LAST:event_deleteButtonActionPerformed

    private void onSelectionChange(ListSelectionEvent evt) {
        updateEditingSpace(!lessonList.isSelectionEmpty());
    }

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancelButtonActionPerformed
        updateEditingSpace(false);
    }// GEN-LAST:event_cancelButtonActionPerformed

    private void checkSaveButton() {
        var inputTitle = lessonTitleInputField.getText();
        var inputContent = contentInputField.getText();
        var inputAdditionalResources = additionalResourcesInputField.getText();
        if (!lessonList.isSelectionEmpty()) {
            var lessonItem = lessonList.getSelectedValue();
            if (lessonItem.getTitle().equals(inputTitle) && lessonItem.getContent().equals(inputContent)
                    && lessonItem.getAdditionalResource().equals(inputAdditionalResources))
                saveButton.setEnabled(false);
            else
                saveButton.setEnabled(true);
        } else {
            saveButton.setEnabled(
                    inputAdditionalResources.length() > 0 && inputContent.length() > 0 && inputTitle.length() > 0);
        }
    }

    private void additionalResourcesInputFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_additionalResourcesInputFieldActionPerformed
        if (saveButton.isEnabled())
            saveButton.requestFocus();
        else
            cancelButton.requestFocus();
    }// GEN-LAST:event_additionalResourcesInputFieldActionPerformed

    private void lessonTitleInputFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_lessonTitleInputFieldActionPerformed
        contentInputField.requestFocus();
    }// GEN-LAST:event_lessonTitleInputFieldActionPerformed

    private void contentInputFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_contentInputFieldActionPerformed
        additionalResourcesInputField.requestFocus();
    }// GEN-LAST:event_contentInputFieldActionPerformed

    private void lessonTitleInputFieldKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_courseTitleInputFieldKeyReleased
        checkSaveButton();
    }// GEN-LAST:event_courseTitleInputFieldKeyReleased

    private void contentInputFieldKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_courseDescriptionInputFieldKeyReleased
        checkSaveButton();
    }// GEN-LAST:event_courseDescriptionInputFieldKeyReleased

    private void additionalResourcesInputFieldKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_additionalResourcesInputFieldKeyReleased
        checkSaveButton();
    }// GEN-LAST:event_additionalResourcesInputFieldKeyReleased

    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lessonListScrollPane = new javax.swing.JScrollPane();
        lessonList = new javax.swing.JList<>();
        lessonDetailsPanel = new javax.swing.JPanel();
        lessonInformationTitle = new javax.swing.JLabel();
        selectionPromptLabel = new javax.swing.JLabel();
        lessonTitleInputLabel = new javax.swing.JLabel();
        lessonContentLabel = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        lessonTitleInputField = new javax.swing.JTextField();
        contentInputField = new javax.swing.JTextField();
        deleteButton = new javax.swing.JButton();
        createNewButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        courseNameLabel = new javax.swing.JLabel();
        lessonAdditionalResourcesLabel = new javax.swing.JLabel();
        additionalResourcesInputField = new javax.swing.JTextField();

        lessonListScrollPane.setViewportView(lessonList);

        lessonDetailsPanel.setLayout(new java.awt.GridBagLayout());

        lessonInformationTitle
                .setFont(lessonInformationTitle.getFont().deriveFont(lessonInformationTitle.getFont().getSize() + 4f));
        lessonInformationTitle.setText("Lesson Information");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        lessonDetailsPanel.add(lessonInformationTitle, gridBagConstraints);

        selectionPromptLabel.setText("Select a lesson to edit its information or create a new lesson");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 40, 0);
        lessonDetailsPanel.add(selectionPromptLabel, gridBagConstraints);

        lessonTitleInputLabel.setFont(lessonTitleInputLabel.getFont()
                .deriveFont(lessonTitleInputLabel.getFont().getStyle() | java.awt.Font.BOLD));
        lessonTitleInputLabel.setText("Title");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 9, 4, 9);
        lessonDetailsPanel.add(lessonTitleInputLabel, gridBagConstraints);

        lessonContentLabel.setFont(
                lessonContentLabel.getFont().deriveFont(lessonContentLabel.getFont().getStyle() | java.awt.Font.BOLD));
        lessonContentLabel.setText("Content");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 9, 4, 9);
        lessonDetailsPanel.add(lessonContentLabel, gridBagConstraints);

        saveButton.setText("Save");
        saveButton.addActionListener(this::saveButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.ipadx = 33;
        gridBagConstraints.insets = new java.awt.Insets(33, 21, 0, 21);
        lessonDetailsPanel.add(saveButton, gridBagConstraints);

        lessonTitleInputField.addActionListener(this::lessonTitleInputFieldActionPerformed);
        lessonTitleInputField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lessonTitleInputFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        lessonDetailsPanel.add(lessonTitleInputField, gridBagConstraints);

        contentInputField.addActionListener(this::contentInputFieldActionPerformed);
        contentInputField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                contentInputFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        lessonDetailsPanel.add(contentInputField, gridBagConstraints);

        deleteButton.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        deleteButton.setForeground(javax.swing.UIManager.getDefaults().getColor("text"));
        deleteButton.setText("Delete");
        deleteButton.addActionListener(this::deleteButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.ipadx = 33;
        gridBagConstraints.insets = new java.awt.Insets(33, 21, 0, 21);
        lessonDetailsPanel.add(deleteButton, gridBagConstraints);

        createNewButton.setText("Create New");
        createNewButton.addActionListener(this::createNewButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 33;
        gridBagConstraints.insets = new java.awt.Insets(23, 0, 0, 0);
        lessonDetailsPanel.add(createNewButton, gridBagConstraints);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(this::cancelButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 33;
        gridBagConstraints.insets = new java.awt.Insets(33, 21, 0, 21);
        lessonDetailsPanel.add(cancelButton, gridBagConstraints);

        courseNameLabel.setFont(courseNameLabel.getFont().deriveFont(
                courseNameLabel.getFont().getStyle() | java.awt.Font.BOLD, courseNameLabel.getFont().getSize() + 6));
        courseNameLabel.setText("Course Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 8, 0);
        lessonDetailsPanel.add(courseNameLabel, gridBagConstraints);

        lessonAdditionalResourcesLabel.setFont(lessonAdditionalResourcesLabel.getFont()
                .deriveFont(lessonAdditionalResourcesLabel.getFont().getStyle() | java.awt.Font.BOLD));
        lessonAdditionalResourcesLabel.setText("Additional Resources");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 9, 4, 9);
        lessonDetailsPanel.add(lessonAdditionalResourcesLabel, gridBagConstraints);

        additionalResourcesInputField.addActionListener(this::additionalResourcesInputFieldActionPerformed);
        additionalResourcesInputField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                additionalResourcesInputFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        lessonDetailsPanel.add(additionalResourcesInputField, gridBagConstraints);

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
                                .addComponent(lessonDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 342,
                                        Short.MAX_VALUE)));
    }// </editor-fold>//GEN-END:initComponents
}

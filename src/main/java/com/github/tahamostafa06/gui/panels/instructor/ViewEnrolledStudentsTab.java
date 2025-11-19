package com.github.tahamostafa06.gui.panels.instructor;

import com.github.tahamostafa06.backend.api.Instructor;
import com.github.tahamostafa06.gui.models.InstructorCourseListModel;

import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;

public class ViewEnrolledStudentsTab extends javax.swing.JPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<com.github.tahamostafa06.backend.courseservice.CourseItem> courseListComponent;
    private javax.swing.JScrollPane courseListScrollPane;
    private javax.swing.JList<String> studentList;
    private javax.swing.JScrollPane studentScrollPane;
    private javax.swing.JPanel studentsViewPanel;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
    private Instructor instructor;
    private InstructorCourseListModel instructorCourseListModel;
    private DefaultListModel<String> studentListModel;

    public ViewEnrolledStudentsTab() {
        initComponents();
        courseListComponent.addListSelectionListener(this::onSelectionChange);
        studentListModel = new DefaultListModel<>();
        studentList.setModel(studentListModel);
    }

    public void updateCourses(Instructor instructor) {
        this.instructor = instructor;
        if (instructorCourseListModel == null) {
            instructorCourseListModel = new InstructorCourseListModel(instructor);
            courseListComponent.setModel(instructorCourseListModel);
        } else {
            instructorCourseListModel.setInstructor(instructor);
        }
    }

    private void onSelectionChange(ListSelectionEvent evt) {
        if (!courseListComponent.isSelectionEmpty()) {
            studentListModel.clear();
            studentListModel.addAll(instructor.getMyStudentsForCourse(courseListComponent.getSelectedValue()));
        } else {
            studentListModel.clear();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        courseListScrollPane = new javax.swing.JScrollPane();
        courseListComponent = new javax.swing.JList<>();
        studentsViewPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        studentScrollPane = new javax.swing.JScrollPane();
        studentList = new javax.swing.JList<>();

        courseListScrollPane.setViewportView(courseListComponent);

        titleLabel.setFont(titleLabel.getFont().deriveFont(titleLabel.getFont().getStyle() | java.awt.Font.BOLD,
                titleLabel.getFont().getSize() + 6));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Enrolled Students");

        studentScrollPane.setViewportView(studentList);

        javax.swing.GroupLayout studentsViewPanelLayout = new javax.swing.GroupLayout(studentsViewPanel);
        studentsViewPanel.setLayout(studentsViewPanelLayout);
        studentsViewPanelLayout.setHorizontalGroup(
                studentsViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(studentsViewPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addComponent(studentScrollPane, javax.swing.GroupLayout.Alignment.TRAILING));
        studentsViewPanelLayout.setVerticalGroup(
                studentsViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(studentsViewPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titleLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(studentScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 225,
                                        Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(courseListScrollPane, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
                        .addComponent(studentsViewPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(courseListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 158,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(studentsViewPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    }// </editor-fold>//GEN-END:initComponents

}

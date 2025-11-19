package com.github.tahamostafa06.gui.panels.instructor;

import com.github.tahamostafa06.backend.api.Instructor;
import com.github.tahamostafa06.gui.panels.CardPanel;
import com.github.tahamostafa06.gui.panels.MainWindowFrame;

public class InstructorDashboardPanel extends CardPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane dashboardTabbedPane;
    private javax.swing.JButton logoutButton;
    private com.github.tahamostafa06.gui.panels.instructor.ManageCoursesTab manageCoursesTab1;
    private javax.swing.JPanel navBarPanel;
    private javax.swing.JLabel studentNameLabel;
    private javax.swing.JLabel studentRoleLabel;
    private com.github.tahamostafa06.gui.panels.instructor.ViewEnrolledStudentsTab viewEnrolledStudentsTab1;
    private com.github.tahamostafa06.gui.panels.instructor.ViewEnrolledStudentsTab viewEnrolledStudentsTab2;
    // End of variables declaration//GEN-END:variables
    private Instructor instructor;
    private static InstructorDashboardPanel instance;

    public InstructorDashboardPanel() {
        initComponents();
        instance = this;
        dashboardTabbedPane.remove(2);
    }

    public static void showLessonViewer(CourseItem selectedCourseItem) {
        var tabManager = instance.dashboardTabbedPane;
        instance.viewLessonsTab.updateLessonView(instance.instructor, selectedCourseItem);
        tabManager.add("Lessons", instance.viewLessonsTab);
        tabManager.setSelectedIndex(2);
    }

    public static void goBackToEnrolledCourses() {
        var tabManager = instance.dashboardTabbedPane;
        tabManager.setSelectedIndex(1);
        tabManager.remove(2);

    }

    @Override
    public void receiveTransitionMessage(Object message) {
        instructor = (Instructor) message;
        updateAllChildren();
    }

    private void updateAllChildren() {
        if (instructor == null)
            return;
        studentNameLabel.setText(instructor.getName());
        viewAvailableCoursesTab.updateCourses(instructor);
        viewEnrolledCoursesTab.updateCourses(instructor);
    }

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_logoutButtonActionPerformed
        MainWindowFrame.switchTo(MainWindowFrame.PANELS.OnboardingPanel);
    }// GEN-LAST:event_logoutButtonActionPerformed

    private void dashboardTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_dashboardTabbedPaneStateChanged
        if (dashboardTabbedPane.getSelectedIndex() != 2 && dashboardTabbedPane.getTabCount() == 3) {
            dashboardTabbedPane.remove(2);
        }
        updateAllChildren();
    }// GEN-LAST:event_dashboardTabbedPaneStateChanged

    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dashboardTabbedPane = new javax.swing.JTabbedPane();
        manageCoursesTab1 = new com.github.tahamostafa06.gui.panels.instructor.ManageCoursesTab();
        viewEnrolledStudentsTab1 = new com.github.tahamostafa06.gui.panels.instructor.ViewEnrolledStudentsTab();
        viewEnrolledStudentsTab2 = new com.github.tahamostafa06.gui.panels.instructor.ViewEnrolledStudentsTab();
        navBarPanel = new javax.swing.JPanel();
        studentNameLabel = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        studentRoleLabel = new javax.swing.JLabel();

        dashboardTabbedPane.addChangeListener(this::dashboardTabbedPaneStateChanged);
        dashboardTabbedPane.addTab("Manage Courses", manageCoursesTab1);
        dashboardTabbedPane.addTab("Manage Lessons", viewEnrolledStudentsTab1);
        dashboardTabbedPane.addTab("Enrolled Students", viewEnrolledStudentsTab2);

        studentNameLabel.setFont(studentNameLabel.getFont().deriveFont(studentNameLabel.getFont().getSize() + 2f));
        studentNameLabel.setText("Instructor Name");

        logoutButton.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        logoutButton.setForeground(javax.swing.UIManager.getDefaults().getColor("text"));
        logoutButton.setText("Log Out");
        logoutButton.addActionListener(this::logoutButtonActionPerformed);

        studentRoleLabel.setFont(
                studentRoleLabel.getFont().deriveFont((studentRoleLabel.getFont().getStyle() | java.awt.Font.ITALIC)));
        studentRoleLabel.setText("Instructor");

        javax.swing.GroupLayout navBarPanelLayout = new javax.swing.GroupLayout(navBarPanel);
        navBarPanel.setLayout(navBarPanelLayout);
        navBarPanelLayout.setHorizontalGroup(
                navBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(navBarPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(navBarPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(navBarPanelLayout.createSequentialGroup()
                                                .addComponent(studentRoleLabel)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(navBarPanelLayout.createSequentialGroup()
                                                .addComponent(studentNameLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(logoutButton)))
                                .addContainerGap()));
        navBarPanelLayout.setVerticalGroup(
                navBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navBarPanelLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(navBarPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(studentNameLabel)
                                        .addComponent(logoutButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(studentRoleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(dashboardTabbedPane)
                        .addComponent(navBarPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(navBarPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dashboardTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 489,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)));
    }// </editor-fold>//GEN-END:initComponents
}

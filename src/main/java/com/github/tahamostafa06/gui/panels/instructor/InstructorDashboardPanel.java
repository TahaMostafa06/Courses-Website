package com.github.tahamostafa06.gui.panels.instructor;

import com.github.tahamostafa06.backend.api.Instructor;
import com.github.tahamostafa06.backend.courseservice.CourseItem;
import com.github.tahamostafa06.gui.panels.CardPanel;
import com.github.tahamostafa06.gui.panels.MainWindowFrame;

public class InstructorDashboardPanel extends CardPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane dashboardTabbedPane;
    private javax.swing.JLabel instructorNameLabel;
    private javax.swing.JLabel instructorRoleLabel;
    private javax.swing.JButton logoutButton;
    private com.github.tahamostafa06.gui.panels.instructor.ManageCoursesTab manageCoursesTab;
    private javax.swing.JPanel navBarPanel;
    private com.github.tahamostafa06.gui.panels.instructor.ViewEnrolledStudentsTab viewEnrolledStudentsTab;
    // End of variables declaration//GEN-END:variables
    private ManageLessonsTab manageLessonsTab;
    private Instructor instructor;
    private static InstructorDashboardPanel instance;

    public InstructorDashboardPanel() {
        initComponents();
        instance = this;
    }

    public static void showLessonViewer(CourseItem selectedCourseItem) {
        // var tabManager = instance.dashboardTabbedPane;
        // instance.manageLessonsTab.updateLessonView(instance.instructor, selectedCourseItem);
        // tabManager.add("Lessons", instance.manageLessonsTab);
        // tabManager.setSelectedIndex(2);
    }

    @Override
    public void receiveTransitionMessage(Object message) {
        instructor = (Instructor) message;
        updateAllChildren();
    }

    private void updateAllChildren() {
        if (instructor == null)
            return;
        instructorNameLabel.setText(instructor.getName());
        manageCoursesTab.updateCourses(instructor);
        viewEnrolledStudentsTab.updateCourses(instructor);
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dashboardTabbedPane = new javax.swing.JTabbedPane();
        manageCoursesTab = new com.github.tahamostafa06.gui.panels.instructor.ManageCoursesTab();
        viewEnrolledStudentsTab = new com.github.tahamostafa06.gui.panels.instructor.ViewEnrolledStudentsTab();
        navBarPanel = new javax.swing.JPanel();
        instructorNameLabel = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        instructorRoleLabel = new javax.swing.JLabel();

        dashboardTabbedPane.addChangeListener(this::dashboardTabbedPaneStateChanged);
        dashboardTabbedPane.addTab("Manage Courses", manageCoursesTab);
        dashboardTabbedPane.addTab("My Students", viewEnrolledStudentsTab);

        instructorNameLabel.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        instructorNameLabel.setText("Instructor Name");

        logoutButton.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        logoutButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        logoutButton.setForeground(javax.swing.UIManager.getDefaults().getColor("text"));
        logoutButton.setText("Log Out");
        logoutButton.addActionListener(this::logoutButtonActionPerformed);

        instructorRoleLabel.setFont(new java.awt.Font("SansSerif", 3, 12)); // NOI18N
        instructorRoleLabel.setText("Instructor");

        javax.swing.GroupLayout navBarPanelLayout = new javax.swing.GroupLayout(navBarPanel);
        navBarPanel.setLayout(navBarPanelLayout);
        navBarPanelLayout.setHorizontalGroup(
            navBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navBarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(navBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(navBarPanelLayout.createSequentialGroup()
                        .addComponent(instructorRoleLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(navBarPanelLayout.createSequentialGroup()
                        .addComponent(instructorNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 547, Short.MAX_VALUE)
                        .addComponent(logoutButton)))
                .addContainerGap())
        );
        navBarPanelLayout.setVerticalGroup(
            navBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navBarPanelLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(navBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(instructorNameLabel)
                    .addComponent(logoutButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(instructorRoleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dashboardTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE)
            .addComponent(navBarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(navBarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dashboardTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
}

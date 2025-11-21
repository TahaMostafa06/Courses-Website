package com.github.tahamostafa06.gui.panels.admin;

import com.github.tahamostafa06.gui.panels.admin.*;
import com.github.tahamostafa06.backend.api.Admin;
import com.github.tahamostafa06.backend.courseservice.CourseItem;
import com.github.tahamostafa06.gui.panels.CardPanel;
import com.github.tahamostafa06.gui.panels.MainWindowFrame;

public class AdminDashboardPanel extends CardPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adminNameLabel;
    private javax.swing.JLabel adminRoleLabel;
    private javax.swing.JTabbedPane dashboardTabbedPane;
    private javax.swing.JButton logoutButton;
    private com.github.tahamostafa06.gui.panels.admin.ManageCoursesTab manageCoursesTab;
    private com.github.tahamostafa06.gui.panels.admin.ManageLessonsTab manageLessonsTab;
    private com.github.tahamostafa06.gui.panels.admin.ManagePendingCoursesTab managePendingCoursesTab;
    private javax.swing.JPanel navBarPanel;
    // End of variables declaration//GEN-END:variables
    //private AllCoursesTab allCoursesTab;
    private Admin admin;
    private static AdminDashboardPanel instance;

    public AdminDashboardPanel() {
        initComponents();
        instance = this;
        dashboardTabbedPane.remove(2);
        //allCoursesTab = new AllCoursesTab();
    }

    public static void showLessonViewer(CourseItem selectedCourseItem) {
        var tabManager = instance.dashboardTabbedPane;
        instance.manageLessonsTab.updateLessonView(instance.admin, selectedCourseItem);
        tabManager.add("Lessons", instance.manageLessonsTab);
        tabManager.setSelectedIndex(2);
    }
    
    @Override
    public void receiveTransitionMessage(Object message) {
        admin = (Admin) message;
        updateAllChildren();
    }

    private void updateAllChildren() {
        if (admin == null)
            return;
        adminNameLabel.setText(admin.getName());
        manageCoursesTab.updateCourses(admin);
        // ManagePendingCoursesTab.updateCourses(admin);
    }

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_logoutButtonActionPerformed
        MainWindowFrame.switchTo(MainWindowFrame.PANELS.OnboardingPanel);
    }// GEN-LAST:event_logoutButtonActionPerformed

    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dashboardTabbedPane = new javax.swing.JTabbedPane();
        manageCoursesTab = new com.github.tahamostafa06.gui.panels.admin.ManageCoursesTab();
        managePendingCoursesTab = new com.github.tahamostafa06.gui.panels.admin.ManagePendingCoursesTab();
        manageLessonsTab = new com.github.tahamostafa06.gui.panels.admin.ManageLessonsTab();
        navBarPanel = new javax.swing.JPanel();
        adminNameLabel = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        adminRoleLabel = new javax.swing.JLabel();

        dashboardTabbedPane.addChangeListener(this::dashboardTabbedPaneStateChanged);
        dashboardTabbedPane.addTab("All Courses", manageCoursesTab);
        dashboardTabbedPane.addTab("Pending Courses", managePendingCoursesTab);
        dashboardTabbedPane.addTab("Manage Lessons", manageLessonsTab);

        adminNameLabel.setFont(adminNameLabel.getFont().deriveFont(adminNameLabel.getFont().getSize()+2f));
        adminNameLabel.setText("Admin Name");

        logoutButton.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        logoutButton.setForeground(javax.swing.UIManager.getDefaults().getColor("text"));
        logoutButton.setText("Log Out");
        logoutButton.addActionListener(this::logoutButtonActionPerformed);

        adminRoleLabel.setFont(adminRoleLabel.getFont().deriveFont((adminRoleLabel.getFont().getStyle() | java.awt.Font.ITALIC)));
        adminRoleLabel.setText("Admin");

        javax.swing.GroupLayout navBarPanelLayout = new javax.swing.GroupLayout(navBarPanel);
        navBarPanel.setLayout(navBarPanelLayout);
        navBarPanelLayout.setHorizontalGroup(
            navBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navBarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(navBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(navBarPanelLayout.createSequentialGroup()
                        .addComponent(adminRoleLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(navBarPanelLayout.createSequentialGroup()
                        .addComponent(adminNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 553, Short.MAX_VALUE)
                        .addComponent(logoutButton)))
                .addContainerGap())
        );
        navBarPanelLayout.setVerticalGroup(
            navBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navBarPanelLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(navBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adminNameLabel)
                    .addComponent(logoutButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminRoleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void dashboardTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_dashboardTabbedPaneStateChanged
        if (dashboardTabbedPane.getSelectedIndex() != 2 && dashboardTabbedPane.getTabCount() == 3) {
            dashboardTabbedPane.remove(2);
        }
        updateAllChildren();
    }//GEN-LAST:event_dashboardTabbedPaneStateChanged
}

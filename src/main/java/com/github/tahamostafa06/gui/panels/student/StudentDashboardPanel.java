package com.github.tahamostafa06.gui.panels.student;

import com.github.tahamostafa06.backend.api.Student;
import com.github.tahamostafa06.gui.panels.CardPanel;
import com.github.tahamostafa06.gui.panels.MainWindowFrame;

public class StudentDashboardPanel extends CardPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane dashboardTabbedPane;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel navBarPanel;
    private javax.swing.JLabel studentNameLabel;
    private javax.swing.JLabel studentRoleLabel;
    private com.github.tahamostafa06.gui.panels.student.ViewAvailableCoursesPane viewAvailableCoursesPane1;
    private com.github.tahamostafa06.gui.panels.student.ViewEnrolledCourses viewEnrolledCourses1;
    // End of variables declaration//GEN-END:variables
    private Student student;

    public StudentDashboardPanel() {
        initComponents();
    }

    @Override
    public void receiveTransitionMessage(Object message) {
        student = (Student) message;
        updateAllChildren();
    }

    private void updateAllChildren() {
        studentNameLabel.setText(student.getName());
        viewAvailableCoursesPane1.updateCourses(student);
    }

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        MainWindowFrame.switchTo(MainWindowFrame.PANELS.OnboardingPanel);
    }//GEN-LAST:event_logoutButtonActionPerformed

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dashboardTabbedPane = new javax.swing.JTabbedPane();
        viewAvailableCoursesPane1 = new com.github.tahamostafa06.gui.panels.student.ViewAvailableCoursesPane();
        viewEnrolledCourses1 = new com.github.tahamostafa06.gui.panels.student.ViewEnrolledCourses();
        navBarPanel = new javax.swing.JPanel();
        studentNameLabel = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        studentRoleLabel = new javax.swing.JLabel();

        dashboardTabbedPane.addTab("Explore Courses", viewAvailableCoursesPane1);

        javax.swing.GroupLayout viewEnrolledCourses1Layout = new javax.swing.GroupLayout(viewEnrolledCourses1);
        viewEnrolledCourses1.setLayout(viewEnrolledCourses1Layout);
        viewEnrolledCourses1Layout.setHorizontalGroup(
            viewEnrolledCourses1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 677, Short.MAX_VALUE)
        );
        viewEnrolledCourses1Layout.setVerticalGroup(
            viewEnrolledCourses1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 454, Short.MAX_VALUE)
        );

        dashboardTabbedPane.addTab("My Courses", viewEnrolledCourses1);

        studentNameLabel.setFont(studentNameLabel.getFont().deriveFont(studentNameLabel.getFont().getSize()+2f));
        studentNameLabel.setText("Student Name");

        logoutButton.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        logoutButton.setForeground(javax.swing.UIManager.getDefaults().getColor("text"));
        logoutButton.setText("Log Out");
        logoutButton.addActionListener(this::logoutButtonActionPerformed);

        studentRoleLabel.setFont(studentRoleLabel.getFont().deriveFont((studentRoleLabel.getFont().getStyle() | java.awt.Font.ITALIC)));
        studentRoleLabel.setText("Student");

        javax.swing.GroupLayout navBarPanelLayout = new javax.swing.GroupLayout(navBarPanel);
        navBarPanel.setLayout(navBarPanelLayout);
        navBarPanelLayout.setHorizontalGroup(
            navBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navBarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(navBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(navBarPanelLayout.createSequentialGroup()
                        .addComponent(studentRoleLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(navBarPanelLayout.createSequentialGroup()
                        .addComponent(studentNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logoutButton)))
                .addContainerGap())
        );
        navBarPanelLayout.setVerticalGroup(
            navBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navBarPanelLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(navBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studentNameLabel)
                    .addComponent(logoutButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(studentRoleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dashboardTabbedPane)
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

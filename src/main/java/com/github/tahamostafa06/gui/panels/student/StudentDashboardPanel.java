package com.github.tahamostafa06.gui.panels.student;

import com.github.tahamostafa06.backend.api.Student;
import com.github.tahamostafa06.backend.courseservice.CourseItem;
import com.github.tahamostafa06.backend.courseservice.LessonItem;
import com.github.tahamostafa06.backend.database.coursedatabase.StudentLessonProgress;
import com.github.tahamostafa06.gui.panels.CardPanel;
import com.github.tahamostafa06.gui.panels.MainWindowFrame;

public class StudentDashboardPanel extends CardPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane dashboardTabbedPane;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel navBarPanel;
    private javax.swing.JLabel studentNameLabel;
    private javax.swing.JLabel studentRoleLabel;
    private com.github.tahamostafa06.gui.panels.student.TakeQuizTab takeQuizTab1;
    private com.github.tahamostafa06.gui.panels.student.ViewAvailableCoursesTab viewAvailableCoursesTab;
    private com.github.tahamostafa06.gui.panels.student.ViewCertificate viewCertificate1;
    private com.github.tahamostafa06.gui.panels.student.ViewEnrolledCoursesTab viewEnrolledCoursesTab;
    private com.github.tahamostafa06.gui.panels.student.ViewLessonsTab viewLessonsTab;
    // End of variables declaration//GEN-END:variables
    private Student student;
    private static StudentDashboardPanel instance;

    public StudentDashboardPanel() {
        initComponents();
        instance = this;
        dashboardTabbedPane.remove(2);
        dashboardTabbedPane.remove(2);
        dashboardTabbedPane.remove(2);
    }

    public static void showLessonViewer(CourseItem selectedCourseItem) {
        var tabManager = instance.dashboardTabbedPane;
        instance.viewLessonsTab.updateLessonView(instance.student, selectedCourseItem);
        tabManager.add("Lessons", instance.viewLessonsTab);
        tabManager.setSelectedIndex(2);
    }

    public static void showQuiz(CourseItem courseItem, LessonItem lesson, StudentLessonProgress progress) {
        var tabManager = instance.dashboardTabbedPane;
        instance.takeQuizTab1.updateQuizView(instance.student, courseItem, lesson, progress);
        tabManager.add("Quiz", instance.takeQuizTab1);
        tabManager.setSelectedIndex(3);
    }

    public static void showCertificate(String studentName, String courseTitle, String courseId, String certificateId,
            String studentId) {
        var tabManager = instance.dashboardTabbedPane;
        instance.viewCertificate1.updateText(studentName, courseTitle, courseId, certificateId, studentId);
        tabManager.add("Certificate", instance.viewCertificate1);
        tabManager.setSelectedIndex(2);
    }

    @Override
    public void receiveTransitionMessage(Object message) {
        student = (Student) message;
        updateAllChildren();
    }

    private void updateAllChildren() {
        if (student == null)
            return;
        studentNameLabel.setText(student.getName());
        viewAvailableCoursesTab.updateCourses(student);
        viewEnrolledCoursesTab.updateCourses(student);
    }

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_logoutButtonActionPerformed
        MainWindowFrame.switchTo(MainWindowFrame.PANELS.OnboardingPanel);
    }// GEN-LAST:event_logoutButtonActionPerformed

    private void dashboardTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_dashboardTabbedPaneStateChanged
        if (dashboardTabbedPane.getSelectedIndex() < 3 && dashboardTabbedPane.getTabCount() == 4) {
            dashboardTabbedPane.remove(3);
        }
        if (dashboardTabbedPane.getSelectedIndex() < 2 && dashboardTabbedPane.getTabCount() == 3) {
            dashboardTabbedPane.remove(2);
        }
        updateAllChildren();
    }// GEN-LAST:event_dashboardTabbedPaneStateChanged

    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dashboardTabbedPane = new javax.swing.JTabbedPane();
        viewAvailableCoursesTab = new com.github.tahamostafa06.gui.panels.student.ViewAvailableCoursesTab();
        viewEnrolledCoursesTab = new com.github.tahamostafa06.gui.panels.student.ViewEnrolledCoursesTab();
        viewLessonsTab = new com.github.tahamostafa06.gui.panels.student.ViewLessonsTab();
        takeQuizTab1 = new com.github.tahamostafa06.gui.panels.student.TakeQuizTab();
        viewCertificate1 = new com.github.tahamostafa06.gui.panels.student.ViewCertificate();
        navBarPanel = new javax.swing.JPanel();
        studentNameLabel = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        studentRoleLabel = new javax.swing.JLabel();

        dashboardTabbedPane.addChangeListener(this::dashboardTabbedPaneStateChanged);
        dashboardTabbedPane.addTab("Explore Courses", viewAvailableCoursesTab);
        dashboardTabbedPane.addTab("My Courses", viewEnrolledCoursesTab);
        dashboardTabbedPane.addTab("Lessons", viewLessonsTab);
        dashboardTabbedPane.addTab("Quiz", takeQuizTab1);
        dashboardTabbedPane.addTab("Certificate", viewCertificate1);

        studentNameLabel.setFont(studentNameLabel.getFont().deriveFont(studentNameLabel.getFont().getSize() + 2f));
        studentNameLabel.setText("Student Name");

        logoutButton.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        logoutButton.setForeground(javax.swing.UIManager.getDefaults().getColor("text"));
        logoutButton.setText("Log Out");
        logoutButton.addActionListener(this::logoutButtonActionPerformed);

        studentRoleLabel.setFont(
                studentRoleLabel.getFont().deriveFont((studentRoleLabel.getFont().getStyle() | java.awt.Font.ITALIC)));
        studentRoleLabel.setText("Student");

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

    // GEN-FIRST:event_dashboardTabbedPaneStateChanged

    // GEN-LAST:event_dashboardTabbedPaneStateChanged
}

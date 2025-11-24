package com.github.tahamostafa06.gui.panels;

import com.github.tahamostafa06.gui.panels.admin.AdminDashboardPanel;
import com.github.tahamostafa06.gui.panels.instructor.InstructorDashboardPanel;
import com.github.tahamostafa06.gui.panels.student.StudentDashboardPanel;
import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.github.tahamostafa06.backend.Server;

public class MainWindowFrame extends javax.swing.JFrame {

    private static MainWindowFrame instance;
    private static Server backendServer;
    private CardLayout cardLayout;
    private OnboardingPanel onboardingPanel;
    private LoginPanel loginPanel;
    private SignupPanel signupPanel;
    private StudentDashboardPanel studentDashboardPanel;
    private InstructorDashboardPanel instructorDashboardPanel;
    private AdminDashboardPanel adminDashboardPanel;
    
    public MainWindowFrame() {
        instance = this;
        try {
            backendServer = Server.getServer();
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(this, "Failed to initialize backend. Exiting..",
                    "SkillsForge failed to start",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        initComponents();
    }

    private void initComponents() {
        cardLayout = new CardLayout();

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                quit();
            }
        });
        getContentPane().setLayout(cardLayout);

        onboardingPanel = new OnboardingPanel();
        loginPanel = new LoginPanel();
        signupPanel = new SignupPanel();
        studentDashboardPanel = new StudentDashboardPanel();
        instructorDashboardPanel = new InstructorDashboardPanel();
        adminDashboardPanel = new AdminDashboardPanel();
        registerCard(onboardingPanel);
        registerCard(loginPanel);
        registerCard(signupPanel);
        registerCard(loginPanel);
        registerCard(studentDashboardPanel);
        registerCard(instructorDashboardPanel);
        registerCard(adminDashboardPanel);
        switchTo(PANELS.OnboardingPanel);

        pack();

        setVisible(true);
    }

    private void quit() {
        var exitCode = 0;
        try {
            backendServer.close();
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(this, "Failed to close backend server and save runtime data. Exiting..",
                    "SkillsForge failed to save data",
                    JOptionPane.ERROR_MESSAGE);
            exitCode = 1;
        }
        dispose();
        System.exit(exitCode);
    }

    public static Server getServer() {
        return backendServer;
    }

    public static enum PANELS {
        OnboardingPanel,
        LoginPanel,
        SignupPanel,
        StudentDashboardPanel,
        InstructorDashboardPanel,
        AdminDashboardPanel;
        
        private CardPanel instance;
    }

    private void registerCard(CardPanel panel) {
        PANELS.valueOf(panel.getClass().getSimpleName()).instance = panel;
        getContentPane().add(panel.getClass().getSimpleName(), panel);
    }

    public static void switchTo(PANELS panel) {
        instance.cardLayout.show(instance.getContentPane(), panel.name());
    }

    public static void switchTo(PANELS panel, Object message) {
        panel.instance.receiveTransitionMessage(message);
        instance.cardLayout.show(instance.getContentPane(), panel.name());
    }
}

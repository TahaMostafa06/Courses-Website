package com.github.tahamostafa06.gui.panels;

import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.github.tahamostafa06.backend.Server;

public class MainWindowFrame extends javax.swing.JFrame {

    private static MainWindowFrame instance;
    private static Server backendServer;
    private CardLayout cardLayout;
    private OnboardingPanel onboardingPanel;
    private LoginPanel loginPanel;
    private SignupPanel signupPanel;
    private LoggedInPanelHolder loggedInPanelHolder;

    public static void switchTo(String name, Object data) {
        instance.cardLayout.show(instance.getContentPane(), name);
    }

    public static Server getServer() {
        return backendServer;
    }

    private void registerCard(JPanel panel) {
        getContentPane().add(panel.getClass().getSimpleName(), panel);
    }

    public MainWindowFrame() {
        instance = this;
        try {
            backendServer = Server.getServer();
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(this, "Failed to initialize backend. Exiting..",
                    "SkillsForge failed to start",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(ABORT);
        }
        initComponents();
    }

    private void initComponents() {
        cardLayout = new CardLayout();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(cardLayout);

        onboardingPanel = new OnboardingPanel();
        loginPanel = new LoginPanel();
        signupPanel = new SignupPanel();
        loggedInPanelHolder = new LoggedInPanelHolder();
        registerCard(onboardingPanel);
        registerCard(loginPanel);
        registerCard(signupPanel);
        registerCard(loggedInPanelHolder);
        switchTo("OnboardingPanel", "");

        pack();

        setVisible(true);

    }
}

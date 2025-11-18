package com.github.tahamostafa06.gui.panels;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
        loggedInPanelHolder = new LoggedInPanelHolder();
        registerCard(onboardingPanel);
        registerCard(loginPanel);
        registerCard(signupPanel);
        registerCard(loggedInPanelHolder);
        switchTo("OnboardingPanel", "");

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
}

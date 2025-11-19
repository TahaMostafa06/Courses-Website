package com.github.tahamostafa06.gui.panels;

public class OnboardingPanel extends CardPanel {
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton loginButton;
    private javax.swing.JButton signupButton;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables

    public OnboardingPanel() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        titleLabel = new javax.swing.JLabel();
        welcomeLabel = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        signupButton = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        titleLabel.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        titleLabel.setText("SkillForge");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(54, 54, 54, 54);
        add(titleLabel, gridBagConstraints);

        welcomeLabel.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        welcomeLabel.setText("Welcome");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        add(welcomeLabel, gridBagConstraints);

        loginButton.setText("Login");
        loginButton.addActionListener(this::loginButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 0);
        add(loginButton, gridBagConstraints);

        signupButton.setText("Sign Up");
        signupButton.addActionListener(this::signupButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 0);
        add(signupButton, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_loginButtonActionPerformed
        MainWindowFrame.switchTo(MainWindowFrame.PANELS.LoginPanel);
    }// GEN-LAST:event_loginButtonActionPerformed

    private void signupButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_signupButtonActionPerformed
        MainWindowFrame.switchTo(MainWindowFrame.PANELS.SignupPanel);
    }// GEN-LAST:event_signupButtonActionPerformed

    @Override
    public void receiveTransitionMessage(Object message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'receiveTransitionMessage'");
    }
}

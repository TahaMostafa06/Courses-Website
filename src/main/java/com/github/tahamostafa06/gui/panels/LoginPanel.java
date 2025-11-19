package com.github.tahamostafa06.gui.panels;

import com.github.tahamostafa06.backend.api.Instructor;
import com.github.tahamostafa06.backend.api.Student;
import com.github.tahamostafa06.backend.api.UserApi;
import com.github.tahamostafa06.gui.validation.LoginValidator;

public class LoginPanel extends CardPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private com.github.tahamostafa06.gui.validation.AlertLabel invalidityAlertLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton submitButton;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
    private LoginValidator loginValidator;

    public LoginPanel() {
        initComponents();
        loginValidator = new LoginValidator(usernameField, passwordField, invalidityAlertLabel,
                MainWindowFrame.getServer().getAuthHelper());
    }

    private void resetComponents() {
        usernameField.setText("");
        passwordField.setText("");
        invalidityAlertLabel.conceal();
    }

    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        titleLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        welcomeLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        submitButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        invalidityAlertLabel = new com.github.tahamostafa06.gui.validation.AlertLabel();

        setLayout(new java.awt.GridBagLayout());

        titleLabel.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        titleLabel.setText("SkillForge");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(54, 100, 54, 100);
        add(titleLabel, gridBagConstraints);

        usernameLabel.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        usernameLabel.setText("Username");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(8, 16, 8, 16);
        add(usernameLabel, gridBagConstraints);

        passwordLabel.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        passwordLabel.setText("Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(8, 16, 8, 16);
        add(passwordLabel, gridBagConstraints);

        usernameField.setFont(usernameField.getFont().deriveFont(usernameField.getFont().getSize()+4f));
        usernameField.addActionListener(this::usernameFieldActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(usernameField, gridBagConstraints);

        welcomeLabel.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        welcomeLabel.setText("Login");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(15, 39, 15, 39);
        add(welcomeLabel, gridBagConstraints);

        passwordField.setFont(passwordField.getFont().deriveFont(passwordField.getFont().getSize()+4f));
        passwordField.addActionListener(this::passwordFieldActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 16, 0);
        add(passwordField, gridBagConstraints);

        submitButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        submitButton.setText("Submit");
        submitButton.addActionListener(this::submitButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        add(submitButton, gridBagConstraints);

        cancelButton.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        cancelButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cancelButton.setForeground(javax.swing.UIManager.getDefaults().getColor("text"));
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(this::cancelButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        add(cancelButton, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(invalidityAlertLabel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_usernameFieldActionPerformed
        usernameField.transferFocus();
    }// GEN-LAST:event_usernameFieldActionPerformed

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_passwordFieldActionPerformed
        submitButtonActionPerformed(evt);
    }// GEN-LAST:event_passwordFieldActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_submitButtonActionPerformed
        UserApi login = loginValidator.verifyLogin();
        if (login != null) {
            resetComponents();
            if (login.getClass() == Student.class)
                MainWindowFrame.switchTo(MainWindowFrame.PANELS.StudentDashboardPanel, login);
            else if (login.getClass() == Instructor.class)
                MainWindowFrame.switchTo(MainWindowFrame.PANELS.InstructorDashboardPanel, login);
        }
    }// GEN-LAST:event_submitButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancelButtonActionPerformed
        cancelButton.setFocusable(false);
        resetComponents();
        cancelButton.setFocusable(true);
        MainWindowFrame.switchTo(MainWindowFrame.PANELS.OnboardingPanel);

    }// GEN-LAST:event_cancelButtonActionPerformed

    @Override
    public void receiveTransitionMessage(Object message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'receiveTransitionMessage'");
    }
}

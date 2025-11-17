package com.github.tahamostafa06.gui.panels;

public class SignupPanel extends javax.swing.JPanel {

    public SignupPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        emailLabel = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        authSeparator = new javax.swing.JSeparator();
        titleLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        welcomeLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        submitButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        repeatPasswordLabel = new javax.swing.JLabel();
        repeatPasswordField = new javax.swing.JPasswordField();
        roleComboBox = new javax.swing.JComboBox<>();
        roleLabel = new javax.swing.JLabel();
        invalidityAlertLabel = new com.github.tahamostafa06.gui.validation.AlertLabel();

        setLayout(new java.awt.GridBagLayout());

        emailLabel.setFont(emailLabel.getFont().deriveFont(emailLabel.getFont().getSize()+4f));
        emailLabel.setText("Email");
        emailLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(8, 16, 24, 16);
        add(emailLabel, gridBagConstraints);

        emailField.setFont(emailField.getFont().deriveFont(emailField.getFont().getSize()+4f));
        emailField.addActionListener(this::emailFieldActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 16, 0);
        add(emailField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(authSeparator, gridBagConstraints);

        titleLabel.setFont(titleLabel.getFont().deriveFont(titleLabel.getFont().getStyle() | java.awt.Font.BOLD, titleLabel.getFont().getSize()+12));
        titleLabel.setText("SkillForge");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(54, 143, 54, 143);
        add(titleLabel, gridBagConstraints);

        usernameLabel.setFont(usernameLabel.getFont().deriveFont(usernameLabel.getFont().getSize()+4f));
        usernameLabel.setText("Username");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(8, 16, 8, 16);
        add(usernameLabel, gridBagConstraints);

        passwordLabel.setFont(passwordLabel.getFont().deriveFont(passwordLabel.getFont().getSize()+4f));
        passwordLabel.setText("Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
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

        welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(welcomeLabel.getFont().getSize()+8f));
        welcomeLabel.setText("Sign Up");
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
        add(passwordField, gridBagConstraints);

        submitButton.setText("Submit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        add(submitButton, gridBagConstraints);

        cancelButton.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        cancelButton.setForeground(javax.swing.UIManager.getDefaults().getColor("text"));
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(this::cancelButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        add(cancelButton, gridBagConstraints);

        repeatPasswordLabel.setFont(repeatPasswordLabel.getFont().deriveFont(repeatPasswordLabel.getFont().getSize()+4f));
        repeatPasswordLabel.setText("Repeat Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(8, 16, 8, 16);
        add(repeatPasswordLabel, gridBagConstraints);

        repeatPasswordField.setFont(repeatPasswordField.getFont().deriveFont(repeatPasswordField.getFont().getSize()+4f));
        repeatPasswordField.addActionListener(this::repeatPasswordFieldActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(repeatPasswordField, gridBagConstraints);

        roleComboBox.setFont(roleComboBox.getFont().deriveFont(roleComboBox.getFont().getSize()+4f));
        roleComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Student", "Instructor" }));
        roleComboBox.addActionListener(this::roleComboBoxActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(roleComboBox, gridBagConstraints);

        roleLabel.setFont(roleLabel.getFont().deriveFont(roleLabel.getFont().getSize()+4f));
        roleLabel.setText("Role");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(8, 16, 8, 16);
        add(roleLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(invalidityAlertLabel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_usernameFieldActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_usernameFieldActionPerformed

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_passwordFieldActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_passwordFieldActionPerformed

    private void repeatPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_repeatPasswordFieldActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_repeatPasswordFieldActionPerformed

    private void roleComboBoxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_roleComboBoxActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_roleComboBoxActionPerformed

    private void emailFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_emailFieldActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_emailFieldActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancelButtonActionPerformed
        MainWindowFrame.switchTo("OnboardingPanel", null);
    }// GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator authSeparator;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private com.github.tahamostafa06.gui.validation.AlertLabel invalidityAlertLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField repeatPasswordField;
    private javax.swing.JLabel repeatPasswordLabel;
    private javax.swing.JComboBox<String> roleComboBox;
    private javax.swing.JLabel roleLabel;
    private javax.swing.JButton submitButton;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}

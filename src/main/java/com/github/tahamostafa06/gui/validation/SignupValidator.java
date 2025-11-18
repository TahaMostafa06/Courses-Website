package com.github.tahamostafa06.gui.validation;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

import com.github.tahamostafa06.backend.api.UserApi;
import com.github.tahamostafa06.backend.auth.AuthenticationHelper;
import com.github.tahamostafa06.backend.auth.Exceptions.EmailAlreadyInUse;
import com.github.tahamostafa06.backend.auth.Exceptions.UserAlreadyExists;

import javax.swing.JPasswordField;

public class SignupValidator {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField repeatPasswordField;
    private JComboBox<String> roleComboBox;
    private JTextField emailField;
    private AlertLabel alertLabel;
    private AuthenticationHelper authHelper;

    public SignupValidator(JTextField usernameField, JPasswordField passwordField, JPasswordField repeatPasswordField,
            JComboBox<String> roleComboBox, JTextField emailField, AlertLabel alertLabel,
            AuthenticationHelper authHelper) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.repeatPasswordField = repeatPasswordField;
        this.roleComboBox = roleComboBox;
        this.emailField = emailField;
        this.alertLabel = alertLabel;
        this.authHelper = authHelper;
        var usernameVerifier = new InputVerifier() {
            @Override
            public boolean verify(JComponent component) {
                return verifyUsernameLength();
            }
        };
        var passwordVerifier = new InputVerifier() {
            @Override
            public boolean shouldYieldFocus(JComponent source, JComponent target) {
                return verify(source) | (target == repeatPasswordField);
            }

            @Override
            public boolean verify(JComponent component) {
                return verifyPassword();
            }
        };
        var repeatPasswordVerifier = new InputVerifier() {
            @Override
            public boolean shouldYieldFocus(JComponent source, JComponent target) {
                return verify(source) | (target == passwordField);
            }

            @Override
            public boolean verify(JComponent component) {
                return verifyReaptedPassword();
            }
        };
        var emailVerifier = new InputVerifier() {
            @Override
            public boolean verify(JComponent component) {
                return verifyEmail();
            }
        };
        this.usernameField.setInputVerifier(usernameVerifier);
        this.passwordField.setInputVerifier(passwordVerifier);
        this.repeatPasswordField.setInputVerifier(repeatPasswordVerifier);
        this.emailField.setInputVerifier(emailVerifier);
    }

    private boolean verifyUsernameLength() {
        boolean check = (usernameField.getText().length() > 0);
        if (!check) {
            alertLabel.display("Username cannot be empty");
        } else {
            alertLabel.conceal();
        }
        return check;
    }

    private boolean verifyPassword() {
        boolean lengthCheck = (String.valueOf(passwordField.getPassword()).length() > 0);
        boolean repeatedCheck = String.valueOf(passwordField.getPassword())
                .equals(String.valueOf(repeatPasswordField.getPassword()));
        var check = lengthCheck && repeatedCheck;
        if (!lengthCheck) {
            alertLabel.display("Password cannot be empty");
        } else if (!repeatedCheck) {
            alertLabel.display("Passwords must be identical");
        } else {
            alertLabel.conceal();
        }
        return check;
    }

    private boolean verifyReaptedPassword() {
        var password = String.valueOf(passwordField.getPassword());
        var repeatedPassword = String.valueOf(repeatPasswordField.getPassword());
        var check = password.equals(repeatedPassword);
        if (!check) {
            alertLabel.display("Passwords must be identical");
        } else {
            alertLabel.conceal();
        }
        return check;
    }

    private boolean verifyEmail() {
        var email = emailField.getText();
        var check = true;
        if (email.length() == 0)
            check = false;
        if (email.contains(".."))
            check = false;
        if (email.startsWith(".") || email.endsWith("."))
            check = false;
        if (!email.contains("."))
            check = false;
        if (email.indexOf("@") != email.lastIndexOf("@"))
            check = false;
        if (!email.contains("@") || email.endsWith("a") || email.startsWith("@"))
            check = false;

        if (!check) {
            alertLabel.display("Invalid email format");
        } else {
            alertLabel.conceal();
        }
        return check;
    }

    public UserApi verifySignup() {
        if (!verifyUsernameLength()) {
            usernameField.requestFocus();
            return null;
        }
        if (!verifyPassword()) {
            passwordField.requestFocus();
            return null;
        }
        if (!verifyEmail()) {
            emailField.requestFocus();
            return null;
        }
        try {
            return authHelper.signUp(roleComboBox.getItemAt(roleComboBox.getSelectedIndex()), usernameField.getText(),
                    emailField.getText(), String.valueOf(passwordField.getPassword()));
        } catch (UserAlreadyExists exception) {
            /*
             * Request focus must come before display
             * since requestFocus calls the InputVerifiers
             */
            usernameField.requestFocus();
            alertLabel.display("A user with that username already exists");
        } catch (EmailAlreadyInUse exception) {
            emailField.requestFocus();
            alertLabel.display("Another user is already registered with that email");
        }
        return null;
    }
}

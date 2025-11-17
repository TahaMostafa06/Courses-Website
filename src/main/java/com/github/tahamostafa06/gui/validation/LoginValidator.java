package com.github.tahamostafa06.gui.validation;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

import com.github.tahamostafa06.backend.api.UserApi;
import com.github.tahamostafa06.backend.auth.AuthenticationHelper;
import com.github.tahamostafa06.backend.auth.Exceptions.IncorrectPassword;
import com.github.tahamostafa06.backend.auth.Exceptions.UserNotFound;

import javax.swing.JPasswordField;

public class LoginValidator {

    JTextField usernameField;
    InputVerifier usernameVerifier;
    JPasswordField passwordField;
    InputVerifier passwordVerifier;
    AlertLabel alertLabel;
    AuthenticationHelper authHelper;

    public LoginValidator(JTextField usernameField, JPasswordField passwordField, AlertLabel alertLabel,
            AuthenticationHelper authHelper) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.alertLabel = alertLabel;
        this.authHelper = authHelper;
        this.usernameVerifier = new InputVerifier() {
            public boolean verify(JComponent component) {
                return verifyUsernameLength();
            }
        };
        this.passwordVerifier = new InputVerifier() {
            public boolean verify(JComponent component) {
                return verifyPasswordLength();
            }
        };
        this.usernameField.setInputVerifier(usernameVerifier);
        this.passwordField.setInputVerifier(passwordVerifier);
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

    private boolean verifyPasswordLength() {
        boolean check = (String.valueOf(passwordField.getPassword()).length() > 0);
        if (!check) {
            alertLabel.display("Password cannot be empty");
        } else {
            alertLabel.conceal();
        }
        return check;
    }

    public UserApi verifyLogin() {
        try {
            return authHelper.login(usernameField.getText(), String.valueOf(passwordField.getPassword()));
        } catch (UserNotFound exception) {
            alertLabel.display("Username was not found");
            usernameField.requestFocus();
        } catch (IncorrectPassword exception) {
            alertLabel.display("Incorrect password entered");
            passwordField.requestFocus();
        }
        return null;
    }

}

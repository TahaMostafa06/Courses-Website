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
    JPasswordField passwordField;
    AlertLabel alertLabel;
    AuthenticationHelper authHelper;

    public LoginValidator(JTextField usernameField, JPasswordField passwordField, AlertLabel alertLabel,
            AuthenticationHelper authHelper) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.alertLabel = alertLabel;
        this.authHelper = authHelper;
        var usernameVerifier = new InputVerifier() {
            @Override
            public boolean shouldYieldFocus(JComponent self, JComponent other) {
                if(other == passwordField)
                    return true;
                return verify(self);
            }
            
            @Override
            public boolean verify(JComponent component) {
                return verifyUsernameLength();
            }
        };
        var passwordVerifier = new InputVerifier() {
            @Override
            public boolean shouldYieldFocus(JComponent self, JComponent other) {
                if(other == usernameField)
                    return true;
                return verify(self);
            }
            
            @Override
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
        if (!verifyUsernameLength()) {
            usernameField.requestFocus();
            return null;
        }
        if (!verifyPasswordLength()) {
            passwordField.requestFocus();
            return null;
        }
        try {
            return authHelper.login(usernameField.getText(), String.valueOf(passwordField.getPassword()));
        } catch (UserNotFound exception) {
            /*
             * Request focus must come before display
             * since requestFocus calls the InputVerifiers
             */
            usernameField.requestFocus();
            alertLabel.display("Username was not found");
        } catch (IncorrectPassword exception) {
            passwordField.requestFocus();
            alertLabel.display("Incorrect password entered");
        }
        return null;
    }

}

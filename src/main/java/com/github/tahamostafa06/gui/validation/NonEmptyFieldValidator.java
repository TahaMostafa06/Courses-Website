package com.github.tahamostafa06.gui.validation;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class NonEmptyFieldValidator {
    private AlertLabel alertLabel;
    public NonEmptyFieldValidator(AlertLabel alertLabel, JTextField... fields) {
        var inputVerifier = new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                return ((JTextField) input).getText().length() > 0;
            }
            
        };
        for (var field : fields) {
            field.setInputVerifier(inputVerifier);
        }
    }
}

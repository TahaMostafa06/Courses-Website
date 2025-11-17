package com.github.tahamostafa06.gui.validation;

import javax.swing.JLabel;
import javax.swing.UIManager;

public class AlertLabel extends JLabel {
    public AlertLabel() {
        setIcon(UIManager.getIcon("OptionPane.errorIcon"));
        conceal();
    }

    public void conceal() {
        setVisible(false);
    }

    public void display() {
        setVisible(true);
    }

    public void display(String text) {
        setText(text);
        setVisible(true);
    }
}

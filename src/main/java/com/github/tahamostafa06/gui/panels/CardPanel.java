package com.github.tahamostafa06.gui.panels;

import javax.swing.JPanel;

public abstract class CardPanel extends JPanel {
    abstract public void receiveTransitionMessage(Object message);
}

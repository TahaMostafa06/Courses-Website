package com.github.tahamostafa06.gui;

public class MainWindowFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainWindowFrame.class.getName());


    public MainWindowFrame() {
        initComponents();
    }

    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        pack();

        setVisible(true);
    }
}

package com.github.tahamostafa06.gui.panels.student;


public class ViewCertificates extends javax.swing.JPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CertificateTitleLabel;
    private javax.swing.JLabel UserNameLabel;
    private javax.swing.JPanel lessonDetailsPanel;
    // End of variables declaration//GEN-END:variables
    
    public ViewCertificates() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lessonDetailsPanel = new javax.swing.JPanel();
        UserNameLabel = new javax.swing.JLabel();
        CertificateTitleLabel = new javax.swing.JLabel();

        lessonDetailsPanel.setLayout(new javax.swing.OverlayLayout(lessonDetailsPanel));

        UserNameLabel.setFont(new java.awt.Font("Script MT Bold", 3, 24)); // NOI18N
        UserNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        UserNameLabel.setText("Long Test User Name");
        lessonDetailsPanel.add(UserNameLabel);

        CertificateTitleLabel.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        CertificateTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CertificateTitleLabel.setText("Certificate of Achievement");
        CertificateTitleLabel.setToolTipText("");
        CertificateTitleLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lessonDetailsPanel.add(CertificateTitleLabel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lessonDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lessonDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

}

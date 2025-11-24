package com.github.tahamostafa06.gui.panels.student;

import java.awt.Dimension;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Insights extends javax.swing.JPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chartContainer;
    private javax.swing.JPanel courseDetailsPanel;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
   
    public Insights() {
        initComponents();
        javax.swing.JPanel chart = generateChart();
        chartContainer.add(chart, java.awt.BorderLayout.CENTER);
        chartContainer.validate(); // Refresh the visual
    }
    public static javax.swing.JPanel generateChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        dataset.addValue(85, "Score", "Java Basics");
        dataset.addValue(92, "Score", "OOP Concepts");
        dataset.addValue(78, "Score", "Threads");
        dataset.addValue(95, "Score", "Exceptions");

        JFreeChart barChart = ChartFactory.createBarChart(
            "Student Progress",      // Chart Title
            "Quiz Topic",            // X-Axis Label
            "Score (%)",             // Y-Axis Label
            dataset,                 // The Data
            PlotOrientation.VERTICAL,
            true, true, false
        );
        
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(500, 300));
        return chartPanel;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        courseDetailsPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        chartContainer = new javax.swing.JPanel();

        courseDetailsPanel.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Insights and Performance");
        courseDetailsPanel.add(jLabel1, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout chartContainerLayout = new javax.swing.GroupLayout(chartContainer);
        chartContainer.setLayout(chartContainerLayout);
        chartContainerLayout.setHorizontalGroup(
            chartContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        chartContainerLayout.setVerticalGroup(
            chartContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        courseDetailsPanel.add(chartContainer, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(courseDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(courseDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

/**
 *
 * @author salex
 */
public class SideMenuPanel extends javax.swing.JPanel {

    /**
     * Creates new form SideMenuPanel
     */
    public SideMenuPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        preferredMoviesButton = new javax.swing.JButton();
        upcomingMoviesButton = new javax.swing.JButton();
        followedMoviesButton = new javax.swing.JButton();
        settingsButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();

        preferredMoviesButton.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        preferredMoviesButton.setText("Preferred movies");
        preferredMoviesButton.setMargin(new java.awt.Insets(10, 14, 10, 14));

        upcomingMoviesButton.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        upcomingMoviesButton.setText("Upcoming movies");
        upcomingMoviesButton.setMargin(new java.awt.Insets(10, 14, 10, 14));
        upcomingMoviesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upcomingMoviesButtonActionPerformed(evt);
            }
        });

        followedMoviesButton.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        followedMoviesButton.setText("Followed movies");
        followedMoviesButton.setActionCommand("");
        followedMoviesButton.setMargin(new java.awt.Insets(10, 14, 10, 14));
        followedMoviesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                followedMoviesButtonActionPerformed(evt);
            }
        });

        settingsButton.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        settingsButton.setText("Settings");
        settingsButton.setMargin(new java.awt.Insets(10, 14, 10, 14));

        logoutButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(preferredMoviesButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(upcomingMoviesButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(followedMoviesButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(settingsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(logoutButton)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(preferredMoviesButton)
                .addGap(18, 18, 18)
                .addComponent(followedMoviesButton)
                .addGap(18, 18, 18)
                .addComponent(upcomingMoviesButton)
                .addGap(18, 18, 18)
                .addComponent(settingsButton)
                .addGap(58, 58, 58)
                .addComponent(logoutButton)
                .addContainerGap(175, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void followedMoviesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_followedMoviesButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_followedMoviesButtonActionPerformed

    private void upcomingMoviesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upcomingMoviesButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_upcomingMoviesButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logoutButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton followedMoviesButton;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton preferredMoviesButton;
    private javax.swing.JButton settingsButton;
    private javax.swing.JButton upcomingMoviesButton;
    // End of variables declaration//GEN-END:variables
}

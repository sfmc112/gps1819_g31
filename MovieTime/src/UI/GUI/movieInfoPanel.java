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
public class movieInfoPanel extends javax.swing.JPanel {

    /**
     * Creates new form movieInfoPanel
     */
    public movieInfoPanel() {
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

        releaseDateLabel = new javax.swing.JLabel();
        releaseDate = new javax.swing.JLabel();
        genreLabel = new javax.swing.JLabel();
        directorLabel = new javax.swing.JLabel();
        mainActorsLabel = new javax.swing.JLabel();
        genre = new javax.swing.JLabel();
        director = new javax.swing.JLabel();
        mainActors = new javax.swing.JLabel();

        releaseDateLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        releaseDateLabel.setText("Release date:");

        releaseDate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        releaseDate.setText("[insert release date]");

        genreLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        genreLabel.setText("Genre(s):");

        directorLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        directorLabel.setText("Directed by:");

        mainActorsLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        mainActorsLabel.setText("Main actors:");

        genre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        genre.setText("[insert genre(s)]");

        director.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        director.setText("[insert director]");

        mainActors.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        mainActors.setText("[insert main actors]");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(releaseDateLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(directorLabel)
                                .addGap(87, 87, 87)
                                .addComponent(director))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(mainActorsLabel)
                                .addGap(54, 54, 54)
                                .addComponent(mainActors))
                            .addComponent(releaseDate)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(genreLabel)
                                .addGap(101, 101, 101)
                                .addComponent(genre)))
                        .addGap(0, 286, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(releaseDateLabel)
                    .addComponent(releaseDate))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(genreLabel)
                    .addComponent(genre))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(directorLabel)
                    .addComponent(director))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mainActorsLabel)
                    .addComponent(mainActors))
                .addGap(36, 36, 36))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel director;
    private javax.swing.JLabel directorLabel;
    private javax.swing.JLabel genre;
    private javax.swing.JLabel genreLabel;
    private javax.swing.JLabel mainActors;
    private javax.swing.JLabel mainActorsLabel;
    private javax.swing.JLabel releaseDate;
    private javax.swing.JLabel releaseDateLabel;
    // End of variables declaration//GEN-END:variables
}
